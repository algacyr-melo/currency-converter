package currencyconverter;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ApiKeyReader {
	public static String readApiKey() throws FileNotFoundException {
		Properties properties = new Properties();

		try {
			FileReader fileReader = new FileReader("src/main/resources/config.properties");
			properties.load(fileReader);
			return properties.getProperty("api.key");
		} catch(IOException e) {
			return null;
		}
	}
}
