package entity;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "companies")
public class Companies implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_companies")
    private int id_companies;
    @Column(name = "name")
    private String name;
    @Column(name = "slogan")
    private String slogan;

    public Companies(int id_companies, String name, String slogan) {
        this.id_companies = id_companies;
        this.name = name;
        this.slogan = slogan;
    }

    public Companies(int id) {
        this.id_companies = id;

    }

    public Companies() {


    }

    public int getId_companies() {
        return id_companies;
    }

    public void setId_companies(int id_companies) {
        this.id_companies = id_companies;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    @Override
    public String toString() {
        return "Companies{" +
                "id_companies=" + id_companies +
                ", name='" + name + '\'' +
                ", slogan='" + slogan + '\'' +
                '}';
    }
}
