import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(DBTest.class);

    public static void main(String[] args) {
        LOGGER.info("按照user_id分表[sharding-jdbc],建表语句正常，注意where条件中需要加入userid路由");
//        for (int i = 0; i < 64; i++) {
//            //printSub(i);
//            printMessage(i);
//            System.out.println();
//        }
        for (int i = 0; i < 32; i++) {
            NewTest(i);
        }
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

    static void printMessage(int i) {
        System.out.println("CREATE TABLE `ar_task_message_send_" + i + "` (\n" +
                "  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',\n" +
                "\t`type` tinyint(4) NOT NULL COMMENT '消息类型 1-系统消息 2-短信通知 3-推送',\n" +
                "  `snapshot_id` bigint(20) NOT NULL COMMENT '镜像id',\n" +
                "  `user_id` varchar(32) NOT NULL COMMENT '用户ID',\n" +
                "  `create_time` datetime NOT NULL COMMENT '创建时间',\n" +
                "  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',\n" +
                "  PRIMARY KEY (`id`),\n" +
                "  UNIQUE KEY `user_send_uk` (`user_id`,`snapshot_id`,`type`) USING BTREE\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='消息发送记录表';");
    }

    static void NewTest(int i) {
        System.out.println("CREATE TABLE op_alliance.pisces_daily_gift_flow_" + i + " (\n" +
                "  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',\n" +
                "  `user_id` varchar(22) NOT NULL COMMENT '用户id',\n" +
                "  `record_id` varchar(20) NOT NULL COMMENT '任务ID',\n" +
                "  `gift_id` bigint(50) NOT NULL COMMENT '礼品包id',\n" +
                "  `award_id` bigint(20) NOT NULL COMMENT '奖品id',\n" +
                "  `seq_id` varchar(32) NOT NULL COMMENT '父订单ID',\n" +
                "  `serial_no` varchar(32) NOT NULL COMMENT '唯一单号-发奖标识',\n" +
                "  `status` tinyint(4) NOT NULL COMMENT '订单状态 2-处理中 1-成功 -1-失败',\n" +
                "  `create_time` datetime NOT NULL COMMENT '创建时间',\n" +
                "  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',\n" +
                "  `who_cost` varchar(60) NOT NULL COMMENT '成本归属',\n" +
                "  `user_phone` varchar(22) NOT NULL COMMENT '用户手机号',\n" +
                "  PRIMARY KEY (`id`),\n" +
                "  UNIQUE KEY `unique_key` (`serial_no`) USING BTREE,\n" +
                "  KEY `index_urg` (`user_id`,`record_id`,`gift_id`) USING BTREE,\n" +
                "  KEY `index_seq` (`seq_id`) USING BTREE\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='智能营销礼品包发放流水表';");
    }


}
