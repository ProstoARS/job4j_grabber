package ru.job4j.ood.dip.exsample3;


public class Ticket {

    private final String ticket;

    public Ticket(String ticket) {
        this.ticket = ticket;
    }

    public boolean buy(BuyForCash buy) {
        return buy.buyForCash(ticket, 1000);
    }

}

