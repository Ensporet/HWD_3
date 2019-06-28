package com.Console.Menu;


import com.CRUD.CrudHibernate;
import com.Console.ActionValue;
import com.Console.IAction;
import com.Console.ObjectConsole;
import com.Console.ObjectConsoleDefault;

import javax.persistence.Column;
import java.lang.reflect.Field;


public class MenuUpdateElement extends ObjectConsoleDefault {

    private final Object ROW;
    private final boolean CAN_CHANGE_ID;
    private final boolean CAN_CHANGE_ID_INCREMENT;
    private final CrudHibernate CRUD = new CrudHibernate();


    public MenuUpdateElement(Object row) {
        this(row, "Update", false, false);

    }


    public MenuUpdateElement(Object row, String name, boolean canChangeId, boolean canChangeIdIncrement) {
        super(name);
        this.CAN_CHANGE_ID = canChangeId;
        this.CAN_CHANGE_ID_INCREMENT = canChangeIdIncrement;

        this.ROW = row;


        this.action();

    }


    @Override
    public void action() {
        super.action();

        if (updateDB()) {
            println(getMassageUpdateDBTrue());
            ObjectConsole.ConsoleUpdate = true;
        } else {
            println(getMassageUpdateDBFalse());
        }
    }


    protected boolean updateDB() {

        try {
            getCRUD().set(getROW());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    private void println(String s) {
        if (s != null) {
            System.out.println(s);
        }
    }

    private String getMassageUpdateDBTrue() {
        return "Update DB is successfully ! ";
    }

    private String getMassageUpdateDBFalse() {
        return "Update DB is not successfully ! ";
    }

    private void updateActions() {
        getACTIONS().clear();

        for (Field field : getROW().getClass().getDeclaredFields()) {
            getACTIONS().add(createAction(field, field.getAnnotation(Column.class)));
        }

    }

    @Override
    protected void update() {
        updateActions();
        super.update();
    }

    private IAction createAction(Field field, Column column) {

        final String NAME_VALUE = ((column == null || column.name().isEmpty()) ? field.getName() : column.name());


        return new ActionValue(field, getROW(), createModificationForNameValue(NAME_VALUE, field), getScanner(), isCAN_CHANGE_ID(), isCAN_CHANGE_ID_INCREMENT());
    }


    private String createModificationForNameValue(String nameValue, Field field) {
        field.setAccessible(true);
        Object value = null;
        try {
            value = field.get(getROW());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return
                nameValue +
                        ((value == null) ? "" : " Value : " + value.toString()) +
                        " Type : " + field.getType();

    }


    public Object getROW() {
        return ROW;
    }

    public boolean isCAN_CHANGE_ID() {
        return CAN_CHANGE_ID;
    }

    public CrudHibernate getCRUD() {
        return CRUD;
    }

    public boolean isCAN_CHANGE_ID_INCREMENT() {
        return CAN_CHANGE_ID_INCREMENT;
    }
}
