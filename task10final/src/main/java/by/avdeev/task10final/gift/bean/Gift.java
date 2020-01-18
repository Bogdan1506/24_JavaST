package by.avdeev.task10final.gift.bean;

import java.util.List;
import java.util.Objects;

public class Gift {
    private Tub tub;
    private List<Sweets> sweetsList;

    public Gift(Tub tub, List<Sweets> sweetsList) {
        this.tub = tub;
        this.sweetsList = sweetsList;
    }

    public Tub getTub() {
        return tub;
    }

    public void setTub(Tub tub) {
        this.tub = tub;
    }

    public List<Sweets> getSweetsList() {
        return sweetsList;
    }

    public void setSweetsList(List<Sweets> sweetsList) {
        this.sweetsList = sweetsList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gift gift = (Gift) o;
        return Objects.equals(tub, gift.tub) &&
                Objects.equals(sweetsList, gift.sweetsList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tub, sweetsList);
    }

    @Override
    public String toString() {
        return "Gift{" +
                "tub=" + tub +
                ", sweetsList=" + sweetsList +
                '}';
    }
}
