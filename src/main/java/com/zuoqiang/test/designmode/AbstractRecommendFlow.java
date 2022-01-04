package com.zuoqiang.test.designmode;

/**
 * 通用流程
 *
 * @author zuo
 */
public abstract class AbstractRecommendFlow<T> implements RecommendFlow<T> {
    @Override
    public RecommendResponse<T> recommend(RecommendRequest request) {
        //1、check
        this.check(request);
        //2、执行业务
        return buildResp(request);
    }

    protected abstract RecommendResponse<T> buildResp(RecommendRequest request);

    protected abstract void check(RecommendRequest request);

}
