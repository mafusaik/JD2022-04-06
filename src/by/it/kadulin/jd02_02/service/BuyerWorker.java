package by.it.kadulin.jd02_02.service;

import by.it.kadulin.jd02_02.entity.*;
import by.it.kadulin.jd02_02.repository.PriceListRepo;
import by.it.kadulin.jd02_02.util.RandomGenerator;
import by.it.kadulin.jd02_02.util.Timer;
import by.it.kadulin.jd02_02.interfaces.CustomerAction;
import by.it.kadulin.jd02_02.interfaces.ShoppingCardAction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuyerWorker extends Thread implements CustomerAction, ShoppingCardAction {
    private final Buyer buyer;
    private final Shop shop;
    private Map<String, Double> listSelectedGoods = new HashMap<>();



    private int countGoodsInCart = 0;
    private final List<String> namesOfPriceList = PriceListRepo.getNamesOfPriceList();

    public BuyerWorker(Buyer buyer, Shop shop) {
        this.buyer = buyer;
        this.shop = shop;
//        shop.getManager().customerEnter();
    }

    @Override
    public void run() {
        enteredStore();
        while (buyer.getShoppingCart().getAmountGoods() != buyer.getCountWTB()) {
            chooseGood();
        }
        if (buyer.getShoppingCart().getAmountGoods() != 0) {
            goToQueue();
        }
        goOut();

    }

    @Override
    public void enteredStore() {
        System.out.println(buyer + " enter to the " + shop);
        takeCart();

    }

    @Override
    public Good chooseGood() {
        System.out.println(buyer + " started to choose goods");
        int timeout = (int) (buyer.getMltSpeedOperation() * RandomGenerator.get(500, 2000));
        Timer.sleep(timeout);
        String chosenGoods = namesOfPriceList.get(RandomGenerator.get(namesOfPriceList.size() - 1));
        double price = PriceListRepo.getPrice(chosenGoods);
        Good good = new Good(chosenGoods, price);
        countGoodsInCart = putToCart(good);
        System.out.println(buyer + " choose " + good);
        System.out.println(buyer + " finished to choose goods");
        return good;
    }

    @Override
    public void goOut() {
        shop.getManager().buyerOut();
        System.out.println(buyer + " leaves the " + shop);
        int timeout = (int) (buyer.getMltSpeedOperation() * RandomGenerator.get(100, 300));
        Timer.sleep(timeout);
        if (shop.getQueue().getQueueSize() == 0 && !shop.getManager().isShopOpened()) {
            synchronized (CashierWorker.monitorCashier) {
                CashierWorker.monitorCashier.notifyAll();
            }
        }
    }

    @Override
    public void goToQueue() {

        Queue queue = shop.getQueue();
        synchronized (buyer) {
            System.out.println(buyer + " go to Queue");
            queue.add(buyer);
            if ((queue.cashierInWork() * Cashier.buyersPerCashier < queue.getQueueSize()) && (queue.cashierInWork() < 5)) {
                queue.cashierOpens();
                synchronized (CashierWorker.monitorCashier) {
                    CashierWorker.monitorCashier.notify();
                }
            }
            buyer.setWaiting(true);
            while (buyer.isWaiting()) {
                try {
                    buyer.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println(buyer + " leaves the Queue");
        }

    }

    @Override
    public void takeCart() {
        ShoppingCart shoppingCart = new ShoppingCart();
        int timeout = (int) (buyer.getMltSpeedOperation() * RandomGenerator.get(100, 300));
        Timer.sleep(timeout);
        buyer.setShoppingCart(shoppingCart);
    }

    @Override
    public int putToCart(Good good) {
        buyer.getShoppingCart().addToCart(good);
//        int timeout = RandomGenerator.get(100, 300);
//        Timer.sleep(timeout);
        return buyer.getShoppingCart().getAmountGoods();
    }
}
