package executordemo;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

public class Task implements Callable<Integer> {
    private int start,end;

    public Task(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public Integer call() throws Exception {
        int res = 0;
        for (int i = start; i <= end; i++) {
            res += i;
        }
        Thread.sleep(100);
        System.out.printf("%s 计算了%d到%d的值为%d\n",Thread.currentThread().getName(),
                start,end,res);


        return res;
    }

}
