package by.avdeev.task09.travelvoucher.bean;

import by.avdeev.task09.travelvoucher.bean.exception.TravelVoucherException;

import java.io.Serializable;
import java.util.Objects;

public class TravelVoucher implements Serializable {
    private static final long serialVersionUID = 1L;
    private Type type;
    private Transport transport;
    private Nutrition nutrition;
    private int period;

    public TravelVoucher(Type type, Transport transport, Nutrition nutrition) {
        this.type = type;
        this.transport = transport;
        this.nutrition = nutrition;
    }

    public TravelVoucher(Type type, Transport transport, Nutrition nutrition, int period) {
        this.type = type;
        this.transport = transport;
        this.nutrition = nutrition;
        this.period = period;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Transport getTransport() {
        return transport;
    }

    public void setTransport(Transport transport) {
        this.transport = transport;
    }

    public Nutrition getNutrition() {
        return nutrition;
    }

    public void setNutrition(Nutrition nutrition) {
        this.nutrition = nutrition;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) throws TravelVoucherException {
        if (period < 1) {
            throw new TravelVoucherException();
        }
        this.period = period;
    }

    @Override
    public String toString() {
        return "TravelVoucher{" +
                "type=" + type +
                ", transport=" + transport +
                ", nutrition=" + nutrition +
                ", period=" + period +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TravelVoucher that = (TravelVoucher) o;
        return type == that.type &&
                transport == that.transport &&
                nutrition == that.nutrition &&
                Objects.equals(period, that.period);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, transport, nutrition, period);
    }
}