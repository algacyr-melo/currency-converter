package currencyconverter;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import currencyconverter.api.exchangerate.ApiKeyReader;
import currencyconverter.api.exchangerate.PairConversionFields;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
		try (HttpClient client = HttpClient.newHttpClient()) {
			// map preset currency code to currency name
			Map<String, String> codeToName = new HashMap<>();
			codeToName.put("USD", "United States Dollar");
			codeToName.put("BRL", "Brazilian Real");
			codeToName.put("ARS", "Argentine Peso");
			codeToName.put("COP", "Colombian Peso");

			// add preset conversions to a list
			List<PresetConversion> presetList = new ArrayList<>();
			presetList.add(new PresetConversion("USD", "BRL"));
			presetList.add(new PresetConversion("BRL", "USD"));
			presetList.add(new PresetConversion("USD", "ARS"));
			presetList.add(new PresetConversion("ARS", "USD"));
			presetList.add(new PresetConversion("USD", "COP"));
			presetList.add(new PresetConversion("COP", "USD"));

			for (int i = 0; i < presetList.size(); i++) {
				System.out.printf(
                        "%d\t%s(%s) => %s(%s)\n",
					i,
                    codeToName.get(presetList.get(i).getFrom()),
                    presetList.get(i).getFrom(),
                    codeToName.get(presetList.get(i).getTo()),
                    presetList.get(i).getTo()
                );
			}

			Scanner s = new Scanner(System.in);
			System.out.print("Digite o número da conversão desejada: ");
			String option = s.nextLine();

			System.out.print("Qual valor deseja converter? ");
			String amount = s.nextLine();

			// setup the request uri
			String apiKey = ApiKeyReader.getKey();
			String uri = String.format(
				"https://v6.exchangerate-api.com/v6/%s/pair/%s/%s/%s",
				apiKey,
				presetList.get(Integer.parseInt(option)).getFrom(),
				presetList.get(Integer.parseInt(option)).getTo(),
				amount
			);

			// setup the HttpRequest
			HttpRequest req = HttpRequest.newBuilder()
				.uri(URI.create(uri))
				.build();

			// send HttpRequest to get an HttpResponse
			HttpResponse<String> res = client.send(req, BodyHandlers.ofString());

			// set Gson to handle ExchangeRate-API field naming policy
			Gson gson = new GsonBuilder()
				.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
				.create();

			// deserialize request body
			PairConversionFields resObj = gson.fromJson(res.body(), PairConversionFields.class);
			System.out.println(resObj);
			s.close();
		}
	}
}
