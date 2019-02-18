package game;

public class SafetyCard implements Card {
    private final String name = "Safety";
    //Apply the affects of the card to the Player as appropriate
    public String play(BornesGame game){
        game.getPlayer().setSafety(true);
        game.getPlayer().setBlocked(false);
        game.getPlayer().setGo(true);
        return game.getPlayer().getName()+" played a SafetyCard! " +
                "Nothing can stop them now!";
    }

    //Check the legality of the use of the card
    public String legal(BornesGame game){
        return "";
    }

    //Returns whether this card requires a target
    public boolean needsTarget(){
        return false;
    }

    @Override
    public String toString(){return name;}
}
