package cardGame;

import java.io.File;
import java.util.Collections;
import java.util.Comparator;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CardGame {

	private static ArrayList<Card> deckOfCards = new ArrayList<Card>();
	private static ArrayList<Card> playerCards = new ArrayList<Card>();


	public static void main(String[] args) {


		Scanner input = null;
		try {
			input = new Scanner(new File("cards.txt"));
		} catch (FileNotFoundException e) {
			// error
			System.out.println("error");
			e.printStackTrace();
		}

		while(input.hasNext()) {
			String[] fields  = input.nextLine().split(",");
			//	public Card(String cardSuit, String cardName, int cardValue, String cardPicture) {
			Card newCard = new Card(fields[0], fields[1].trim(),
					Integer.parseInt(fields[2].trim()), fields[3]);
			deckOfCards.add(newCard);	
		}

		shuffle();

		//for(Card c: deckOfCards)
			//System.out.println(c);

		//deal the player 5 cards
		for(int i = 0; i < 4; i++) {
			playerCards.add(deckOfCards.remove(i));
		}
		
		System.out.println("players cards");
		for(Card c: playerCards)
			System.out.println(c);

		System.out.println("pairs is " + checkFor2Kind());


		// Print out player's score by calling the methond scoreCalc
		System.out.println("Player score: " + scoreCalc(playerCards));

		// Creating a new scanner and calling the method sortPlaterHand
		Scanner scanner = new Scanner(System.in);
		sortPlayerHand(playerCards, scanner);

	}//end main

	public static void shuffle() {

		//shuffling the cards by deleting and reinserting
		for (int i = 0; i < deckOfCards.size(); i++) {
			int index = (int) (Math.random()*deckOfCards.size());
			Card c = deckOfCards.remove(index);
			//System.out.println("c is " + c + ", index is " + index);
			deckOfCards.add(c);
		}
	}

	//check for 2 of a kind in the players hand
	public static boolean checkFor2Kind() {

		int count = 0;
		for(int i = 0; i < playerCards.size() - 1; i++) {
			Card current = playerCards.get(i);
			Card next = playerCards.get(i+1);
			
			for(int j = i+1; j < playerCards.size(); j++) {
				next = playerCards.get(j);
				//System.out.println(" comparing " + current);
				//System.out.println(" to " + next);
				if(current.equals(next))
					count++;
			}//end of inner for
			if(count == 1)
				return true;

		}//end outer for
		return false;
	}

	// New feature calculate score
	public static int scoreCalc(ArrayList<Card> hand) {
		int total = 0;
		for(Card c : hand) {
			total += c.getValue();
		}
		return total;
	}

	// New feature sort the player's hand
	public static void sortPlayerHand(ArrayList<Card> playerCards, Scanner scanner) {
		int sortingChoice;
		System.out.println("How would you like to sort your hand?");
		System.out.println("You can sort it by 1. Card value or 2. Card name. Please choose either 1 or 2.");
		sortingChoice = scanner.nextInt();

		// Compare two cards by value using c.getValue()
		if (sortingChoice == 1) {
			// Sorts a list based on the custom rule which we define using Comparator
			Collections.sort(playerCards, new Comparator<Card>() {
				// Define how two Card objects should be compared
				public int compare(Card c1, Card c2) {
					return Integer.compare(c1.getValue(), c2.getValue());
				}
			});
			System.out.println("You hand has been sorted by value: ");
		}

		// Uses comparator to compare card name
		else if (sortingChoice == 2) {
			Collections.sort(playerCards, new Comparator<Card>() {
				public int compare(Card c1, Card c2) {
					return c1.getName().compareTo(c2.getName());
				}
			});
			System.out.println("Sorted by name:");
		}
		else {
			System.out.println("Invalid input.");
		}

		// Print final hand
		for (Card c : playerCards) {
			System.out.println(c);
		}
	}
}//end class
