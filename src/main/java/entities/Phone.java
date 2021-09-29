package entities;

import javax.persistence.*;

@Entity
public class Phone
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "p_id", nullable = false)
    private int p_id;
    private int number;
    private int person_id;

    public Phone()
    {
    }

    public Phone(int number)
    {
        this.number = number;
    }

    public int getNumber()
    {
        return number;
    }

    public void setNumber(int number)
    {
        this.number = number;
    }

    public int getPerson_id()
    {
        return person_id;
    }

    public void setPerson_id(int person_id)
    {
        this.person_id = person_id;
    }

    public Integer getP_id()
    {
        return p_id;
    }



    public void setP_id(int p_id)
    {
        this.p_id = p_id;
    }

    @Override
    public String toString()
    {
        return getClass().getSimpleName() + "(" +
                "p_id = " + p_id + ", " +
                "number = " + number + ", " +
                "person_id = " + person_id + ")";
    }
}
