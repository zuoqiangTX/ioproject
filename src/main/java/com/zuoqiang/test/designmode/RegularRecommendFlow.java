package com.zuoqiang.test.designmode;

/**
 * 定期模板方法实现
 *
 * @author zuo
 */
public class RegularRecommendFlow extends AbstractRecommendFlow<String> {
    @Override
    protected RecommendResponse<String> buildResp(RecommendRequest request) {
        return null;
    }

    @Override
    protected void check(RecommendRequest request) {

    }
}
