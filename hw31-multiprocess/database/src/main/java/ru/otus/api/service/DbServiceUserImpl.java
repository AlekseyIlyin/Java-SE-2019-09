package ru.otus.api.service;

import ru.otus.api.dao.UserDao;
import ru.otus.models.User;

import java.util.List;
import java.util.Optional;

public class DbServiceUserImpl implements DBServiceUser {
    private final UserDao userDao;

    public DbServiceUserImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    public Long saveUser(User user) {
        return userDao.saveUser(user);
    }


    public Optional<User> getUser(long id) {
        Optional<User> userOptional = userDao.findUserById(id);

        return userOptional;
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return userDao.findByLogin(login);
    }
}
