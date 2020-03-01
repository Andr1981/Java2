package homework3;

public class PhoneBookMainTestDrive {
    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.add("Петров","89991111111");
        phoneBook.add("Иванов","89992222222");
        phoneBook.add("Сидоров","89993333333");
        phoneBook.add("Петров","89994444444");
        phoneBook.add("Скворцов","89995555555");
        phoneBook.add("Сидоров","89996666666");
        phoneBook.info();
        System.out.println("У Петрова номер " + phoneBook.get("Петров"));
        System.out.println("У Сидорова номер "+ phoneBook.get("Сидоров"));

    }
}
