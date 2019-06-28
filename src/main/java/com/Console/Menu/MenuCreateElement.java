package com.Console.Menu;


import java.lang.reflect.InvocationTargetException;

public class MenuCreateElement extends MenuUpdateElement {


    public MenuCreateElement(Object newRow) {
        super(newRow, "Setting created by new object", true, false);

    }

    public static Object createNewObject(Class entity) {
        Object object = null;

        try {
            object = entity.getConstructor().newInstance();
            System.out.println("Object created successfully");
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
            System.out.println("Create object failed");
        }
        return object;
    }

    @Override
    protected boolean updateDB() {

        try {
            getCRUD().add(getROW());
            return true;
        } catch (Exception e) {
            e.printStackTrace();

        }
        return false;
    }


}
