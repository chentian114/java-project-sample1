package com.chen.sample1.tool.message;

public enum MsgCode {

    /**
     * 操作成功
     */
    Success(200, "操作成功"),
    /**
     * 请求参数错误
     */
    Param_Error(400, "请求参数错误"),

    /**
     * 没有登录
     */
    Not_Login(401, "鉴权失败，请重新登录"),

    /**
     * 非法授权
     */
    UNAUTHORIZED(403, "没有权限访问该功能"),

    /**
     * 失败
     */
    Error(500, "请求失败，请稍后再试");

    MsgCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public String toString() {
        return String.valueOf(code);
    }

    public int value() {
        return code;
    }

    public String desc() {
        return desc;
    }

    private int code;
    private String desc;
}
