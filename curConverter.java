import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
class curConverter{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the base currency : ");
        String base = scanner.next().toUpperCase();
        System.out.println("Enter the target currency : ");
        String target = scanner.next().toUpperCase();
        System.out.println("Enter the amount to convert: ");
        double amount = scanner.nextDouble();

        try {
            String apiKey = "d99c62550c0d6a687e491162"; 
            String apiURL = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/" + base;
            URL url = new URL(apiURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            String jsonResponse = response.toString();
            String searchKey = "\"" + target + "\":";
            int startIndex = jsonResponse.indexOf(searchKey) + searchKey.length();
            int endIndex = jsonResponse.indexOf(",", startIndex);
            if (endIndex == -1) {
                endIndex = jsonResponse.indexOf("}", startIndex);
            }
            String rateStr = jsonResponse.substring(startIndex, endIndex).trim();
            double rate = Double.parseDouble(rateStr);
            double converted = amount * rate;
            System.out.printf("Converted amount: %.2f %s\n", converted, target);

        } catch (Exception e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }

        scanner.close();
    }
}