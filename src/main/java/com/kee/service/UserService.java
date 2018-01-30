package com.kee.service;

import com.kee.db.dao.UserDao;
import com.kee.db.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by kee on 2018/1/9.
 */
@Service
public class UserService {
    @Autowired
    UserDao userDao;

    @Transactional
    public void insertSomeUser(int count) {
        for (int i = 0; i < count; i++) {
            User user = new User();
            user.setName("haikuan" + i);
            userDao.inserUser(user);

        }
    }
}
