package BANKOMAT.BALANCE;
import BANKOMAT.Card;
import BANKOMAT.ENTRY.SelectPosition;
import BANKOMAT.ATM.AtmMoney;

public class Balance {
    public void toCheckBalance(Card card, AtmMoney atm){
        System.out.println("Your balance is " + card.getScore());

        SelectPosition selectPosition = new SelectPosition();
        selectPosition.toSelect(card,atm);

   }
}





