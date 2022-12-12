package com.esgi.al.cleancode.project.Super_Cards.domain;

import java.util.ArrayList;

public class Deck {
        ArrayList<Hero> heroArrayList;

        private Deck(){
            this.heroArrayList = new ArrayList<>();
        }

        public static Deck create(){
            return new Deck();
        }

        public static Deck of(ArrayList<Hero> heroArrayListToUse){
            Deck newDeck = new Deck();
            newDeck = newDeck.addHeros(heroArrayListToUse);
            return newDeck;
        }

        public Deck addHeros(ArrayList<Hero> heroArrayListToUse){
            Deck newDeck = new Deck();
            newDeck.heroArrayList = this.heroArrayList;
            newDeck.heroArrayList.addAll(heroArrayListToUse);
            return newDeck;
        }

        public Deck retrieveHeroByIndex(int heroIndex){
            Deck newDeck = new Deck();
            newDeck.heroArrayList = this.heroArrayList;
            newDeck.heroArrayList.remove(heroIndex);
            return newDeck;
        }

        public Hero getHeroByIndex(int heroIndex){
            return this.heroArrayList.get(heroIndex);
        }
}
