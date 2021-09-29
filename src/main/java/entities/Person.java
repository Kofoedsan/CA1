package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;


@Entity
@NamedQuery(name = "Person.deleteAllRows", query = "DELETE from Person")
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int person_id;
    private String fName;
    private String lName;
    private String email;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Phone phone;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Address address;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Hobby> hobbies = new ArrayList<>();

    public Person() {
    }

    public Person(String fName, String lName, String email, Phone phone, Address address, List<Hobby> hobbies) {
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.hobbies = hobbies;
    }

    public List<Hobby> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<Hobby> hobbies) {
        this.hobbies = hobbies;
    }


    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Person(String fName, String lName, String email)
    {
        this.fName = fName;
        this.lName = lName;
        this.email = email;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public int getPerson_id()
    {
        return person_id;
    }

    public void setPerson_id(int person_id)
    {
        this.person_id = person_id;
    }


    public String getfName()
    {
        return fName;
    }

    public void setfName(String fName)
    {
        this.fName = fName;
    }

    public String getlName()
    {
        return lName;
    }

    public void setlName(String lName)
    {
        this.lName = lName;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    @Override
    public String toString()
    {
        return getClass().getSimpleName() + "(" +
                "person_id = " + person_id + ", " +
                "fName = " + fName + ", " +
                "lName = " + lName + ", " +
                "email = " + email + ")";
    }
}
