package dtos;

import entities.Address;
import entities.Cityinfo;
import entities.Person;
import entities.Phone;

import java.util.ArrayList;
import java.util.List;

public class AddressDTO {

    private int dto_a_id;
    private String dto_street;
    private List<Person> dto_persons;
    private Cityinfo dto_cityinfo;

    public AddressDTO() {
    }


    public static List<AddressDTO> getDtos(List<Address> ad) {
        List<AddressDTO> adDTO = new ArrayList();
        ad.forEach(a -> adDTO.add(new AddressDTO(a)));
        return adDTO;
    }

    public AddressDTO(Address a) {
        if (a.getA_id() != 0){
            this.dto_a_id = a.getA_id();
        }
        this.dto_street = a.getStreet();
        this.dto_persons = a.getPersons();
        this.dto_cityinfo = a.getCityinfo();
    }



    public int getDto_a_id() {
        return dto_a_id;
    }

    public void setDto_a_id(int dto_a_id) {
        this.dto_a_id = dto_a_id;
    }

    public String getDto_street() {
        return dto_street;
    }

    public void setDto_street(String dto_street) {
        this.dto_street = dto_street;
    }

    public List<Person> getDto_persons() {
        return dto_persons;
    }

    public void setDto_persons(List<Person> dto_persons) {
        this.dto_persons = dto_persons;
    }

    public Cityinfo getDto_cityinfo() {
        return dto_cityinfo;
    }

    public void setDto_cityinfo(Cityinfo dto_cityinfo) {
        this.dto_cityinfo = dto_cityinfo;
    }
}