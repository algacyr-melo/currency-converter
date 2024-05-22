# currency-converter

A simple, terminal-based currency converter with a few conversion options

## Installation

1. Clone this repository:
```bash
git clone git@github.com:algacyr-melo/currency-converter.git
```

2. Navigate to the project directory:
```bash
cd currency-converter
```

3. Build the project with Maven
```bash
mvn clean install
```

## Usage

1. Visit [exchangerate-api.com/](https://www.exchangerate-api.com/) and get a free key
3. Navigate to the main directory
```bash
cd src/main
```
3. Create a config.properties file inside resources directory

Use your favorite text editor, just ensure there is a resources directory with config.properties file within main directory

```src/main/resources/config.properties```
4. Paste your API key in config.properties file:
```api.key=[YOUR_API_KEY]```

5. Run the app:
```bash
mvn exec:java -Dexec.mainClass="currencyconverter.Main"
```
