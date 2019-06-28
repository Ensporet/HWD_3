package com.CRUD;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


public class CrudHibernate<T> {


    private static final SessionFactory SESSION_FACTORY = new Configuration().configure().buildSessionFactory();

    public static SessionFactory getSESSION_FACTORY() {
        return SESSION_FACTORY;
    }

    public List<T> getAll(Class<T> cl) {
        List<T> list = new ArrayList<>();

        funcGet(session -> {
            List<T> l = session.createQuery("From " + cl.getName(), cl).list();
            if (l != null) {
                list.addAll(l);
            }
            return null;
        });

        return list;
    }

    //---
    private StringBuffer createRequestGetAll(Class<T> cl) {
        return createRequestGetAll(cl, null);
    }

    private StringBuffer createRequestGetAll(Class<T> cl, final String IF) {

        StringBuffer stringBuffer = null;
        final String NAME_TABLE = getTableName(cl);
        if (NAME_TABLE == null) {
            return null;
        }

        for (Field field : cl.getDeclaredFields()) {
            Column annotation = field.getDeclaredAnnotation(Column.class);
            if (annotation == null) {
                continue;
            }

            if (stringBuffer == null) {
                stringBuffer = new StringBuffer("SELECT ");
            } else {
                stringBuffer.append(", ");
            }
            annotation.name();
            stringBuffer.append(annotation.name().isEmpty() ? field.getName() : annotation.name());
        }

        return (stringBuffer == null) ? null :
                stringBuffer.append(" FROM ").append(NAME_TABLE).append((IF == null) ? "" : " WHERE " + IF);

    }

    //-------------------------------------------------

    /**
     * @param cl
     * @return can null
     */
    private String getTableName(Class<T> cl) {
        Table table = cl.getDeclaredAnnotation(Table.class);
        return table == null ? null : table.name().isEmpty() ? cl.getName() : table.name();
    }

    public void delete(T t) {
        funcSet(session -> {
            session.delete(t);
        });
    }

    //---------------------------------------------------------

    public void deleteAll(Class<T> type) {

        funcSet(session -> {
            String name = type.getDeclaredAnnotation(Table.class).name();
            if (name.isEmpty()) {
                name = type.getName();
            }

            session.createNativeQuery("DElETE FROM " + name).executeUpdate();

        });

    }

    //------------------------

    public T get(Class<T> cl, Serializable serializable) {

        return funcGet(session -> session.get(cl, serializable));
    }

    //-------------------------

    public void set(T t) {
        funcSet(session -> {
            session.update(t);
        });
    }


    //--------------------------------------------------

    public void add(T t) {
        funcSet(session -> {
            session.save(t);
        });
    }

    private List getKeies(T t) {
        List objects = new ArrayList();

        for (Field field : t.getClass().getDeclaredFields()) {
            if (field.getAnnotation(Id.class) != null) {
                field.setAccessible(true);
                try {
                    objects.add(field.get(t));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    continue;
                }
            }
        }

        return objects;
    }

    private void funcSet(Func f) {
        Session session =
                CrudHibernate.getSESSION_FACTORY().openSession();
        Transaction transaction = session.beginTransaction();
        f.action(session);
        transaction.commit();
        session.close();
    }

    private T funcGet(FuncGet<T> f) {
        T t;
        Session session =
                CrudHibernate.getSESSION_FACTORY().openSession();
        t = f.action(session);
        session.close();

        return t;
    }

    private interface Func {
        void action(Session session);
    }


    private interface FuncGet<T> {
        T action(Session session);
    }
}
