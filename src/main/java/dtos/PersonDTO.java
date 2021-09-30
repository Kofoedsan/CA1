package dtos;

import entities.Address;
import entities.Hobby;
import entities.Person;

import java.util.ArrayList;
import java.util.List;


public class PersonDTO {
    private int dto_id;
    private String dto_fName;
    private String dto_lName;
    private String dto_email;
    private int dto_phone;
    private int dto_zipCode;
    private String dto_street;
    private String dto_city;
//    private List<Hobby> dto_hobbies = new ArrayList<>();
    private List<HobbyDTO> dto_hobbies;

    public static List<PersonDTO> getDtos(List<Person> lp) {
        List<PersonDTO> pdtos = new ArrayList();
        lp.forEach(p -> pdtos.add(new PersonDTO(p)));
        return pdtos;
    }

    public PersonDTO(Person p) {
        if (p.getPerson_id() != 0){
            this.dto_id = p.getPerson_id();
        }
        this.dto_fName = p.getfName();
        this.dto_lName = p.getlName();
        this.dto_email = p.getEmail();
        this.dto_phone = p.getPhone().getNumber();
        this.dto_street = p.getAddress().getStreet();
        this.dto_city = p.getAddress().getCityinfo().getCity();
        this.dto_zipCode = p.getAddress().getCityinfo().getZipCode();
        this.dto_hobbies = HobbyDTO.getDtos(p.getHobbies());
    }

    public int getDto_phone() {
        return dto_phone;
    }

    public void setDto_phone(int dto_phone) {
        this.dto_phone = dto_phone;
    }

    public int getDto_zipCode() {
        return dto_zipCode;
    }

    public void setDto_zipCode(int dto_zipCode) {
        this.dto_zipCode = dto_zipCode;
    }

    public String getDto_street() {
        return dto_street;
    }

    public void setDto_street(String dto_street) {
        this.dto_street = dto_street;
    }

    public String getDto_city() {
        return dto_city;
    }

    public void setDto_city(String dto_city) {
        this.dto_city = dto_city;
    }

    public List<HobbyDTO> getDto_hobbies() {
        return dto_hobbies;
    }

    public void setDto_hobbies(List<HobbyDTO> dto_hobbies) {
        this.dto_hobbies = dto_hobbies;
    }

    public long getDto_id() {
        return dto_id;
    }

    public void setDto_id(int dto_id) {
        this.dto_id = dto_id;
    }

    public String getDto_fName() {
        return dto_fName;
    }

    public void setDto_fName(String dto_fName) {
        this.dto_fName = dto_fName;
    }

    public String getDto_lName() {
        return dto_lName;
    }

    public void setDto_lName(String dto_lName) {
        this.dto_lName = dto_lName;
    }

    public String getDto_email() {
        return dto_email;
    }

    public void setDto_email(String dto_email) {
        this.dto_email = dto_email;
    }

    @Override
    public String toString() {
        return "PersonDTO{" +
                "dto_id=" + dto_id +
                ", dto_fName='" + dto_fName + '\'' +
                ", dto_lName='" + dto_lName + '\'' +
                ", dto_email='" + dto_email + '\'' +
                '}';
    }
}
