package test;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import static java.lang.Thread.sleep;

/**
 * @author liux
 * @version 1.0
 */
@Data
public class Thread01_Create {

    static Integer i = 0;
    static Integer j = 0;

    public void method1() {
        // 构造方法的参数是给线程指定名字，推荐
        Thread t1 = new Thread("t1") {
            @Override
            // run 方法内实现了要执行的任务
            public void run() {
                System.out.println("->1");
            }
        };
        t1.start();
    }

    public void method2() {
        Runnable runnable = () -> {
            // 要执行的任务
            System.out.println("-->2");
        };

        Runnable b2 = () -> System.out.println("2");

        // 创建线程对象
        Thread t = new Thread(runnable);
        t.start();
    }

    public void method3() throws ExecutionException, InterruptedException {
        // 创建任务对象
        FutureTask<Integer> task3 = new FutureTask<>(() -> {
            System.out.println("--->3");
            return 100;
        });
// 参数1 是任务对象; 参数2 是线程名字，推荐
        new Thread(task3, "t3").start();
// 主线程阻塞，同步等待 task 执行完毕的结果
        Integer result = task3.get();

        System.out.println(result);

    }

    public void method59() throws Exception {
        Thread t1 = new Thread(() -> {
            try {
                sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i=10;
        });
        t1.start();
        t1.join();
        System.out.println(i);
    }

    public void method76() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i = 10;
        });
        Thread t2 = new Thread(() -> {
            try {
                sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i = 20;
        });
        long start = System.currentTimeMillis();
        t1.start();
        t2.start();
        t2.join();
        t1.join();
        System.out.println(i);
    }

    public void method101() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                j++;
            }
        }, "t1");
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                j--;
            }
        }, "t2");
        t1.start();
        t2.start();
        System.out.println(j);
    }

    public void method119(){
        List<Thread> list = new ArrayList<>();
        for (int j = 0; j < 2; j++) {
            Thread thread = new Thread(() -> {
                for (int k = 0; k < 5000; k++) {
                    synchronized (this.getClass()) {
                        i++;
                    }
                }
            }, "" + j);
            list.add(thread);
        }
        list.forEach(Thread::start);
        list.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(i);
    }

    public static void main(String[] args) throws Exception {

        Thread01_Create thred_01_create = new Thread01_Create();
        //-------------创建线程
//        thred_01_create.method1();
//        thred_01_create.method2();
//        thred_01_create.method3();

        //------------测试join
//        thred_01_create.method59();
//        thred_01_create.method76();
//            thred_01_create.method101();
//            thred_01_create.method119();

    }

}
