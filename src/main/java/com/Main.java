package com;


import com.CRUD.CrudHibernate;
import com.Console.Menu.MenuMain;

import java.util.Scanner;

public class Main {

    public final static Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        println("Start HWD_3");
        new MenuMain();
        exit();
    }

    //------------------------

    public static void exit() {

        println("Exit");
        CrudHibernate.getSESSION_FACTORY().close();
        Main.SCANNER.close();
        System.exit(0);

    }

    public static void println(String word) {
        System.out.println(">-----< " + word + " >-----<");
    }


}
