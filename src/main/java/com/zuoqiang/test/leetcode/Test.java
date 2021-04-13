package com.zuoqiang.test.leetcode;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author zuoqiang
 * @version 1.0
 * @description 判断扑克牌大小
 * <p>
 * 题目：给出一组扑克比较。每张扑克有特定花色和数值，且没有两张完全一样的扑克。
 * 花色有“红桃”、“黑桃”、“方片”、“梅花”四种，大小分为“A, K, Q, J, T（代表10）, 9, 8, 7, 6, 5, 4, 3, 2”（由大到小）。
 * 五张完全不同的牌构成一幅手牌，我们需要比较两手牌的大小，两手牌可能部分牌是公用牌，
 * 不同类型手牌大小规则由大到小如下：
 * Royal Flush: 只能是AKQJT，且同花
 * Flush Straight: 是同花，且是顺子（5432A是最小的顺子，最大的为AKQJT）
 * Four of a Kind: AAAAx（x表示任意牌, A表示四张一样大小的牌，如果两手牌都是Four of a Kind，则比较A的大小）
 * Full House: AAABB（A表示3张一样大小的牌，B表示2张一样大小的牌，如果两手牌都是Full House，则比较A的大小，如果两手牌都是Full Hose且A相同，则比较B的大小）
 * Straight：顺子（5432A是最小的顺子，最大的为AKQJT），但非同花
 * Flush: （5张牌花色相同，如果两手牌都是Flush，则比较最大的那张牌的大小，如果仍然一样，则以此类推，直到完全一样）
 * Three of a Kind: AAAxy （A表示3张一样大小的牌，xy表示任意牌，且x>y, A表示3张一样大小的牌，如果两首牌都是Three of a Kind，则比较A的大小，如果A相同，比较x，如果x相同，比较y）
 * Two Pairs: AABBx （A表示两张一样大的牌，B表示两张一样大的牌，x表示任意牌，且A>B，如果两手牌都是Two pairs，则比较A的大小，如果A相同，比较B，如果B相同，比较x）
 * Pair: AAxyz（A表示两张一样大的牌，x>y>z，两手牌首先比较A的大小，如果相同，则依次比较x, y, z的大小）
 * High Cards：abcde（a>b>c>d>e，以此比较大小）
 * <p>
 * 输入：两手牌（数据结构自己定义，不考虑数据输入不合法，如，不用考虑输入为“黑桃X”。考察重点是比较逻辑本身），
 * 然后比较大小。输出较大的手牌，如果相等则返回相等
 * </p>
 * @date 2021/4/13 8:14 下午
 */

public class Test {
    @Data
    public static class Ticket {
        private int num;
        private int color;

        public Ticket(int num, int color) {
            this.num = num;
            this.color = color;
        }
    }

    @Data
    public static class Player {
        private List<Ticket> ticketList;

        public Player(List<Ticket> ticketList) {
            this.ticketList = ticketList;
        }
    }

    public static class Game {

        /**
         * 计算扑克牌的大小
         *
         * @param player1
         * @param player2
         * @return 0 一样 正数player1大 负数player2 大
         */
        public static int sort(Player player1, Player player2) {
            List<Ticket> t1 = player1.getTicketList();
            List<Ticket> t2 = player2.getTicketList();
            //先内部排序 按照大小[A, K, Q, J, T（代表10）, 9, 8, 7, 6, 5, 4, 3, 2]
            Collections.sort(t1, new Comparator<Ticket>() {
                @Override
                public int compare(Ticket o1, Ticket o2) {
                    if (o1.num == 1) {
                        return 1;
                    }
                    return o1.num - o2.num;
                }
            });
            Collections.sort(t2, new Comparator<Ticket>() {
                @Override
                public int compare(Ticket o1, Ticket o2) {
                    if (o1.num == 1) {
                        return 1;
                    }
                    return o1.num - o2.num;
                }
            });
            //认为一样大


            return 0;
        }
    }


    public static void main(String[] args) {
        List<Ticket> ticket1 = Lists.newArrayList();
        //todo 省略加入player1的牌
        Player player1 = new Player(ticket1);
        List<Ticket> ticket2 = Lists.newArrayList();
        //todo 省略加入player1的牌
        Player player2 = new Player(ticket2);
        int sort = Game.sort(player1, player2);
        if (sort > 0) {
            ticket1.forEach(System.out::println);
        } else if (sort < 0) {
            ticket2.forEach(System.out::println);
        } else {
            System.out.println("一样大");
        }
    }
}
