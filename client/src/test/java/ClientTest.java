import java.util.Arrays;

import org.json.JSONArray;
import org.json.JSONObject;

import com.bsapi.Client;

public class ClientTest {
    public static void main(String[] args) throws Exception {
        int successes = 0;
        int tests = 2;

        System.out.println("Starting unit tests for Client.java...");
        
        System.out.println("Initiallizing Client class...\n");
        Client client = new Client();

        // Unit tests

        //      ArrayToJSON test
        System.out.println("ArrayToJSON unit test:");
        int[] arr = { 4, 6, 1, 7, 3, 7 };
        JSONObject jsonObject = client.ArrayToJSON(arr);
        JSONArray jsonArray = new JSONArray(jsonObject.get("body").toString());
        int a = jsonArray.optInt(0);
        int b = jsonArray.optInt(3);
        int c = jsonArray.optInt(5);

        if ( (a == arr[0]) && (b == arr[3]) && (c == arr[5]) ) {
            System.out.println("ArrayToJSON unit test success.\n");
            successes++;
        } else {
            System.out.println("ArrayToJSON unit test failed.\n");
        }


        //      JSONToArray test
        System.out.println("JSONToArray unit test:");
        JSONArray jsonArray2 = new JSONArray().putAll(arr);
        int[] arr2 = client.JSONToArray(jsonArray2);

        if (Arrays.equals(arr, arr2)) {
            System.out.println("JSONToArray unit test success.\n");
            successes++;
        } else {
            System.out.println("JSONToArray unit test failed.\n");
        }

        System.out.println("All Client.java unit tests complete, " + successes + "/" + tests + " succeded.");
    }
}