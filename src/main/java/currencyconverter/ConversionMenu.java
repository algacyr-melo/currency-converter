package currencyconverter;

import java.util.List;

public class ConversionMenu {
    private final List<PresetConversion>  conversionList;

    public ConversionMenu(List<PresetConversion> conversionList) {
        this.conversionList = conversionList;
    }

    public void show() {
        System.out.println();
        for (int i = 0; i < conversionList.size(); i++) {
            PresetConversion conversion = conversionList.get(i);
            System.out.printf(
                    "%d\t%s(%s) => %s(%s)\n",
                    i,
                    conversion.from().code(),
                    conversion.from().name(),
                    conversion.to().code(),
                    conversion.to().name()
            );
        }
        System.out.println();
    }
}
