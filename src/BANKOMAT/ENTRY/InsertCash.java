package BANKOMAT.ENTRY;
import BANKOMAT.Card;
import BANKOMAT.READ.Read;
import BANKOMAT.ATM.AtmMoney;
import java.io.*;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
public class InsertCash {
    public void insert(Card card, AtmMoney atm) {
        //Объявление метода insert. Принимает объекты Card и AtmMoney в качестве аргументов и не возвращает значения.
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the amount to add to the score: ");

        int plus = sc.nextInt();  // Вводится сумма для пополнения, которая сохраняется в переменной plus.
        if(plus <= 100000) {      //выполняется условие в том случае если сумма пополнения не более 100000

            List<Card> cardList = new Read().getReadList(); ////Создается список объектов Card, полученных из файла, с использованием метода getReadList() из класса Read.
            //Создаем новый список list1, в котором обновляется баланс карты (m.setScore(m.getScore() + plus))
            //для соответствующей карты, и затем этот список собирается обратно в список с использованием Stream API.
            List<Card> list1 = cardList.stream().map(m -> {
                if (m.getAccountNumber().equals(card.getAccountNumber())) {  //Проверяется, совпадает ли номер счета карты m с номером счета карты card.
                    m.setScore(m.getScore() + plus);                         //: Если номера счетов совпадают, то устанавливается новое значение баланса карты m, увеличивая его на значение переменной plus.
                    card.setScore(m.getScore());                             // Затем баланс карты card устанавливается равным балансу карты m. Таким образом, баланс карты card синхронизируется с балансом карты m.
                }
                return m;
            }).collect(Collectors.toList());

            atm.setMoney(atm.getMoney() + plus);                            // Обновляется баланс банкомата, уменьшая его на сумму plus.

            try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter("C:\\Users\\shumo\\OneDrive\\Desktop\\BKM.txt"))) {
                // Открывается блок try с созданием BufferedWriter для записи в файл.
                for (Card thisList : list1) {
                    // В цикле проходимся по списку list1 и записывает данные каждой карты в файл.
                    fileWriter.write(thisList.getName() + " " + thisList.getSureName() + " " +
                            thisList.getAccountNumber() + " " + thisList.getPassword() + " " +
                            thisList.getScore());
                    fileWriter.newLine();
                }
                System.out.println("Money has sent successfully on the score");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {   ////выполняется условие else в случае не пывалнения if (minus <= atm.getMoney() && minus <= card.getScore())
            System.out.println("Operation was not successfully, try again");
        }
        SelectPosition selectPosition = new SelectPosition();
        selectPosition.toSelect(card,atm);
    }
}

