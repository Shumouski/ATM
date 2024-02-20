package BANKOMAT.ENTRY;
import BANKOMAT.Card;
import BANKOMAT.BALANCE.Balance;
import BANKOMAT.ATM.AtmMoney;
import java.util.Scanner;
public class SelectPosition {
public void toSelect(Card card, AtmMoney atm){
    int command;
    Scanner cs = new Scanner(System.in);
    System.out.println();
    System.out.println("""
                    Select the number
                    1. Check score
                    2. Put down money
                    3. Pick up money
                    4. Exit the system""");
    command = cs.nextInt();
    switch (command){
        case 1:
            Balance balance = new Balance();
            balance.toCheckBalance(card, atm);
            break;
        case 2:
            InsertCash insert = new InsertCash();
            insert.insert(card, atm);
            break;
        case 3:
            GetMoney pickUp = new GetMoney(atm);
            pickUp.pickUpMoney(card, atm);
            break;
        case 4:
            System.out.println("Thank you for using our bank");
            System.exit(0);
        default:
            System.out.println("\nSuch command is missing\n");
            SelectPosition selectPosition = new SelectPosition();
            selectPosition.toSelect(card, atm);
    }
    }
}
