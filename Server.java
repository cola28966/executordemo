package executordemo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class Server {
    private ThreadPoolExecutor executor;
    public Server(){
        executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(4);
    }
    public void submitTask() throws ExecutionException, InterruptedException {
        int tot=0;
        List<Future<Integer>> results = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Task task = new Task(100*i+1,100*(i+1));
            Future<Integer> result = executor.submit(task);
            results.add(result);
        }

        do {
            System.out.printf("完成了%d个任务\n",executor.getCompletedTaskCount());
            for (int i = 0; i < results.size(); i++) {
                Future<Integer> result = results.get(i);
                System.out.printf("Task %d is %s\n",i,result.isDone());
            }
            Thread.sleep(50);
        }while (results.size()>executor.getCompletedTaskCount());
        System.out.printf("完成了%d个任务\n",executor.getCompletedTaskCount());
        for (int i = 0; i < results.size(); i++) {
            Future<Integer> result = results.get(i);
            System.out.printf("Task %d is %s\n",i,result.isDone());
        }
        for(Future<Integer> result:results){
            tot+=result.get();
        }
        System.out.println("1~1000的值相加等于"+tot);
    }
    public void endServer(){
        executor.shutdown();
    }
}
