package entities;

import javax.persistence.*;

@Entity
public class Address
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "a_id", nullable = false)
    private int a_id;
    private int person_id;
    private int cityInfo_id;
    private String street;




    public int getA_id()
    {
        return a_id;
    }

    public void setA_id(int a_id)
    {
        this.a_id = a_id;
    }

    public int getPerson_id()
    {
        return person_id;
    }

    public void setPerson_id(int person_id)
    {
        this.person_id = person_id;
    }

    public int getCityInfo_id()
    {
        return cityInfo_id;
    }

    public void setCityInfo_id(int cityInfo_id)
    {
        this.cityInfo_id = cityInfo_id;
    }

    public String getStreet()
    {
        return street;
    }

    public void setStreet(String street)
    {
        this.street = street;
    }
}
