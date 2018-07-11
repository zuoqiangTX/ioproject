import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(DBTest.class);
    public static void main(String[] args) {
        LOGGER.info("按照user_id分表[sharding-jdbc],建表语句正常，注意where条件中需要加入userid路由");
        for (int i = 0; i < 64; i++) {
            //printSub(i);
            //test(i);
            printData(i);
            System.out.println();

        }
    }

    private static void test(int i) {
        System.out.println("SELECT COUNT(*) FROM ar_task_main_order_" + i + "  a WHERE  a.task_snapshot_id = '77';\n");
    }

    static void printSub(int i) {
        System.out.println("CREATE TABLE `ar_task_sub_order_" + i + "` (\n" +
                "  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',\n" +
                "  `main_id` varchar(255) NOT NULL COMMENT '主订单号',\n" +
                "  `user_id` varchar(32) NOT NULL COMMENT '用户id',\n" +
                "  `task_id` bigint(20) NOT NULL COMMENT '任务id',\n" +
                "  `serial_no` varchar(32) NOT NULL COMMENT '唯一单号',\n" +
                "  `task_type` tinyint(4) NOT NULL COMMENT '任务类型',\n" +
                "  `task_snapshot_id` bigint(20) NOT NULL COMMENT '任务快照id',\n" +
                "  `award_id` bigint(20) NOT NULL COMMENT '奖品id',\n" +
                "  `award_type` tinyint(4) NOT NULL COMMENT '奖品类型 1-自定义奖品 2-指定铜板 3-成长值',\n" +
                "  `body` varchar(32) DEFAULT NULL COMMENT '奖品内容 award_type=2 为铜板数 award_type=3为成长值',\n" +
                "  `status` tinyint(1) NOT NULL COMMENT '1-初始化 3-成功 5-失败',\n" +
                "  `create_time` datetime NOT NULL COMMENT '创建时间',\n" +
                "  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',\n" +
                "  PRIMARY KEY (`id`),\n" +
                "  UNIQUE KEY `serial_no` (`serial_no`) COMMENT '唯一单号',\n" +
                "  KEY `user_Id` (`user_id`)\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='任务发放奖品子订单流水表';");
    }

    static void printMain(int i) {
        System.out.println("CREATE TABLE `ar_task_main_order_" + i + "` (\n" +
                "  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',\n" +
                "  `user_id` varchar(32) NOT NULL COMMENT '用户id',\n" +
                "  `task_id` bigint(20) NOT NULL COMMENT '任务id',\n" +
                "  `serial_no` varchar(32) NOT NULL COMMENT '唯一单号',\n" +
                "  `task_type` tinyint(4) NOT NULL COMMENT '任务类型',\n" +
                "  `task_snapshot_id` bigint(20) NOT NULL COMMENT '任务快照id',\n" +
                "  `award_desc` varchar(32) NOT NULL COMMENT '奖品描述',\n" +
                "  `status` tinyint(1) NOT NULL COMMENT '1-初始化 3-成功 5-失败',\n" +
                "  `total_count` int(11) NOT NULL COMMENT '总子订单数',\n" +
                "  `create_time` datetime NOT NULL COMMENT '创建时间',\n" +
                "  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',\n" +
                "  PRIMARY KEY (`id`),\n" +
                "  UNIQUE KEY `serial_no` (`serial_no`) COMMENT '唯一单号'\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='任务发放奖品主订单流水表';");
    }

    static void printData(int i) {
        System.out.println("CREATE TABLE `ar_task_data_" + i + "` (\n" +
                "  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',\n" +
                "  `snapshot_id` bigint(20) NOT NULL COMMENT '镜像id',\n" +
                "  `task_id` bigint(20) NOT NULL COMMENT '任务id',\n" +
                "  `group_id` bigint(20) NOT NULL COMMENT '群组id',\n" +
                "  `exec_date` int(11) NOT NULL COMMENT '执行日期',\n" +
                "  `user_id` varchar(32) NOT NULL COMMENT '用户ID',\n" +
                "  `create_time` datetime NOT NULL COMMENT '创建时间',\n" +
                "  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',\n" +
                "  PRIMARY KEY (`id`),\n" +
                "  UNIQUE KEY `user_id_uk` (`snapshot_id`,`exec_date`,`user_id`) USING BTREE\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='任务执行数据表';");
    }

}
