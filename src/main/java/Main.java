import android.app.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int persons = InputChecker.persons();
        List<Item> items = new ArrayList<>();
        while (true){
            items.add(Calculator.add());
            System.out.println("Для добавления ещё одного товара введите любой текст. Введите команду \"Завершить\" для того, чтоб завершить процесс добавления товаров. ");
            if (InputChecker.complete()) {
                break;
            }
        }
        for (Item item : items) {
            System.out.println(item.name);
        }
        Calculator.count(items, persons);
    }
}


class InputChecker {
    public static int persons() {
        int persons;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("На скольких человек необходимо разделить счёт?");
            if (scanner.hasNextInt()) {
                persons = scanner.nextInt();
                if (persons > 1) {
                    return (persons);
                }
                System.out.println("Количество человек должно быть больше 1.");
            } else {
                System.out.println("Вы ввели не целое число.");
            }
        }
    }

    public static float price() {
        float price;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Введите стоимость товара. Она должна быть в формате рубли.копейки:");
            if (scanner.hasNextFloat()) {
                price = Math.round(scanner.nextFloat() * 100.0f) / 100.0f;//выкинул лишние знаки сейчас, что бы не было погрешности
                if (price > 0.0f) {
                    return (price);
                }
                System.out.println("Стоимость должна быть больше 0.");
            } else if (scanner.hasNextInt()) {
                price = (float)scanner.nextInt();
                if (price > 0.0f) {
                    return (price);
                }
                System.out.println("Стоимость должна быть больше 0.");
            }else {
                System.out.println("Вы ввели не стоимость в неверном формате.");
            }
        }
    }

    public static boolean complete() {
        Scanner scanner = new Scanner(System.in);
        String complete = "Завершить";
        return (complete.equalsIgnoreCase(scanner.nextLine()));
    }
}


class Calculator{
    public static Item add() {
        Item item = new Item();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите название товара:");
        item.name = scanner.nextLine();
        item.price = InputChecker.price();
        return (item);
    }

    public static void count(List<Item> items, int persons){
        float sum = 0;
        float perPerson;
        int remainder;
        double  roundedSum;
        for (Item item : items) {
            System.out.println(item.name);
            sum = sum + item.price;
        }
        remainder = (int) (sum % persons * 100.0f);
        perPerson = sum / persons;
        roundedSum = Math.floor(sum);
        if (roundedSum == 1) {
            System.out.println(String.format("Каждый человек должен заплатить %.2f рубль", perPerson));
        } else if (roundedSum > 1 && roundedSum < 5) {
            System.out.println(String.format("Каждый человек должен заплатить %.2f рубля", perPerson));
        } else {
            System.out.println(String.format("Каждый человек должен заплатить %.2f рублей.", perPerson));
        }
        if (remainder != 0) {
            System.out.println("К сожалению ровно поделить не выйдет и ещё " + remainder + " из них должны доплатить по копейке.");
        }
    }
}


class Item{
    String name;
    float price;
}