package eu.kartoffelquadrat.xoxresttest;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.body.RequestBodyEntity;
import org.junit.Test;

/**
 * All Unit tests for StockLocation Resources
 */
public class StockLocationsTest extends RestTestUtils {

    /**
     * Verifies the list of stores listed by default contains the "Lyon" store.
     *
     * @throws UnirestException
     */
    @Test
    public void testStocklocationsGet() throws UnirestException {

        // Try to retrieve comments for default book
        HttpResponse<String> stocklocations = Unirest.get(getServiceURL("/stocklocations")).asString();
        verifyOk(stocklocations);

        // Verify and return comments content
        String body = stocklocations.getBody();
        assert body.contains("Lyon");
    }

    /**
     * Verifies the default stock amount (retrieved as bundle with all books) lists the number of copies for
     * 9780739360385 (Harry Potter) as "4".
     *
     * @throws UnirestException
     */
    @Test
    public void testStocklocationsStocklocationGet() throws UnirestException {

        String location = "Lyon";

        // Try to retrieve comments for default book
        HttpResponse<String> stockAmount = Unirest.get(getServiceURL("/stocklocations/" + location)).asString();
        verifyOk(stockAmount);

        // Verify initial stock count for default book
        String[] body = stockAmount.getBody().split(":");
        assert body[0].contains("9780739360385");
        assert body[1].contains("4");
    }

    /**
     * Verifies the default stock amount for book 9780739360385 (Harry Potter) is "4".
     *
     * @throws UnirestException
     */
    @Test
    public void testStocklocationsStocklocationIsbnsGet() throws UnirestException {

        String location = "Lyon";
        String isbn = "9780739360385";

        // Try to retrieve comments for default book
        HttpResponse<String> stockAmount = Unirest.get(getServiceURL("/stocklocations/" + location + "/" + isbn)).asString();
        verifyOk(stockAmount);

        // Verify initial stock count for default book
        assert (stockAmount.getBody().equals("4"));
    }

    /**
     * Modifies the stock amount for new random book and verifies changes took effect.
     *
     * @throws UnirestException
     */
    @Test
    public void testStocklocationsStocklocationIsbnsPost() throws UnirestException {

        // Prepare random book (so test repetition does not lead to collision)
        String isbn = getRandomIsbn();
        addTestBook(isbn);
        String location = "Lyon";
        String amount = "42";

        // Try to add comment for random new book
        RequestBodyEntity o = Unirest.post(getServiceURL("/stocklocations/" + location + "/" + isbn)).header("Content-Type", "application/json").body(amount);
        HttpResponse<String> addStockReply = o.asString();
        verifyOk(addStockReply);

        // Verify resulting amount.
        HttpResponse<String> stockAmount = Unirest.get(getServiceURL("/stocklocations/" + location + "/" + isbn)).asString();
        verifyOk(stockAmount);
        assert (stockAmount.getBody().equals(amount));
    }
}
