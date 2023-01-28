package com.anatkh.commonUtil.emum;

public enum BizCodeEnum {
//
// 10:通用
//001:参数格式校验
//    11:商品
//12:订单
//    13: 购物车
//    14:物流
    UNKNOWN_EXCEPTION(10000,"系统未知异常"),
    VALID_EXCEPTION(10001,"参数格式校验失败"),
    PRODUCT_UP_EXCEPTION(11000,"尚品上架异常");
    private Integer code;
    private String msg;
     BizCodeEnum(Integer code,String msg){
        this.code=code;
        this.msg=msg;
    }
    public Integer getCode(){
        return this.code;
    }

    public String getMsg(){
        return this.msg;
    }
}
