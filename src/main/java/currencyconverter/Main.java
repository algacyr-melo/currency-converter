package currencyconverter;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] Args) throws FileNotFoundException {
        System.out.println("hello currency converter!");

		String apiKey = ApiKeyReader.readApiKey();
		System.out.println("apiKey: " + apiKey);
    }
}
