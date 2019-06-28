package com.Console;

import com.ClassFinder.ClassFinder;
import com.Console.ActValue.Act;
import com.Console.ActValue.IActValue;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class ActionValue<T> implements IAction {

    private final static String NAME_PACK_ACT_VALUE = "com.Console.ActValue";
    private final Field FIELD;
    private final Object MAIN_OBJECT;
    private final String NAME_VALUE;
    private final IActValue ACT_VALUE;
    private final Scanner SCANNER;
    private final boolean CAN_CHANGE_ID;
    private final boolean CAN_CHANGE_ID_INCREMENT;


    public ActionValue(Field FIELD, Object MAIN_OBJECT, Scanner scanner) {
        this(FIELD, MAIN_OBJECT, null, scanner, false, false);

    }

    public ActionValue(Field FIELD, Object MAIN_OBJECT, String name_value, Scanner scanner, boolean CAN_CHANGE_ID, boolean CAN_CHANGE_ID_INCREMENT) {
        this.CAN_CHANGE_ID = CAN_CHANGE_ID;
        this.CAN_CHANGE_ID_INCREMENT = CAN_CHANGE_ID_INCREMENT;
        this.FIELD = FIELD;
        this.MAIN_OBJECT = MAIN_OBJECT;
        NAME_VALUE = name_value;
        getFIELD().setAccessible(true);
        this.SCANNER = scanner;
        this.ACT_VALUE = this.initializationActValue();
    }


    /**
     * this method mast be constructor the last of initialization parameter
     */
    private IActValue initializationActValue() {

        for (Class cl : ClassFinder.find(ActionValue.NAME_PACK_ACT_VALUE)) {
            Act act = (Act) cl.getDeclaredAnnotation(Act.class);
            if (act == null) {
                continue;
            }


            for (Class type : act.type()) {
                if (getFIELD().getType().equals(type)) {
                    try {
                        return (IActValue) cl.getConstructor().newInstance();
                    } catch (InstantiationException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return null;
    }

    @Override
    public String getName() {
        return getNAME_VALUE();
    }

    @Override
    public void action() {

        if (isFieldId() && !isCAN_CHANGE_ID()) {
            println(getMassageProhibitionSetValue("Value CAN_CHANGE_ID = " + isCAN_CHANGE_ID()));
            return;
        }


        if (!isCAN_CHANGE_ID_INCREMENT() && isAutoIncrement()) {
            println(getMassageProhibitionSetValue("Value CAN_CHANGE_ID_INCREMENT = " + isCAN_CHANGE_ID_INCREMENT()));
            return;
        }

        Object returnValue = getACT_VALUE().isTrueFormat(getSCANNER());
        if (returnValue != null) {
            setValue((T) returnValue);
            ObjectConsole.ConsoleUpdate = true;

        }
    }

    private boolean isFieldId() {
        return getFIELD().getDeclaredAnnotation(Id.class) != null;
    }

    private String getMassageProhibitionSetValue(String explanation) {
        return "This value prohibition update . " + ((explanation == null) ? "" : explanation) +
                "\n<<<<----------Return";
    }


    private void println(String s) {
        if (s != null) {
            System.out.println(s);
        }
    }


    private boolean isAutoIncrement() {
        return getFIELD().getDeclaredAnnotation(GeneratedValue.class) != null;
    }

    public T getValue() {
        try {
            return (T) getFIELD().get(getMAIN_OBJECT());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setValue(T value) {
        try {
            getFIELD().set(getMAIN_OBJECT(), value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    public Field getFIELD() {
        return FIELD;
    }

    public Object getMAIN_OBJECT() {
        return MAIN_OBJECT;
    }

    public String getNAME_VALUE() {
        return NAME_VALUE;
    }

    public IActValue getACT_VALUE() {
        return ACT_VALUE;
    }

    public Scanner getSCANNER() {
        return SCANNER;
    }


    public boolean isCAN_CHANGE_ID() {
        return CAN_CHANGE_ID;
    }

    public boolean isCAN_CHANGE_ID_INCREMENT() {
        return CAN_CHANGE_ID_INCREMENT;
    }
}
