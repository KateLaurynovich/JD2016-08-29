package by.it.tsiamruk.jd02_02;

/**
 * Created by waldemar on 03/10/2016.
 */

public class Buyer extends Thread implements IBuyer, IBacket {
    private int number;

    public Buyer(int number) {
        this.number = number;
        this.setName("Buyer №" + number);
    }

    @Override
    public void run() {
        enterToMarket();
        takeBacked();
        chooseGoods();
        goToQueue();
        goToOut();
    }

    @Override
    public void enterToMarket() {
        System.out.println(this + " зашел в магазин.");
    }

    @Override
    public void chooseGoods() {
        for (int i = 1; i < Helper.rnd(1, 4); i++) {
            Helper.sleep(Helper.rnd(100, 200));
            String goodsName = Goods.random();
            System.out.println(this + " выбрал товары: " + goodsName);
            putGoodsToBacket();
        }

    }

    @Override
    public String toString() {
        return "Buyer №" + number;
        //this.getName();
    }

    @Override
    public void takeBacked() {
        Helper.sleep(Helper.rnd(100, 200));
        System.out.println(this + " взял корзину для продуктов.");
    }

    @Override
    public void putGoodsToBacket() {
        Helper.sleep(Helper.rnd(100, 200));
        System.out.println(this + " кладёт товар в корзину.");
    }

    @Override
    public void goToQueue() {
        synchronized (QueueBuyers.monitorQueueBuyers) {
            QueueBuyers.add(this);
            System.out.println(this + " stand in Queue");
        }
        synchronized (this) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void goToOut() {
        System.out.println(this + " вышел из магазина.");
    }
}
