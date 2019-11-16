package dataProvider;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {

  private Properties properties;
  private final String propertyFilePath = "configs//configuration.properties";


  public ConfigFileReader() {
    BufferedReader reader;
    try {
      reader = new BufferedReader(new FileReader(propertyFilePath));
      properties = new Properties();
      try {
        properties.load(reader);
        reader.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
    }
  }

  public String getApplicationUrl() {
    String url = properties.getProperty("url");
    if (url != null) return url;
    else throw new RuntimeException("url not specified in the Configuration.properties file.");
  }

  public long getTimeOut() {
    String timeoutSeconds = properties.getProperty("timeoutSeconds");
    if (timeoutSeconds != null) return Long.parseLong(timeoutSeconds);
    else throw new RuntimeException("timeoutSeconds not specified in the Configuration.properties file.");
  }
}


