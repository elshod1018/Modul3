package uz.pdp.uz_pdp.M3_4_2.service;

import uz.pdp.uz_pdp.M3_4_2.entity.Card;
import uz.pdp.uz_pdp.M3_4_2.exceptions.CardTypeNotMatchException;
import uz.pdp.uz_pdp.M3_4_2.exceptions.NotEnoughMoneyException;

public class CardService {
    public String  transferMoney(Card card1, Card card2,long transferMoney){
        if (!card1.getCardType().getType().equals(card2.getCardType().getType())){
            throw new CardTypeNotMatchException();
        }
        if (card1.getBalance()<transferMoney*1.01){
            throw new NotEnoughMoneyException();
        }
        card1.setBalance(card1.getBalance()-transferMoney*1.01);
        card2.setBalance(card2.getBalance()+transferMoney);
        return transferMoney+" "+card1.getCardType().getType()+" transfered successfully !";
    }
}
