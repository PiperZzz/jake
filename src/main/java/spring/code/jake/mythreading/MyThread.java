package spring.code.jake.mythreading;

import java.util.concurrent.*;

public class MyThread extends Thread {
    public MyThread() {
    }

    @Override
    public void run() {
        callExternalService();
    }

    public static void extendsThread() throws InterruptedException {
        MyThread myThread = new MyThread();
        myThread.start(); // 开始用新线程执run()方法内的行异步任务

        // 执行其他任务，不会阻塞主线程

        myThread.join(); // 阻塞主线程，等待异步任务完成
    }

    public static void runnable(boolean useThreadPool) {
        Runnable runnableTask = () -> callExternalService();

        if (useThreadPool) {
            ExecutorService localThreadPool = Executors.newFixedThreadPool(3);
            localThreadPool.execute(runnableTask); // 提交Runnable异步任务到线程池
        } else {
            new Thread(runnableTask).start();
        }
    }

    public static void callable() throws InterruptedException, ExecutionException {
        Callable<String> callableTask = () -> callExternalService();

        ExecutorService localThreadPool = Executors.newFixedThreadPool(3);

        Future<String> future = localThreadPool.submit(callableTask); // 提交Callable异步任务到线程池

        String result = future.get();

        System.out.println("Result: " + result);

        localThreadPool.shutdown(); // 关闭线程池，不再接受新任务，但是会等待所有任务完成
        // localThreadPool.shutdownNow(); // 关闭线程池，不再接受新任务，并尝试停止正在执行的任务
    }

    public static void completableFutureRun() {
        CompletableFuture.runAsync(() -> callExternalService()); // CompletableFuture默认会使用全局线程池ForkJoinPool
    }

    public static void completableFutureSupply() throws InterruptedException, ExecutionException {
        ExecutorService localThreadPool = Executors.newFixedThreadPool(3);

        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> callExternalService(),
                localThreadPool);

        completableFuture.join();

        String result = completableFuture.get();

        System.out.println("Result: " + result);

        localThreadPool.shutdown();
    }

    public static void completableFutureMethods() {
        @SuppressWarnings("unused")
        CompletableFuture<String> thenApply = CompletableFuture.supplyAsync(() -> callExternalService()).thenApply(result -> callAnotherExternalService(result + "new input"));

        CompletableFuture.supplyAsync(() -> callExternalService()).thenAccept(result -> System.out.println("Result: " + result));
         /* thenAccept() 和 thenApply() 的不同在于它不返回任何结果，它只是对自己的异步任务完成后的结果进行消费 */


        
                
        CompletableFuture<String> thenCombine = CompletableFuture.supplyAsync(() -> callExternalService())
                .thenCombine(CompletableFuture.supplyAsync(() -> callExternalService()), (result1, result2) -> result1 + result2);
    }

    public static void threadMethods() throws InterruptedException {
        Thread thread = new Thread(new MyThread());
        thread.start();

        Thread.sleep(MAX_PRIORITY);
        thread.wait();

        //thread.yield();
    }

    private static String callExternalService() {
        // 模拟调用外部服务，并返回响应
        return "External Service Response";
    }

    private static String callAnotherExternalService(String str) {
        // 模拟调用其它外部服务，并返回响应
        return "Another External Service Response" + str;
    }
}