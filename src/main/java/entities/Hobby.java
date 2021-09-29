package entities;

import javax.persistence.*;

@Entity
public class Hobby
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int h_id;
    private String name;
    private String description;
    private int person_id;

    public Hobby()
    {
    }

    public Hobby(String name, String description)
    {
        this.name = name;
        this.description = description;
    }

    public int getH_id()
    {
        return h_id;
    }

    public void setH_id(int h_id)
    {
        this.h_id = h_id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public int getPerson_id()
    {
        return person_id;
    }

    public void setPerson_id(int person_id)
    {
        this.person_id = person_id;
    }

    @Override
    public String toString()
    {
        return getClass().getSimpleName() + "(" +
                "h_id = " + h_id + ", " +
                "name = " + name + ", " +
                "description = " + description + ", " +
                "person_id = " + person_id + ")";
    }
}
