package org.cx.test;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @author grass
 * @date 2017/11/30
 */
public class FutureDemo {
    public static void main(String[] args) {

        Random random = new Random();

        ExecutorService executor = Executors.newFixedThreadPool(1);

        Future<String> future = executor.submit(()->{
            int value = random.nextInt(200);

            System.out.printf("helloworld() costs %s ms.", value);

            try {
                TimeUnit.MILLISECONDS.sleep(value);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return "hello world";
        });


        try {
            //超时流程
            future.get(100, TimeUnit.MILLISECONDS);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
            System.out.printf("time out....");
        }

        executor.shutdown();
    }
}
