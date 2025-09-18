package org.neolcr.theory;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

public class Threads {
    public static void main(String[] args) throws Exception {
        System.out.println("Java Threads Demo (Platform vs Virtual)\n");
        platformThreadsDemo(50); // adjust counts for demo speed
        virtualThreadsDemo(5_000); // large number to illustrate scalability
        virtualThreadsExecutorDemo(5_000);
    }

    /**
     * Demonstrates creating a moderate number of traditional platform threads.
     * Each thread performs a blocking sleep to emulate I/O wait.
     */
    private static void platformThreadsDemo(int threadCount) throws InterruptedException {
        System.out.println("=== Platform Threads Demo (" + threadCount + ") ===");
        Instant start = Instant.now();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < threadCount; i++) {
            int idx = i;
            Thread t = new Thread(() -> blockingTask(idx));
            t.start();
            threads.add(t);
        }
        for (Thread t : threads) t.join();
        Duration d = Duration.between(start, Instant.now());
        System.out.println("Platform threads completed in: " + d.toMillis() + " ms\n");
    }

    /**
     * Demonstrates creating a large number of virtual threads directly.
     * Virtual threads are lightweight; the JVM multiplexes them over a small pool
     * of carrier (platform) threads. Blocking calls (e.g., Thread.sleep, I/O) release
     * the carrier, allowing better utilization.
     */
    private static void virtualThreadsDemo(int threadCount) throws InterruptedException {
        System.out.println("=== Virtual Threads Demo (" + threadCount + ") ===");
        Instant start = Instant.now();
        List<Thread> threads = new ArrayList<>(threadCount);
        for (int i = 0; i < threadCount; i++) {
            int idx = i;
            Thread t = Thread.ofVirtual().unstarted(() -> blockingTask(idx));
            t.start();
            threads.add(t);
        }
        for (Thread t : threads) t.join();
        Duration d = Duration.between(start, Instant.now());
        System.out.println("Virtual threads completed in: " + d.toMillis() + " ms\n");
    }

    /**
     * Uses the virtual thread per task executor for a structured style.
     */
    private static void virtualThreadsExecutorDemo(int tasks) throws ExecutionException, InterruptedException {
        System.out.println("=== Virtual Thread Per Task Executor Demo (" + tasks + ") ===");
        Instant start = Instant.now();
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            List<Callable<String>> callables = new ArrayList<>();
            IntStream.range(0, tasks).forEach(i -> callables.add(() -> {
                blockingTask(i);
                return "ok";
            }));
            List<Future<String>> futures = executor.invokeAll(callables);
            for (Future<String> f : futures) f.get();
        }
        Duration d = Duration.between(start, Instant.now());
        System.out.println("Virtual executor tasks completed in: " + d.toMillis() + " ms\n");
    }

    private static void blockingTask(int idx) {
        try {
            // Simulate blocking I/O (sleep). Virtual threads will yield carrier.
            Thread.sleep(20); // keep short for demo runtime
            if (idx == 0) {
                // Print only once to avoid flooding
                if (Thread.currentThread().isVirtual()) {
                    System.out.println("Running on a virtual thread sample name=" + Thread.currentThread());
                } else {
                    System.out.println("Running on a platform thread sample name=" + Thread.currentThread());
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

/*
Summary:
- Platform (traditional) threads map 1:1 to OS threads. They are heavier: limited number (thousands) before overhead grows.
- Virtual threads (final in JDK 21; preview in JDK 19/20) are JVM-managed, scheduled over a small pool of carrier threads.
- Blocking I/O in a virtual thread does not pin the carrier (for supported blocking operations), improving scalability for I/O bound workloads.
- Use platform threads for:
    * Long-running tasks (e.g., event loops, thread confinement patterns)
    * Operations requiring specific OS-level thread characteristics (e.g., JNI pinning, thread-local heavy use)
- Use virtual threads for:
    * High-concurrency, mostly-blocking tasks (serve many requests, call databases/services)
    * Simplify code vs. async callbacks (structured concurrency style)
Caveats:
- Avoid heavy ThreadLocal reliance (memory footprint for many virtual threads).
- Some blocking operations that are not JDK-managed may still pin carrier threads.
*/
