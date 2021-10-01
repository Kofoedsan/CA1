package entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "phone_id", nullable = false)
    private int phone_id;
    private int number;

    @ManyToOne
    private Person person;


    public Phone() {
    }

    public Person getPerson() {
        return person;
    }

    public int getPhone_id() {
        return phone_id;
    }

    public void setPhone_id(int phone_id) {
        this.phone_id = phone_id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "phone_id=" + phone_id +
                ", number=" + number +
                '}';
    }
}
