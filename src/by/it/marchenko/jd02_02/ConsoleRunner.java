package by.it.marchenko.jd02_02;

import by.it.marchenko.jd02_02.models.Store;
import by.it.marchenko.jd02_02.repository.GoodRepo;
import by.it.marchenko.jd02_02.repository.PriceListRepo;
import by.it.marchenko.jd02_02.repository.StockRepo;
import by.it.marchenko.jd02_02.services.StoreWorker;

public class ConsoleRunner {
    public static void main(String[] args) {

        Printer printer = new Printer(System.out);
        Store store = new Store("BestStore", 1);
        StockRepo stockRepo = new StockRepo();
        GoodRepo goodRepo = new GoodRepo();
        PriceListRepo priceRepo = new PriceListRepo();
        StoreWorker storeWorker = new StoreWorker(stockRepo, store, goodRepo, priceRepo, printer);

        storeWorker.start();
    }
}
