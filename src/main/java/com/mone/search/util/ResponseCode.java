package com.mone.search.util;

/**
 * @Author:Drossy
 * @Description
 * @Date:Created in  2019/1/7 13:50
 * @Modified By:
 */
public enum ResponseCode {
    SUCCESS(0,"SUCCESS"),
    ERROR(1,"ERROR"),
    REQUEST_PARAMS_ERROR(1003,"请求参数错误"),
    NOT_WX_LOGIN(1004,"非微信端登录"),
    WX_LOGIN_PARAMS_ERROR(1005,"微信登录code错误"),
    NO_LOGIN(1006,"登录状态失效"),
    STORE_LOGIN_ERROR(2001,"账号或密码错误，请重新输入"),
    STORE_NO_LOGIN(2002,"商户登录状态失效"),
    STORE_NO_AUTHORITY(2003,"没有权限访问"),
    CAPTCHACODE_ERROR(3001,"图片验证码校验失败"),
    PHONE_CODE_ERROR(3002,"获取手机验证码失败"),
    USER_PHONE_IS_VERIFI(3003,"用户手机已绑定"),
    PHONE_IS_REGED(3004,"手机号码已被绑定"),
    REG_PHONE_ERROR(3005,"绑定手机号不一致"),
    PHONE_CODE_VERIFI_ERROR(3006,"手机验证码错误"),
    PHONE_CODE_IS_EXPIRE(3007,"手机验证码过期"),
    PHONE_NO_BINDING(3008,"手机账号没有绑定"),
    PASSWORD_ERROR(3009,"密码错误"),
    COUPON_ISNULL(4001,"优惠券已领取完"),
    COUPON_ISRECIVED(4002,"已领取过该优惠券"),
    COUPON_RECIVED_SUCCESS(4002,"优惠券领取成功"),
    COUPON_IS_USED(4003,"优惠券已使用，请重新下单"),
    COUPON_IS_PASSDATED(4003,"优惠券已过期，请重新下单"),
    CARDNO_IS_ERROR(4004,"您输入的会员卡编号有误"),
    CARD_ISRECIVE(4005,"该会员卡已被绑定"),
    GET_CARD_SUCCESS(4006,"会员卡绑定成功"),
    GOOD_LIMIT(4007,"限购商品已在购物车中或已被购买"),
    ACTIVITY_LIMIT(4008,"已参加过首单立减活动"),
    ADD_CART_SUCCESS(4009,"成功"),
    COUPON_RECIVED_OUT_TIME(4010,"不在抢红包时间范围内"),
    Not_Filled(5000,"未填写"),
    Length_Is_Too_Long(5001,"超过规定长度"),
    Have_Filled_In(5002,"已填写过"),
    Number_ERROR(5003,"数量填写错误"),
    Not_Deleted(5004,"不能删除"),
    Not_In_Time(5005,"不处于未未开始状态");

    private final int code;
    private final String desc;

    ResponseCode(int code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

}
