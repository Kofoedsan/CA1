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


    @Override
    public String toString()
    {
        return getClass().getSimpleName() + "(" +
                "h_id = " + h_id + ", " +
                "name = " + name + ", " +
                "description = " + description;
    }
}
