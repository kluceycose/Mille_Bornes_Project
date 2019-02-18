package game;

import java.util.ArrayList;
import java.util.List;

public class Player {
    //Variables//
    private String name;
    private int distance;
    private boolean rollPlayed;
    private boolean blocked;
    private boolean safety;
    private List<Card> hand;
    private BornesGame game;
    private String cardMsg;

    //Constructor//
    Player(String _name){
        name = _name;
        distance = 0;
        rollPlayed = false;
        blocked = false;
        safety = false;
        hand = new ArrayList<>();
    }

    /*Package Interface*/
    //Getters//
    String getName()                { return name; }
    boolean isBlocked()             { return blocked; }
    boolean canGo()                 { return rollPlayed; }
    boolean hasSafety()             { return safety; }
    int getDistance()               { return distance; }
    List<String> showHand(){
        List<String> handCards = new ArrayList<>();
        for(Card card: hand){
            handCards.add(card.toString());
        }
        return handCards;
    }

    //Setters//
    void setGame(BornesGame _game)  { game = _game; }
    void addToHand(Card card)       { hand.add(card); }
    void setGo(boolean bool)        { rollPlayed = bool; }
    void setBlocked(boolean bool)   { blocked = bool; }
    void setSafety(boolean bool)    { safety = bool; }
    void addDistance(int mileage)   { distance += mileage; }
    Card getCard(int cardNum)       { return hand.get(cardNum); }
    void discard(int choice)        { hand.remove(choice); }

    //Test Methods//
    void setDistance(int dist)      { distance = dist; }
    void emptyHand()                { hand.clear(); }
}
