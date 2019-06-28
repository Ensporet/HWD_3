package entity;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "skills")
public class Skills implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_skills")
    private int id_skills;

    @Column(name = "name")
    private String name;

    @Column(name = "level")
    private String level;

    public Skills() {
    }

    public Skills(int id_skills, String name, String level) {
        this.id_skills = id_skills;
        this.name = name;
        this.level = level;
    }

    public Skills(int id) {
        this.id_skills = id;
    }

    public int getId_skills() {
        return id_skills;
    }

    public void setId_skills(int id_skills) {
        this.id_skills = id_skills;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "Skills{" +
                "id_skills=" + id_skills +
                ", name='" + name + '\'' +
                ", level='" + level + '\'' +
                '}';
    }
}
