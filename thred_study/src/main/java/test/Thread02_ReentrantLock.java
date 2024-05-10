package test;

import org.apache.kafka.common.utils.Java;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author liux
 * @version 1.0
 */
public class Thread02_ReentrantLock {

    //公平锁，顺序执行
    private static ReentrantLock reentrantLock = new ReentrantLock(true);

    static int x;
    static Object m = new Object();
    static boolean flag = true;


    //ReentrantLock 基本使用
    public void method13() throws InterruptedException {
        reentrantLock.lock();
        reentrantLock.lockInterruptibly();//可打断锁
        reentrantLock.unlock();

    }

    public void method26() {

        //happens-before 规定了对共享变量的写操作对其它线程的读操作可见，它是可见性与有序性的一套规则总结，抛
        //开以下 happens-before 规则，JMM 并不能保证一个线程对共享变量的写，对于其它线程对该共享变量的读可见
        //线程解锁 m 之前对变量的写，对于接下来对 m 加锁的其它线程对该变量的读可见
        new Thread(() -> {
            synchronized (m) {
                x = 10;
            }
        }, "t1").start();

        new Thread(() -> {
            synchronized (m) {
                System.out.println(x);
            }
        }, "t2").start();
    }

    public void method44() throws InterruptedException {
        Thread t1 = new Thread(()->{
            x = 10;
        },"t1");
        t1.start();
        t1.join();
        System.out.println(x);
    }

    public void method40() throws InterruptedException {
        Thread thread = new Thread("t1") {
            @Override
            public void run() {
//                System.out.println("111");
                while (flag) {
                    //println自带Synnaized方法导致能读到最新的flag
                    System.out.println("t1");
                }
            }
        };

        thread.start();

        Thread.sleep(100);

        flag = false;
    }

    //原子操作API
    public void method72(){
        AtomicInteger atomicInteger = new AtomicInteger(100);

        int i = atomicInteger.addAndGet(-1);

        System.out.println(i+"====="+atomicInteger.toString());
    }

//    下面的代码在运行时，由于 SimpleDateFormat 不是线程安全的 ，
//    有很大几率出现 java.lang.NumberFormatException 或者出现不正确的日期解析结果，

//    思路 - 不可变
//    如果一个对象在不能够修改其内部状态（属性），那么它就是线程安全的，因为不存在并发修改啊！这样的对象在
//    org.apache.kafka.common.utils.Java 中有很多，例如在 Java 8 后，提供了一个新的日期格式化类：DateTimeFormatter
    public void method81(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        DateTimeFormatter sd2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    sdf.parse("1951-04-21");
                } catch (Exception e) {

                }
            }).start();
        }
    }

        public static void main (String[]args) throws InterruptedException {

            Thread02_ReentrantLock thread02_reentrantLock = new Thread02_ReentrantLock();

//        //可重入锁
//        thread02_reentrantLock.method13();
//        thread02_reentrantLock.method26();
//        thread02_reentrantLock.method40();
//        thread02_reentrantLock.method44();
//        thread02_reentrantLock.method72();
        thread02_reentrantLock.method81();

        }
    }
