package com.mettre.usually.enum_;

public enum StateEnum {

    SUBMITTED("已提交"), ADOPTED("已采纳"), RESOLVED("已解决");

    public String state;

    StateEnum(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public static boolean contains(String state) {
        for (StateEnum typeEnum : StateEnum.values()) {
            if (typeEnum.name().equals(state)) {
                return true;
            }
        }
        return false;
    }
}
