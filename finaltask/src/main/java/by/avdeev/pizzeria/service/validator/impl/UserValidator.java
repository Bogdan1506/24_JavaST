package by.avdeev.pizzeria.service.validator.impl;

import by.avdeev.pizzeria.service.validator.Validator;

import java.util.Map;

public class UserValidator implements Validator {
    private final static String LOGIN = "login";
    private final static String PASSWORD = "password";
    private final static String NEW_PASSWORD = "newPassword";

    @Override
    public boolean validate(Map<String, Object> parameters, Map<String, String> invalidParameters) {
        boolean isValid = true;
        for (Map.Entry<String, Object> pair : parameters.entrySet()) {
            switch (pair.getKey()) {
                case LOGIN:
                    String login = String.valueOf(parameters.get(LOGIN));
                    if (!login.matches("\\w+")) {
                        isValid = false;
                        invalidParameters.put(LOGIN, "Login consists incorrect symbols");
                    }
                    break;
                case PASSWORD:
                    String password = String.valueOf(parameters.get(PASSWORD));
                    if (!password.matches("\\w{5,30}")) {
                        isValid = false;
                        invalidParameters.put(PASSWORD, "Password consists incorrect symbols");
                    }
                    if (password.length() > 30 || password.length() < 5) {
                        isValid = false;
                        invalidParameters.put(PASSWORD, "Incorrect new password's size");
                    }
                    break;
                case NEW_PASSWORD:
                    String newPassword = String.valueOf(parameters.get(NEW_PASSWORD));
                    if (!newPassword.matches("\\w{5,30}")) {
                        isValid = false;
                        invalidParameters.put(NEW_PASSWORD, "New password consists incorrect symbols");
                    }
                    if (newPassword.length() > 30 || newPassword.length() < 5) {
                        isValid = false;
                        invalidParameters.put(NEW_PASSWORD, "Incorrect new password's size");
                    }
                    break;
            }
        }
        return isValid;
    }
}
