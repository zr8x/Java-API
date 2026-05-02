import java.util.Arrays;

import com.bsapi.Server;

public class ServerTest {
    public static void main(String[] args) throws Exception {
        System.out.println("Starting unit tests for Server.java...");

        int successes = 0;
        int tests = 1;

        System.out.println("Initiallizing Server class...\n");
        Server server = new Server();

        //      bubbleSort
        System.out.println("bubbleSort unit test:");
        int[] arr = { 4, 6, 1, 7, 3, 7 };
        int[] referenceArr = { 1, 3, 4, 6, 7, 7 };
        int[] sortedArray = Server.bubbleSort(arr);

        if (Arrays.equals(referenceArr, sortedArray)) {
            System.out.println("bubbleSort unit test success.\n");
            successes++;
        } else {
            System.out.println("bubbleSort unit test failed\n");
        }

        System.out.println("All Server.java unit tests complete, " + successes + "/" + tests + " succeded.");
    }
}