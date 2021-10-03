package facades;

import entities.Person;
import entities.Phone;
import org.junit.jupiter.api.*;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PersonFacadeTest
{

    private static EntityManagerFactory emf;
    private static PersonFacade facade;
    Person person1;

    public PersonFacadeTest()
    {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = PersonFacade.getPersonFacadeMethods(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the code below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            person1 = new Person("René", "Andersen", "Reneandersen@hotmail.com");

            em.getTransaction().begin();
            em.createNamedQuery("Person.deleteAllRows").executeUpdate();
            em.persist(person1);
            em.persist(new Person("Nicklas", "Nivk", "sfsd@dvcs"));
            em.persist(new Person("simon", "sddd", "adad5ad"));

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    // TODO: Delete or change this method
    @Test
    public void testAFacadeMethod() throws Exception {
        assertEquals(3, facade.getPersonCount(), "Expects two rows in the database");
    }

    @Test
    void getPersonFacadeMethods()
    {
    }

    @Test
    void addPerson()
    {
    }

    @Test
    void getPersonCount()
    {
    }

    @Test
    void deletePerson()
    {
        int expected = person1.getPerson_id();
        int actual = facade.deletePerson(person1.getPerson_id()).getDto_id();
        assertEquals(expected, actual);
    }

    @Test
    void getPerson()
    {
        int expected = person1.getPerson_id();
        int actual = facade.getPerson(person1.getPerson_id()).getDto_id();
        assertEquals(expected, actual);
    }

    @Test
    void getAllPersons()
    {
    }

    @Test
    void getAllPersonsWithHobby()
    {
    }

    @Test
    void getAllPersonsLivingInCity()
    {
    }

    @Test
    void getAllPhonesFromPersonWithHobby()
    {
    }

    @Test
    void updatePerson()
    {
    }

    @Test
    void getPersonByPhone()
    {
    }
}