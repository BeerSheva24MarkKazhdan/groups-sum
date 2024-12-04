package telran.numbers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class ThreadsPoolGroupSum extends ThreadsGroupSum{

    public ThreadsPoolGroupSum(int[][]groups) {
        super(groups);
    }

    @Override
    protected void startTasks(FutureTask<Long>[] tasks) {
        ExecutorService threadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        for (int i = 0; i < tasks.length; i++) {
            tasks[i] = new FutureTask<>(new OneGroupSum(groups[i]));
            threadPool.submit(tasks[i]);
        }
        threadPool.shutdown();
    }
}