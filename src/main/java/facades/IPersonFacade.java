package facades;

import dtos.PersonDTO;
import dtos.PersonsDTO;

public interface IPersonFacade
{

    PersonDTO addPerson(PersonDTO p);

    PersonDTO deletePerson(int id);

    PersonDTO getPerson(int id);

    PersonDTO updatePerson(PersonDTO p);

    PersonsDTO getAllPersons();

    PersonsDTO getAllPersonsWithHobby(int id);

    PersonsDTO getAllPersonsLivingInCity(int id);

    PersonsDTO getAllPhonesFromPersonWithHobby(int id);


}
