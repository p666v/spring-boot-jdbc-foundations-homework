package ru.itsjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.itsjava.dao.UserDao;
import ru.itsjava.domain.User;

import java.sql.SQLException;
import java.util.List;

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

        System.out.println("userDao.findById(2L) = " + userDao.findById(2L));

        List<User> allUsers = userDao.listUsers();

        for (User record : allUsers) {
            System.out.print("ID: " + record.getId());
            System.out.print(", Name: " + record.getName());
            System.out.println(", Age: " + record.getAge());
        }

//        Console.main(args);
    }

}
