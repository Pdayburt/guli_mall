package com.anatkh.commonUtil.constant;

import lombok.Data;


public class WareConstant {
    public enum PurchaseStatusEnum{
        CREATED(0,"新建"),
        ASSIGNED(1,"已分配"),
        RECEIVE(2,"已领取"),
        FINISH(3,"已完成"),
        ERROR(4,"错误");

        private Integer code;
        private String msg;
         PurchaseStatusEnum(Integer code,String msg){
            this.code=code;
            this.msg=msg;
        }

        public Integer getCode() {
            return code;
        }
        public String getMsg() {
            return msg;
        }
    }

    public enum PurchaseDetailStatusEnum{
        CREATED(0,"新建"),
        ASSIGNED(1,"已分配"),
        BUY(2,"正在采购"),
        FINISH(3,"已完成"),
        ERROR(4,"采购失败");

        private Integer code;
        private String msg;
        PurchaseDetailStatusEnum(Integer code,String msg){
            this.code=code;
            this.msg=msg;
        }

        public Integer getCode() {
            return code;
        }
        public String getMsg() {
            return msg;
        }
    }
}
