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
2. Expose your key to the system
```bash
export API_KEY="put_your_key_here"
```
3. Run the app:
```bash
mvn exec:java -Dexec.mainClass="currencyconverter.Main"
```
