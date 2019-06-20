package entity;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


@Entity
@Table(name = "dev_pro")
public class DevPro implements Serializable {


    @Id
    @Column(name = "id_developers")
    private int id_developers;


    @Id
    @Column(name = "id_projects")
    private int id_projects;

    public DevPro(int id_developers, int id_projects) {
        this.id_developers = id_developers;
        this.id_projects = id_projects;
    }

    public DevPro() {
    }

    public int getId_developers() {
        return id_developers;
    }

    public void setId_developers(int id_developers) {
        this.id_developers = id_developers;
    }

    public int getId_projects() {
        return id_projects;
    }

    public void setId_projects(int id_projects) {
        this.id_projects = id_projects;
    }

    @Override
    public String toString() {
        return "DevPro{" +
                "id_developers=" + id_developers +
                ", id_projects=" + id_projects +
                '}';
    }
}
