package BANKOMAT.ENTRY;
import BANKOMAT.Card;
import BANKOMAT.READ.Read;
import BANKOMAT.ATM.AtmMoney;
import java.util.List;
import java.util.Scanner;

public class Entry {
    public static Card toEntry(Read cards, AtmMoney atm) {
        Scanner sc = new Scanner(System.in);

        int maxAttempts = 3;     // Максимальное количество попыток ввода пароля
        int attempts = 0;        //Текущее количество попыток
        int enterPassword;       //Переменная ввода пароля
        boolean check = false;   //Переменная проверки

        List<Card> list = cards.getReadList();   //Создаем List и передаем в него гетерные данные записанные с класса Read
        Card card = null;                        //Для начала приравниваем наш обьект к null

        while (attempts < maxAttempts) {         //Задаем в цикле сравнение попыток ввода пароля
            System.out.println("Enter password, please");  //Запрос ввода пароля в консоли
            enterPassword = sc.nextInt();                  //Переменная ввода пароля = запуск запроса в консоль через сканер
            for (Card c : list) {                          //Проверяем все наши данные считанные с листа имеется ли там данный пароль
                if (enterPassword == c.getPassword()) {    //Если имеется то ->
                    card = c;                              //Данному обьекту присваивается данный обьект с его всеми значениями
                    check = true;                          //Переменная проверки есть true
                    System.out.println("Password is correct, welcome to the system " + c.getName() + " " + c.getSureName());
                    break;                                 //Завершаем поиск обьекта выходим из данного цикла
                }
            }
            if (check) {
                // Если пароль верный, выходим из цикла
                break;
            } else {
                // Если пароль неверный, увеличиваем счетчик попыток и предлагаем ввести пароль снова
                attempts++;
                System.out.println("Incorrect password. You have " + (maxAttempts - attempts) + " attempts left.");
            }
        }
        if (card == null) {
            // Если количество попыток исчерпано, значит в данном списке не имеется нужный пароль, завершаем программу
            System.out.println("Card is blocked. Please call the bank to decide problem.");
            System.exit(0);
        }
        //Если данный пароль существует в нашей коллекции после завершения цикла происходит ->
        // Переход к данному методу данного класса selectPosition.toSelect и возврат карточки return card;
        //Где хранится данный пользователь со всеми своими параметрами для дальнейшей работы в системе
        SelectPosition selectPosition = new SelectPosition();
        selectPosition.toSelect(card, atm);
        return card;
    }
}
