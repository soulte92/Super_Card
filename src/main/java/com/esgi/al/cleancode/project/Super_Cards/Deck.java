package com.esgi.al.cleancode.project.Super_Cards;

import java.util.ArrayList;

public class Deck {
        ArrayList<Hero> heroArrayList;

        private Deck(){
            this.heroArrayList = new ArrayList<>();
        }

        public static Deck create(){
            return new Deck();
        }

        public static Deck of(ArrayList<Hero> HeroArrayListToUse){
            Deck newDeck = new Deck();
            return newDeck;//TODO To correct
        }

        //TODO Add hero or HeroArraylist

        //TODO Retrieve Hero

        //TODO List Heros

        //TODO Get Hero

}
