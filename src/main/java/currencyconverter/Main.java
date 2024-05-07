package currencyconverter;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import currencyconverter.api.exchangerate.ApiKeyReader;
import currencyconverter.api.exchangerate.PairConversionResponse;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
		try (HttpClient client = HttpClient.newHttpClient()) {
			Map<Integer, String[]> optionToPair = new HashMap<>();
			optionToPair.put(1, new String[]{"USD", "BRL"});
			optionToPair.put(2, new String[]{"ARS", "BRL"});

			PresetPairConversion preset = new PresetPairConversion(optionToPair);

			Scanner s = new Scanner(System.in);
			System.out.println("código\t");
			System.out.println("1	United States Dollar(USD)	=>	Brazilian Real(BRL)");
			System.out.println("2	Argentine Peso(ARS)			=>	Brazilian Real(BRL)");
			System.out.print("Digite o código da conversão desejada: ");
			String option = s.nextLine();

			System.out.print("Qual valor deseja converter? ");
			String amount = s.nextLine();

			String[] pair = preset.getOptionPair(Integer.parseInt(option));
			String apiKey = ApiKeyReader.getKey();
			String uri = String.format(
				"https://v6.exchangerate-api.com/v6/%s/pair/%s/%s/%s",
				apiKey,
				pair[0],
				pair[1],
				amount
			);

			System.out.println("Convertendo " + amount + " " + pair[0] + " para " + pair[1] + "...");

			// build request message
			HttpRequest req = HttpRequest.newBuilder()
				.uri(URI.create(uri))
				.build();

			// send request and get a response
			HttpResponse<String> res = client.send(req, BodyHandlers.ofString());

			// set Gson according ExchangeRate-API field naming policy
			Gson gson = new GsonBuilder()
				.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
				.create();

			// deserialize request body
			PairConversionResponse resObj = gson.fromJson(res.body(), PairConversionResponse.class);
			System.out.println(resObj);
			s.close();
		}
	}
}
