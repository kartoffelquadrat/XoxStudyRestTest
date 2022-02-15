package eu.kartoffelquadrat.xoxresttest;

/**
 * Encodes minimal information required for launch instructions.
 *
 * @author Maximilian Schiedermeier
 */
public class Player {

    String name;
    String preferredColour;

    public Player(String name, String preferredColour) {
        this.name = name;
        this.preferredColour = preferredColour;
    }

    public Player() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPreferredColour() {
        return preferredColour;
    }

    public void setPreferredColour(String preferredColour) {
        this.preferredColour = preferredColour;
    }

    @Override
    public boolean equals(Object other) {

        if (other == null)
            return false;

        if (other.getClass() != Player.class)
            return false;

        return (name == ((Player) other).name) && (preferredColour == ((Player) other).preferredColour);
    }

    /**
     * builds JSON representation of the player object.
     *
     * @return JSON string
     */
    @Override
    public String toString() {
        return "{name:" + name + ",preferredColour:" + preferredColour + '}';
    }
}
