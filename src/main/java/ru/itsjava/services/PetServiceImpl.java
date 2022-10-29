package ru.itsjava.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itsjava.dao.PetDao;
import ru.itsjava.domain.Pet;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PetServiceImpl implements PetService {
    private final PetDao petDao;

    @Override
    public void create(Pet pet) {
        long id = petDao.create(pet);
        System.out.println("id нового пользователя = "+ id);
    }

    @Override
    public List<Pet> printAllPets() {
        return petDao.printAllPets();
    }

    @Override
    public Pet findByBreed(String breed) {
        return petDao.findByBreed(breed);
    }
}
