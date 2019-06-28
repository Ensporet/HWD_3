package com.Console.Menu;

import com.ClassFinder.ClassFinder;
import com.Console.IAction;
import com.Console.ObjectConsoleDefault;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;


public class MenuTables extends ObjectConsoleDefault {


    public MenuTables() {
        super("Tables");
        this.action();

    }


    @Override
    protected void update() {

        this.getACTIONS().clear();
        this.getACTIONS().addAll(createActions());


        super.update();

    }


    private List<IAction> createActions() {

        List<IAction> list = new ArrayList<>();

        for (Class entity : getAllEntity()) {
            IAction action = createMenuTable(entity);
            if (action != null) {
                list.add(action);
            }
        }

        return list;
    }


    private IAction createMenuTable(Class entity) {

        javax.persistence.Table annotation = (javax.persistence.Table) entity.getAnnotation(javax.persistence.Table.class);

        return (annotation == null) ? null : new IAction() {
            @Override
            public String getName() {
                return (annotation.name().isEmpty()) ? entity.getTypeName() : annotation.name();
            }

            @Override
            public void action() {
                new MenuTable(this.getName(), entity);
            }
        };

    }

    private List<Class> getAllEntity() {
        List<Class> list = new ArrayList<>();
        for (Class cl : ClassFinder.find("entity")) {
            if (isClassEntity(cl)) {
                list.add(cl);
            }
        }
        return list;

    }

    private boolean isClassEntity(Class cl) {
        return cl.getDeclaredAnnotation(Entity.class) != null;
    }


}
