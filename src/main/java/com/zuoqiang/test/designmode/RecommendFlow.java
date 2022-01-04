package com.zuoqiang.test.designmode;

/**
 * 推荐接口
 *
 * @author zuo
 */
public interface RecommendFlow<T> {
    /**
     * @param request
     * @return
     */
    RecommendResponse<T> recommend(RecommendRequest request);
}
