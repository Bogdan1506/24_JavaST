package by.avdeev.task10final.gift.bean;

import java.util.Objects;

public class Tub {
    private Size size;
    private Form form;
    private Material material;
    private Color color;

    public enum Color {
        BLACK, BLUE, GREEN, YELLOW, RED, PURPLE, WHITE
    }

    public enum Material {
        CARDBOARD, PLASTIC, METAL, WOOD
    }

    public enum Form {
        CIRCLE, TRIANGLE, RECTANGLE, SQUARE
    }

    public enum Size {
        SMALL(1), MEDIUM(2.5), LARGE(5);

        double capacity;

        Size(double capacity) {
            this.capacity = capacity;
        }

        public double getCapacity() {
            return capacity;
        }
    }

    public Tub(Size size, Form form, Material material, Color color) {
        this.size = size;
        this.form = form;
        this.material = material;
        this.color = color;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tub tub = (Tub) o;
        return size == tub.size &&
                form == tub.form &&
                material == tub.material &&
                color == tub.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, form, material, color);
    }

    @Override
    public String toString() {
        return "Tub{" +
                "size=" + size +
                ", form=" + form +
                ", material=" + material +
                ", color=" + color +
                '}';
    }
}
