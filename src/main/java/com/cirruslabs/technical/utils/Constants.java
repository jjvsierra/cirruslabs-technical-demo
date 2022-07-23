package com.cirruslabs.technical.utils;

public class Constants {
    public static final String CANADA = "Canada";

    public static final String PASSWORD_PATTERN = "^(?=.*[A-Z])(?=.*\\d)(?=.*[_#$%.])[A-Za-z\\d_#$%.]{8,}$";

    public static final String PASSWORD_EMPTY_ERROR_MESSAGE = "Password must not be empty";

    public static final String USERNAME_EMPTY_ERROR_MESSAGE = "Username must not be empty";

    public static final String IP_ADDRESS_EMPTY_ERROR_MESSAGE = "IP address must not be empty";

    public static final String WELCOME_CANADA_MESSAGE = "Hello %s, Welcome from %s, Canada.";

    public static final String USER_NOT_ELIGIBLE_ERROR_MESSAGE = "User is not eligible to register";

    public static final String PASSWORD_PATTERN_ERROR_MESSAGE = "Password must be greater than 8 characters, " +
            "containing at least 1 number, 1 Capitalized letter, 1 special character in this set “_ # $ % .";


}
