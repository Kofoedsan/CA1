package facades;

import dtos.*;
import entities.Address;
import entities.Cityinfo;
import entities.Person;
import entities.Phone;
import entities.*;
import errorhandling.PersonException;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
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
//TODO Lav check for at se om personen eksistere på email
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
                    List<Phone> phoneList = new ArrayList<>();
                    for (int j = 0; j < p.getDto_phones().size(); j++) {
                        Phone phone = new Phone(p.getDto_phones().get(j).getDto_number(), person);
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
    public PersonDTO deletePerson(int id) throws PersonException {
        EntityManager em = getEntityManager();
        Person p = em.find(Person.class, id);

        if (p == null)
            throw new PersonException(404, "Could not delete person with id: " + id + " bacause the person does not exist");
        try {
            em.getTransaction().begin();
            for (int i = 0; i < p.getPhones().size(); i++) {
                em.remove(p.getPhones().remove(i));
            }
            em.remove(p.getAddress());
            em.remove(p);
            em.getTransaction().commit();
        } finally {
            em.clear();
            em.close();
        }
        return new PersonDTO(p);
    }

    @Override
    public PersonDTO getPerson(int id) throws PersonException {

        EntityManager em = emf.createEntityManager();
        System.out.println("Kig HER" + id);
        if (em.find(Person.class, id) == null) {
            throw new PersonException(404, "Could not find person with id: " + id + " bacause the person does not exist");
        }
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
        return new PersonsDTO(result);

    }


    @Override
    public PersonsDTO getAllPhonesFromPersonWithHobby(int id) {
        return null;
    }

    @Override
    public PersonDTO updatePerson(PersonDTO p) {
        EntityManager em = getEntityManager();
        Person person = em.find(Person.class, p.getDto_id());

        person.setfName(p.getDto_fName());
        person.setlName(p.getDto_lName());
        person.setEmail(p.getDto_email());

        Address newadr = em.find(Address.class, person.getAddress().getA_id());
        newadr.setStreet(p.getDto_street());

//         cty = em.find(Cityinfo.class, person.getAddress().getCityinfo().getZipCode());

        Cityinfo cty = new Cityinfo(p.getDto_zipCode(),p.getDto_city());
        newadr.setCityinfo(cty);
        person.setAddress(newadr);


        List<Phone> phoneList = new ArrayList<>();

        for (int i = 0; i < p.getDto_phones().size(); i++) {
            int nr = p.getDto_phones().get(i).getDto_number();
            Phone phone = new Phone(nr, person);
            phoneList.add(phone);
        }
        person.setPhones(phoneList);


        List<Hobby> hobbyList = new ArrayList<>();
        for (int i = 0; i < p.getDto_hobbies().size(); i++) {
            Hobby hobby = em.find(Hobby.class, p.getDto_hobbies().get(i).getDto_name());
            hobby.setName(p.getDto_hobbies().get(i).getDto_name());
            hobbyList.add(hobby);
        }
        person.setHobbies(hobbyList);

        try {
            em.getTransaction().begin();
            em.merge(person);
            em.getTransaction().commit();
        } finally {

            em.close();
        }
        return new PersonDTO(p);
    }


    public PersonDTO getPersonByPhone(int id) {
        EntityManager em = getEntityManager();
        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p JOIN p.phone h WHERE h.number = :name", Person.class);
        query.setParameter("name", id);
        Person person = query.getSingleResult();

        return new PersonDTO(person);

    }


}
