package dtos;

import entities.Person;

import java.util.ArrayList;
import java.util.List;


public class PersonDTO {
    private int dto_id;
    private String dto_fName;
    private String dto_lName;
    private String dto_email;

    public PersonDTO()
    {
    }

    public PersonDTO(String dto_fName, String dto_lName, String dto_email)
    {
        this.dto_fName = dto_fName;
        this.dto_lName = dto_lName;
        this.dto_email = dto_email;
    }

    public static List<PersonDTO> getDtos(List<Person> lp){
        List<PersonDTO> pdtos = new ArrayList();
        lp.forEach(p->pdtos.add(new PersonDTO(p)));
        return pdtos;
    }

    public PersonDTO(Person p)
    {
        if(p.getPerson_id() > 0) {
            this.dto_id = p.getPerson_id(); }
        this.dto_fName = p.getfName();
        this.dto_lName = p.getlName();
        this.dto_email = p.getEmail();
    }


    public long getDto_id()
    {
        return dto_id;
    }

    public void setDto_id(int dto_id)
    {
        this.dto_id = dto_id;
    }

    public String getDto_fName()
    {
        return dto_fName;
    }

    public void setDto_fName(String dto_fName)
    {
        this.dto_fName = dto_fName;
    }

    public String getDto_lName()
    {
        return dto_lName;
    }

    public void setDto_lName(String dto_lName)
    {
        this.dto_lName = dto_lName;
    }

    public String getDto_email()
    {
        return dto_email;
    }

    public void setDto_email(String dto_email)
    {
        this.dto_email = dto_email;
    }

    @Override
    public String toString()
    {
        return "PersonDTO{" +
                "dto_id=" + dto_id +
                ", dto_fName='" + dto_fName + '\'' +
                ", dto_lName='" + dto_lName + '\'' +
                ", dto_email='" + dto_email + '\'' +
                '}';
    }
}
