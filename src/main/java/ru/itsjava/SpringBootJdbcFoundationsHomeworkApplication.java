package ru.itsjava;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.itsjava.dao.UserDao;
import ru.itsjava.domain.User;

import java.sql.SQLException;

@SpringBootApplication
public class SpringBootJdbcFoundationsHomeworkApplication {

    public static void main(String[] args) throws SQLException {
        ApplicationContext context =
                SpringApplication.run(SpringBootJdbcFoundationsHomeworkApplication.class, args);

        UserDao userDao = context.getBean(UserDao.class);
        System.out.println("Количество пользователей = " + userDao.count());

        User user = new User("Sveta", 18);
        userDao.create(user);

        System.out.println("Количество пользователей = " + userDao.count());

        User updateUser = new User("TANOS", 100000);
        updateUser.setId(1L);
        userDao.updateUser(updateUser);

        userDao.delete(updateUser);
        System.out.println("Количество пользователей = " + userDao.count());

        Console.main(args);
    }

}
