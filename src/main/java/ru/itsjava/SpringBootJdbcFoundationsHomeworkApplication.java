package ru.itsjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.itsjava.dao.UserDao;

import java.sql.SQLException;

@SpringBootApplication
public class SpringBootJdbcFoundationsHomeworkApplication {

    public static void main(String[] args) throws SQLException {
        ApplicationContext context =
                SpringApplication.run(SpringBootJdbcFoundationsHomeworkApplication.class, args);

        UserDao userDao = context.getBean(UserDao.class);

    }

}
