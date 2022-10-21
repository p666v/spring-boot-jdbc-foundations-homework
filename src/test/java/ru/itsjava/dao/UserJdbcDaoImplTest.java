package ru.itsjava.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.itsjava.domain.User;

import static org.junit.jupiter.api.Assertions.assertEquals;

@JdbcTest
@Import(UserDaoImpl.class)
public class UserJdbcDaoImplTest {
    private static final String DEFAULT_NAME = "TANOS";
    private static final int DEFAULT_AGE = 100000;

    @Autowired
    private UserDao userDao;

    @Test
    public void shouldHaveCorrectCount() {
        int actualStudentsCount = userDao.count();
        assertEquals(2, actualStudentsCount);
    }

    @Test
    public void shouldHaveCorrectInsert() {
        User expectedStudent = new User(3L, DEFAULT_NAME, DEFAULT_AGE);
        userDao.create(expectedStudent);
        User actualStudent = userDao.findById(3L);

        assertEquals(expectedStudent, actualStudent);
    }

    @Test
    public void shouldHaveCorrectUpdate() {
        User expectedStudent = new User(1L, DEFAULT_NAME, DEFAULT_AGE);
        userDao.update(expectedStudent);
        User actualStudent = userDao.findById(1L);

        assertEquals(expectedStudent, actualStudent);
    }

    @Test
    public void shouldHaveCorrectDelete() {
        User deleteStudent = userDao.findById(1L);
        userDao.delete(deleteStudent);

        assertEquals(userDao.count(), 1);
    }



}
