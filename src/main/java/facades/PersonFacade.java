package facades;

import dtos.PersonDTO;
import dtos.PersonsDTO;
import entities.Person;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import utils.EMF_Creator;


public class PersonFacade implements IPersonFacade
{

    private static PersonFacade instance;
    private static EntityManagerFactory emf;

    private PersonFacade() {}

    public static PersonFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new PersonFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public static void main(String[] args) {
        emf = EMF_Creator.createEntityManagerFactory();
        PersonFacade fe = getFacadeExample(emf);
        fe.DONOTUSE().forEach(dto->System.out.println(dto));
    }

//DO NOT USE THESE TWO METHODS
    public List<PersonDTO> DONOTUSE(){
        EntityManager em = emf.createEntityManager();
        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p", Person.class);
        List<Person> rms = query.getResultList();
        return PersonDTO.getDtos(rms);
    }

    public long getRenameMeCount(){
        EntityManager em = emf.createEntityManager();
        try{
            long renameMeCount = (long)em.createQuery("SELECT COUNT(p) FROM Person p").getSingleResult();
            return renameMeCount;
        }finally{
            em.close();
        }
    }
//END OF TWO METHODS

    @Override
    public PersonDTO addPerson(PersonDTO p) {
        Person person = new Person(p.getDto_fName(), p.getDto_lName(), p.getDto_email(),p.getDto_phone(),p.getDto_address(),p.getDto_hobbies());
        EntityManager em = emf.createEntityManager();

//        if (p.getDto_fName() == null || p.getDto_lName() == null || p.getDto_email() == null )
//        {
////            throw new PersonException(400, "to Create a new Person First Name and/or Last Name is must be included");
//        }
        try
        {
            em.getTransaction().begin();
            em.persist(person);
            em.getTransaction().commit();
        } finally
        {
            em.close();
        }
        return new PersonDTO(person);
    }

    @Override
    public PersonDTO deletePerson(int id) {
        EntityManager em = getEntityManager();
        Person p = em.find(Person.class, id);
//        if (p == null)
//            throw new PersonException(404, "Could not delete person with: " + id + " bacause it does not exist");
        try
        {
            em.getTransaction().begin();
            em.remove(p);
            em.getTransaction().commit();
        } finally
        {
            em.close();
        }
        return new PersonDTO(p);
    }

    @Override
    public PersonDTO getPerson(int id) {
        return null;
    }

    @Override
    public PersonDTO updatePerson(PersonDTO p) {
        return null;
    }

    @Override
    public PersonsDTO getAllPersons() {
        EntityManager em = getEntityManager();
        TypedQuery <Person> query = em.createQuery("SELECT p FROM Person p JOIN fetch p.address JOIN fetch p.hobbies JOIN fetch  p.phone", Person.class);
        List<Person> persons = query.getResultList();
        return new PersonsDTO(persons);
    }

    @Override
    public PersonsDTO getAllPersonsWithHobby(int id) {
        return null;
    }

    @Override
    public PersonsDTO getAllPersonsLivingInCity(int id) {
        return null;
    }

    @Override
    public PersonsDTO getAllPhonesFromPersonWithHobby(int id) {
        return null;
    }
}
