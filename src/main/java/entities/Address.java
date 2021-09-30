package entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "a_id", nullable = false)
    private int a_id;
    private String street;

    @OneToMany(mappedBy = "address")
    private List<Person> persons;

    @JoinColumn
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Cityinfo cityinfo;

    public Address() {
    }

    public Address(String street) {
        this.street = street;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public Cityinfo getCityinfo() {
        return cityinfo;
    }

    public void setCityinfo(Cityinfo cityinfo_id) {
        this.cityinfo = cityinfo_id;
    }

    public int getA_id() {
        return a_id;
    }

    public void setA_id(int a_id) {
        this.a_id = a_id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
