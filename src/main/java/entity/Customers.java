package entity;



import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "customers")
public class Customers implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_customers")
    private int id_customers;

    @Column(name = "name")
    private String name;

    @Column(name = "liquidity")
    private int liquidity;

    public Customers() {
    }

    public Customers(int id_customers, String name, int liquidity) {
        this.id_customers = id_customers;
        this.name = name;
        this.liquidity = liquidity;
    }

    public Customers(int id) {
        this.id_customers = id;
    }

    public int getId_customers() {
        return id_customers;
    }

    public void setId_customers(int id_customers) {
        this.id_customers = id_customers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLiquidity() {
        return liquidity;
    }

    public void setLiquidity(int liquidity) {
        this.liquidity = liquidity;
    }

    @Override
    public String toString() {
        return "Customers{" +
                "id_customers=" + id_customers +
                ", name='" + name + '\'' +
                ", liquidity=" + liquidity +
                '}';
    }
}
