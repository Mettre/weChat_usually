package com.mettre.usually.enum_;

public enum ResultEnum {


    OK("200", "成功"),
    SINGTIMEOUT("400", "超时"),
    UNREGISTER("E107", "该手机号未注册"),
    REGISTERED("E108", "该手机号已注册"),
    ACCOUNT_PASSWORD_ERROR("E109", "账号或者密码不正确"),
    CATEGORYCREATED("E110", "该分类已创建"),
    NEWSNULL("E111", "新闻不存在"),
    NEWSIDNOTEMPTY("E112", "新闻id为空"),
    COMMENTARYDELETING("E113", "该条评论已被删除，无法评论"),
    DYNAMICTYPE("E114", "资讯类型不能为空"),
    USEREMPTY("E115", "发布失败"),
    IMG_NOT_EMPTY("102", "图片不存在"),
    IMG_QINIUYUN_EMPTY("103", "上传失败，请检查七牛云配置"),
    IMG_FORMAT_ERROR("104", "图片格式不正确"),
    IMG_INSERT_ERROR("102", "图片插入失败"),
    PERMISSION_DENIED("E401", "未登录");

    private String code;
    private String msg;


    ResultEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
