package facades;

import dtos.PersonDTO;
import entities.Person;

import javax.persistence.EntityManagerFactory;
import utils.EMF_Creator;


public class Populator {
    public static void populate(){
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        PersonFacade fe = PersonFacade.getFacadeExample(emf);
        fe.create(new PersonDTO(new Person("First 1", "Last 1","jej")));
        fe.create(new PersonDTO(new Person("First 2", "Last 2", "ascda55")));
        fe.create(new PersonDTO(new Person("First 3", "Last 3", "77sdffs")));
    }
    
    public static void main(String[] args) {
        populate();
    }
}
