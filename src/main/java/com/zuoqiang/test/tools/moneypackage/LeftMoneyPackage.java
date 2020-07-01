package com.zuoqiang.test.tools.moneypackage;

public class LeftMoneyPackage {
    //剩余红包数
    public int remainSize;
    //剩余钱数
    public double remainMoney;

    public LeftMoneyPackage() {
    }

    public LeftMoneyPackage(int remainSize, double remainMoney) {
        this.remainSize = remainSize;
        this.remainMoney = remainMoney;
    }

    public int getRemainSize() {
        return remainSize;
    }

    public void setRemainSize(int remainSize) {
        this.remainSize = remainSize;
    }

    public double getRemainMoney() {
        return remainMoney;
    }

    public void setRemainMoney(double remainMoney) {
        this.remainMoney = remainMoney;
    }
}
