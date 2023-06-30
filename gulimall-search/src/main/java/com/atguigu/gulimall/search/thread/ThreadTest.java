package com.atguigu.gulimall.search.thread;


import java.util.concurrent.*;

public class ThreadTest {
    public static ExecutorService executor = Executors.newFixedThreadPool(10);


        /**
         * 1.继承Thread
         *         Thread01 thread = new Thread01();
         *         thread.start();
         * 2.实现Runnable接口
         *         Runnable01 runnable01 = new Runnable01();
         *         new Thread(runnable01).start();
         * 3.实现Callable接口，FutureTask（可以拿到返回结果，可以处理异常）
         *         FutureTask<Integer> futureTask = new FutureTask<>(new Callable01());
         *         new Thread(futureTask).start();
         *         //阻塞等待整个线程执行完成，获取返回结果
         *         Integer integer = futureTask.get();
         * 4，线程池
         *      给线程池直接提交任务
         *      1.创建：
         *          1）Executors
         *          2）ThreadPoolExecutor
         *
         * 区别：
         *      1、2不能得到返回值，3可以得到返回值
         *      1、2、3都不能控制资源
         *      4可以控制资源，性能稳定
         */

        //以后在业务代码里面，以上三种启动线程方式都不用【将所有的多线程异步任务都交给线程池执行】
        //new Thread(()-> System.out.println("hello")).start();
        //当前系统中池只有一两个，每个异步任务,提交给线程池让他自己去执行任务

        /**
         * 七大参数
         * corePoolSize:[5] 核心线程数[一直存在]；线程池，创建好以后就准备就绪的线程数量，就等待来姐搜异步任务去执行
         * maximumPoolSize:最大线程数量；控制资源
         * keepAliveTime:存活时间。如果当前正在的线程数量大于core数量
         *              释放空闲的线程（maximumPoolSize-corePoolSize）。只要线程空闲大于指定的keepAliveTime
         * TimeUnit unit:时间单位
         * BlockingQueue<Runnable> workQueue：阻塞队列，如果任务有很多，救火将目前多的任务凡在队列里面
         *              只要有线程空闲，就会去队列里面去除新的任务继续执行
         * threadFactory:线程的创建工场
         * RejectedExecutionHandler handler:如果队列满了，按照我们指定的拒绝策略拒绝执行任务
         *
         * 工作顺序：
         * 1.线程池创建，准备哈core数量的核心线程，准备接受任务
         * 1.1、core满了，就将再进来的任务放入阻塞队列中，空闲的core就会自己去阻塞队列获取任务执行
         * 1.2、阻塞队列阻塞，就直接开新线程执行，最大只能开到max指定的数量
         * 1.3、max满了就用RejectedExecutionHandler拒绝任务
         * 1.4、max都执行完成，有很多空闲，在指定的时间keepAliveTime以后，释放max-core这些线程
         *
         * 一个线程池 core 1； max 20； queue 50； 100并发怎么分配
         * 7个会立即得到执行，50个进入队列，再开13个进行执行，剩下30个就使用拒绝策略
         * 如果不想抛弃还要执行。CallerRunsPolicy
         */


//        Executors.newCachedThreadPool() core是0，所有都可回收
//        Executors.newFixedThreadPool() 固定大小，core=max；都不可回收
//        Executors.newScheduledThreadPool() 定时任务的线程池
//        Executors.newSingleThreadExecutor() 单线程的线程池，后台从队列里面获取任务，挨个执行

//        service.execute(new Runnable01());

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        System.out.println("main...run...");
/*        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            System.out.println("当前线程" + Thread.currentThread().getId());
            int i = 10 / 2;
            System.out.println("运行结果" + i);
        }, executor);*/

        //方法完成后的感知
        /*CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("当前线程" + Thread.currentThread().getId());
            int i = 10 / 0;
            System.out.println("运行结果" + i);
            return i;
        }, executor).whenComplete((res,exception)->{
            //虽然能得到异常信息，但没法修改返回数据
            System.out.println("异步任务完成了...结果是：" + res + "；异常是： " + exception);
        }).exceptionally(throwable -> {
            //可以感知异常，同时返回默认值
            return 10;
        });*/

        //方法执行完后的处理
        /*CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("当前线程" + Thread.currentThread().getId());
            int i = 10 / 0;
            System.out.println("运行结果" + i);
            return i;
        }, executor).handle((res,thr)->{
            if (res != null){
                return res*2;
            }
            if (thr != null){
                return 0;
            }
            return 0;
        });*/

        /**
         * 线程串行化
         * 1.thenRun：不能获取到上一步的执行结果
         * .thenRunAsync(() -> {
         *             System.out.println("任务2启动了...");
         *         });
         * 2.thenAcceptAsync能接收上一步结果，但无返回值
         * 3.thenApplyAsync能接收上一步结果，有返回值
         */
        CompletableFuture.supplyAsync(() -> {
            System.out.println("当前线程" + Thread.currentThread().getId());
            int i = 10 / 4;
            System.out.println("运行结果" + i);
            return i;
        }, executor).thenApplyAsync(res->{
            System.out.println("任务2启动了..." + res);
            return "Hello" + res;
        }, executor);

        System.out.println("main...end...");
    }

    public static class Thread01 extends Thread{
        @Override
        public void run() {
            System.out.println("当前线程"+Thread.currentThread().getId());
            int i = 10 / 2;
            System.out.println("运行结果" + i);
        }
    }

    public static class Runnable01 implements Runnable{

        @Override
        public void run() {
            System.out.println("当前线程"+Thread.currentThread().getId());
            int i = 10 / 2;
            System.out.println("运行结果" + i);
        }
    }

    public static class Callable01 implements Callable<Integer>{

        @Override
        public Integer call() throws Exception {
            System.out.println("当前线程"+Thread.currentThread().getId());
            int i = 10 / 2;
            System.out.println("运行结果" + i);
            return i;
        }
    }
}
