package com.zuoqiang.test.design.builder;

import lombok.Data;
import lombok.ToString;

import java.io.File;

/**
 * @author zuoqiang
 * @version 1.0
 * @description builder模式初探
 * @date 2020/5/6 5:42 下午
 */

@Data
@ToString
public class IsvConfig {
    private final String apiKey;
    private final String secret;
    private final int interval;
    private final File ivrFileDir;

    public IsvConfig(String apiKey, String secret, int interval, File ivrFileDir) {
        this.apiKey = apiKey;
        this.secret = secret;
        this.interval = interval;
        this.ivrFileDir = ivrFileDir;
    }

    public static IsvConfig.Builder custom() {
        return new IsvConfig.Builder();
    }

    public static IsvConfig.Builder copy(IsvConfig config) {
        return (new IsvConfig.Builder()).setApiKey(config.getApiKey()).setInterval(config.getInterval()).
                setIvrFileDir(config.getIvrFileDir()).setSecret(config.getSecret());
    }

    public static class Builder {
        private String apiKey;
        private String secret;
        private int interval;
        private File ivrFileDir;

        public Builder() {
            this.apiKey = "null";
            this.secret = "null";
            this.interval = -1;
            this.ivrFileDir = new File("./");
        }

        public Builder setApiKey(String apiKey) {
            this.apiKey = apiKey;
            return this;
        }

        public Builder setSecret(String secret) {
            this.secret = secret;
            return this;
        }

        public Builder setInterval(int interval) {
            this.interval = interval;
            return this;
        }

        public Builder setIvrFileDir(File ivrFileDir) {
            this.ivrFileDir = ivrFileDir;
            return this;
        }

        public IsvConfig build() {
            return new IsvConfig(apiKey, secret, interval, ivrFileDir);
        }
    }

    public static void main(String[] args) {
        IsvConfig config = IsvConfig.custom().setApiKey("abc").setSecret("bb").build();
        System.out.println(config);
    }

}
