package com.Console.ActValue;

import java.util.Scanner;

/**
 * Class task to check value formats
 * Expansion of functionality
 *
 * @param <T>
 */
public interface IActValue<T> {


    String getMassage();

    T isTrueFormat(String string);

    T isTrueFormat(Scanner scanner);

    default String getMassageUpdateFalse() {
        return "Update is false";
    }

    default String getMassageUpdatedTrue() {
        return "Update is successfully";
    }

    default String getMassageCancel() {
        return ">-----< Cancel >-----<";
    }

    default String getInfoCancel() {
        return (cancelString() == null || cancelString().isEmpty()) ? null : "Cancel : [" + cancelString() + "]";
    }

    default String cancelString() {
        return "x";
    }

}
