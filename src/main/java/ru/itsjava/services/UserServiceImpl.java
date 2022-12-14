package ru.itsjava.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itsjava.dao.UserDao;
import ru.itsjava.domain.User;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    @Override
    public void create(User user) {
        long id = userDao.create(user);
        System.out.println("id нового пользователя = "+ id);
    }

    @Override
    public List<User> printAllUsers() {
        return userDao.listUsers();
    }
}
