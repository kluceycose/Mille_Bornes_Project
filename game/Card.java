package game;

interface Card {
    //Apply the affects of the card to the Player as appropriate
    String play(BornesGame game);
    //Check the legality of the use of the card
    String legal(BornesGame game);
    //Returns whether this card requires a target
    boolean needsTarget();
}
