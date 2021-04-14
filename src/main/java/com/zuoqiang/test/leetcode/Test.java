package com.zuoqiang.test.leetcode;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import javafx.util.Pair;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Triple;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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
        //大小1-13
        private int num;
        //花色 1-4
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

        private static final String ROYAL_FLUSH = "13121110";
        private static final int PLAYER_ONE_BIG = 1;
        private static final int PLAYER_TWO_BIG = -1;
        private static final int PLAYER_SANE = 0;

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
            Collections.sort(t1, (o1, o2) -> {
                return compareTicket(o1, o2);
            });
            Collections.sort(t2, (o1, o2) -> {
                return compareTicket(o1, o2);
            });
            System.out.println("" + t1);
            System.out.println(t2);
            //认为一样大
            return compare(t1, t2);
        }

        /**
         * 当o1>o2返回负数
         *
         * @param o1
         * @param o2
         * @return
         */
        private static int compareTicket(Ticket o1, Ticket o2) {
            if (o1.num == 1 && o2.num == 1) {
                return 0;
            }
            if (o1.num == 1 && o2.num > 1) {
                return -1;
            }
            if (o2.num == 1 && o1.num > 1) {
                return 1;
            }
            return o2.num - o1.num;
        }

        /**
         * 比较逻辑
         *
         * @param t1
         * @param t2
         * @return 0 一样 正数player1大 负数player2 大
         */
        private static int compare(List<Ticket> t1, List<Ticket> t2) {
            //先比较是否特殊情况
            String t1Str = transferNumToString(t1);
            String t2Str = transferNumToString(t2);
            int[] t1Array = t1.stream().mapToInt(Ticket::getColor).toArray();
            int[] t2Array = t2.stream().mapToInt(Ticket::getColor).toArray();
            int t1Sum = t1.stream().mapToInt(Ticket::getNum).sum();
            int t2Sum = t1.stream().mapToInt(Ticket::getNum).sum();
            if (t1Str.equals(t2Str) && isSameColor(t1Array) && isSameColor(t2Array)) {
                //花色&&大小相同
                return PLAYER_SANE;
            }
            //---RoyalFlush-----只能是AKQJT，且同花-------
            if (isRoyalFlush(t1, t1Str)) {
                //t1大
                return PLAYER_ONE_BIG;
            } else if (isRoyalFlush(t2, t1Str)) {
                return PLAYER_TWO_BIG;
            }
            //------Flush Straight: 是同花，且是顺子（5432A是最小的顺子，最大的为AKQJT）---
            // 都是顺子(花色相同大小相同前面已经过滤了)
            if (isStraight(t1) && isStraight(t2)) {
                //1)先比较顺子大(直接计算大小的和，哪个大哪个就是（因为已经是顺子了）)
                if (t1Sum == t2Sum) {
                    Boolean sameColorT1 = isSameColor(t1Array);
                    if (sameColorT1) {
                        return PLAYER_ONE_BIG;
                    } else {
                        return PLAYER_TWO_BIG;
                    }
                } else {
                    return t1Sum - t2Sum;
                }
            }
            //------Four of a Kind: AAAAx（x表示任意牌, A表示四张一样大小的牌，如果两手牌都是Four of a Kind，则比较A的大小）
            Pair<Boolean, Integer> t1Pair = fourAndKind(t1);
            Pair<Boolean, Integer> t2Pair = fourAndKind(t2);
            if (t1Pair.getKey() && t2Pair.getKey()) {
                Integer t1Num = t1Pair.getValue();
                Integer t2Num = t2Pair.getValue();
                return t1Num.compareTo(t2Num);
            } else if (t1Pair.getKey() && !t2Pair.getKey()) {
                return PLAYER_ONE_BIG;
            } else if (t2Pair.getKey() && !t1Pair.getKey()) {
                return PLAYER_TWO_BIG;
            }
            //---Full House: AAABB（A表示3张一样大小的牌，B表示2张一样大小的牌，如果两手牌都是Full House，则比较A的大小，如果两手牌都是Full Hose且A相同，则比较B的大小）
            fourHouse(t1);
            return PLAYER_SANE;
        }

        /**
         * @param tickets
         * @return
         */
        public static Triple<Boolean, Boolean, Integer> fourHouse(List<Ticket> tickets) {
            boolean hasThree = false;
            boolean hasTwo = false;
            Integer threeNum = null;
            List<Integer> collect = tickets.stream().map(Ticket::getNum).collect(Collectors.toList());
            Map<String, Integer> countMap = Maps.newHashMap();
            collect.forEach(e -> {
                if (countMap.containsKey(e.toString())) {
                    Integer num = countMap.get(e);
                    countMap.put(e.toString(), num + 1);
                } else {
                    countMap.put(e.toString(), 0);
                }
            });
            //判断hashmap是否有一个值为4的key,有的话返回值，否则返回false;
            for (String key : countMap.keySet()) {
                int num = countMap.get(key);
                if (num == 3) {
                    hasThree = true;
                    threeNum = Integer.parseInt(key);
                }
                if (num == 2) {
                    hasTwo = true;
                }
            }
            return Triple.of(hasThree, hasTwo, threeNum);
        }


        /**
         * 判断是否为 A表示四张一样大小的牌并且返回对应的牌
         *
         * @param tickets
         * @return
         */
        public static Pair<Boolean, Integer> fourAndKind(List<Ticket> tickets) {
            List<Integer> collect = tickets.stream().map(Ticket::getNum).collect(Collectors.toList());
            Map<String, Integer> countMap = Maps.newHashMap();
            collect.forEach(e -> {
                if (countMap.containsKey(e.toString())) {
                    Integer num = countMap.get(e);
                    countMap.put(e.toString(), num + 1);
                } else {
                    countMap.put(e.toString(), 0);
                }
            });
            //判断hashmap是否有一个值为4的key,有的话返回值，否则返回false;
            for (String key : countMap.keySet()) {
                int num = countMap.get(key);
                if (num == 4) {
                    return new Pair<>(true, Integer.parseInt(key));
                }
            }
            return new Pair<>(false, 0);
        }

        /**
         * 判断是否为RoyalFlush
         * 只能是AKQJT，且同花
         *
         * @param t
         * @param tStr
         * @return
         */
        private static boolean isRoyalFlush(List<Ticket> t, String tStr) {
            return ROYAL_FLUSH.equals(tStr) && isSameColor(t.stream().mapToInt(Ticket::getNum).toArray());
        }

        /**
         * 是否顺子
         *
         * @param
         * @param t
         * @return
         */
        private static boolean isStraight(List<Ticket> t) {
            //todo 判断是否为顺子
            return true;
        }

        /**
         * 是否同色
         *
         * @param nums
         * @return
         */
        public static Boolean isSameColor(int[] nums) {
            Set<String> set = Sets.newHashSet();
            set.add(String.valueOf(nums[0]));
            for (int i = 1; i < nums.length; i++) {
                int num = nums[i];
                if (!set.contains(String.valueOf(num))) {
                    return false;
                }
            }
            return true;
        }


        /**
         * 转换牌为字符串
         *
         * @param tickets
         * @return
         */
        private static String transferNumToString(List<Ticket> tickets) {
            return StringUtils.join(tickets.stream().map(Ticket::getNum).collect(Collectors.toList()), "");
        }

        /**
         * 转换花色为字符串
         *
         * @param tickets
         * @return
         */
        private static String transferColorToString(List<Ticket> tickets) {
            return StringUtils.join(tickets.stream().map(Ticket::getColor).collect(Collectors.toList()), "");
        }

    }


    public static void main(String[] args) {
        List<Ticket> ticket1 = Lists.newArrayList();
        ticket1.add(new Ticket(1, 1));
        ticket1.add(new Ticket(13, 2));
        ticket1.add(new Ticket(2, 3));
        ticket1.add(new Ticket(3, 4));
        ticket1.add(new Ticket(4, 4));
        ticket1.add(new Ticket(1, 2));
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
