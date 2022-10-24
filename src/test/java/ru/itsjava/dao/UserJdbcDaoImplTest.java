package ru.itsjava.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.itsjava.domain.Pet;
import ru.itsjava.domain.User;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@JdbcTest
@Import(UserDaoImpl.class)
public class UserJdbcDaoImplTest {
    private static final String DEFAULT_NAME = "TANOS";
    private static final int DEFAULT_AGE = 100000;
    private static final Pet DEFAULT_PET = new Pet(1L, "Dog");

    @Autowired
    private UserDao userDao;

    @DisplayName("корректный метод учёта количества пользователей")
    @Test
    public void shouldHaveCorrectCount() {
        int actualUsersCount = userDao.count();
        assertEquals(2, actualUsersCount);
    }

    @DisplayName("корректный метод добавления пользователя в БД")
    @Test
    public void shouldHaveCorrectInsert() {
        User expectedUser = new User(DEFAULT_NAME, DEFAULT_AGE, DEFAULT_PET);
        long idFromDB = userDao.create(expectedUser);
        System.out.println(idFromDB);
        User actualUser = userDao.findById(idFromDB);

        assertAll(() -> assertEquals(expectedUser.getName(), actualUser.getName()),
                () -> assertEquals(expectedUser.getAge(), actualUser.getAge()));
    }

    @DisplayName("корректный метод обновления пользователя")
    @Test
    public void shouldHaveCorrectUpdate() {
        User expectedUser = new User(1L, DEFAULT_NAME, DEFAULT_AGE,DEFAULT_PET);
        userDao.update(expectedUser);
        User actualUser = userDao.findById(1L);

        assertEquals(expectedUser, actualUser);
    }

    @DisplayName("корректный метод удаления пользователя")
    @Test
    public void shouldHaveCorrectDelete() {
        User deleteUser = userDao.findById(1L);
        userDao.delete(deleteUser);

        assertEquals(userDao.count(), 1);
    }



}
