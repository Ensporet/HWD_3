package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;


@Entity
@javax.persistence.Table(name = "com_pro")
public class ComPro implements Serializable {

    @Id
    @Column(name = "id_companies")
    private int id_companies;

    @Id
    @Column(name = "id_project")
    private int id_projects;


    public ComPro(int id_companies, int id_projects) {
        this.id_companies = id_companies;
        this.id_projects = id_projects;
    }

    public ComPro() {
    }

    public int getId_companies() {
        return id_companies;
    }

    public void setId_companies(int id_companies) {
        this.id_companies = id_companies;
    }

    public int getId_projects() {
        return id_projects;
    }

    public void setId_projects(int id_projects) {
        this.id_projects = id_projects;
    }

    @Override
    public String toString() {
        return "ComPro{" +
                "id_companies=" + id_companies +
                ", id_projects=" + id_projects +
                '}';
    }
}
