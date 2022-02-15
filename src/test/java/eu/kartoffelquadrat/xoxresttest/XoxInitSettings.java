package eu.kartoffelquadrat.xoxresttest;

import java.util.LinkedList;

/**
 * Bean that compiles all information required to initialize a new Xox game instance. Pass an instance of this class to
 * the controller to create a new game session.
 *
 * @author Maximilian Schiedermeier
 */
public class XoxInitSettings {

    // List of players, in seating order and the preferred colours.
    LinkedList<Player> players;

    // Creator of the game. Typically the first player.
    String creator;

    /**
     * Default constructor.
     */
    public XoxInitSettings() {
    }

    /**
     * Overloaded constructor to directly set the players and indicate which player is the creator.
     *
     * @param players as the list of players participating in the game
     * @param creator as the name of the game creator (commonly the player who begins the game). Must match the string
     *                of exactly one of the player object name fields.
     */
    public XoxInitSettings(LinkedList<Player> players, String creator) {
        this.players = players;
        this.creator = creator;
    }

    /**
     * Getter for the players field.
     *
     * @return List of players participating in a new game.
     */
    public LinkedList<Player> getPlayers() {
        return players;
    }

    /**
     * Setter for the players field.
     *
     * @param players List of players participating in a new game.
     */
    public void setPlayers(LinkedList<Player> players) {
        this.players = players;
    }

    /**
     * Getter for the creator field.
     *
     * @return name of the player marked as creator.
     */
    public String getCreator() {
        return creator;
    }

    /**
     * Setter for the creator field.
     *
     * @param creator as the name of the creator. Should match the name field of one of the player objects in the
     *                players list.
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }
}
