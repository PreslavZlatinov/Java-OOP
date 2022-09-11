package petStore;


import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class PetStoreTests {

    @Test
    public void test_GetCount(){
        PetStore petStore = new PetStore();
        Animal animal = new Animal("Pesho",12,12.12);
        petStore.addAnimal(animal);
        assertEquals(1,petStore.getCount());
    }

    @Test
    public void test_FindAllAnimalsWithMaxKilograms(){
        PetStore petStore = new PetStore();
        Animal animal = new Animal("Pesho",12,12.12);
        Animal animal1 = new Animal("Pesho1",13,12.12);
        Animal animal2 = new Animal("Pesho2",14,12.12);

        petStore.addAnimal(animal);
        petStore.addAnimal(animal1);
        petStore.addAnimal(animal2);

        List<Animal> allAnimalsWithMaxKilograms = petStore.findAllAnimalsWithMaxKilograms(12);
        assertEquals(2,allAnimalsWithMaxKilograms.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_Add(){
        PetStore petStore = new PetStore();
        petStore.addAnimal(null);
    }

    @Test
    public void test_GetTheMostExpensiveAnimal(){
        PetStore petStore = new PetStore();
        Animal animal = new Animal("Pesho",12,13.12);
        Animal animal1 = new Animal("Pesho1",13,12.12);
        Animal animal2 = new Animal("Pesho2",14,18.12);

        petStore.addAnimal(animal);
        petStore.addAnimal(animal1);
        petStore.addAnimal(animal2);

        Animal theMostExpensiveAnimal = petStore.getTheMostExpensiveAnimal();

        assertEquals("Pesho2",theMostExpensiveAnimal.getSpecie());
    }

    @Test
    public void test_FindAllAnimalBySpecie(){
        PetStore petStore = new PetStore();
        Animal animal = new Animal("Pesho",12,13.12);
        Animal animal1 = new Animal("Pesho",13,12.12);
        Animal animal2 = new Animal("Pesho",14,18.12);

        petStore.addAnimal(animal);
        petStore.addAnimal(animal1);
        petStore.addAnimal(animal2);

        List<Animal> pesho = petStore.findAllAnimalBySpecie("Pesho");

        assertEquals(3,pesho.size());
    }

}

