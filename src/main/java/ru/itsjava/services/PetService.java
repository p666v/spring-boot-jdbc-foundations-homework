package ru.itsjava.services;

import ru.itsjava.domain.Pet;
import ru.itsjava.domain.User;

import java.util.List;

public interface PetService {
    void create(Pet pet);

    List<Pet> printAllPets();
    Pet findByBreed(String breed);
}
