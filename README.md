# currency-converter

## Development Notes

### Creating an ApiKeyReader
In order to avoid hardcoding the API key, I put the key inside a
config.properties file inside the resources dir. Then, I created the
ApiKeyReader (as the name says) to read the API key from this config file.

To achieve that I am using 2 usefull classes, Properties and FileReader.

#### Properties
Properties class can be saved to or, in this case, loaded from a
stream. It represents a set of key-value pairs, each one being a string.

for example:
"api.key=YOUR_API_KEY"

So the above string is going to be loaded from the config.properties file to my
Properties object

#### FileReader
FileReader is meant for reading streams of characters. I am creating a
FileReader in order to open the file and then passing it to the load() method
of Properties class.

#### References
[Class Properties](https://docs.oracle.com/en/java/javase/22/docs/api/java.base/java/util/Properties.html)
[Class FileReader](https://docs.oracle.com/en/java/javase/22/docs/api/java.base/java/io/FileReader.html)

