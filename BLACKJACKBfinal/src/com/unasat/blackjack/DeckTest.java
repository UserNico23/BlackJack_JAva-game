package com.unasat.blackjack;

import org.junit.jupiter.api.Test;

import static com.unasat.blackjack.Values.FIVE;
import static com.unasat.blackjack.Values.FOUR;
import static org.junit.jupiter.api.Assertions.*;

class DeckTest {

    @Test
    public void testToString() {
        // Create a CardList instance with some cards
        Deck deck = new Deck();
        deck.addCard(new Card(Suit.HEART, Values.ACE));
        deck.addCard(new Card(Suit.CLUB, Values.TWO));
        deck.addCard(new Card(Suit.DIAMOND, Values.KING));

        // Expected output
        String expectedOutput = "\n HEART-ACE" +
                                "\n CLUB-TWO"  +
                                "\n DIAMOND-KING";




        // Get the actual output using the toString method
        String actualOutput = deck.toString();

        // Assert that the actual output matches the expected output
        assertEquals(expectedOutput, actualOutput);
    }
}








