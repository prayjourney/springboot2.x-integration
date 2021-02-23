package com.zgy.bootintegration.utils;

/**
 * @author: z.g.y
 * @date: 2020/6/5
 * @description:
 */
public class TestThread {
    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.activeCount());
//        run123();
//        while(Thread.activeCount() ==1){
//            System.out.println("ok!");
//            System.out.println("结束");
//        }

        new Thread(() -> {
            try {
                Thread.sleep(5000);
                System.out.println("你好: " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        System.out.println("hello: " + Thread.currentThread().getName());
    }

    public static void run123() throws InterruptedException {
        Thread thread = new Thread();

        thread.sleep(20000);
        thread.start();
    }
}
