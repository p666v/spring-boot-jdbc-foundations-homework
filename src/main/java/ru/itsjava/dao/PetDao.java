package ru.itsjava.dao;

import ru.itsjava.domain.Pet;


import java.util.List;

public interface PetDao {

    long create(Pet pet);

    void delete(Pet pet);

    Pet findByBreed(String breed);

    List<Pet> printAllPets();

    int count();
}
