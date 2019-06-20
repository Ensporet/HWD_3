package com.CRUD;


import entity.Companies;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


public class CrudHibernate<T>  {


    private static final SessionFactory SESSION_FACTORY = new Configuration().configure().buildSessionFactory();


    public void updata (T t) {
    funcSet(session -> session.update(t));
    }

    public static void main(String[] args) {
        System.out.println("d");

        Session session =
                CrudHibernate.getSESSION_FACTORY().openSession();

        Transaction transaction = session.beginTransaction();
        session.update(new Companies(12,"d","d"));
        transaction.commit();

        session.close();
    }




    private void funcSet(Func f){
        Session session =
        CrudHibernate.getSESSION_FACTORY().openSession();
        Transaction transaction = session.beginTransaction();
        f.action(session);
        transaction.commit();
        session.close();
    }

    private void funcGet(Func f){
        Session session =
                CrudHibernate.getSESSION_FACTORY().openSession();
        f.action(session);
        session.close();
    }

    private interface  Func{
        void action(Session session);
    }



    public static SessionFactory getSESSION_FACTORY() {
        return SESSION_FACTORY;
    }
}
