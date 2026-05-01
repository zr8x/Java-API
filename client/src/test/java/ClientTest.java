import com.bsapi.Client;

public class ClientTest {
    public static void main(String[] args) throws Exception {
        int[] array = { 3, 4, 1, 67, 1, 8 };

        Client client = new Client();
        int[] sortedArray = client.SendPostArray(array);
        
        for (int i = 0; i < sortedArray.length; i++) {
            System.out.print(String.valueOf(sortedArray[i]) + ", ");
        }
        System.out.print("\n");
    }
}