import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallServer {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        List<Long> resultOnlySync = verify(true);
        List<Long> resultAsync = verify(false);

        writeToFile(resultOnlySync, "resultSync");
        writeToFile(resultAsync, "resultAsync");
    }

    private static List<Long> verify(Boolean sync) throws ExecutionException, InterruptedException {

        List<Long> result = new ArrayList<>();
        List<Thread> threadList = new ArrayList<>();
        List<FutureTask<Long>> futureTasks = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {

            Worker worker = new Worker(sync);
            FutureTask<Long> task = new FutureTask<>(worker);
            futureTasks.add(task);
            Thread thread = new Thread(task);
            threadList.add(thread);
        }

        for(Thread thread: threadList){
            thread.start();
        }

        for(FutureTask<Long> task : futureTasks){
            result.add(task.get());
        }

        return result;
    }

    private static void writeToFile(List<Long> result, String resultPath) {
        try {
            BufferedWriter out = new BufferedWriter(
                    new FileWriter(String.format("src\\%s.txt", resultPath)));

            out.write(result.toString());
            out.newLine();
            out.close();
        } catch (IOException e) {

            System.out.println("exception occurred" + e);
        }
    }

}
