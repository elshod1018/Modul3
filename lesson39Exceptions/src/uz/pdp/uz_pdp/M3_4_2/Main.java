package uz.pdp.uz_pdp.M3_4_2;
//        Card klassi(id,owner, balance va cardType), CardType enum(UZCARD, HUMO,VISA, UNIONPAY),
//        CardTypeNotMatch(cartalar tiofasi mos bo’lmagan holat)  va
//        NotEnoughMoney(pul yetarli bo’lmagan holat) exceptionlari orqali
//        kartadan kartaga pul o’tkazuvchi dastur tuzing. Uzcard va humo kartalari
//        pul birligi so’m hamda Visa va Unionpay kartalari pul birligi dollar.
//        Pul birligi bir xil bo’lgan kartalardan bir-biriga pul o’tkazish tartibi o’rnatilgan.
//        Agarda bunday tartibdan boshqa tarzda pul o’tkazishga urinish bo’lsa CardTypeNotMatch
//        exceptioni chaqirilsin. Agarda hisobdagi pul ko’chirilayotgan summadan kam bo’lsa
//        NotEnoughMoney exceptioni chaqirilsin.

import uz.pdp.uz_pdp.M3_4_2.entity.Card;
import uz.pdp.uz_pdp.M3_4_2.enums.CardType;
import uz.pdp.uz_pdp.M3_4_2.exceptions.CardTypeNotMatchException;
import uz.pdp.uz_pdp.M3_4_2.exceptions.NotEnoughMoneyException;
import uz.pdp.uz_pdp.M3_4_2.service.CardService;

public class Main {
    static Card card1=new Card("Elshod",5107000, CardType.HUMO);
    static Card card2=new Card("Dilshod",17000, CardType.UZCARD);
    static Card card3=new Card("Shamshod",57000, CardType.VISA);
    static Card card4=new Card("Gulshod",210000, CardType.UNION_PAY);
    public static void main(String[] args) {
        CardService cardService=new CardService();
        System.out.println("First situation:");
        try {
            cardService.transferMoney(card1,card3,1000);
        }catch (CardTypeNotMatchException e){
            System.out.print(card1.getOwner()+" can't transfer money to "+card3.getOwner()+"  ");
            System.out.println(e.getMessage());
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Second situation:");
        try {
            cardService.transferMoney(card2,card1,20000);
        } catch (NotEnoughMoneyException e){
            System.out.print(card2.getOwner()+" can't transfer money to "+card1.getOwner()+"  ");
            System.out.println(e.getMessage());
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Third situation:");
        try {
            System.out.println(cardService.transferMoney(card1, card2, 40000));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
