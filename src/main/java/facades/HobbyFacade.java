package facades;

import dtos.HobbiesDTO;
import dtos.HobbyDTO;
import entities.Hobby;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class HobbyFacade implements IHobbyFacade {

    private static HobbyFacade instance;
    private static EntityManagerFactory emf;

    private HobbyFacade() {}

    public static HobbyFacade getHobbyFacadeMethods(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new HobbyFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public long hobbyAmount(){
        EntityManager em = emf.createEntityManager();
        try{
            long renameMeCount = (long)em.createQuery("SELECT COUNT(p) FROM Person p").getSingleResult();
            return renameMeCount;
        }finally{
            em.close();
        }
    }

    @Override
    public HobbyDTO addHobby(HobbyDTO hdto) {
        return null;
    }

    public HobbiesDTO getAllHobbies() {
        EntityManager em = getEntityManager();
        TypedQuery <Hobby> query = em.createQuery("SELECT h FROM Hobby h", Hobby.class);
        List<Hobby> hobbies = query.getResultList();
        return new HobbiesDTO(hobbies);
    }

    @Override
    public HobbyDTO removeHobby(int id) {
        return null;
    }

    @Override
    public HobbyDTO updateHobby(HobbyDTO hdto) {
        return null;
    }
}
