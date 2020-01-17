package by.avdeev.task10final.calendar.bean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Calendar {
    private List<Date> dates = new ArrayList<>();
    private int year;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<Date> getDates() {
        return dates;
    }

    public void setDates(List<Date> dates) {
        this.dates = dates;
    }

    public boolean isLeap() {
        if (this.year % 4 == 0) {
            return this.year % 100 != 0 || this.year % 400 == 0;
        }
        return false;
    }

    public class Date {
        private int day;
        private int month;
        private int year;
        private Status status;
        private String name;

        public Date(int day, int month, int year) {
            this.day = day;
            this.month = month;
            this.year = year;
            this.status = Status.WORKDAY;
        }

        public Date(int day, int month, int year, Status status, String name) {
            this.day = day;
            this.month = month;
            this.year = year;
            this.status = status;
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Status getStatus() {
            return status;
        }

        public void setStatus(Status status) {
            this.status = status;
        }

        public int getDay() {
            return day;
        }

        public void setDay(int day) {
            this.day = day;
        }

        public int getMonth() {
            return month;
        }

        public void setMonth(int month) {
            this.month = month;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        @Override
        public String toString() {
            return day + " " + month + " " + year + " " + status + " " + name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Date date = (Date) o;
            return day == date.day &&
                    month == date.month &&
                    year == date.year;
        }

        @Override
        public int hashCode() {
            return Objects.hash(day, month, year);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Calendar calendar = (Calendar) o;
        return year == calendar.year &&
                Objects.equals(dates, calendar.dates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dates, year);
    }

    @Override
    public String toString() {
        return dates.toString();
    }

    public static void main(String[] args) {
        System.out.println(LocalDate.now());
    }
}
