package com.kee.base.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kee on 2018/1/30.
 */
public class ResultBean<T> {

    public static final int CODE_SUCCESS = 0;

    /**
     * 20502:
     * 2:服务级错误（1为系统级错误）
     * 05:服务模块代码
     * 02:具体错误代码
     */
    private int code;// 20502
    private String msg;
    private T data;
    private Map<String, Object> extra;//附加数据，字段、内容不定


    private ResultBean() {

    }


    public boolean isSuccess() {
        return code == CODE_SUCCESS;
    }

    public static ResultBean getDefaultResultBean() {
        return new ResultBean().setSystemError(CODE_SUCCESS);
    }

    /**
     * 设置错误详细信息
     * 错误类型的来源可以是枚举、静态类或者数据库，在该方法进行实现
     *
     * @return
     */
    public ResultBean setSystemError(int code) {
        String message = ErrorTypeBean.getMessage(code);
        setCode(code).setMsg(message);
        return this;
    }

    /**
     * 添加条目到返回数据体中，返回数据体必须为数组形式
     *
     * @param e
     * @param <E>
     * @return
     */
    public <E> ResultBean addBodyArrayItem(E e) {
        if (data == null) {
            this.data = (T) new ArrayList<E>();
        }
        if (data instanceof List) {
            List<E> data = (List<E>) this.data;
            data.add(e);
        } else {
            throw new RuntimeException("T is not instanceof List");
        }
        return this;
    }

    public <E> ResultBean addBodyArrayAll(List<E> beanList) {
        if (data == null) {
            this.data = (T) new ArrayList<E>();
        }
        if (data instanceof List) {
            List<E> data = (List<E>) this.data;
            data.addAll(beanList);
        } else {
            throw new RuntimeException("T is not instanceof List");
        }
        return this;
    }

    public ResultBean addExtraInfo(String key, Object value) {
        if (extra == null) {
            extra = new HashMap<String, Object>();
        }
        extra.put(key, value);
        return this;
    }

    /*****************************************************************************/

    public int getCode() {
        return code;
    }

    public ResultBean setCode(int code) {
        this.code = code;
        return this;
    }


    public String getMsg() {
        return msg;
    }

    public ResultBean setMsg(String msg) {
        this.msg = msg;
        return this;
    }


    public T getData() {
        return data;
    }

    public ResultBean setData(T data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return "ResultBean{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                ", extra=" + extra +
                '}';
    }
}
