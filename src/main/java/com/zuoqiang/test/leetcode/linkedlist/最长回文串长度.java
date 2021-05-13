package com.zuoqiang.test.leetcode.linkedlist;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zuoqiang
 * @version 1.0
 * @description <a>
 * 给定一个包含大写字母和小写字母的字符串，找到通过这些字母构造成的最长的回文串。
 * <p>
 * 在构造过程中，请注意区分大小写。比如 "Aa" 不能当做一个回文字符串。
 * <p>
 * 注意:
 * 假设字符串的长度不会超过 1010。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * "abccccdd"
 * <p>
 * 输出:
 * 7
 * <p>
 * 解释:
 * 我们可以构造的最长的回文串是"dccaccd", 它的长度是 7。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-palindrome
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * </a>
 * @date 2021/5/13 3:20 下午
 */

public class 最长回文串长度 {
    public static int longestPalindrome(String s) {
        //使用一个hash表来统计长度
        int odd = 0;
        char[] chars = s.toCharArray();
        HashMap<Character, Integer> hash = new HashMap<>();
        for (char a : chars) {
            if (!hash.containsKey(a)) {
                hash.put(a, 1);
            } else {
                hash.put(a, hash.get(a) + 1);
            }
        }
        //统计完所有次数，统计有几个是奇数
        for (Map.Entry<Character, Integer> map : hash.entrySet()) {
            if (map.getValue() % 2 != 0) {
                odd++;
            }
        }
        System.out.println("odd" + odd);
        int len = s.length();
        return (odd > 0) ? len - odd + 1 : len;
    }

    public static void main(String[] args) {
        System.out.println(longestPalindrome("abab"));
    }
}
