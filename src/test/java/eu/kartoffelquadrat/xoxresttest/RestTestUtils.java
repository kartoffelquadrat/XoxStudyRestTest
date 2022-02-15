package eu.kartoffelquadrat.xoxresttest;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.Random;

public class RestTestUtils {

    private final String SERVICE_LOCATION = "http://127.0.0.1:8080/";

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
     * Helper method to inspect HttpResponse and ensure return code is in 2XX range (OK / other success)
     * @param response as a previously received HttpResponse object
     */
    protected void verifyOk(HttpResponse<String> response) {

        assert response.getStatus()/100 == 2;
    }

    /**
     * Helper method to create a random ISBN.
     *
     * @return new random isbn number (Stringified positive number)
     */
    protected String getRandomIsbn() {
        return Integer.toString(Math.abs(new Random().nextInt()));
    }
}
