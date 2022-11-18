package uz.pdp.uz_pdp.M3_4_2.exceptions;

public class NotEnoughMoneyException extends RuntimeException{
    public NotEnoughMoneyException() {
        super("Card has no enough money");
    }
}
