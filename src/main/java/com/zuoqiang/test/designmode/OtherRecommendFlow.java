package com.zuoqiang.test.designmode;

/**
 * @author zuo
 */
public class OtherRecommendFlow extends AbstractRecommendFlow<OtherVo>{
    @Override
    protected RecommendResponse<OtherVo> buildResp(RecommendRequest request) {
        return null;
    }

    @Override
    protected void check(RecommendRequest request) {

    }
}
