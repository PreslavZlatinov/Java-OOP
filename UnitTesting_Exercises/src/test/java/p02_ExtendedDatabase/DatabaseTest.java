package p02_ExtendedDatabase;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

import java.util.Arrays;

import static org.junit.Assert.*;

public class DatabaseTest {

    private Database database;

    private Person[] people;

    @Before
    public void setUp() throws Exception {

        people = new Person[3];

        Person person = new Person(1,"Nikola");
        Person person1 = new Person(2,"Angel");
        Person person2 = new Person(3,"Preslav");

        people[0] = person;
        people[1] = person1;
        people[2] = person2;

        database = new Database(people);
    }

    @Test
    public void test_CreateConstructor_ShouldCreate_Constructor(){

        Assert.assertEquals(people.length,database.getElements().length);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void test_CreateConstructor_ShouldThrow() throws OperationNotSupportedException {
        Person[] persons = new Person[0];

        Database database1 = new Database(persons);
    }

    @Test
    public void test_Add_ShouldAdd_Person() throws OperationNotSupportedException {

        Person person = new Person(69, "Baba Gusi");

        database.add(person);

        Assert.assertEquals(people.length+1,database.getElements().length);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void test_Add_ShouldThrow_Exception() throws OperationNotSupportedException {

        database.add(null);

    }

    @Test
    public void test_Remove_ShouldRemove_Person() throws OperationNotSupportedException {
        database.remove();
        Assert.assertEquals(people.length-1,database.getElements().length);
        database.remove();
        Assert.assertEquals(people.length-2,database.getElements().length);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void test_Remove_ShouldThrow_Exception() throws OperationNotSupportedException {

        Person[] persons = new Person[1];

        Database database1 = new Database(persons);

        database1.remove();
        database1.remove();
    }

    @Test(expected = OperationNotSupportedException.class)
    public void test_FindByName_ThrowsException_IfUsernameIsNull() throws OperationNotSupportedException {

        database.findByUsername(null);
    }

    @Test
    public void test_FindByName_ShouldFind_Person() throws OperationNotSupportedException {

        Person personExpected = Arrays.stream(database.getElements())
                .filter(person -> person.getUsername().equals("Nikola"))
                .findFirst().orElse(null);

        Person actualPerson = database.findByUsername("Nikola");

        assertEquals(personExpected,actualPerson);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void test_FindByName_ShouldThrow_Exception_ForUniqueNames() throws OperationNotSupportedException {
        Person p1 = new Person(11,"Anatoli");
        Person p2 = new Person(12,"Anatoli");
        database.add(p1);
        database.add(p2);

        database.findByUsername("Anatoli");
    }

    ////

    @Test
    public void test_FindByID_ShouldFind_Person() throws OperationNotSupportedException {

        Person personExpected = Arrays.stream(database.getElements())
                .filter(person -> person.getId() == 1)
                .findFirst().orElse(null);

        Person actualPerson = database.findById(1);

        assertEquals(personExpected,actualPerson);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void test_FindById_ShouldThrow_Exception_ForUniqueNames() throws OperationNotSupportedException {
        Person p1 = new Person(11,"Anatoli");
        Person p2 = new Person(11,"Marti");
        database.add(p1);
        database.add(p2);

        database.findById(11);
    }
}