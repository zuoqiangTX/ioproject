package com.zuoqiang.test.designmode;

import lombok.Data;

import java.util.Map;

/**
 * @author zuo
 */
@Data
public class RecommendResponse<T> {
    private Map<String, T> resp;
}
