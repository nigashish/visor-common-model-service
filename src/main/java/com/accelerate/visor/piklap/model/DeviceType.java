package com.accelerate.visor.piklap.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.accelerate.visor.common.util.EnumUtils;

public enum DeviceType {


    TABLET("Tablet"),
    PIKLAP("PiKlap"),
    LEARNING_ANDROID("LearningAndroid"),
    LEARNING_IOS("LearningIos");


    private final String displayName;

    DeviceType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static DeviceType fromDisplayName(String s) {
        return (DeviceType) EnumUtils.lookupEnum(s, values(), "getDisplayName");
    }

    public static List<DeviceType> getList() {
        return Collections.unmodifiableList(Arrays.asList(DeviceType.values()));
    }
}
