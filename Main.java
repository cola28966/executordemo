package executordemo;

import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) {
        Server server = new Server();
        try {
            server.submitTask();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
