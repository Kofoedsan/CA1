package facades;

import dtos.PersonDTO;
import dtos.PersonsDTO;

public interface IPersonFacade
{

    public PersonDTO addPerson(PersonDTO p);

    public PersonDTO deletePerson(int id);

    public PersonDTO getPerson(int id);

    public PersonDTO updatePerson(PersonDTO p);

    public PersonsDTO getAllPersons();

    public PersonsDTO getAllPersonsWithHobby(int id);

    public PersonsDTO getAllPersonsLivingInCity(int id);

    public PersonsDTO getAllPhonesFromPersonWithHobby(int id);


}
