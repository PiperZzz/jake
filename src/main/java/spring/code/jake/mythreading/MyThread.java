package spring.code.jake.mythreading;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.Executors;

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

    public static void completableFutureSupply(String str) throws InterruptedException, ExecutionException{
        ExecutorService localThreadPool = Executors.newFixedThreadPool(3);

        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> callAnotherExternalService(str),
                localThreadPool);

        String result = completableFuture.get();

        System.out.println("Result: " + result);

        localThreadPool.shutdown();
    }

    @SuppressWarnings("unused")
    public static void completableFutureMethods() {
        ExecutorService localThreadPool = Executors.newFixedThreadPool(3);
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> callExternalService());
        CompletableFuture<String> completableFutureAnother = CompletableFuture.supplyAsync(() -> callAnotherExternalService("new input"));

        CompletableFuture<Void> allOf = CompletableFuture.allOf(completableFuture, completableFutureAnother);
        /* allOf() 方法接收一个CompletableFuture数组作为参数，当所有CompletableFuture都完成时，返回一个新的CompletableFuture */

        CompletableFuture<Object> anyOf = CompletableFuture.anyOf(completableFuture, completableFutureAnother);
        /* anyOf() 方法接收一个CompletableFuture数组作为参数，当任意一个CompletableFuture完成时，返回一个新的CompletableFuture */

        CompletableFuture<String> thenApply = completableFuture.thenApply(result -> callAnotherExternalService(result + "new input"));
        /* thenApply() 方法接收一个函数作为参数，该函数接收上一个异步任务的结果，并返回一个新的结果 */

        CompletableFuture<String> thenApplyAsync = completableFuture.thenApplyAsync(result -> callAnotherExternalService(result + "new input"), localThreadPool);
        /* thenApplyAsync() 方法接收一个函数作为参数，该函数接收上一个异步任务的结果，并返回一个新的结果，并使用新线程执行 */

        CompletableFuture<String> thenCompose = completableFuture.thenCompose(result -> CompletableFuture.supplyAsync(() -> callAnotherExternalService(result + "new input")));
        /* thenCompose() 方法接收一个函数作为参数，该函数接收上一个异步任务的结果，并返回一个新的CompletableFuture，该CompletableFuture会使用新线程执行 */

        CompletableFuture<String> thenComposeAsync = completableFuture.thenComposeAsync(result -> CompletableFuture.supplyAsync(() -> callAnotherExternalService(result + "new input")), localThreadPool);
        /* thenComposeAsync() 方法接收一个函数作为参数，该函数接收上一个异步任务的结果，并返回一个新的CompletableFuture，该CompletableFuture会使用新线程执行 */
                        
        CompletableFuture<String> thenCombine = completableFuture.thenCombine(completableFutureAnother, (result, resultAnother) -> result + resultAnother);
        /* thenCombine() 方法接收两个CompletableFuture作为参数，并使用新线程执行，并返回一个新的结果 */

        CompletableFuture<String> thenCombineAsync = completableFuture.thenCombineAsync(completableFutureAnother, (result, resultAnother) -> result + resultAnother, localThreadPool);
        /* thenCombineAsync() 方法接收两个CompletableFuture作为参数，并使用新线程执行，并返回一个新的结果 */

        completableFuture.thenAccept(result -> System.out.println("Result: " + result));
        /* thenAccept() 方法接收一个函数作为参数，该函数接收上一个异步任务的结果，但不返回新的结果 */

        completableFuture.thenAcceptAsync(result -> System.out.println("Result: " + result), localThreadPool);

        completableFuture.thenRun(() -> System.out.println("Runnable task"));
        /* thenRun() 方法接收一个Runnable作为参数，该Runnable不接收任何参数，也不返回任何结果 */

        completableFuture.thenRunAsync(() -> System.out.println("Runnable task"), localThreadPool);
        /* thenRunAsync() 方法接收一个Runnable作为参数，该Runnable不接收任何参数，也不返回任何结果，并使用新线程执行 */
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
        return "Another External Service Response - " + str;
    }
}