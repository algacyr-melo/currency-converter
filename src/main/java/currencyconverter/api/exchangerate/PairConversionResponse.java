package currencyconverter.api.exchangerate;

public record PairConversionResponse (
String result,
String baseCode,
String targetCode,
double conversionRate,
double conversionResult
) {}
