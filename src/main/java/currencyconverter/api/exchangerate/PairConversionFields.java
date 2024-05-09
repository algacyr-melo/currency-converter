package currencyconverter.api.exchangerate;

public record PairConversionFields(
String result,
String baseCode,
String targetCode,
double conversionRate,
double conversionResult
) {}
