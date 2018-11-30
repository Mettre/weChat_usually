package com.mettre.usually.enum_;

public enum GenderEnum {


    MAN("男"), WOMEN("女");


    public String gender;

    GenderEnum(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public static boolean contains(String gender) {
        for (GenderEnum typeEnum : GenderEnum.values()) {
            if (typeEnum.name().equals(gender)) {
                return true;
            }
        }
        return false;
    }
}
