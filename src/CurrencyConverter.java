import java.io.InputStreamReader;
import java.io.IOException;
import java.net.URL;
import java.net.HttpURLConnection;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class CurrencyConverter {
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/2abe4061e1201432907ca3db/latest/";

    public JsonObject getConversionRates(String baseCurrency) throws IOException {
        String apiUrl = API_URL + baseCurrency;

        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode != 200) {
            throw new IOException("Error en la conexión a la API: " + responseCode);
        }

        InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());
        JsonObject jsonResponse = JsonParser.parseReader(inputStreamReader).getAsJsonObject();
        inputStreamReader.close();

        return jsonResponse;
    }

    public double getConversionRate(String fromCurrency, String toCurrency) throws IOException {
        JsonObject rates = getConversionRates(fromCurrency).getAsJsonObject("conversion_rates");
        if (rates.has(toCurrency)) {
            return rates.get(toCurrency).getAsDouble();
        } else {
            throw new IOException("Código de moneda no válido: " + toCurrency);
        }
    }
}


