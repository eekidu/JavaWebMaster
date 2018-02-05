package com.kee.service;

import com.kee.db.dao.LoginQrcodeDao;
import com.kee.db.model.LoginQrcode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by kee on 2018/2/2.
 */
@Service
public class LoginQrcodeService {
    public static final int PRE_QR_VALID_TIME = 1000 * 60;
    @Autowired
    LoginQrcodeDao loginQrcodeDao;

    /**
     * WEB_STEP_1
     * 获取一个二维码
     *
     * @return
     */
    public LoginQrcode createLoginQrcode() {
        System.out.println("LoginQrcodeService = " + Thread.currentThread() + "  ;" + this.toString());
        LoginQrcode loginQrcode = new LoginQrcode();
        loginQrcode.setQrcode(UUID.randomUUID().toString());
        long insert = loginQrcodeDao.insert(loginQrcode);
        if (insert != 1) {
            throw new RuntimeException("数据库异常");
        }
        return loginQrcode;
    }

    /**
     * 查询二维码是否有效；
     * 1.是否存在并在有效期
     * 2.如果超时更新状态
     *
     * @param qrcode
     * @return
     */
    public boolean checkQrValid(String qrcode) throws Exception {
        LoginQrcode loginQrcode = loginQrcodeDao.selectLoginQrcodeByQrcode(qrcode);
        if (loginQrcode == null) {
            throw new RuntimeException("二维码无效");
        }
        if ((new Date().getTime() - loginQrcode.getCrateTime().getTime()) > PRE_QR_VALID_TIME) {
            updateQrState(loginQrcode, QrcodeState.TIMEOUT);
            throw new RuntimeException("二维码已经超时");
        }

        if (loginQrcode.getState() != QrcodeState.CREATE_DEFAULT.getState()) {
            throw new RuntimeException("二维码状态异常");
        }
        return true;
    }


    /**
     * 更新状态
     *
     * @param loginQrcode
     * @param state
     */
    public void updateQrState(LoginQrcode loginQrcode, QrcodeState state) {
        loginQrcode.setState(state.getState());
        loginQrcodeDao.update(loginQrcode);
    }

    public void updateQrState(String qrcode, QrcodeState state) {
        LoginQrcode loginQrcode = loginQrcodeDao.selectLoginQrcodeByQrcode(qrcode);
        updateQrState(loginQrcode, state);
    }


    public static List<LoginQrcode> loginQrcodesUserQrSuccessed = new ArrayList<>();


    public void getUserQrSuccessedLoginQrcode() {
        loginQrcodesUserQrSuccessed = loginQrcodeDao.selectLoginQrcodeByState(QrcodeState.USER_QR_SUCCESS.getState());
    }

    public boolean isInQrSuccessList(String qrcode) {
        getUserQrSuccessedLoginQrcode();
        for (LoginQrcode loginQrcode : loginQrcodesUserQrSuccessed) {
            if (qrcode.equals(loginQrcode.getQrcode())) {
                return true;
            }
        }
        return false;
    }


    public enum QrcodeState {
        TIMEOUT(1),
        CREATE_DEFAULT(0),
        USER_QR_SUCCESS(1),
        RETURN_TO_WEB_SUCCESS(2);
        private int state;

        QrcodeState(int state) {
            this.state = state;
        }

        public int getState() {
            return state;
        }

        public QrcodeState setState(int state) {
            this.state = state;
            return this;
        }
    }
}
