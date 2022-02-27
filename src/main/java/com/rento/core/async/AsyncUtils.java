package com.rento.core.async;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AsyncUtils {

    private static ExecutorService executorService;

    public static Future submit(Runnable runnable) {
        return executorService.submit(runnable);
    }

    public static <V> Future<V> submit(Callable<V> callable) {
        return executorService.submit(callable);
    }

    public static void init(ExecutorService executorServiceInstance) {
        executorService = executorServiceInstance;
    }
}
