package by.it.kudelko.jd02_01;

public class Shop {

    private final String name;

    public Shop(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Shop: " + name;
    }
}
