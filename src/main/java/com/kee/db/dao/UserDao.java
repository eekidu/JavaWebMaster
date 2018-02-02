package com.kee.db.dao;

import com.kee.db.model.User;
import org.apache.ibatis.annotations.Param;

/**
 * Created by wosyo on 2018/1/3.
 */
public interface UserDao {
    
    int inserUser(@Param("user") User user);
}
