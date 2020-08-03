package TestRunners;

import org.apache.commons.lang3.StringUtils;

public class TestDefaultValues {

  public static final String DEFAULT_OPERATING_SYSTEM = "Windows";
  //public static final String DEFAULT_OPERATING_SYSTEM = "MacOS";
  public static final String DEFAULT_BROWSER = "Chrome";
  public static final String DEFAULT_LINK = "http://www.facebook.com";
  public static final String DEFAULT_USER = "jasecuframework@gmail.com";
  public static final String DEFAULT_PASSWORD = "CORRECTPASSWORD";

  private static String operatingSystem;
  private static String browser;
  private static String link;
  private static String user;
  private static String password;

  public static String getOperatingSystem() { return StringUtils.isEmpty( operatingSystem ) ? DEFAULT_OPERATING_SYSTEM : operatingSystem; }

  public static void setOperatingSystem(String operatingSystem) {
    TestDefaultValues.operatingSystem = operatingSystem;
  }

  public static String getBrowser() {
    return StringUtils.isEmpty( browser ) ? DEFAULT_BROWSER : browser;
  }

  public static void setBrowser(String browser) {
    TestDefaultValues.browser = browser;
  }

  public static String getLink() {
    return StringUtils.isEmpty( link ) ? DEFAULT_LINK : link;
  }

  public static void setLink(String link) {
    TestDefaultValues.link = link;
  }

  public static String getUser() {
    return StringUtils.isEmpty( user ) ? DEFAULT_USER : user;
  }

  public static void setUser(String user) {
    TestDefaultValues.user = user;
  }

  public static String getPassword() {
    return StringUtils.isEmpty( password ) ? DEFAULT_PASSWORD : password;
  }

  public static void setPassword(String password) {
    TestDefaultValues.password = password;
  }


}
