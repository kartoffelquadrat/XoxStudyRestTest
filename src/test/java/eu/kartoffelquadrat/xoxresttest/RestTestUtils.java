package eu.kartoffelquadrat.xoxresttest;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.Random;

public class RestTestUtils {

  /**
   * Helper method to tell if CUD was effective by verification by subsequent R is requested in form
   * of system properties entry. Corresponding maven argument: -Dreadverif=true See:
   * https://stackoverflow.com/a/9923049
   */
  public static boolean isReadVerficationsRequested() {
    boolean readVerifRequested = Boolean.valueOf(System.getProperty("readverif"));
    System.out.println("Read verification of corrupting operations enabled: " + readVerifRequested);
    return readVerifRequested;
  }

  private final String SERVICE_LOCATION = "http://127.0.0.1:8080/xox/";

  /**
   * Helper method to construct service URI out of provided relative resource location.
   *
   * @param localResource as the relative service resource. Must start with leading slash.
   * @return URI string for the provided local resource.
   */
  protected String getServiceURL(String localResource) {
    return SERVICE_LOCATION + localResource;
  }

  /**
   * Helper method to inspect HttpResponse and ensure return code is in 2XX range (OK / other
   * success)
   *
   * @param response as a previously received HttpResponse object
   */
  protected void verifyOk(HttpResponse<String> response) {

    int status = response.getStatus();
    assert status / 100 == 2;
  }
}
