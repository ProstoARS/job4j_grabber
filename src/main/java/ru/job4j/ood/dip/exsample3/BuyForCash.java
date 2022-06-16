package ru.job4j.ood.dip.exsample3;

class BuyForCash {

    public boolean buyForCash(String ticket, int cash) {
        return ticket != null && cash > 1000;
    }
}
