//package facades;
//
//import entities.*;
//import utils.EMF_Creator;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.AfterEach;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
////Uncomment the line below, to temporarily disable this test
////@Disabled
//public class FacadeExampleTest
//{
//
//    private static EntityManagerFactory emf;
//    private static PersonFacade facade;
//    Person p1;
//    Person p2;
//
//    public FacadeExampleTest()
//    {
//    }
//
//    @BeforeAll
//    public static void setUpClass()
//    {
//        emf = EMF_Creator.createEntityManagerFactoryForTest();
//        facade = PersonFacade.getPersonFacadeMethods(emf);
//    }
//
//    @AfterAll
//    public static void tearDownClass()
//    {
////        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
//    }
//
//    // Setup the DataBase in a known state BEFORE EACH TEST
//    @BeforeEach
//    public void setUp()
//    {
//        EntityManager em = emf.createEntityManager();
//        p1 = new Person("Førstenavn1", "Sidstenavn1", "Email1");
//        Cityinfo city1 = new Cityinfo(3700, "Rønne");
//        Address address1 = new Address("Kaldbakgade 8");
//        address1.setCityinfo(city1);
//        p1.setAddress(address1);
//
//        p2 = new Person("Førstenavn2", "Sidstenavn2", "Email2");
//        Cityinfo city2 = new Cityinfo(180, "Kaldbak");
//        Address address2 = new Address("SortePerVej 8");
//        address2.setCityinfo(city2);
//        p2.setAddress(address2);
//
//        List<Hobby> testhobby1 = new ArrayList<>();
//        Hobby h1 = (em.find(Hobby.class, "Action figur"));
//        testhobby1.add(h1);
//        p1.setHobbies(testhobby1);
//
//        List<Hobby> testhobby2 = new ArrayList<>();
//        Hobby h2 = (em.find(Hobby.class, "Stand-up"));
//        testhobby2.add(h2);
//        p2.setHobbies(testhobby2);
//
//        List<Phone> testPhone1 = new ArrayList<>();
//        Phone phone1 = new Phone(1111, p1);
//        testPhone1.add(phone1);
//        p1.setPhones(testPhone1);
//
//        List<Phone> testPhone2 = new ArrayList<>();
//        Phone phone2 = new Phone(2222, p2);
//        testPhone2.add(phone2);
//        p2.setPhones(testPhone2);
//
//        try
//        {
//            em.getTransaction().begin();
//            em.createNativeQuery("DELETE from PERSON_PHONE").executeUpdate();
//            em.createNativeQuery("DELETE from PERSON_HOBBY").executeUpdate();
//            em.createNamedQuery("Phone.deleteAllRows").executeUpdate();
//            em.createNamedQuery("Person.deleteAllRows").executeUpdate();
//            em.createNamedQuery("Address.deleteAllRows").executeUpdate();
//            em.persist(p1);
//            em.persist(p2);
//            em.getTransaction().commit();
//        } finally
//        {
//            em.close();
//        }
//    }
//
////    @AfterEach
////    public void tearDown() {
//////        Remove any data after each test was run
////    }
//
//    @Test
//    public void testAFacadeMethod() throws Exception
//    {
//        assertEquals(2, facade.getPersonCount(), "Expects two rows in the database");
//    }
//
//    @Test
//    void getPerson()
//    {
//        int expected = p1.getPerson_id();
//        int actual = facade.getPerson(p1.getPerson_id()).getDto_id();
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    void deletePerson()
//    {
//        int expected = p2.getPerson_id();
//        int actual = facade.deletePerson(p2.getPerson_id()).getDto_id();
//        assertEquals(expected, actual);
//    }
//
//
//}
