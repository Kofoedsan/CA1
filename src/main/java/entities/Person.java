package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;


@Entity
@NamedQuery(name = "Person.deleteAllRows", query = "DELETE from Person")
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int person_id;
    private int a_id;
    private int p_id;
    private int h_id;
    private String fName;
    private String lName;
    private String email;


    public Person() {
    }


    public Person(String fName, String lName, String email)
    {
        this.fName = fName;
        this.lName = lName;
        this.email = email;
    }

    public int getPerson_id()
    {
        return person_id;
    }

    public void setPerson_id(int person_id)
    {
        this.person_id = person_id;
    }

    public int getA_id()
    {
        return a_id;
    }

    public void setA_id(int a_id)
    {
        this.a_id = a_id;
    }

    public int getP_id()
    {
        return p_id;
    }

    public void setP_id(int p_id)
    {
        this.p_id = p_id;
    }

    public int getH_id()
    {
        return h_id;
    }

    public void setH_id(int h_id)
    {
        this.h_id = h_id;
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
                "a_id = " + a_id + ", " +
                "p_id = " + p_id + ", " +
                "h_id = " + h_id + ", " +
                "fName = " + fName + ", " +
                "lName = " + lName + ", " +
                "email = " + email + ")";
    }
}
