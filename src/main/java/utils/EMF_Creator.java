package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EMF_Creator {

    public static void startREST_TestWithDB() {
        System.setProperty("IS_INTEGRATION_TEST_WITH_DB", "testing");
    }


    public static void endREST_TestWithDB() {
        System.clearProperty("IS_INTEGRATION_TEST_WITH_DB");
    }

    public static EntityManagerFactory createEntityManagerFactory() {
        return createEntityManagerFactory(false);
    }

    public static EntityManagerFactory createEntityManagerFactoryForTest() {
        return createEntityManagerFactory(true);
    }

    private static EntityManagerFactory createEntityManagerFactory(boolean isTest) {

             boolean isDeployed = (System.getenv("DEPLOYED") != null);
        if (isDeployed) {
                      String user = System.getenv("USER");
            String pw = System.getenv("PW");
            String dbName = getDbName(); //Gets the database name from pom.xml
            String connection_str = System.getenv("CONNECTION_STR") + dbName;
            Properties props = new Properties();
            props.setProperty("javax.persistence.jdbc.user", user);
            props.setProperty("javax.persistence.jdbc.password", pw);
            props.setProperty("javax.persistence.jdbc.url", connection_str);
            props.setProperty("javax.persistence.jdbc.driver", "com.mysql.cj.jdbc.Driver");

            //Sets the production log-level to show only potential problems
            props.setProperty("eclipselink.logging.level","WARNING");
            props.setProperty("eclipselink.logging.level.sql","WARNING");
            return Persistence.createEntityManagerFactory("pu", props);
        }

        String puName = isTest || System.getProperty("IS_INTEGRATION_TEST_WITH_DB") != null ? "puTest" : "pu"; //Only legal names
        if (puName.equals("puTest")) {
            System.out.println("Using the TEST database via persistence-unit --> puTest ");
        } else {
            System.out.println("Using the DEV database via persistence-unit --> pu ");
        }
        EntityManagerFactory emf = null;
        try {
         emf =  Persistence.createEntityManagerFactory(puName, null);

        } catch (javax.persistence.PersistenceException ex){
            System.out.println("##########################################################");
            System.out.println("######      ERROR Creating a persistence Unit       ######");
            System.out.println("###### Have you started the dev and test databases? ######");
            System.out.println("######          (docker-compose up -d )             ######");
            System.out.println("##########################################################");
            throw ex;
        }
         return emf;
    }

    private static String getDbName() {
        Properties pomProperties;
        InputStream is = EMF_Creator.class.getClassLoader().getResourceAsStream("properties-from-pom.properties");
        pomProperties = new Properties();
        try {
            pomProperties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pomProperties.getProperty("db.name");
    }

}
