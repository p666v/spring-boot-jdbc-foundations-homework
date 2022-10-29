package ru.itsjava.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.itsjava.domain.Pet;

import static org.junit.jupiter.api.Assertions.assertEquals;

@JdbcTest
@Import(PetDaoImpl.class)
public class PetJdbcDaoImplTest {
    private static final String DEFAULT_BREED = "hamster";
    private static final String DEFAULT_DELETE = "Cat";

    @Autowired
    private PetDao petDao;

    @DisplayName("корректный метод добавления питомца в БД")
    @Test
    public void shouldHaveCorrectInsert() {
        Pet expectedPet = new Pet(DEFAULT_BREED);
        petDao.create(expectedPet);
        Pet actualPet = petDao.findByBreed(DEFAULT_BREED);

        assertEquals(expectedPet.getBreed(), actualPet.getBreed());
    }

    @DisplayName("корректный метод удаления питомца")
    @Test
    public void shouldHaveCorrectDelete() {
        Pet deletePet = petDao.findByBreed(DEFAULT_DELETE);
        petDao.delete(deletePet);

        assertEquals(petDao.count(), 1);
    }

    @DisplayName("корректный метод учёта количества питомцев")
    @Test
    public void shouldHaveCorrectCount() {
        int actualPetsCount = petDao.count();
        assertEquals(2, actualPetsCount);
    }

}
