package com.Console.Menu;

import com.Console.IAction;
import com.Console.ObjectConsole;
import com.Console.ObjectConsoleDefault;

import javax.persistence.Id;
import java.lang.reflect.Field;
import java.util.List;

public class MenuSelectedElement extends ObjectConsoleDefault {

    private final List<Object> ROWS;
    private Object selectedRow;
    private String selectedRowName;


    public MenuSelectedElement(String name, List rows) {
        super(name);

        this.ROWS = rows;

        action();
    }


    @Override
    protected void update() {
        createActions();
        super.update();
    }

    private void createActions() {
        getACTIONS().clear();

        for (Object obj : getROWS()) {
            getACTIONS().add(createSelect(obj));
        }

    }

    private IAction createSelect(Object object) {
        return new IAction() {
            @Override
            public String getName() {
                return createNameObject(object);
            }

            @Override
            public void action() {
                System.out.println("Selected <" + this.getName() + ">");
                ObjectConsole.ConsoleIteration = ObjectConsole.ITERATION_CLOSE;
                setSelectedRow(object);
                setSelectedRowName(this.getName());
            }
        };
    }


    private String createNameObject(Object obj) {
        final String SEPARATOR_ID = " | ";
        String name = "";

        for (Field field : obj.getClass().getDeclaredFields()) {
            if (field.getDeclaredAnnotation(Id.class) != null) {
                field.setAccessible(true);
                try {
                    Object value = field.get(obj);
                    if (!name.isEmpty()) {
                        name += SEPARATOR_ID;
                    }
                    name += (value == null) ? "NULL" : value.toString();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            }
        }

        return (name.isEmpty()) ? "NULL" : name;
    }


    public List<Object> getROWS() {
        return ROWS;
    }

    public Object getSelectedRow() {
        return selectedRow;
    }

    public void setSelectedRow(Object selectedRow) {
        this.selectedRow = selectedRow;
    }

    public String getSelectedRowName() {
        return selectedRowName;
    }

    public void setSelectedRowName(String selectedRowName) {
        this.selectedRowName = selectedRowName;
    }
}
