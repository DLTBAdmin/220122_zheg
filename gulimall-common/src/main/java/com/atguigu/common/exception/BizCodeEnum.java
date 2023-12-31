package com.atguigu.common.exception;

public enum BizCodeEnum {

    UNKNOW_EXCEPTION(10000,"系统位置异常"),
    VAILD_EXCEPTION(10001,"参数格式校验失败"),
    PEODUCT_UP_EXCEPTON(11000, "商品上架异常");

    private int code;
    private String msg;
    BizCodeEnum(int code, String msg){
        this.code = code;
        this.msg = msg;
    }
    public int getCode(){
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
