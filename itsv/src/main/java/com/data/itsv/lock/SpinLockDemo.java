package com.data.itsv.lock;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class SpinLockDemo {

    private static volatile AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public static void myLock(){
        Thread thread = Thread.currentThread();

        System.out.println(thread.getName()+":进来了!!");
        //atomicReference.compareAndSet(null,thread)  如果atomicReference.get()==null 那么就赋值为thread
        while (!atomicReference.compareAndSet(null,thread)){

        }
    }

    public static void myUnLock(){
        Thread thread = Thread.currentThread();

//atomicReference.compareAndSet(thread,null);  如果atomicReference.get()==thread 那么就赋值为null
        atomicReference.compareAndSet(thread,null);

        System.out.println("释放锁");
    }

    public static void main(String[] args) {
        SpinLockDemo spinLockDemo = new SpinLockDemo();

        new Thread(()->{
            spinLockDemo.myLock();
            try {
                System.out.println("AA执行");
                TimeUnit.SECONDS.sleep(5);
                System.out.println("AA结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo.myUnLock();
        },"AA").start();
        new Thread(()->{
            spinLockDemo.myLock();
            try {
                System.out.println("BB执行");
                TimeUnit.SECONDS.sleep(1);
                System.out.println("BB结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo.myUnLock();
        },"BB").start();
    }

}