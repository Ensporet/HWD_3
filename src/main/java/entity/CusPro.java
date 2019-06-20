package entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "cus_pro")
public class CusPro implements Serializable {

    @Id
    @Column(name = "id_customers")
    private int id_customers;

    @Id
    @Column(name = "id_projects")
    private int id_projects;

    public CusPro() {
    }

    public CusPro(int id_customers, int id_projects) {
        this.id_customers = id_customers;
        this.id_projects = id_projects;
    }

    public int getId_customers() {
        return id_customers;
    }

    public void setId_customers(int id_customers) {
        this.id_customers = id_customers;
    }

    public int getId_projects() {
        return id_projects;
    }

    public void setId_projects(int id_projects) {
        this.id_projects = id_projects;
    }

    @Override
    public String toString() {
        return "CusPro{" +
                "id_customers=" + id_customers +
                ", id_projects=" + id_projects +
                '}';
    }
}
