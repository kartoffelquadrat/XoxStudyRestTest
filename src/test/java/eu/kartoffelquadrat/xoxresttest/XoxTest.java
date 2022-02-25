package eu.kartoffelquadrat.xoxresttest;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import eu.kartoffelquadrat.xoxinternals.controller.Ranking;
import eu.kartoffelquadrat.xoxinternals.controller.XoxClaimFieldAction;
import eu.kartoffelquadrat.xoxinternals.model.Board;
import eu.kartoffelquadrat.xoxinternals.model.ModelAccessException;
import eu.kartoffelquadrat.xoxinternals.model.Player;
import eu.kartoffelquadrat.xoxinternals.model.XoxInitSettings;
import org.junit.Assert;
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
     * Test access to ranking information of a test game
     */
    @Test
    public void testXoxIdGet() throws UnirestException, ModelAccessException {

        // Add new game
        long id = addSampleGame();

        // Get Ranking info and analyze
        HttpResponse<String> rankingReply = Unirest.get(getServiceURL(Long.toString(id))).asString();
        verifyOk(rankingReply);

        // Ranking can not be deserialized conveniently, due to final / real-only fields in ranking
        Ranking ranking = new Gson().fromJson(rankingReply.getBody(), Ranking.class);

        // Verify ranking properties
        Assert.assertFalse("Access to test game marked game over while the sample game should be still running.", ranking.isGameOver());
        Assert.assertTrue("Max should have a score of 0 in the sample game, but the value listed by ranking object is not 0.", ranking.getScoreForPlayer("Max") == 0);
        Assert.assertTrue("Moritz should have a score of 0 in the sample game, but the value listed by ranking object is not 0.", ranking.getScoreForPlayer("Moritz") == 0);
    }

    /**
     * Try to delete a sample game (game is added first, uniquely for this purpose)
     *
     * @throws UnirestException
     */
    @Test
    public void testXoxIdDelete() throws UnirestException {

        // Add new game
        long id = addSampleGame();

        // Verify game id exists
        assert getAllRegisteredGameIds().contains(id);

        // Delete game again
        HttpResponse<String> deleteGameReply = Unirest.delete(getServiceURL(Long.toString(id))).asString();
        verifyOk(deleteGameReply);

        // Verify game is no longer there
        Assert.assertFalse("Deleted test game, but the list of existing game still contains its ID.", getAllRegisteredGameIds().contains(id));
    }

    /**
     * Test to verify if endpoint for board retrieval works as expected.
     *
     * @throws UnirestException
     */
    @Test
    public void testXoxIdBoardGet() throws UnirestException {


        // Add new game
        long id = addSampleGame();

        // Verify game id exists
        assert getAllRegisteredGameIds().contains(id);

        // Verify board layout (empty)
        HttpResponse<String> getBoardResponse = Unirest.get(getServiceURL(Long.toString(id) + "/board")).asString();
        Board board = new Gson().fromJson(getBoardResponse.getBody(), Board.class);

        // Verify board status (must be empty board)
        Assert.assertTrue("Sample board should be empty, but corresponding flag is false.", board.isEmpty());
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                Assert.assertTrue(board.isFree(x, y));
            }
        }
        Assert.assertFalse("Board should not have three in a line, but the corresponding field is set to true", board.isThreeInALine());
    }

    /**
     * Try to retrieve player info for sample xox game instance.
     *
     * @throws UnirestException
     */
    @Test
    public void testXoxIdPlayersGet() throws UnirestException {

        // Add new game
        long id = addSampleGame();

        // Verify game id exists
        assert getAllRegisteredGameIds().contains(id);

        // Access players resource
        HttpResponse<String> getPlayersResponse = Unirest.get(getServiceURL(Long.toString(id) + "/players")).asString();
        verifyOk(getPlayersResponse);
        Player[] players = new Gson().fromJson(getPlayersResponse.getBody(), Player[].class);

        Assert.assertTrue("Not exactly two players in sample game.", players.length == 2);
        Assert.assertTrue("First player not Max", players[0].getName().equals("Max"));
        Assert.assertTrue("First player colour not #CAFFEE", players[0].getPreferredColour().equals("#CAFFEE"));
        Assert.assertTrue("Second player not Moritz", players[1].getName().equals("Moritz"));
        Assert.assertTrue("Second player colour not #1CE7EA", players[1].getPreferredColour().equals("#1CE7EA"));
    }

    /**
     * For both players, retrieve the array of action objects (sample game) and check for length.
     *
     * @throws UnirestException
     */
    @Test
    public void testXoxIdPlayersIdActionsGet() throws UnirestException {

        // Add new game
        long id = addSampleGame();

        // Verify game id exists
        assert getAllRegisteredGameIds().contains(id);

        // Access players resource, parse response to hash indexed map
        HttpResponse<String> getActionsResponsePlayer1 = Unirest.get(getServiceURL(Long.toString(id) + "/players/Max/actions")).asString();
        verifyOk(getActionsResponsePlayer1);
        XoxClaimFieldAction[] actionsPlayer1 = new Gson().fromJson(getActionsResponsePlayer1.getBody(), new XoxClaimFieldAction[]{}.getClass());

        // All 9 fields must be accessible, there should be 9 entries in hashmap
        Assert.assertTrue("Retrieved actions bundle does not contain 9 entries, while the xox board is empty", actionsPlayer1.length == 9);

        // Do the same for player 2 (not their turn) resource, parse response to hash indexed map
        HttpResponse<String> getActionsResponsePlayer2 = Unirest.get(getServiceURL(Long.toString(id) + "/players/Moritz/actions")).asString();
        verifyOk(getActionsResponsePlayer2);
        XoxClaimFieldAction[] actionsPlayer2 = new Gson().fromJson(getActionsResponsePlayer2.getBody(), new XoxClaimFieldAction[]{}.getClass());

        // action map for player 2 must be empty (not their turn)
        Assert.assertTrue("Retrieved actions bundle does not contain 0 entries, while it is not player 2s turn.", actionsPlayer2.length == 0);
    }

    /**
     * Test placing a marker on the board, by sending a post for the corresponding hash value
     *
     * @throws UnirestException
     */
//    @Test
//    public void testXoxIdPlayersIdActionsPost() throws UnirestException {

//        // Add new game
//        long id = addSampleGame();
//
//        // Verify game id exists
//        assert getAllRegisteredGameIds().contains(id);
//
//        // Access players resource, parse response to hash indexed map
//        HttpResponse<String> postActionResponse = Unirest.post(getServiceURL(Long.toString(id) + "/players/Max/actions")).body("0").asString();
//        verifyOk(postActionResponse);

//        // Access players resource, parse response to hash indexed map
//        HttpResponse<String> getActionsResponsePlayer2 = Unirest.get(getServiceURL(Long.toString(id) + "/players/Moritz/actions")).asString();
//        verifyOk(getActionsResponsePlayer2);
//        LinkedHashMap<String, Action> actionsPlayer2 = new Gson().fromJson(getActionsResponsePlayer2.getBody(), new LinkedHashMap<String, Action>().getClass());
//
//        // Remaining 8 fields must be accessible, there should be 9 entries in hashmap
//        Assert.assertTrue("Retrieved actions bundle does not contain 9 entries, while the xox board is empty", actionsPlayer2.size()==8);
//
//        // Do the same for player 1 (not their turn) resource, parse response to hash indexed map
//        HttpResponse<String> getActionsResponsePlayer1 = Unirest.get(getServiceURL(Long.toString(id) + "/players/Max/actions")).asString();
//        verifyOk(getActionsResponsePlayer1);
//        LinkedHashMap<String, Action> actionsPlayer1 = new Gson().fromJson(getActionsResponsePlayer1.getBody(), new LinkedHashMap<String, Action>().getClass());
//
//        // action map for player 2 must be empty (not their turn)
//        Assert.assertTrue("Retrieved actions bundle does not contain 0 entries, while it is not player 1s turn.", actionsPlayer1.size()==0);

//    }

    /**
     * Helper method to look up list of all registered game IDs as collection.
     *
     * @return
     */
    private LinkedList<Long> getAllRegisteredGameIds() throws UnirestException {

        HttpResponse<String> allGamesResponse = Unirest.get(getServiceURL("")).asString();
        verifyOk(allGamesResponse);

        // Verify default sample game is present
        String allGamesString = allGamesResponse.getBody();
        Type listType = new TypeToken<LinkedList<Long>>() {
        }.getType();
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
}
