package eu.kartoffelquadrat.xoxresttest;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.Test;

import java.lang.reflect.Type;
import java.util.LinkedList;

/**
 * All Unit tests for Xox Resources
 */
public class XoxTest
        extends RestTestUtils {

    /**
     * Try to retrieve list of all games. Verify the default sample game is present.
     */
    @Test
    public void testXoxGet() throws UnirestException {

        long sampleGameId = 42;
        assert getAllRegisteredGameIds().contains(sampleGameId);
    }

    /**
     * Try to add new game
     */
    @Test
    public void testXoxPost() throws UnirestException {

        // Add new game
        long id = addSampleGame();

        // Verify the game exists
        assert getAllRegisteredGameIds().contains(id);

    }

    /**
     * Helper method to look up list of all registered game IDs as collection.
     * @return
     */
    private LinkedList<Long> getAllRegisteredGameIds() throws UnirestException {

        HttpResponse<String> allGamesResponse = Unirest.get(getServiceURL("")).asString();
        verifyOk(allGamesResponse);

        // Verify default sample game is present
        String allGamesString = allGamesResponse.getBody();
        Type listType = new TypeToken<LinkedList<Long>>(){}.getType();
        LinkedList<Long> allGameIds = new Gson().fromJson(allGamesString, listType);
        return allGameIds;
    }

    /**
     * Helper method to add a new sample game to the backend.
     *
     * @return HttpResponse<String> that encodes server reply.
     */
    private long addSampleGame() throws UnirestException {
        // Try to add new Game
        LinkedList<Player> players = new LinkedList<>();
        players.add(new Player("Max", "#CAFFEE"));
        players.add(new Player("Moritz", "#1CE7EA"));
        XoxInitSettings testSettings = new XoxInitSettings(players, "Max");

        // String JSON-encoded testSettings:
        String jsonTestSettings = new Gson().toJson(testSettings);
        HttpResponse<String> addGameResponse = Unirest.post(getServiceURL("")).header("Content-Type", "application/json").body(jsonTestSettings).asString();
        verifyOk(addGameResponse);
        return Long.parseLong(addGameResponse.getBody());
    }


//    /**
//     * Verify that GET on /isbns returns 200 and expected catalogue. Every resource is covered by exactly one test.
//     */
//    @Test
//    public void testIsbnsGet() throws UnirestException {
//
//        // Try to retrieve catalogue
//        HttpResponse<String> catalogue = Unirest.get(getServiceURL("/isbns")).asString();
//        verifyOk(catalogue);
//
//        // Verify and return catalogue content
//        String body = catalogue.getBody();
//        assert body.contains("9780739360385");
//        assert body.contains("9780553382563");
//        assert body.contains("9781977791122");
//        assert body.contains("9780262538473");
//    }
//
//    /**
//     * Verify that GET on /isbns/{isbn} returns 200 and expected book details
//     */
//    @Test
//    public void testIsbnsIsbnGet() throws UnirestException {
//
//        // Try to retrieve catalogue
//        HttpResponse<String> bookDetails = Unirest.get(getServiceURL("/isbns/9780739360385")).asString();
//        verifyOk(bookDetails);
//
//        // Verify catalogue content
//        assert bookDetails.getBody().contains("priceInCents");
//        assert bookDetails.getBody().contains("bookAbstract");
//    }
//
//    /**
//     * Verify that PUT on /isbns/{isbn} returns 200 and allows adding a book to catalogue. Also verifies the new isbn
//     * appears in list and subsequently removes it, to leave server is original state.
//     */
//    @Test
//    public void testIsbnsIsbnPut() throws UnirestException {
//
//        // Using a random ISBN to avoid clash on multiple test run.
//        String randomIsbn = getRandomIsbn();
//        HttpResponse<String> addBookReply = addTestBook(randomIsbn);
//        verifyOk(addBookReply);
//
//        // Verify catalogue content (must now contain the new book)
//        String catalogue = Unirest.get(getServiceURL("/isbns")).asString().getBody();
//        assert catalogue.contains(randomIsbn);
//    }

}
