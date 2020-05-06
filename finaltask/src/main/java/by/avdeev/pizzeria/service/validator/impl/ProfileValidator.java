package by.avdeev.pizzeria.service.validator.impl;

import by.avdeev.pizzeria.service.validator.Validator;

import java.util.Map;

public class ProfileValidator implements Validator {

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
                case "surname":
                    String surname = (String) parameters.get("surname");
                    if (!surname.matches("[A-aZ-z]+")) {
                        isValid = false;
                        invalidParameters.put("surname", "Surname consists incorrect symbols");
                    }
                    break;
                case "email":
                    String email = String.valueOf(parameters.get("email"));
                    if (!email.isEmpty() && !email.matches("\\w+@\\w+")) {
                        isValid = false;
                        invalidParameters.put("email", "Email consists incorrect symbols");
                    }
                case "phone":
                    String phone = (String) parameters.get("phone");
                    if (!phone.matches("\\+\\d+")) {
                        isValid = false;
                        invalidParameters.put("phone", "Phone consists incorrect symbols");
                    }
                    break;
                case "address":
                    String address = (String) parameters.get("address");
                    if (!address.matches("\\w+")) {
                        isValid = false;
                        invalidParameters.put("address", "Address consists incorrect symbols");
                    }
                    break;
            }
        }
        return isValid;
    }
}
