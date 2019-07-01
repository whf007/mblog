package com.mtons.mblog.enums;

/**
 * Created by Administrator on 2019/5/17.
 */
public enum RadioStatusEnum {
    init("init","初始化"),
    success("success","成功"),
    fail("fail","失败");

    private String code;
    private String message;
    RadioStatusEnum(String code , String message) {
        this.code = code;
        this.message = message;
    }
    /**
     * 通过代码获取枚举项
     * @param code
     * @return
     */
    public static RadioStatusEnum getByCode(String code) {
        if (code == null) {
            return null;
        }

        for (RadioStatusEnum item : RadioStatusEnum.values()) {
            if (item.getCode().equals(code)) {
                return item;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
