package com.unasat.blackjack;

import java.util.ArrayList;
import java.util.Random;

public class Deck {

    //constructor maken voor Deck
    //isntance variable Arraylisy cards
    //Arraylistt van cards


    //instance vars
    private ArrayList<Card> cards;

    //construtor
    public Deck(){

        this.cards = new ArrayList<Card>();
    }

    //method om een full deck of cards te maken//fac.meth
    public void createFullDeck(){
        //Cards genereren
        for (Suit cardSuit : Suit.values()){
            for(Values cardValue : Values.values()){
                //add new card to the deck
                this.cards.add(new Card(cardSuit, cardValue));
            }
        }
    }

    //method om deck class te shufflen
   /* public void shuffle(){
      ArrayList<Card> tmpDeck = new ArrayList<Card>();
      //random object gebruiken om random numbers te genereren
        Random random = new Random();
        int randomCardIndex = 0;
        int originalSize = this.cards.size();

        for(int i = 0; 1 < originalSize; i++){
            //generate random index rand.nextInt((max - min) + 1) + min;
            randomCardIndex = random.nextInt((this.cards.size() - 1) + 1);
            tmpDeck.add(this.cards.get(randomCardIndex));

            //remove from original deck
            this.cards.remove(randomCardIndex);
        }
        this.cards = tmpDeck;
    }*/

    public void shuffle() {
        ArrayList<Card> tmpDeck = new ArrayList<Card>();
        Random random = new Random();
        int randomCardIndex = 0;
        int originalSize = this.cards.size();

        for (int i = 0; i < originalSize; i++) {
            randomCardIndex = random.nextInt((this.cards.size() - 1 - 0) + 1);
            tmpDeck.add(this.cards.get(randomCardIndex));
            this.cards.remove(randomCardIndex);
        }
        this.cards = tmpDeck;
    }

    //return String met alle cards
    public String toString(){
        String cardListOutPut = "";
        //Loop dat String uitprint met alle cardvalues
         for (Card aCard : this.cards){
             cardListOutPut += "\n " + aCard.toString();
         }
         return cardListOutPut;
    }
//getters and setters
    public void removeCard(int i){
        this.cards.remove(i);
    }

    public Card getCard(int i){
        return this.cards.get(i);
    }

    public void addCard(Card addCard){
        this.cards.add(addCard);
    }

    //Draw card van de Deck
    public void draw(Deck comingFrom){
        this.cards.add(comingFrom.getCard(0));
        comingFrom.removeCard(0);
    }

    public int deckSize(){
        return this.cards.size();
    }

    public void moveAllToDeck(Deck moveTo){
        int thisDecksize = this.cards.size();

        //put cars into moveTo deck
        for(int i = 0; i < thisDecksize; i++){
           moveTo.addCard(this.getCard(i));
        }

        for(int i = 0; i < thisDecksize; i++){
            this.removeCard(0);
        }
    }

//return total value of cards in deck
    public int cardsValue() {
        int totalValue = 0;
        int aces = 0;

        for (Card aCard : this.cards) {
            switch (aCard.getValues()) {
                case TWO:
                    totalValue += 2;
                    break;
                case THREE:
                    totalValue += 3;
                    break;
                case FOUR:
                    totalValue += 4;
                    break;
                case FIVE:
                    totalValue += 5;
                    break;
                case SIX:
                    totalValue += 6;
                    break;
                case SEVEN:
                    totalValue += 7;
                    break;
                case EIGHT:
                    totalValue += 8;
                    break;
                case NINE:
                    totalValue += 9;
                    break;
                case TEN:
                    totalValue += 10;
                    break;
                case JACK:
                    totalValue += 10;
                    break;
                case QUEEN:
                    totalValue += 10;
                    break;
                case KING:
                    totalValue += 10;
                    break;
                case ACE:
                    aces += 1;
                    break;
            }
        }

        for (int i = 0; i < aces; i++) {

            if (totalValue > 10) {
                totalValue += 1;
            } else {
                totalValue += 11;
            }
        }

        return totalValue;
    }


}
