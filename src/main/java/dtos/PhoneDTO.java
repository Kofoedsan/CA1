package dtos;

import entities.Address;
import entities.Person;
import entities.Phone;

import java.util.ArrayList;
import java.util.List;


public class PhoneDTO {

    private int dto_phone_id;
    private int dto_number;
    private Person dto_person;

    public PhoneDTO() {
    }

    public static List<PhoneDTO> getDtos(List<Phone> ph) {
        List<PhoneDTO> adDTO = new ArrayList();
        ph.forEach(a -> adDTO.add(new PhoneDTO(a)));
        return adDTO;
    }

    public PhoneDTO(int dto_number)
    {
        this.dto_number = dto_number;
    }

    public PhoneDTO(Phone a) {
        if (a.getPhone_id() != 0) {
        }
        this.dto_person = a.getPerson();
        this.dto_phone_id = a.getPhone_id();
        this.dto_number = a.getNumber();

    }


    public int getDto_phone_id() {
        return dto_phone_id;
    }

    public void setDto_phone_id(int dto_phone_id) {
        this.dto_phone_id = dto_phone_id;
    }

    public int getDto_number() {
        return dto_number;
    }

    public void setDto_number(int dto_number) {
        this.dto_number = dto_number;
    }

    public Person getDto_person() {
        return dto_person;
    }

    public void setDto_person(Person dto_person) {
        this.dto_person = dto_person;
    }
}
