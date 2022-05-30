package by.it.arsenihlaz.jd02_01;

import java.util.HashMap;
import java.util.Map;

public class CustomerWorker extends Thread implements CustomerAction, ShoppingCardAction {

    private final Customer customer;
    private final Shop shop;

    public CustomerWorker(Customer customer, Shop shop) {
        this.customer = customer;
        this.shop = shop;
    }

    @Override
    public void run() {
        enteredStore();
        takeCart();
        chooseGood();
        // putToCart(Good good);
        goOut();
    }

    @Override
    public void enteredStore() {
        System.out.println(customer + " enter to the " + shop);
    }

    @Override
    public Good chooseGood() {
        System.out.println(customer + " started choosing products");
        int timeout = RandomGenerator.get(500, 2000);
        Timer.sleep(timeout);
        Good good = new Good("bread", 2.2);
        System.out.println(customer + " chose a good " + good);
        System.out.println(customer + " finished choosing goods");
        return good;
    }

    @Override
    public void goOut() {
        System.out.println(customer + " leaves " + shop);
    }

    @Override
    public void takeCart() {
        System.out.println(customer + " take the cart");
    }

    @Override
    public int putToCart(Good good) {
        Map<String, Double> cart = new HashMap<>();
        cart.put(good.getName(), good.getPrice());

        return cart.size();
    }
}