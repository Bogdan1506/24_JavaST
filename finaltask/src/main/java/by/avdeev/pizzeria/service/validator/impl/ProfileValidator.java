package by.avdeev.pizzeria.service.validator.impl;

import by.avdeev.pizzeria.service.validator.Validator;

import java.util.Map;

public class ProfileValidator implements Validator {
    private static final String SURNAME = "surname";
    private static final String EMAIL = "email";
    private static final String PHONE = "phone";
    private static final String ADDRESS = "address";

    @Override
    public boolean validate(Map<String, Object> parameters, Map<String, String> invalidParameters) {
        boolean isValid = true;
        for (Map.Entry<String, Object> pair : parameters.entrySet()) {
            switch (pair.getKey()) {
                case "name":
                    String name = (String) parameters.get("name");
                    if (!name.matches("[A-aZ-z]+")) {
                        isValid = false;
                        invalidParameters.put("name", "Name consists incorrect symbols");
                    }
                    break;
                case SURNAME:
                    String surname = (String) parameters.get(SURNAME);
                    if (!surname.matches("[A-aZ-z]+")) {
                        isValid = false;
                        invalidParameters.put(SURNAME, "Surname consists incorrect symbols");
                    }
                    break;
                case EMAIL:
                    String email = String.valueOf(parameters.get(EMAIL));
                    if (!email.isEmpty() && !email.matches("\\w+@\\w+\\.\\w+")) {
                        isValid = false;
                        invalidParameters.put(EMAIL, "Email consists incorrect symbols");
                    }
                    break;
                case PHONE:
                    String phone = (String) parameters.get(PHONE);
                    if (!phone.matches("\\+\\d+")) {
                        isValid = false;
                        invalidParameters.put(PHONE, "Phone consists incorrect symbols");
                    }
                    break;
                case ADDRESS:
                    String address = (String) parameters.get(ADDRESS);
                    if (!address.matches("\\w+")) {
                        isValid = false;
                        invalidParameters.put(ADDRESS, "Address consists incorrect symbols");
                    }
                    break;
            }
        }
        return isValid;
    }
}
