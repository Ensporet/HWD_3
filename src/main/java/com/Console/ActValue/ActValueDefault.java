package com.Console.ActValue;

import java.util.Scanner;

public abstract class ActValueDefault<T> implements IActValue<T> {


    @Override
    public T isTrueFormat(Scanner scanner) {

        printlnMessage(getMassage());
        printlnMessage(getInfoCancel());
        String  enteredUser = scanner.nextLine();

        if(enteredUser != null && !enteredUser.isEmpty() && enteredUser.equals(cancelString())) {
            printlnMessage(getMassageCancel());
            return null;
        }

        T obj = isTrueFormat(enteredUser);
        if (obj == null) {
            printlnMessage(getMassageUpdateFalse());
        } else {printlnMessage(getMassageUpdatedTrue());}

        return obj;

    }


    private void printlnMessage(String massage) {
        if (massage != null) {
            System.out.println(massage);
        }}


}
