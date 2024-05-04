package currencyconverter;

public record ApiResponse (
String result,
String baseCode,
String targetCode,
double conversionRate,
double conversionResult
) {}
