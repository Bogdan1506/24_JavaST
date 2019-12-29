package by.avdeev.task08.bean;

import by.avdeev.task08.service.PhoneService;
import by.avdeev.task08.service.exception.ServiceException;
import by.avdeev.task08.service.factory.ServiceFactory;

import java.util.Objects;

public class Phone implements Comparable<Phone> {
    private long id;
    private String name;
    private String surname;
    private String patronymic;
    private String address;
    private long creditCard;
    private long debit;
    private long credit;
    private int insideLine;
    private int outsideLine;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(long creditCard) {
        this.creditCard = creditCard;
    }

    public long getDebit() {
        return debit;
    }

    public void setDebit(long debit) {
        this.debit = debit;
    }

    public long getCredit() {
        return credit;
    }

    public void setCredit(long credit) {
        this.credit = credit;
    }

    public int getInsideLine() {
        return insideLine;
    }

    public void setInsideLine(int insideLine) {
        this.insideLine = insideLine;
    }

    public int getOutsideLine() {
        return outsideLine;
    }

    public void setOutsideLine(int outsideLine) {
        this.outsideLine = outsideLine;
    }

    @Override
    public int compareTo(Phone o) {
        return this.getName().toUpperCase().charAt(0) - o.getName().toUpperCase().charAt(0);
    }

    public static class Builder {
        private long id;
        private String name;
        private String surname;
        private String patronymic;
        private String address;
        private long creditCard;
        private long debit;
        private long credit;
        private int insideLine;
        private int outsideLine;

        public Builder(String name, String surname, String patronymic) throws ServiceException {
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            PhoneService service = serviceFactory.getPhoneService();
            this.id = service.countId();
            this.name = name;
            this.surname = surname;
            this.patronymic = patronymic;
        }

        public void setId(long id) {
            this.id = id;
        }

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder setCreditCard(long creditCard) {
            this.creditCard = creditCard;
            return this;
        }

        public Builder setDebit(long debit) {
            this.debit = debit;
            return this;
        }

        public Builder setCredit(long credit) {
            this.credit = credit;
            return this;
        }

        public Builder setInsideLine(int insideLine) {
            this.insideLine = insideLine;
            return this;
        }

        public Builder setOutsideLine(int outsideLine) {
            this.outsideLine = outsideLine;
            return this;
        }

        public Phone build() {
            return new Phone(this);
        }
    }

    private Phone(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.surname = builder.surname;
        this.patronymic = builder.patronymic;
        this.address = builder.address;
        this.creditCard = builder.creditCard;
        this.debit = builder.debit;
        this.credit = builder.credit;
        this.outsideLine = builder.outsideLine;
        this.insideLine = builder.insideLine;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Phone phone = (Phone) o;
        return creditCard == phone.creditCard &&
                debit == phone.debit &&
                credit == phone.credit &&
                insideLine == phone.insideLine &&
                outsideLine == phone.outsideLine &&
                Objects.equals(name, phone.name) &&
                Objects.equals(surname, phone.surname) &&
                Objects.equals(patronymic, phone.patronymic) &&
                Objects.equals(address, phone.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, patronymic, address, creditCard, debit, credit, insideLine, outsideLine);
    }

    @Override
    public String toString() {
        return "Phone{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", address='" + address + '\'' +
                ", creditCard=" + creditCard +
                ", debit=" + debit +
                ", credit=" + credit +
                ", insideLine=" + insideLine +
                ", outsideLine=" + outsideLine +
                '}';
    }
}

