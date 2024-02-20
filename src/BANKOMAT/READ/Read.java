package BANKOMAT.READ;
import BANKOMAT.Card;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class Read {
    private List<Card>readList;              //Создается приватное поле readList типа List<Card>. Это поле предназначено для хранения объектов класса Card

    public List<Card> getReadList() {       //Определяется публичный метод getReadList(), который возвращает readList.
        return readList;                    //Этот метод предоставляет доступ к полю readList извне класса.
    }

    public Read(){                         //Конструктор класса Read
        readList = cardList();             //Вызывается метод cardList() для заполнения списка readList объектами Card.
    }
    public static List<Card> cardList() {         // Начинается определение статического метода cardList(), который возвращает список объектов типа Card.
        List<Card> cardList = new ArrayList<>();  //Создается новый объект ArrayList и присваивается переменной cardList. Этот список будет заполнен объектами Card.

        try (BufferedReader file = new BufferedReader(new FileReader("C:\\Users\\shumo\\OneDrive\\Desktop\\BKM.txt"))) {
            //Начинается блок try с открытием файла BKM.txt для чтения. Используется try-with-resources, чтобы автоматически закрыть BufferedReader после завершения блока.

            String line;                                                  // Объявляется переменная line, которая будет использоваться для считывания строк из файла.
            while ((line = file.readLine()) != null) {                    //Начинается цикл чтения строк из файла до тех пор, пока не достигнут конец файла.
                String[] array = line.split("\\s+");                //Разбивается текущая строка на массив строк, используя пробел как разделитель. Результат сохраняется в массив array.

                //Начинается цикл, который итерирует через каждый пятый элемент массива array.
                //Внутри цикла считываются значения для создания объекта Card.
                //Имя, фамилия, номер счета, пароль и счет считываются из массива array и используются для создания нового объекта Card.

                for (int i = 0; i < array.length; i += 5) {
                    String name = array[i];
                    String sureName = array[i + 1];
                    String acNumber = array[i + 2];
                    int password = Integer.valueOf(array[i + 3]);       //valueOf используется для преобразования строк, представляющих пароль и оценку (score)
                    double score = Double.valueOf(array[i + 4]);        //из массива array, в соответствующие числовые типы (int и double).
                    cardList.add(new Card(acNumber, password, score, name, sureName));  //Созданный объект Card добавляется в список cardList.
                }
            }

        } catch (FileNotFoundException e) {  //Обработка исключения, если файл не найден. Сообщение об ошибке выводится на консоль.
            e.printStackTrace();
        } catch (IOException e) {            //Обработка исключения ввода-вывода. Сообщение об ошибке также выводится на консоль.
            e.printStackTrace();
        }
        return cardList;                     //Возвращается список cardList с объектами Card.
    }
}
