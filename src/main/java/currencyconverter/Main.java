package currencyconverter;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
		try (HttpClient client = HttpClient.newHttpClient()) {

			String apiKey = ApiKeyReader.readApiKey();

			Scanner s = new Scanner(System.in);
			while (true) {
				System.out.println(">> Conversor de Moeda <<");
				System.out.println("Digite 'sair' para sair rs");

				System.out.print("Digite o valor na moeda base: ");
				String amount = s.nextLine();
				if (amount.equals("sair")) {
					break ;
				}

				// currency code examples:
				// ARS, BOB, BRL, CLP, COP, USD
				System.out.println("Digite o código de três letras da moeda");
				System.out.print("Converter de: ");
				String fromCurrencyCode = s.nextLine();
				if (fromCurrencyCode.equals("sair")) {
					break ;
				}

				System.out.println("Digite o código de três letras da moeda");
				System.out.print("Para: ");
				String toCurrencyCode = s.nextLine();
				if (toCurrencyCode.equals("sair")) {
					break ;
				}
				String uri = String.format(
					"https://v6.exchangerate-api.com/v6/%s/pair/%s/%s/%s",
					apiKey,
					fromCurrencyCode,
					toCurrencyCode,
					amount
				);

				HttpRequest req = HttpRequest.newBuilder()
					.uri(URI.create(uri))
					.build();
				HttpResponse<String> res = client.send(req, BodyHandlers.ofString());
				Gson gson = new GsonBuilder()
					.setPrettyPrinting()
					.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
					.create();
				ApiResponse resObject = gson.fromJson(res.body(), ApiResponse.class);
				System.out.println(resObject);
			}
			s.close();
		}
	}
}
