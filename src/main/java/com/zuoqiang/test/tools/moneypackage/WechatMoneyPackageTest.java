package com.zuoqiang.test.tools.moneypackage;

import java.util.Random;

public class WechatMoneyPackageTest {
    public static final int MONEY_PACKGE_SIZE = 5;
    public static final double MONEY_PACKGE_MONEY = 5;

    public static void main(String[] args) {
        LeftMoneyPackage leftMoneyPackage = new LeftMoneyPackage(MONEY_PACKGE_SIZE, MONEY_PACKGE_MONEY);
        double money = 0;
        while (leftMoneyPackage.getRemainSize() != 0) {
            money = getRandomMoney(leftMoneyPackage);
            System.out.println("红包为：" + money);
        }
    }

    public static double getRandomMoney(LeftMoneyPackage _leftMoneyPackage) {
        // remainSize 剩余的红包数量
        // remainMoney 剩余的钱
        if (_leftMoneyPackage.remainSize == 1) {
            _leftMoneyPackage.remainSize--;
            return (double) Math.round(_leftMoneyPackage.remainMoney * 100) / 100;
        }
        Random r = new Random();
        //最小0.01
        double min = 0.01;
        //最大是平均值*2
        double max = _leftMoneyPackage.remainMoney / _leftMoneyPackage.remainSize * 2;
        double money = r.nextDouble() * max;
        money = money <= min ? 0.01 : money;
        money = Math.floor(money * 100) / 100;
        _leftMoneyPackage.remainSize--;
        _leftMoneyPackage.remainMoney -= money;
        return money;
    }
}
