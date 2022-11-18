package uz.pdp.uz_pdp.M3_4_2.entity;

import uz.pdp.uz_pdp.M3_4_2.enums.CardType;

public class Card {
    private static long counter=0;
    {
        counter++;
        id=counter;
    }
    private final long id;
    private String owner;
    private double balance;
    private CardType cardType;

    public Card() {
    }

    public Card(String owner, double balance, CardType cardType) {
        this.owner = owner;
        this.balance = balance;
        this.cardType = cardType;
    }

    public long getId() {
        return id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", owner='" + owner + '\'' +
                ", balance=" + balance +
                ", cardType=" + cardType +
                '}';
    }
}
