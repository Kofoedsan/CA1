package facades;

import dtos.PersonDTO;
import dtos.PersonsDTO;
import errorhandling.PersonException;

public interface IPersonFacade
{

    PersonDTO addPerson(PersonDTO p) throws Exception;

    PersonDTO deletePerson(int id) throws PersonException;

    PersonDTO getPerson(int id) throws PersonException;

    PersonDTO updatePerson(PersonDTO p);

    PersonsDTO getAllPersons();

    PersonsDTO getAllPersonsWithHobby(String name);

    PersonsDTO getAllPersonsLivingInCity(int id);

    PersonsDTO getAllPhonesFromPersonWithHobby(int id);


}
