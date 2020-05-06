package by.avdeev.pizzeria.entity;

import java.io.Serializable;
import java.util.Objects;

public class Profile implements Entity, Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private User user;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String address;

    public Profile() {
    }

    public Profile(int id, User user, String name, String surname, String email, String phone, String address) {
        this.id = id;
        this.user = user;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public Profile(User user, String name, String surname, String email, String phone, String address) {
        this.user = user;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public Profile(int id, String name, String surname, String email, String phone, String address) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public Profile(String name, String surname, String email, String phone, String address) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profile profile = (Profile) o;
        return id == profile.id &&
                Objects.equals(user, profile.user) &&
                Objects.equals(name, profile.name) &&
                Objects.equals(surname, profile.surname) &&
                Objects.equals(email, profile.email) &&
                Objects.equals(phone, profile.phone) &&
                Objects.equals(address, profile.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, name, surname, email, phone, address);
    }

    @Override
    public String toString() {
        return "Profile{" +
                "id=" + id +
                ", user=" + user +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

}
