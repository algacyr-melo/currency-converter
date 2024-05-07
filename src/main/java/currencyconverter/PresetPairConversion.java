package currencyconverter;

import java.util.Map;

public class PresetPairConversion {
	private Map<Integer, String[]>	optionToPair;

	public PresetPairConversion(Map<Integer, String[]> optionToPair) {
		this.optionToPair = optionToPair;
	}

	public String[] getOptionPair(Integer option) {
		return optionToPair.get(option);
	}
}
