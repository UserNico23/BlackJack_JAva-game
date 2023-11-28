package com.unasat.blackjack;

import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Blackjack {

    public static void main(String[] args) {

        String filepath = "Mario death/Game over sound effect.wav";
/*
        soundeffects gameOverSound = new soundeffects();
        gameOverSound.playSound(filepath);*/




        // Welcome message
        System.out.println("Welcome to BlackJack!");

        // Create and shuffle deck using the API
        String deckId = createAndShuffleDeck();
        if (deckId == null) {
            System.out.println("Failed to create and shuffle the deck. Exiting...");
            return;
        }

        // Deck maken
        Deck playingDeck = new Deck();
        playingDeck.createFullDeck();
        playingDeck.shuffle();

        // Deck creëren voor de player
        Deck playerDeck = new Deck();
        Deck dealerDeck = new Deck();

        double player_Money = 100.00;

        Scanner userInput = new Scanner(System.in);

        // Game loop (met while loops)
        while (player_Money > 0) {
            //door spelen
            //Neem de bet van de player
            System.out.println("you currently have $" + player_Money + ", how much would you like to bet?");
            double player_Bet = userInput.nextDouble();
            if(player_Bet > player_Money){
                System.out.println("UNPOSSIBLE!!!!");
                break;
            }

            boolean endRound = false;

            //dealen
            //player krijgt twee cards
            playerDeck.draw(playingDeck);
            playerDeck.draw(playingDeck);

            //dealer krijgt ook 2 cards
            dealerDeck.draw(playingDeck);
            dealerDeck.draw(playingDeck);

            while(true){
                System.out.println("Your hand: ");
                System.out.println(playerDeck.toString());
                System.out.println("Your hand is valued at: " + playerDeck.cardsValue());


                //desplay dealer hand
                System.out.println("Dealer Hand: " + dealerDeck.getCard(0).toString() + "and [hidden]");

                //what wilt de player doen
                System.out.println("Would you like to (1)Hit or (2) Stand?");
                int response = userInput.nextInt();

                //they hit
                if(response == 1){
                    playerDeck.draw(playingDeck);
                    System.out.println("You draw a: " + playerDeck.getCard(playerDeck.deckSize()-1).toString());
                    //bust if > 21
                    if(playerDeck.cardsValue() > 21){
                        System.out.println("Bust, Currently Valued at: " + playerDeck.cardsValue());
                        player_Money -= player_Bet;
                        endRound = true;
                        break;
                    }
                    soundeffects gameOverSound = new soundeffects();
                    gameOverSound.playSound(filepath);
                }

                if(response == 2){
                    break;
                }

            }

            //reveal dealer cards
            System.out.println("Dealer Cards: " + dealerDeck.toString());
            //see if dealer has more points than player
            if((dealerDeck.cardsValue() > playerDeck.cardsValue()) && endRound == false){
                System.out.println("Dealer won!");
                player_Money -= player_Bet;
                endRound = true;
            }

            //Dealer draws tot 16, stand op 17
            while((dealerDeck.cardsValue() < 17) && endRound == false){
                dealerDeck.draw(playingDeck);
                System.out.println("Dealer draws: " + dealerDeck.getCard(dealerDeck.deckSize()-1).toString());

            }
            //desplay TOtale value for delaer
            System.out.println("Dealer's hand is Valued at: " + dealerDeck.cardsValue());
            //determine if dealer busted
            if((dealerDeck.cardsValue() > 21) && endRound == false){
                System.out.println("Dealer busts, you win!");
                player_Money += player_Bet;
                endRound = true;
            }

            //determine if push
            if((playerDeck.cardsValue() == dealerDeck.cardsValue()) && endRound == false){
                System.out.println("push");
                endRound = true;
            }

            if((playerDeck.cardsValue() > dealerDeck.cardsValue()) && endRound == false){
                System.out.println("You win the HAND!!!");
                player_Money += player_Bet;
                endRound = true;
            }

            playerDeck.moveAllToDeck(playingDeck);
            dealerDeck.moveAllToDeck(playingDeck);
            System.out.println("End of hand.");
        }


        System.out.println("Game OVER!!");

    }


    private static String createAndShuffleDeck() {
        try {
            // Create a URL object with the API endpoint
            URL url = new URL("https://www.deckofcardsapi.com/api/deck/new/shuffle/?deck_count=1");

            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set request method to GET
            connection.setRequestMethod("GET");

            // Get the response code
            int responseCode = connection.getResponseCode();

            // If the response code is OK (200), read the response
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                // Parse the JSON response to get the deck ID
                String jsonResponse = response.toString();
                int start = jsonResponse.indexOf("\"deck_id\":") + 11;
                int end = jsonResponse.indexOf("\"", start);
                return jsonResponse.substring(start, end);
            } else {
                System.out.println("API request failed with response code: " + responseCode);
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
        return null;
    }
}


