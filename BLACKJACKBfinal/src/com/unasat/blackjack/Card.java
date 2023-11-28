package com.unasat.blackjack;

public class Card {

    private Suit    suit;
    private Values  values;


    //Constructor voor de cards
    public Card(Suit suit, Values values){

        //this refers to instance of a card
        this.values = values;
        this.suit   =   suit;
    }

    //method om Suit en Value te returnen
    public String toString(){
        return this.suit.toString() + "-" + this.values.toString();
    }

    //get method voor value
    //deze class gebruiken we om values te checken in de deck class wanneer willen weten wat de value van de card is
    //om te determen hoevel punten in de player's hand zijn
    public Values getValues() {
        return this.values;
    }

}
