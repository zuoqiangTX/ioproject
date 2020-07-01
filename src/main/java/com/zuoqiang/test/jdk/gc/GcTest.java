package com.zuoqiang.test.jdk.gc;

/**
 * @author baiyue
 * @desrption -Xms：初始堆大小，默认为物理内存的1/64(<1GB)；默认(MinHeapFreeRatio参数可以调整)空余堆内存小于40%
 * 时，JVM就会增大堆直到-Xmx的最大限制
 * <p>
 * -Xmx：最大堆大小，默认(MaxHeapFreeRatio参数可以调整)空余堆内存大于70%时，JVM会减少堆直到 -Xms的最小
 * 限制
 * <p>
 * -Xmn：新生代的内存空间大小，注意：此处的大小是（eden+ 2 survivor space)。与jmap -heap中显示的New
 * gen是不同的。整个堆大小=新生代大小 + 老生代大小 + 永久代大小。 在保证堆大小不变的情况下，增大新生代后,
 * 将会减小老生代大小。此值对系统性能影响较大,Sun官方推荐配置为整个堆的3/8。
 * <p>
 * -XX:SurvivorRatio：新生代中Eden区域与Survivor区域的容量比值，默认值为8。两个Survivor区与一个Eden区
 * 的比值为2:8,一个Survivor区占整个年轻代的1/10。
 * <p>
 * -Xss：每个线程的堆栈大小。JDK5.0以后每个线程堆栈大小为1M,以前每个线程堆栈大小为256K。应根据应用的线程所
 * 需内存大小进行适当调整。在相同物理内存下,减小这个值能生成更多的线程。但是操作系统对一个进程内的线程数还是
 * 有限制的，不能无限生成，经验值在3000~5000左右。一般小的应用， 如果栈不是很深， 应该是128k够用的，大的应
 * 用建议使用256k。这个选项对性能影响比较大，需要严格的测试。和threadstacksize选项解释很类似,官方文档似乎
 * 没有解释,在论坛中有这样一句话:”-Xss is translated in a VM flag named ThreadStackSize”一般设置这
 * 个值就可以了。
 * <p>
 * -XX:PermSize：设置永久代(perm gen)初始值。默认值为物理内存的1/64。
 * <p>
 * -XX:MaxPermSize：设置持久代最大值。物理内存的1/4。
 */
public class GcTest {
    /**
     * 3.1 Major GC
     * 上面方括号内部的[PSYoungGen: 2673K->496K(38400K)]，表示
     * <p>
     * GC前该内存区域已使用容量->GC后该内存区域已使用容量，后面圆括号里面的38400K为该内存区域的总容量。
     * 方括号外面的 2673K->504K(125952K), 0.0010649 secs]，表示
     * <p>
     * GC前Java堆已使用容量->GC后Java堆已使用容量，后面圆括号里面的125952K为Java堆总容量。   PSYoungGen耗时
     * [Times: user=0.00 sys=0.00, real=0.00 secs]分别表示
     * <p>
     * 用户消耗的CPU时间   内核态消耗的CPU时间     操作从开始到结束所经过的墙钟时间（Wall Clock Time）
     * user是用户态耗费的时间，sys是内核态耗费的时间，real是整个过程实际花费的时间。user+sys是CPU时间，每个CPU core单独计算，所以这个时间可能会是real的好几倍。
     * <p>
     * CPU时间和墙钟时间的差别是，墙钟时间包括各种非运算的等待耗时，例如等待磁盘I/O、等待线程阻塞，而CPU时间不包括这些耗时。
     * <p>
     * 3.2 Full GC
     * 2018-06-15T10:44:26.631-0800: [Full GC (System.gc()) [PSYoungGen: 496K->0K(38400K)] [ParOldGen: 8K->402K(87552K)] 504K->402K(125952K), [Metaspace: 3300K->3300K(1056768K)], 0.0066154 secs] [Times: user=0.01 sys=0.00, real=0.00 secs]
     * 从左到右分别为
     * <p>
     * [GC类型 (System.gc()) [Young区: GC前Young的内存占用->GC后Young的内存占用(Young区域总大小)] [old老年代: GC前Old的内存占用->GC后Old的内存占用(Old区域总大小)] GC前堆内存占用->GC后堆内存占用(JVM堆总大小), [永久代区: GC前占用大小->C后占用大小(永久代区总大小)], GC用户耗时] [Times:用户耗时 sys=系统时间, real=实际时间]
     * <p>
     * 3.3 规律
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("我在活着。。。。");
        System.out.println("gc vm option:参数:" + "-Xms20M -Xmx20M -Xmn10M -verbose:gc -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:+PrintGCDateStamps");
        System.gc();
        System.out.println("我要死了。。。。");
    }
}
