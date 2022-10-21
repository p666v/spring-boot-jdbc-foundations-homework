package ru.itsjava.dao;

import ru.itsjava.domain.User;

import java.util.List;

public interface UserDao {

    void create(User user);

    void update(User user);

    void delete(User user);

    int count();

    User findById(long id);

    List<User> listUsers();

}
