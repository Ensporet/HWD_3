package com.Console.Functions;

import com.CRUD.CrudHibernate;
import com.Console.IAction;
import com.Console.Menu.MenuTable;
import entity.DevPro;
import entity.Developers;
import entity.Projects;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@HWFunction
public class Function4 implements IAction {

    private final CrudHibernate CRUD = new CrudHibernate();

    @Override
    public String getName() {
        return "List of projects in the following format: creation date - project name - the number of developers on this project.";
    }

    @Override
    public void action() {

        List<Projects> projectsList = (List<Projects>) getCRUD().getAll(Projects.class);
        List<TableNewFormat> tableNewFormatList = new ArrayList<>();

        for (Projects projects : projectsList) {

            tableNewFormatList.add(new TableNewFormat(valueData(), valueName(projects), sizeDevelopers(projects)));

        }

        System.out.println(
                MenuTable.getAllInfoRows(TableNewFormat.class, tableNewFormatList));

    }


    private Date valueData() {
        return new Date();
    }

    private String valueName(Projects projects) {
        return projects.getName();
    }

    private int sizeDevelopers(Projects projects) {

        List<DevPro> devProList = ((List<DevPro>) getCRUD().getAll(DevPro.class)).stream().filter(devPro -> projects.getId_projects() == devPro.getId_projects()).collect(Collectors.toList());
        return ((List<Developers>) getCRUD().getAll(Developers.class)).stream().filter(developers -> {

            for (DevPro devPro : devProList) {
                if (devPro.getId_developers() == developers.getId_developers()) {
                    return true;
                }
            }
            return false;
        }).collect(Collectors.toList()).size();

    }


    public CrudHibernate getCRUD() {
        return CRUD;
    }
}
