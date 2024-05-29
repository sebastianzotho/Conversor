import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.io.FileWriter;
import java.io.IOException;

public class CurrencyFileGenerator {
    public void guardarJson(JsonObject conversionRates, String baseCurrency) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String fileName = baseCurrency + "_rates.json";
        try (FileWriter escritura = new FileWriter(fileName)) {
            gson.toJson(conversionRates, escritura);
        }
    }
}