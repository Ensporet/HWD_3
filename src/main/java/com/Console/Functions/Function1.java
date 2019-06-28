package com.Console.Functions;

import com.CRUD.CrudHibernate;
import com.Console.IAction;
import com.Console.Menu.MenuTable;
import com.Console.ObjectConsole;
import com.Console.ObjectConsoleDefault;
import entity.DevPro;
import entity.Developers;
import entity.Projects;

import java.util.List;

@HWFunction
public class Function1 extends ObjectConsoleDefault {

    private final CrudHibernate CRUD = new CrudHibernate();
    private final Class ENTITY_PROJECTS = Projects.class;

    public Function1() {
        super("All developers of a separate project");
    }

    @Override
    protected void update() {
        updateActions();
        super.update();
    }

    private void updateActions() {
        getACTIONS().clear();

        for (Object obj : getCRUD().getAll(getENTITY_PROJECTS())) {
            getACTIONS().add(createAction((Projects) obj));
        }

    }

    private IAction createAction(Projects projects) {

        return new IAction() {
            @Override
            public String getName() {
                return projects.getName();
            }

            @Override
            public void action() {
                printlnDevelopers(projects);
                ObjectConsole.ConsoleIteration = ObjectConsole.ITERATION_CLOSE;
            }
        };

    }


    private void printlnDevelopers(Projects projects) {


        List<DevPro> devProList = getCRUD().getAll(DevPro.class);  //  dev_pro
        for (int i = devProList.size() - 1; i > -1; i--) {
            DevPro devPro = devProList.get(i);
            if (devPro.getId_projects() != projects.getId_projects()) {
                devProList.remove(i);
            }
        }

        System.out.println(devProList);


        List<Developers> developersList = getCRUD().getAll(Developers.class); // developers
        for (int i = developersList.size() - 1; i > -1; i--) {
            Developers developers = developersList.get(i);

            boolean isTrye = false;
            for (DevPro devPro : devProList) {
                if (devPro.getId_developers() == developers.getId_developers()) {
                    isTrye = true;
                    break;
                }
            }
            if (!isTrye) {
                developersList.remove(i);
            }

        }


        System.out.println(MenuTable.getAllInfoRows(Developers.class, developersList));


    }

    public CrudHibernate getCRUD() {
        return CRUD;
    }

    public Class getENTITY_PROJECTS() {
        return ENTITY_PROJECTS;
    }
}
