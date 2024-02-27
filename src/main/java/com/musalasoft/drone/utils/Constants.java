package com.musalasoft.drone.utils;

import org.jetbrains.annotations.Contract;

public class Constants {

    @Contract(value = "_, null -> false", pure = true)
    public static boolean validatePassword(String password, String cpassword) {
        return password.equals(cpassword);
    }

}
