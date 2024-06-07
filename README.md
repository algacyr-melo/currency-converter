# currency-converter

A simple, terminal-based currency converter with a few conversion options

## Installing

*Dependencies: Java14+, Maven*

1. Clone the repo
```bash
git clone git@github.com:algacyr-melo/currency-converter.git
```

2. Go to the project directory
```bash
cd currency-converter
```

3. Build with Maven
```bash
mvn clean package
```

## Usage

1. Visit [exchangerate-api.com/](https://www.exchangerate-api.com/) and get a free key
2. Enter the command below replacing with your key
```bash
export API_KEY="YOUR_API_KEY"
```
3. Start the converter
```bash
mvn exec:java -Dexec.mainClass="currencyconverter.Main"
```

