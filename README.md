# store

Sur linux :
- Se placer à la racine du projet et faire la commande : "chmod u+x server-cucumber/src/test/resources/driver/geckodriver"
- Remplacer la fonction @BeforeClass dans server-cucumber/src/test/java/selenium/CalculatorSeleniumTest.java par le bloc suivant : </br>
  @BeforeClass </br>
  public static void setup() { </br>
  String path = CalculatorSeleniumTest.class.getClassLoader() </br>
  .getResource("driver/geckodriver") </br>
  .getFile(); </br>
  path = path.replace("/target/test-classes", "/src/test/resources");
  System.setProperty("webdriver.gecko.driver", URLDecoder.decode(path)); </br>
  driver = new FirefoxDriver(); </br>
  driverWait = new WebDriverWait(driver, 10); </br>
  driver.get("http://localhost:4200"); </br>
  }
