package facades;

import dtos.PersonDTO;
import dtos.PersonsDTO;
import entities.Person;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import utils.EMF_Creator;


public class PersonFacade implements IPersonFacade
{

    private static PersonFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
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
        fe.getAll().forEach(dto->System.out.println(dto));
    }

    public PersonDTO create(PersonDTO pdto){
        Person p = new Person(pdto.getDto_fName(), pdto.getDto_lName(), pdto.getDto_email());
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new PersonDTO(p);
    }
    public PersonDTO getById(long id){
        EntityManager em = getEntityManager();
        return new PersonDTO(em.find(Person.class, id));
    }

    public long getRenameMeCount(){
        EntityManager em = getEntityManager();
        try{
            long renameMeCount = (long)em.createQuery("SELECT COUNT(p) FROM Person p").getSingleResult();
            return renameMeCount;
        }finally{
            em.close();
        }
    }

    public List<PersonDTO> getAll(){
        EntityManager em = emf.createEntityManager();
        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p", Person.class);
        List<Person> rms = query.getResultList();
        return PersonDTO.getDtos(rms);
    }



    @Override
    public PersonDTO addPerson(PersonDTO p)
    {
        Person person = new Person(p.getDto_fName(), p.getDto_lName(), p.getDto_email());
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
    public PersonDTO deletePerson(int id)
    {
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
    public PersonDTO getPerson(int id)
    {
       EntityManager em = getEntityManager();
       return new PersonDTO(em.find(Person.class, id));
    }

    @Override
    public PersonDTO updatePerson(PersonDTO p)
    {
        return null;
    }

    @Override
    public PersonsDTO getAllPersons()
    {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p", Person.class);
        List<Person> persons = query.getResultList();
        return new PersonsDTO(persons);
    }

    @Override
    public PersonsDTO getAllPersonsWithHobby(int id)
    {
        return null;
    }

    @Override
    public PersonsDTO getAllPersonsLivingInCity(int id)
    {
        return null;
    }

    @Override
    public PersonsDTO getAllPhonesFromPersonWithHobby(int id)
    {
        return null;
    }
}