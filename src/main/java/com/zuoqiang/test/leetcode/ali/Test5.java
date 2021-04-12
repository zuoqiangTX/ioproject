package com.zuoqiang.test.leetcode.ali;

import lombok.Data;

/**
 * @author zuoqiang
 * @version 1.0
 * @description
 * @date 2021/4/12 11:45 上午
 */


public class Test5 {
    @Data
    class Depart {
        /**
         * 部门id
         */
        private Integer departmentId;

        /**
         * 父部门id
         */
        private Integer parentDepartmentId;
    }

    public void delete(Integer partId) {
        if (judgeHasPart(partId)) {
            //查询子部门id
            int sunDepartmentId = selectSunDepartmentId(partId);
            //递归删除本部门
            delete(sunDepartmentId);
        }
        deleteSelf(partId);
    }

    private int selectSunDepartmentId(Integer partId) {
        return 0;
    }

    private void deleteSelf(Integer partId) {

    }

    private boolean judgeHasPart(Integer partId) {
        return false;
    }
}
