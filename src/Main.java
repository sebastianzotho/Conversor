import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CurrencyConverter converter = new CurrencyConverter();
        CurrencyFileGenerator fileGenerator = new CurrencyFileGenerator();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("*********************");
            System.out.println("Seleccione una opción:");
            System.out.println("1) Dólares a Pesos Argentinos");
            System.out.println("2) Pesos Argentinos a Dólares");
            System.out.println("3) Dólar a Real Brasileño");
            System.out.println("4) Real Brasileño a Dólar");
            System.out.println("5) Dólar a Peso Colombiano");
            System.out.println("6) Peso Colombiano a Dólar");
            System.out.println("7) Guardar tasas de cambio en un archivo");
            System.out.println("8) Salir");
            System.out.println("*********************");

            int option = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (option) {
                    case 1:
                        convertCurrency(converter, "USD", "ARS");
                        break;
                    case 2:
                        convertCurrency(converter, "ARS", "USD");
                        break;
                    case 3:
                        convertCurrency(converter, "USD", "BRL");
                        break;
                    case 4:
                        convertCurrency(converter, "BRL", "USD");
                        break;
                    case 5:
                        convertCurrency(converter, "USD", "COP");
                        break;
                    case 6:
                        convertCurrency(converter, "COP", "USD");
                        break;
                    case 7:
                        System.out.print("Ingrese el código de moneda base para guardar (por ejemplo, USD): ");
                        String baseCurrency = scanner.nextLine().toUpperCase();
                        JsonObject conversionRates = converter.getConversionRates(baseCurrency);
                        fileGenerator.guardarJson(conversionRates, baseCurrency);
                        System.out.println("Tasas de cambio guardadas en " + baseCurrency + "_rates.json");
                        break;
                    case 8:
                        exit = true;
                        break;
                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                }
            } catch (IOException e) {
                System.out.println("Error al convertir moneda o guardar archivo: " + e.getMessage());
            }
        }

        scanner.close();
    }

    private static void convertCurrency(CurrencyConverter converter, String fromCurrency, String toCurrency) throws IOException {
        double rate = converter.getConversionRate(fromCurrency, toCurrency);
        System.out.println("1 " + fromCurrency + " equivale a " + rate + " " + toCurrency);
    }
}