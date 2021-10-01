package facades;

import dtos.PersonDTO;
import dtos.PersonsDTO;
import entities.Address;
import entities.Cityinfo;
import entities.Person;
import entities.Phone;
import entities.*;


import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

public class PersonFacade implements IPersonFacade {

    private static PersonFacade instance;
    private static EntityManagerFactory emf;

    private PersonFacade() {
    }

    public static PersonFacade getPersonFacadeMethods(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new PersonFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }


    @Override
    public PersonDTO addPerson(PersonDTO p) {
        EntityManager em = emf.createEntityManager();
        Person person = new Person(p.getDto_fName(), p.getDto_lName(), p.getDto_email());
        Phone phone = new Phone(p.getDto_phone());
        Address address = new Address(p.getDto_street());
        Cityinfo cityinfo = new Cityinfo(p.getDto_zipCode(), p.getDto_city());
        address.setCityinfo(cityinfo);
        person.setAddress(address);
        person.setPhone(phone);

//      Hobby hobby = new Hobby("navn1","Wiki1","Cat1","type2");
//      Hobby hobby1 = new Hobby("ad","Wiki1","Cat1","type2");

        List<Hobby> hobbyList = new ArrayList<>();
//      hobbyList.add(hobby);

        person.setHobbies(hobbyList);



        try {
            em.getTransaction().begin();
            try {
                em.persist(person);
            } catch (Exception e) {
                em.merge(person);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new PersonDTO(person);
    }

    public long getPersonCount() {
        EntityManager em = emf.createEntityManager();
        try {
            long renameMeCount = (long) em.createQuery("SELECT COUNT(p) FROM Person p").getSingleResult();
            return renameMeCount;
        } finally {
            em.close();
        }
    }

    @Override
    public PersonDTO deletePerson(int id) {
        EntityManager em = getEntityManager();
        Person p = em.find(Person.class, id);
//        if (p == null)
//            throw new PersonException(404, "Could not delete person with: " + id + " bacause it does not exist");
        try {
            em.getTransaction().begin();
            em.remove(p);
            em.remove(p.getPhone());
            em.remove(p.getAddress());
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new PersonDTO(p);
    }

    @Override
    public PersonDTO getPerson(int id) {
        EntityManager em = emf.createEntityManager();
        return new PersonDTO(em.find(Person.class, id));
    }

    @Override
    public PersonsDTO getAllPersons() {
        EntityManager em = getEntityManager();
        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p", Person.class);
        List<Person> persons = query.getResultList();
        return new PersonsDTO(persons);
    }

    @Override
    public PersonsDTO getAllPersonsWithHobby(String name)
    {

        EntityManager em = getEntityManager();
        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p JOIN p.hobbies h WHERE h.name = :name", Person.class);
        query.setParameter("name", name);
        List<Person> result = query.getResultList();
        System.out.println(result);
        return new PersonsDTO(result);

    }

    @Override
    public PersonsDTO getAllPersonsLivingInCity(int id) {
            EntityManager em = getEntityManager();
            TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p JOIN p.address.cityinfo a WHERE a.zipCode = :id ", Person.class);
            query.setParameter("id", id);
            List<Person> result = query.getResultList();
            System.out.println(result);
            return new PersonsDTO(result);

    }


    @Override
    public PersonsDTO getAllPhonesFromPersonWithHobby(int id) {
        return null;
    }

    @Override
    public PersonDTO updatePerson(PersonDTO p) {
        return null;
    }
}
