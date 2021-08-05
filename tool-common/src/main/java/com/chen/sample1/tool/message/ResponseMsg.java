package com.chen.sample1.tool.message;


import cn.hutool.json.JSONObject;

import java.io.Serializable;

/**
 * 统一响应消息对象
 * @author ChenTian
 **/

public class ResponseMsg implements Serializable {
    /** 返回结果  */
    private int statusCode = MsgCode.Success.value();
    /** 返回结果描述 */
    private String message = MsgCode.Success.desc();
    /** 返回结果数据，根据不同接口获得对应JSON数据结果 */
    private Object data;

    public ResponseMsg() {
    }

    public ResponseMsg(MsgCode msgCode) {
        this.statusCode = msgCode.value();
        this.message = msgCode.desc();
        this.data = new JSONObject();
    }

    public ResponseMsg(int code, String message) {
        this.statusCode = code;
        this.message = message;
    }

    public ResponseMsg(int code, Object data, String message) {
        this.statusCode = code;
        this.message = message;
        this.data = data;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    /**
     * 校验结果对象是否成功
     * @date 2019/11/4
     */
    public static boolean checkSuccess(ResponseMsg responseMsg){
        if(responseMsg==null){
            return false;
        }
        if(responseMsg.getStatusCode()==MsgCode.Success.value()){
            return true;
        }
        return false;
    }

    /**
     * 创建一个成功应答对象
     * @date 2019/11/5
     */
    public static ResponseMsg createSuccessResponse(){
        ResponseMsg responseMsg = new ResponseMsg();
        return responseMsg;
    }
    /**
     * 创建一个成功应答对象
     * @date 2019/11/5
     */
    public static ResponseMsg createSuccessResponse(Object data){
        ResponseMsg responseMsg = new ResponseMsg();
        responseMsg.setData(data);
        return responseMsg;
    }

    /**
     * 创建一个失败应答对象
     * @date 2019/11/5
     */
    public static ResponseMsg createFailResponse(MsgCode msgCode){
        return new ResponseMsg(msgCode.value(), msgCode.desc());
    }
    /**
     * 创建一个失败应答对象
     * @date 2019/11/5
     */
    public static ResponseMsg createFailResponse(MsgCode msgCode,String errMsg){
        return new ResponseMsg(msgCode.value(), errMsg);
    }
}
