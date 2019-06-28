package com.Console.Functions;

import javax.persistence.Column;
import java.util.Date;

public class TableNewFormat {


    @Column
    private Date dateCreate;
    @Column
    private String nameProject;
    @Column
    private int sizeDevelopers;

    public TableNewFormat(Date dataCreate, String nameProject, int sizeDevelopers) {
        this.dateCreate = dataCreate;
        this.nameProject = nameProject;
        this.sizeDevelopers = sizeDevelopers;
    }

    public TableNewFormat() {
    }


    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getNameProject() {
        return nameProject;
    }

    public void setNameProject(String nameProject) {
        this.nameProject = nameProject;
    }

    public int getSizeDevelopers() {
        return sizeDevelopers;
    }

    public void setSizeDevelopers(int sizeDevelopers) {
        this.sizeDevelopers = sizeDevelopers;
    }
}
