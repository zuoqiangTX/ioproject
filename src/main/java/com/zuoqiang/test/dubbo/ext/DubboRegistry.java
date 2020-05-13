package com.zuoqiang.test.dubbo.ext;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author zuoqiang
 * @version 1.0
 * @description todo
 * @date 2020/5/12 5:09 下午
 */

@Slf4j
public class DubboRegistry {
    // Local disk cache file
    private File file;

    private final AtomicLong lastCacheChanged = new AtomicLong();

    /**
     * 注册中心缓存写入执行器。
     * <p>
     * 线程数=1
     */
    // File cache timing writing
    private final ExecutorService registryCacheExecutor = Executors.newFixedThreadPool(1, new NamedThreadFactory("DubboSaveRegistryCache", true));


    /**
     * 本地磁盘缓存。
     **/
    // Local disk cache, where the special key value.registies records the list of registry centers, and the others are the list of notified service providers
    private final Properties properties = new Properties();

    public DubboRegistry() {
        String filename = "/Users/zuoqiang/IdeaProjects/ioproject/src/main/resources/data/commonRegistry-lli-risk-admin-registry.properties";
        File file = null;
        if (StringUtils.isNotEmpty(filename)) {
            file = new File(filename);
            if (!file.exists() && file.getParentFile() != null && !file.getParentFile().exists()) {
                if (!file.getParentFile().mkdirs()) {
                    throw new IllegalArgumentException("Invalid registry store file " + file + ", cause: Failed to create directory " + file.getParentFile() + "!");
                }
            }
        }
        this.file = file;
        // 加载本地磁盘缓存文件到内存缓存
        loadProperties();
    }

    /**
     * 加载本地磁盘缓存文件到内存缓存，即 {@link #file} => {@link #properties}
     */
    private void loadProperties() {
        if (file != null && file.exists()) {
            InputStream in = null;
            try {
                // 文件流
                in = new FileInputStream(file);
                // 读取文件流
                properties.load(in);
                if (log.isInfoEnabled()) {
                    log.info("Load registry store file " + file + ", data: " + properties);
                }
            } catch (Throwable e) {
                log.warn("Failed to load registry store file " + file, e);
            } finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        log.warn(e.getMessage(), e);
                    }
                }
            }
        }
    }

    /**
     * 保存内存缓存到本地磁盘缓存文件，即 {@link #properties} => {@link #file}
     *
     * @param version 数据版本号
     */
    public void doSaveProperties(long version) {
        if (version < lastCacheChanged.get()) {
            log.info("版本号比当前小,不保存");
            return;
        }
        if (file == null) {
            return;
        }
        // Save
        try {
            // 创建 .lock 文件
            File lockfile = new File(file.getAbsolutePath() + ".lock");
            if (!lockfile.exists()) {
                lockfile.createNewFile();
            }
            // 随机读写文件操作
            RandomAccessFile raf = new RandomAccessFile(lockfile, "rw");
            try {
                FileChannel channel = raf.getChannel();
                try {
                    // 获得文件锁
                    FileLock lock = channel.tryLock();
                    // 获取失败
                    if (lock == null) {
                        throw new IOException("Can not lock the registry cache file " + file.getAbsolutePath() + ", ignore and retry later, maybe multi java process use the file, please config: dubbo.registry.file=xxx.properties");
                    }
                    // 获取成功，进行保存
                    // Save
                    try {
                        if (!file.exists()) {
                            file.createNewFile();
                        }
                        FileOutputStream outputFile = new FileOutputStream(file);
                        try {
                            properties.store(outputFile, "Dubbo Registry Cache");
                        } finally {
                            outputFile.close();
                        }
                        // 释放文件锁
                    } finally {
                        lock.release();
                    }
                    // 释放文件 Channel
                } finally {
                    channel.close();
                }
                // 释放随机读写文件操作
            } finally {
                raf.close();
            }
        } catch (Throwable e) {
            // 版本号过小，不保存
            if (version < lastCacheChanged.get()) {
                log.info("版本号比当前小,不保存");
                return;
                // 重新异步保存，一般情况下为上面的获取锁失败抛出的异常。通过这样的方式，达到保存成功。
            } else {
                registryCacheExecutor.execute(new SaveProperties(lastCacheChanged.incrementAndGet()));
            }
            log.warn("Failed to save registry store file, cause: " + e.getMessage(), e);
        }
    }

    /**
     * 保存配置的 Runnable
     */
    private class SaveProperties implements Runnable {

        /**
         * 数据版本号
         */
        private long version;

        private SaveProperties(long version) {
            this.version = version;
        }

        @Override
        public void run() {
            doSaveProperties(version);
        }
    }

    public Properties getCacheProperties() {
        return properties;
    }
}
