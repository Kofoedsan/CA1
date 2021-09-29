package facades;

import dtos.RenameMeDTO;
import entities.Person;

import javax.persistence.EntityManagerFactory;
import utils.EMF_Creator;


public class Populator {
    public static void populate(){
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        FacadeExample fe = FacadeExample.getFacadeExample(emf);
        fe.create(new RenameMeDTO(new Person("First 1", "Last 1")));
        fe.create(new RenameMeDTO(new Person("First 2", "Last 2")));
        fe.create(new RenameMeDTO(new Person("First 3", "Last 3")));
    }
    
    public static void main(String[] args) {
        populate();
    }
}
