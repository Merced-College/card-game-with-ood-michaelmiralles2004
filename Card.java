package cardGame;

public class Card {
    // Instance variables to store the detailes of a Card
    private String suit;
    private String name;
    private int value;
    private String picture;

    // Constructor to make a new Card object
    public Card(String suit, String name, int value, String picture) {
        this.suit = suit;
        this.name = name;
        this.value = value;
        this.picture = picture;
    }

    // Mutator to allow us to change the cards Suit if needed
    public void setSuit(String suit) {
        this.suit = suit;
    }
    // Accessor to read what suit is
    public String getSuit() {
        return suit;
    }

    // Mutator & Accessor for name
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    // Mutator & Accessor for value
    public void setValue(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }

    // Mutator & Accessor for picture
    public void setPicture(String picture) {
        this.picture = picture;
    }
    public String getPicture() {
        return picture;
    }

    // toString method: print the card
    @Override
    public String toString() {
        return name + " of " + suit + "(value: " + value + ")";
    }
}