package com.Console.Functions;

import com.CRUD.CrudHibernate;
import com.Console.IAction;
import com.Console.Menu.MenuTable;
import entity.DevSki;
import entity.Developers;
import entity.Skills;

import java.util.List;
import java.util.stream.Collectors;

@HWFunction
public class Function3 implements IAction {
    private final CrudHibernate CRUD = new CrudHibernate();


    public Function3() {

    }

    @Override
    public String getName() {
        return "List of all developers have middle skills";
    }


    @Override
    public void action() {

        final String LEVEL = "middle";


        List<Skills> skillsList = ((List<Skills>) getCRUD().getAll(Skills.class)).stream().filter(skills -> {
            return LEVEL.equals(skills.getLevel());
        }).collect(Collectors.toList());


        List<DevSki> devSkiList = ((List<DevSki>) getCRUD().getAll(DevSki.class)).stream().filter(devSki -> {

                    for (Skills skills : skillsList) {
                        if (skills.getId_skills() == devSki.getId_skills()) {
                            return true;
                        }
                    }
                    return false;
                }
        ).collect(Collectors.toList());

        List<Developers> developersList =
                ((List<Developers>) getCRUD().getAll(Developers.class))
                        .stream()
                        .filter(developers -> {

                                    for (DevSki devSki : devSkiList) {
                                        if (devSki.getId_developers() == developers.getId_developers()) {
                                            return true;
                                        }
                                    }
                                    return false;
                                }
                        ).collect(Collectors.toList());
        System.out.println(MenuTable.getAllInfoRows(Developers.class, developersList));
    }
    public CrudHibernate getCRUD() {
        return CRUD;
    }
}
