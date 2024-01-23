package org.example;

import org.example.utilities.HibernateUtil;
import org.example.view.Menu;
import org.hibernate.Session;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.dialog();
    }
}