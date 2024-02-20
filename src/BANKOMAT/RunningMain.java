package BANKOMAT;
import BANKOMAT.ENTRY.Entry;
import BANKOMAT.READ.Read;
import BANKOMAT.ATM.AtmMoney;

public class RunningMain {
    public static void main(String[] args) {
        Read read = new Read();
        AtmMoney atm = new AtmMoney(100000);
        Entry.toEntry(read,atm);

    }
}
