package game;

import java.util.*;

public class BornesGame {
    //Variables//
    private List<Player> players;
    private List<Card> deck;
    static final int goalDistance = 250;
    private final int fixedSeed = 200;
    private int turn, numPlayers, target;
    private final String badPlayMsg = "Card cannot be played: ";

    //Constructors
    //Default Constructor
    //Generates a random deck
    public BornesGame(){
        players = new ArrayList<>();
        deck = new ArrayList<>();
        initialize(null);
        turn = 0;
    }

    /*Argumented Constructor
     *Initializes a specific test scenario if "test1" is
     * entered at the command line
     *Initializes a random, but repeatable deck if "fixed"
     * is entered at the command line
     *Initializes a random deck if anything else is entered
     * at the command line*/
    public BornesGame(String arg){
        players = new ArrayList<>();
        deck = new ArrayList<>();
        if(arg.equals("test1")){
            test1Initialize();
        }
        else if(arg.equals("fixed")){
            Random rand = new Random(fixedSeed);
            initialize(rand);
        }
        else{
            initialize(null);
        }
    }

    //Deck initializer for non-test runs
    private void initialize(Random rand){
        for(int i=0; i<14; i++){
            deck.add(new RollCard());
        }
        for(int i=0; i<10; i++){
            deck.add(new MileageCard(25));
        }
        for(int i=0; i<10; i++){
            deck.add(new MileageCard(50));
        }
        for(int i=0; i<10; i++){
            deck.add(new MileageCard(75));
        }
        for(int i=0; i<12; i++){
            deck.add(new MileageCard(100));
        }
        for(int i=0; i<4; i++){
            deck.add(new MileageCard(200));
        }
        for(int i=0; i<18; i++){
            deck.add(new HazardCard());
        }
        for(int i=0; i<18; i++){
            deck.add(new HazardCard());
        }
        for(int i=0; i<24; i++){
            deck.add(new RemedyCard());
        }
        for(int i=0; i<4; i++){
            deck.add(new SafetyCard());
        }
        if(rand == null)
            Collections.shuffle(deck);
        else
            Collections.shuffle(deck, rand);

    }

    //Deck initializer for test run1
    private void test1Initialize(){
        List<List<Card>> testCards = new ArrayList<>();
        for(int i=0; i<6; i++){
            testCards.add(new ArrayList<>());
        }
        for(int i=0; i<14; i++){
            testCards.get(0).add(new RollCard());
        }
        for(int i=0; i<10; i++){
            testCards.get(1).add(new MileageCard(25));
        }
        for(int i=0; i<10; i++){
            testCards.get(2).add(new MileageCard(50));
        }
        for(int i=0; i<10; i++){
            testCards.get(3).add(new MileageCard(75));
        }
        for(int i=0; i<12; i++){
            testCards.get(4).add(new MileageCard(100));
        }
        for(int i=0; i<4; i++){
            testCards.get(5).add(new MileageCard(200));
        }
        deck.add(testCards.get(2).remove(0));
        deck.add(testCards.get(3).remove(0));
        deck.add(testCards.get(2).remove(0));
        deck.add(testCards.get(2).remove(0));
        deck.add(testCards.get(2).remove(0));
        deck.add(testCards.get(1).remove(0));
        deck.add(testCards.get(1).remove(0));
        deck.add(testCards.get(0).remove(0));
        deck.add(testCards.get(1).remove(0));
        deck.add(testCards.get(2).remove(0));
        deck.add(testCards.get(1).remove(0));
        deck.add(testCards.get(0).remove(0));
        deck.add(testCards.get(1).remove(0));
        deck.add(testCards.get(1).remove(0));
        deck.add(testCards.get(4).remove(0));
        deck.add(testCards.get(2).remove(0));
        deck.add(testCards.get(0).remove(0));
        deck.add(testCards.get(2).remove(0));
        deck.add(testCards.get(0).remove(0));
        deck.add(testCards.get(1).remove(0));
        deck.add(testCards.get(5).remove(0));
        deck.add(testCards.get(0).remove(0));
        deck.add(testCards.get(4).remove(0));
        deck.add(testCards.get(2).remove(0));
        deck.add(testCards.get(3).remove(0));
        deck.add(testCards.get(3).remove(0));
        List<Card> temp = new ArrayList<>();
        for(List<Card> list: testCards){
            for(int i=0; i<list.size(); i++){
                temp.add(list.remove(0));
            }
        }
        Collections.shuffle(temp);
        for(int i=0; i<temp.size(); i++){
            deck.add(temp.remove(0));
        }
    }

    /*Public Interface*/
    //Game Interface//
    public int getGoal()    { return goalDistance; }//Return the winning distance
    public int getTurn()    { return turn; }        //Return the player's turn number
    public void nextTurn(){                         //Move to the next player's turn
        turn++;
        if(turn == numPlayers)
            turn = 0;
    }
    public boolean gameDone(){
        return (goalReached() || (deckEmpty()&&playersEmpty()));
    }
    public String getWinner(){
        if(goalReached())
            return players.get(turn).getName();
        Player p;
        for(int i=0; i<numPlayers; i++){
            for(int j=i+1; j<numPlayers; j++){
                if(players.get(i).getDistance()<players.get(j).getDistance()){
                    p = players.get(i);
                    players.set(i, players.get(j));
                    players.set(j, p);
                }
            }
        }
        return players.get(0).getName();
    }

    //Player Interface//
    //Initializes the number of players passed by the client and returns them
    public void initializePlayers(int _numPlayers){
        numPlayers = _numPlayers;
        for(int i = 0; i < numPlayers; i++) {
            players.add(new Player("Player " + (i + 1)));
            players.get(i).setGame(this);
        }
        for(Player p: players){
            for(int j=0; j<6; j++){
                p.addToHand(deck.remove(0));
            }
        }
    }
    //Returns a list of the names of the players
    public List<String> playerList(){
        List<String> pList = new ArrayList<>();
        for(Player p: players){
            pList.add(p.getName());
        }
        return pList;
    }
    //Draws a card from the deck and adds it to the player's hand
    public void draw(){
        players.get(turn).addToHand(deck.remove(0));
    }
    //Discards a chosen card from the Player's hand
    public String discard(int cardChoice) {
        String s = players.get(turn).getCard(cardChoice).toString();
        players.get(turn).discard(cardChoice);
        return s;
    }
    //Returns the distance traveled by the current player
    public int distanceTraveled()       { return players.get(turn).getDistance(); }
    //Returns list of card names in current Player's hand
    public List<String> getHand()       { return players.get(turn).showHand(); }


    //Card Interface//
    //Returns true if a card is legal to play, false otherwise
    public String isLegal(int cardNum){
        String s = players.get(turn).getCard(cardNum).legal(this);
        if (s.length()==0){
            return s;
        }
        return badPlayMsg +s;
    }
    //Returns true if a card needs a target, false otherwise
    public boolean needsTarget(int cardNum){
        return players.get(turn).getCard(cardNum).needsTarget();
    }
    //Sets the target for the next card to be played that needs one
    public void setTarget(int _target){ target = _target; }
    //Play the card chosen by the player
    public String play(int cardNum){
        String s = players.get(turn).getCard(cardNum).play(this);
        players.get(turn).discard(cardNum);
        return s;
    }

    /*Package Interface*/
    //Getters//
    Player targetPlayer(){ return players.get(target); }
    Player getPlayer(){return players.get(turn);}

    //Win condition checkers
    private boolean goalReached(){
        for(int i=0; i<numPlayers; i++){
            if(players.get(i).getDistance() >= goalDistance) {
                return true;
            }
        }
        return false;
    }
    private boolean deckEmpty(){
        return deck.isEmpty();
    }
    private boolean playersEmpty(){
        for(Player p: players){
            if(!p.showHand().isEmpty())
                return false;
        }
        return true;
    }

    //Test Methods//
    void emptyDeck()            {deck.clear();}
}
