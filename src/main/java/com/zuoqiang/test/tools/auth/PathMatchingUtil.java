package com.zuoqiang.test.tools.auth;

import org.apache.shiro.util.AntPathMatcher;
import org.apache.shiro.util.PatternMatcher;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zuoqiang
 * @version 1.0
 * @description 路径匹配/权限系统用
 * @date 2020/4/9 10:14 上午
 */

public class PathMatchingUtil {
    private static PatternMatcher pathMatcher = new AntPathMatcher();

    public static String getPathWithinApplication(HttpServletRequest request) {
        return WebUtils.getPathWithinApplication(request);
    }

    public static boolean pathsMatch(String path, HttpServletRequest request) {
        String requestURI = getPathWithinApplication(request);
        return pathsMatch(path, requestURI);
    }

    public static boolean pathsMatch(String pattern, String path) {
        return pathMatcher.matches(pattern, path);
    }

}
