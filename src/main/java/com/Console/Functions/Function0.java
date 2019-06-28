package com.Console.Functions;

import com.CRUD.CrudHibernate;
import com.Console.IAction;
import com.Console.ObjectConsole;
import com.Console.ObjectConsoleDefault;
import entity.DevPro;
import entity.Developers;
import entity.Projects;

import java.util.List;


@HWFunction
public class Function0 extends ObjectConsoleDefault {

    private final CrudHibernate CRUD = new CrudHibernate();
    private final Class ENTITY_PROJECTS = Projects.class;

    public Function0() {

        super("Salary (amount) of all developers of a separate project;");
    }

    @Override
    protected void update() {
        updateActions();
        super.update();
    }

    private void updateActions() {

        getACTIONS().clear();

        for (Object obj : getCRUD().getAll(getENTITY_PROJECTS())) {
            getACTIONS().add(createAction(obj));
        }
    }

    private IAction createAction(Object row) {
        return new IAction() {
            @Override
            public String getName() {
                return row.toString();
            }

            @Override
            public void action() {

                System.out.println(Function0.this.getName() + " : " + SalaryAllDeveloperProject((Projects) row));//Нужно выбрать проэкт
                ObjectConsole.ConsoleIteration = ObjectConsole.ITERATION_CLOSE;
            }
        };
    }


    private int SalaryAllDeveloperProject(Projects projects) {

        List<DevPro> devProList = getCRUD().getAll(DevPro.class); // dev_pro
        for (int i = devProList.size() - 1; i > -1; i--) {
            DevPro devPro = devProList.get(i);
            if (devPro.getId_projects() != projects.getId_projects()) {
                devProList.remove(i);
            }
        }


        List<Developers> developersList = getCRUD().getAll(Developers.class); //developers
        for (int i = developersList.size() - 1; i > -1; i--) {
            Developers developers = developersList.get(i);

            boolean isTrue = false;
            for (DevPro devPro : devProList) {
                if (devPro.getId_developers() == developers.getId_developers()) {
                    isTrue = true;
                    break;
                }
            }
            if (!isTrue) {
                developersList.remove(i);
            }
        }


        int salary = 0;
        for (Developers developers : developersList) {
            salary += developers.getSalary();
        }

        return salary;
    }


    public CrudHibernate getCRUD() {
        return CRUD;
    }

    public Class getENTITY_PROJECTS() {
        return ENTITY_PROJECTS;
    }

}
