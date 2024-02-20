package BANKOMAT.ENTRY;
import BANKOMAT.Card;
import BANKOMAT.READ.Read;
import BANKOMAT.ATM.AtmMoney;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class GetMoney {
    int minus;        //Переменная minus, которая будет использоваться для хранения суммы средств, которую планируем снять.
    AtmMoney atm;    //Переменная atm типа AtmMoney, объект - представляющий банкомат и его состояние.
    public GetMoney(AtmMoney atm) {   // Это конструктор класса getMoneyes. Принимает объект AtmMoney в качестве аргумента и инициализирует поле atm этим объектом.
        this.atm = atm;
    }
    public void pickUpMoney(Card card, AtmMoney atm) {
        //Объявление метода pickUpMoney. Принимает объекты Card и AtmMoney в качестве аргументов и не возвращает значения.

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the amount to get: ");
        minus = sc.nextInt();                                               // Вводится сумма для снятия, которая сохраняется в переменной minus.

        if (minus <= atm.getMoney() && minus <= card.getScore()) {         //Проверяется, достаточно ли средств как в банкомате (atm.getMoney()) так и на карте (card.getScore()) для выполнения операции снятия.
            List<Card> cardList = new Read().getReadList();                //Создается список объектов Card, полученных из файла, с использованием метода getReadList() из класса Read.

            //Создаем новый список list1, в котором обновляется баланс карты (m.setScore(m.getScore() - minus))
            //для соответствующей карты, и затем этот список собирается обратно в список с использованием Stream API.
            List<Card> list1 = cardList.stream().map(m -> {
                if (m.getAccountNumber().equals(card.getAccountNumber())) {
                    m.setScore(m.getScore() - minus);
                    card.setScore(m.getScore());
                }
                return m;
            }).collect(Collectors.toList());

            // Обновиляем баланс банкомата только если снятие прошло успешно
            atm.setMoney(atm.getMoney() - minus);

            // Перезаписали файл с обновленными данными
            try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter("C:\\Users\\shumo\\OneDrive\\Desktop\\BKM.txt"))) {

                // В цикле проходится по списку list1 и записывает данные каждой карты в файл.
                for (Card thisList : list1) {
                    fileWriter.write(thisList.getName() + " " + thisList.getSureName() + " " +
                            thisList.getAccountNumber() + " " + thisList.getPassword() + " " +
                            thisList.getScore());
                    fileWriter.newLine();
                }
                System.out.println("Thank you for using our bank");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {   //выполняется условие else в случае не пывалнения if (minus <= atm.getMoney() && minus <= card.getScore())
            System.out.println("ATM does not have enough money or you have exceeded the card limit.");
        }

        SelectPosition selectPosition = new SelectPosition();
        selectPosition.toSelect(card, atm);
    }
}