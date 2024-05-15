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
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
		try (HttpClient client = HttpClient.newHttpClient()) {
			List<PresetConversion> presetConversions = getPresetConversions();

			ConversionMenu menu = new ConversionMenu(presetConversions);
			Scanner s = new Scanner(System.in);
			while (true) {
				menu.show();

				System.out.print("Digite 'sair' para encerrar o programa");
				System.out.println();

				System.out.print("Digite o número da conversão desejada: ");
				String menuOption = s.nextLine();
				if (menuOption.equalsIgnoreCase("sair")){
					break ;
				}

				// validate user input
				PresetConversion conversion;
				try {
					int i = Integer.parseInt(menuOption);
					conversion = presetConversions.get(i);
				} catch (NumberFormatException | IndexOutOfBoundsException e) {
					System.out.println("Erro: opção inválida");
					continue ;
				}

				// keep prompting the user until it enters a valid input
				String conversionAmount;
				while (true) {
					System.out.print("Digite o valor a ser convertido: ");
					conversionAmount = s.nextLine();

					// validate user input
					try {
						Float.parseFloat(conversionAmount);
						break ;
					} catch (NumberFormatException e) {
						System.out.println("Erro: Formato de número inválido");
					}
				}

                // setup the request uri
				String apiKey = ApiKeyReader.getKey();
				String uri = String.format(
						"https://v6.exchangerate-api.com/v6/%s/pair/%s/%s/%s",
						apiKey,
						conversion.from().code(),
						conversion.to().code(),
						conversionAmount
				);

				try {
					// setup the HttpRequest
					HttpRequest req = HttpRequest.newBuilder()
							.uri(URI.create(uri))
							.build();

					HttpResponse<String> res = client.send(req, BodyHandlers.ofString());

					// set Gson to handle ExchangeRate-API field naming policy
					Gson gson = new GsonBuilder()
							.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
							.create();

					// deserialize request body
					PairConversionFields resFields = gson.fromJson(res.body(), PairConversionFields.class);
					System.out.println(resFields);

				} catch (IOException | InterruptedException e) {
					System.out.println(e.getMessage());
				}
			}
			s.close();
		}
	}

	private static List<PresetConversion> getPresetConversions() {
		List<PresetConversion> preset = new ArrayList<>();

		// create currency with code and name information
		Currency USD = new Currency("USD", "United States Dollar");
		Currency BRL = new Currency("BRL", "Brazilian Real");
		Currency ARS = new Currency("ARS", "Argentine Peso");

		// add conversions to preset list
		preset.add(new PresetConversion(USD, BRL));
		preset.add(new PresetConversion(USD, ARS));
		preset.add(new PresetConversion(BRL, ARS));
		preset.add(new PresetConversion(BRL, USD));
		preset.add(new PresetConversion(ARS, USD));
		preset.add(new PresetConversion(ARS, BRL));
		return preset;
	}
}
