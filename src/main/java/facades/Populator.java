package facades;

import dtos.PersonDTO;
import entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import utils.EMF_Creator;


public class Populator {
    public static void populate() {
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();


        Person ps1 = new Person("Store", "fede", "nick");
        Person ps2 = new Person("Grimme", "Fede", "Nick");
        Person ps3 = new Person("Negerkongen", "Kongeneger", "negermail@negerland.neger");

        Hobby h1 = new Hobby("svømning", "Svømme","2","5");
        Hobby h2 = new Hobby("Fiskning", "Fiskeri","3","6");

        Phone p1 = new Phone(55);
        Phone p2 = new Phone(66);
        Phone p3 = new Phone(77);

        Address a1 = new Address("Nordlyst 8 ");
        Address a2 = new Address("Nexø");
        Address a3 = new Address("Svaneke");

        Cityinfo c1 = new Cityinfo(3700, "Rønne");
        Cityinfo c2 = new Cityinfo(3780, "Nexø");
        Cityinfo c3 = new Cityinfo(3740, "Svaneke");

        ps1.getHobbies().add(h1);
        ps1.setPhone(p1);
        a1.setCityinfo(c1);

        ps1.setAddress(a1);

        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(ps1);
            em.persist(ps2);
            em.persist(ps3);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public static void main(String[] args) {
        populate();
    }
}
