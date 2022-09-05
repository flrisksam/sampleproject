package fileReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class configPropertyFileReader 
{
	static Properties prop;
	
	public configPropertyFileReader() throws Exception
	{
		File src = new File("./Config.property");
		
		FileInputStream fis = new FileInputStream(src);
		
		prop = new Properties();
		
		prop.load(fis);
	}
	
	public static String ChromeDriverPath()
	{
		return prop.getProperty("ChromeDriverPath");
	}
	
	public static String URL()
	{
		return prop.getProperty("URL");
	}

}