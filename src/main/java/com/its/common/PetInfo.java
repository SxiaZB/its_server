package com.its.common;

public enum PetInfo {
    LEVEL_1(550000, "一线名宠"),
    LEVEL_2(420000, "二线名宠"),
    LEVEL_3(340000, "三线名宠"),
    LEVEL_4(270000, "四线名宠"),
    LEVEL_5(210000, "五线名宠"),
    LEVEL_6(160000, "六线名宠"),
    LEVEL_7(110000, "七线名宠"),
    LEVEL_8(70000, "八线名宠"),
    LEVEL_9(40000, "九线名宠"),
    LEVEL_10(20000, "十线名宠"),
    LEVEL_11(10000, "十一线名宠"),
    LEVEL_12(6000, "十二线名宠"),
    LEVEL_13(3000, "十三线名宠"),
    LEVEL_14(1500, "十四线名宠"),
    LEVEL_15(800, "十五线名宠"),
    LEVEL_16(400, "十六线名宠"),
    LEVEL_17(200, "十七线名宠"),
    LEVEL_18(100, "十八线名宠"),
    LEVEL_0(0, "潜力新宠");


    private PetInfo(int value, String msg) {
        this.value = value;
        this.level = msg;
    }

    int value;
    String level;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public static String getLevel(long value) {
        if (value < LEVEL_18.value) {
            return LEVEL_0.level;
        } else if (value >= LEVEL_18.value && value < LEVEL_17.value) {
            return LEVEL_18.level;
        } else if (value >= LEVEL_17.value && value < LEVEL_16.value) {
            return LEVEL_17.level;
        } else if (value >= LEVEL_16.value && value < LEVEL_15.value) {
            return LEVEL_16.level;
        } else if (value >= LEVEL_15.value && value < LEVEL_14.value) {
            return LEVEL_15.level;
        } else if (value >= LEVEL_14.value && value < LEVEL_13.value) {
            return LEVEL_14.level;
        } else if (value >= LEVEL_13.value && value < LEVEL_12.value) {
            return LEVEL_13.level;
        } else if (value >= LEVEL_12.value && value < LEVEL_11.value) {
            return LEVEL_12.level;
        } else if (value >= LEVEL_11.value && value < LEVEL_10.value) {
            return LEVEL_11.level;
        } else if (value >= LEVEL_10.value && value < LEVEL_9.value) {
            return LEVEL_10.level;
        } else if (value >= LEVEL_9.value && value < LEVEL_8.value) {
            return LEVEL_9.level;
        } else if (value >= LEVEL_8.value && value < LEVEL_7.value) {
            return LEVEL_8.level;
        } else if (value >= LEVEL_7.value && value < LEVEL_6.value) {
            return LEVEL_7.level;
        } else if (value >= LEVEL_6.value && value < LEVEL_5.value) {
            return LEVEL_6.level;
        } else if (value >= LEVEL_5.value && value < LEVEL_4.value) {
            return LEVEL_5.level;
        } else if (value >= LEVEL_4.value && value < LEVEL_3.value) {
            return LEVEL_4.level;
        } else if (value >= LEVEL_3.value && value < LEVEL_2.value) {
            return LEVEL_3.level;
        } else if (value >= LEVEL_2.value && value < LEVEL_1.value) {
            return LEVEL_2.level;
        } else {
            return LEVEL_1.level;
        }
    }
}
