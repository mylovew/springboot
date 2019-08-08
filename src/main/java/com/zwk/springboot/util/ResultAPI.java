package com.zwk.springboot.util;

import java.io.Serializable;

/**
 * @program: springboot
 * @description: ajax返回对象
 * @author: wkzhang
 * @create: 2019-08-08 10:10
 */
public class ResultAPI implements Serializable {

    private static final long serialVersionUID = -1298944628263977841L;
    //响应业务状态
    private Integer status;
    //相应消息
    private String msg;
    //相应中的数据
    private Object data;

    public ResultAPI() {
        super();
    }

    public ResultAPI(Integer status) {
        this.status = status;
    }

    public ResultAPI(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public ResultAPI(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultAPI{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
