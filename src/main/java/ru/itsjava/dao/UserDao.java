package ru.itsjava.dao;

import ru.itsjava.domain.User;

public interface UserDao {

    void create(User user);

    void updateUser(User user);

    void delete(User user);

    int count();

}
