package entities;

import javax.persistence.*;

@Entity
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "phone_id", nullable = false)
    private int phone_id;
    private int number;

    public Phone() {
    }

    public Phone(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getPhone_id() {
        return phone_id;
    }

    public void setPhone_id(int phone_id) {
        this.phone_id = phone_id;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "phone_id=" + phone_id +
                ", number=" + number +
                '}';
    }
}
