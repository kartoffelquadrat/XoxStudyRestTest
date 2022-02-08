package eu.kartoffelquadrat.xoxresttest;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.Random;

public class RestTestUtils {

    private final String SERVICE_LOCATION = "http://127.0.0.1:8080/bookstore";

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

    /**
     * Helper method to add a test book with provided isbn
     *
     * @return HttpResponse representing the server reply.
     */
    protected HttpResponse<String> addTestBook(String isbn) throws UnirestException {

        // JSON body for the book to add.
        String body = "{\n" +
                "  \"isbn\": " + isbn + ",\n" +
                "  \"title\": \"The Uninhabitable Earth\",\n" +
                "  \"author\": \"David Wallace-Wells\",\n" +
                "  \"priceInCents\": 2447,\n" +
                "  \"bookAbstract\": \"It is worse, much worse, than you think. The slowness of climate change is a fairy tale, perhaps as pernicious as the one that says it isn’t happening at all, and comes to us bundled with several others in an anthology of comforting delusions: that global warming is an Arctic saga, unfolding remotely; that it is strictly a matter of sea level and coastlines, not an enveloping crisis sparing no place and leaving no life un-deformed.\"\n" +
                "}";

        // Try to add book to backend
        return Unirest.put(getServiceURL("/isbns/" + isbn)).header("Content-Type", "application/json; charset=utf-8")
                .body(body).asString();
    }
}
