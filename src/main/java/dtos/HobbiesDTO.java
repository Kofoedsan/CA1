package dtos;

import entities.Hobby;

import java.util.ArrayList;
import java.util.List;

public class HobbiesDTO {

    List<HobbyDTO> all = new ArrayList();

    public HobbiesDTO(List<Hobby> hobbiEntity)
    {
        for (Hobby p : hobbiEntity)
        {
            all.add(new HobbyDTO(p));
        }
    }

    @Override
    public String toString() {
        return "HobbiesDTO{" +
                "all=" + all +
                '}';
    }

    // Test only
    public int getSize() {
        return all.size() ;
    }

}
