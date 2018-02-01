package com.kee.db.dao;

import com.kee.db.model.LoginQrcode;
import org.apache.ibatis.annotations.Param;
//import org.apache.ibatis.annotations.Param;

/**
 * Created by wosyo on 2018/2/1.
 */
public interface LoginQrcodeDao {

    int insert(LoginQrcode loginQrcode);

    LoginQrcode selectLoginQrcode(@Param("qrcode") String qrcode);

    int update(LoginQrcode loginQrcode);
}
