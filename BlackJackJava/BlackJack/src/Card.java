public class Card {

    private String strSuit;
    private int intVal;

    public Card(String suit, int val)
    {
        this.strSuit=suit;
        this.intVal=val;
    }


    // Getter method to retrieve the value of the card
    public int getValue() {
        //If value is 1 (Ace), returned as 1
        //11, 12, 13 are treated as 10
        if (intVal >= 11 && intVal <= 13) {
            return 10;  // Face cards (Jack, Queen, King) are worth 10
        }
        return intVal; // For other cards (2-10), return the card's value as is
    }

    public String toString()
    {
        String strData=strSuit+" "+intVal;
        return strData;
    }

}
