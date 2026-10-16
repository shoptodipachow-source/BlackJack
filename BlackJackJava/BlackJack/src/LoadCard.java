import java.util.*;

public class LoadCard {

    public static void main(String args[])
    {
        //Loads in 52 cards
        Vector cardDeck=new Vector();
        String[] strSuit={"S","H","C","D"};
        for(int i=0; i<strSuit.length;i++)
        {
            for(int j=1; j<=13; j++)
            {
                cardDeck.add(new Card(strSuit[i],j));
            }
        }

        //Shuffles them all ---- takes one card at random from the deck and puts it in a pile to shuffle
        Stack shfldDeck=new Stack(); //Has nothing yet
        while(!cardDeck.isEmpty()) //IS NOT EMPTY
        {
            int intRnd=(int)(Math.random()*cardDeck.size()); //gets a random number considering the size of the card deck
            shfldDeck.push(cardDeck.get(intRnd)); //push is adding an element to the top of the stack of shfldDeck
            cardDeck.remove(intRnd); //removes that random number from the deck.... loop runs until it is fully empty
        }
        System.out.println(shfldDeck);

        // Create players and the dealer
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nHow many players do you want?");
        int intUser = scanner.nextInt();
        Player[] players = new Player[intUser-1]; //user chooses players
        for (int i = 0; i < intUser-1; i++) {
            players[i] = new Player();
        }
        Player dealer = new Player(); //1 dealer


        //Deal initial two cards to each player and dealer
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < intUser-1; j++) {
                players[j].hands.add(shfldDeck.pop()); //Takes last element from shfldDeck and adds it to player's hands
            }
            dealer.hands.add(shfldDeck.pop()); //Takes last element from shfldDeck and adds it to dealer's hands
        }
        for(int i=0; i<players.length; i++)
        {
            System.out.println("P:"+i+" :" + players[i].hands);
        }
        System.out.println("\nDealer: " +dealer.hands.elementAt(1)); //Has 2

        for (int i=0; i<players.length; i++) {
            int intTotal = HandTotal(players[i].hands);
            System.out.println("Player "+ i+ "'s Hand Total: " + intTotal);
        }

        //each player turn
        for (int i = 0; i < players.length; i++) {
            Player currentPlayer = players[i];
            System.out.println("Player " + i + ", your turn!");
            int total1 = HandTotal(players[i].hands); //get player number total
            while (bust(total1) =! true) {
                System.out.print("Do you want to hit or stay? (H or S) ");
                String choice = scanner.nextLine();

                if (choice.equals("S")) {
                    System.out.println("Player " + i + " stands.");
                    break;
                } else if (choice.equals("H")) {
                    // Draw a card
                    String drawnCard = shfldDeck.pop().toString();
                    currentPlayer.hands.add(drawnCard);
                    System.out.println("Player " + i + " drew: " + drawnCard);

                    // Calculate total
                    int total = HandTotal(players[i].hands);
                    System.out.println("Player " + i + "'s hand total: " + total);

                    if (total > 21) {
                        System.out.println("Player " + i + " busted!");
                        bust(total) = true;
                    } else if (total == 21) {
                        System.out.println("Player " + i + " wins with a Blackjack!");
                        break;
                    }
                } else {
                    System.out.println("Invalid choice. Please choose H or S.");
                }
            }

            System.out.println("Dealer, your turn!"); //Dealers turn last
            int total3 = HandTotal(dealer.hands);
            while (bust(total3) == "false") {
                System.out.print("Do you want to hit or stay? (H or S) ");
                String choice1 = scanner.nextLine();

                if (choice1.equals("S")) {
                    System.out.println("Player " + i + " stands.");
                    break;
                } else if (choice1.equals("H")) {
                    // Draw a card
                    String drawnCard1 = shfldDeck.pop().toString();
                    dealer.hands.add(drawnCard1);
                    System.out.println("dealer drew: " + drawnCard1);

                    // Calculate total
                    int total5 = HandTotal(players[i].hands);
                    System.out.println("Player " + i + "'s hand total: " + total5);

                    if (total5 > 21) {
                        System.out.println("Player " + i + " busted!");
                        bust(total5) = "true";
                    } else if (total5 == 21) {
                        System.out.println("Dealer wins with a Blackjack!");
                        break;
                    }
                } else {
                    System.out.println("Invalid choice. Please choose H or S.");
                }
            }
        }

    }

    public static int HandTotal(Vector hand) {
        int intTotal = 0;

        for (Object obj : hand) { // Loop through each card in the hand
            Card card = (Card) obj; // Cast the object to a Card
            int value = card.getValue(); // Get the card's value
            intTotal += Math.min(value, 10); // Face cards (11, 12, 13) are treated as 10
        }

        return intTotal; // Returns total value of hand
    }

    public static String bust(int total) {
        if (total > 21) {
            return "true";
        }
        else
        {
            return "false";
        }
    }
}

