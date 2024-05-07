package currencyconverter.api.exchangerate;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ApiKeyReader {
	public static String getKey() {
		Properties properties = new Properties();
		try {
			FileReader fileReader = new FileReader("src/main/resources/config.properties");
			properties.load(fileReader);
		} catch(IOException e) {
			System.out.println(e.getMessage());
		}
		return properties.getProperty("api.key");
	}
}
