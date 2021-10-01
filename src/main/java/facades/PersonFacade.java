package facades;

import dtos.*;
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
    public PersonDTO addPerson(PersonDTO p) throws Exception {
        EntityManager em = emf.createEntityManager();
//TODO Lav check for at se om personen eksistere på email & check on tlf eksistere.
        Person person = new Person();
        person.setfName(p.getDto_fName());
        person.setlName(p.getDto_lName());
        person.setEmail(p.getDto_email());
        Cityinfo city = new Cityinfo(p.getDto_zipCode(), p.getDto_city());
        Address address = new Address(p.getDto_street());
        address.setCityinfo(city);
        person.setAddress(address);

        if (p.getDto_phones() != null) {
            for (int i = 0; i < p.getDto_phones().size(); i++) {
                if (em.find(Phone.class, p.getDto_phones().get(i).getDto_number()) != null) {
                    throw new Exception();
                } else {
                    List<Phone> phoneList= new ArrayList<>();
                    for (int j = 0; j < p.getDto_phones().size(); j++) {
                        Phone phone=new Phone(p.getDto_phones().get(j).getDto_number(),person);
                        phoneList.add(phone);
                    }
                    person.setPhones(phoneList);
                }
            }
        } else {
            throw new Exception();
        }

        List<Hobby> hobbies = new ArrayList<>();

        if (p.getDto_hobbies() != null) {
            for (int i = 0; i < p.getDto_hobbies().size(); i++) {
                if (em.find(Hobby.class, p.getDto_hobbies().get(i).getDto_name()) != null) {
                    Hobby h = (em.find(Hobby.class, p.getDto_hobbies().get(i).getDto_name()));
                    hobbies.add(h);
                }
            }
            person.setHobbies(hobbies);
        } else {
            throw new Exception();
        }

        try {
            em.getTransaction().begin();
            em.persist(person);
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
            em.remove(p.getPhones());
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
    public PersonsDTO getAllPersonsWithHobby(String name) {

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
