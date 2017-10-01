package com.jchen.javaStu.concurrent.blog.base.eleven;

/**
 * 生产者和消费者问题
 * (01) 生产者仅仅在仓储未满时候生产，仓满则停止生产。
 * (02) 消费者仅仅在仓储有产品时候才能消费，仓空则等待。
 * (03) 当消费者发现仓储没产品可消费时候会通知生产者生产。
 * (04) 生产者在生产出可消费产品时候，应该通知等待的消费者去消费。
 */
public class ThreadTest{
    public static void main(String[] args){
        Depot depot=new Depot(100);
        Producer producer=new Producer(depot);
        Customer customer=new Customer(depot);
        producer.produce(60);
        producer.produce(120);
        customer.consume(90);
        customer.consume(150);
        producer.produce(110);
    }

}

//仓库
class Depot {
    private int capacity;//仓库的容量
    private int size;//仓库的实际容量

    public Depot(int capacity) {
        this.capacity = capacity;
        this.size = 0;
    }

    public synchronized void produce(int val) {
        try {
            int left = val;//left表示想要生产的数量
            while (left > 0) {
                while (size >=capacity) {
                    wait();
                    // 获取“实际生产的数量”(即库存中新增的数量)
                    // 如果“库存”+“想要生产的数量”>“总的容量”，则“实际增量”=“总的容量”-“当前容量”。(此时填满仓库)
                    // 否则“实际增量”=“想要生产的数量”
                    int inc = (size + left) > capacity ? (capacity - size) : left;
                    size += inc;
                    left -= inc;
                    System.out.printf("%s produce(%3d) --> left=%3d, inc=%3d, size=%3d\n",
                            Thread.currentThread().getName(), val, left, inc, size);
                    //通知消费者可以消费了
                    notifyAll();

                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void consume(int val) {
        try {
            // left 表示“客户要消费数量”(有可能消费量太大，库存不够，需多此消费)
            int left = val;
            while (left > 0) {
                // 库存为0时，等待“生产者”生产产品。
                while (size <= 0)
                    wait();
                // 获取“实际消费的数量”(即库存中实际减少的数量)
                // 如果“库存”<“客户要消费的数量”，则“实际消费量”=“库存”；
                // 否则，“实际消费量”=“客户要消费的数量”。
                int dec = (size < left) ? size : left;
                size -= dec;
                left -= dec;
                System.out.printf("%s consume(%3d) <-- left=%3d, dec=%3d, size=%3d\n",
                        Thread.currentThread().getName(), val, left, dec, size);
                notifyAll();
            }
        } catch (InterruptedException e) {
        }
    }

    public String toString() {
        return "capacity:" + capacity + ", actual size:" + size;
    }
}
//生产者
class Producer{
    private Depot depot;

    public Producer(Depot depot){
        this.depot=depot;
    }
    //消费品：新建一个线程想仓库中生产产品
    public void produce(final int val){
        new Thread(){
            public void run(){
                depot.produce(val);
            }
        }.start();
    }
}
//消费者
class Customer{
    private Depot depot;
    public Customer(Depot depot){
        this.depot=depot;
    }
    //消费产品:新建一个线程从仓库中消费产品
    public void consume(final int val){
        new Thread(){
            @Override
            public void run(){
                depot.consume(val);
            }
        }.start();
    }
}
