package homework3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class PhoneBook {
    private HashMap<String, ArrayList<String>> phoneBook = new LinkedHashMap<>();

    public void add(String surName, String tel) {
        ArrayList<String> telList = phoneBook.get(surName);
        if (telList == null) telList = new ArrayList<>();
        telList.add(tel);
        phoneBook.put(surName, telList);
    }

    public ArrayList<String> get(String surName) {
        return phoneBook.get(surName);
    }

    public void info() {
        System.out.println(phoneBook);
    }
}
