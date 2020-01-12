package by.avdeev.task10final.calendar.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Calendar {
    private List<Date> dates = new ArrayList<>();
    private Months month;

    public Months getMonth() {
        return month;
    }

    public void setMonth(Months month) {
        this.month = month;
    }

    public List<Date> getDates() {
        return dates;
    }

    public void setDates(List<Date> dates) {
        this.dates = dates;
    }

    public class Date {
        private int day;
        private int month;
        private int year;
        private Status status;

        public Date(int day, int month, int year) {
            this.day = day;
            this.month = month;
            this.year = year;
            this.status = Status.WORKDAY;
        }

        public Date(int day, int month, int year, Status status) {
            this.day = day;
            this.month = month;
            this.year = year;
            this.status = status;
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

        public boolean isLeap() {
            if (this.year % 4 == 0) {
                return this.year % 100 != 0 || this.year % 400 == 0;
            }
            return false;
        }

        @Override
        public String toString() {
            return day + " " + month + " " + year + " " + status;
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
        return Objects.equals(dates, calendar.dates) &&
                month == calendar.month;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dates, month);
    }

    @Override
    public String toString() {
        return dates.toString();
    }
}
