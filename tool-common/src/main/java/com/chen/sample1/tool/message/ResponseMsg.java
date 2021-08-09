package com.chen.sample1.tool.message;


import cn.hutool.json.JSONObject;
import com.chen.sample1.tool.constant.AliErrorCodeEnum;

import java.io.Serializable;

/**
 * 统一响应消息对象
 * @author ChenTian
 **/

public class ResponseMsg implements Serializable {
    /** 返回结果  */
    private String code = AliErrorCodeEnum.SUCCESS.getCode();
    /** 返回结果描述 */
    private String description = AliErrorCodeEnum.SUCCESS.getDescription();
    /** 错误信息 */
    private String errorMessage;
    /** 返回结果数据，根据不同接口获得对应JSON数据结果 */
    private Object data;

    public ResponseMsg() {
    }

    public ResponseMsg(AliErrorCodeEnum codeEnum) {
        this.code = codeEnum.getCode();
        this.description = codeEnum.getDescription();
        this.data = new JSONObject();
    }

    public ResponseMsg(AliErrorCodeEnum codeEnum, String errorMessage) {
        this(codeEnum);
        this.errorMessage = errorMessage;
    }

    public ResponseMsg(AliErrorCodeEnum codeEnum, String errorMessage, Object data) {
        this(codeEnum,errorMessage);
        this.data = data;
    }

    /**
     * 校验结果对象是否成功
     * @date 2019/11/4
     */
    public static boolean checkSuccess(ResponseMsg responseMsg){
        if(responseMsg == null){
            return false;
        }
        if(AliErrorCodeEnum.SUCCESS.getCode().equals(responseMsg.getCode())){
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
    public static ResponseMsg createFailResponse(AliErrorCodeEnum codeEnum){
        return new ResponseMsg(codeEnum);
    }
    /**
     * 创建一个失败应答对象
     * @date 2019/11/5
     */
    public static ResponseMsg createFailResponse(AliErrorCodeEnum codeEnum,String errorMessage){
        return new ResponseMsg(codeEnum, errorMessage);
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
