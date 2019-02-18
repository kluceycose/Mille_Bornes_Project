package game;

public class HazardCard implements Card {
    private final String name = "Hazard";
    //Apply the affects of the card to the Player as appropriate
    public String play(BornesGame game){
        game.targetPlayer().setGo(false);
        game.targetPlayer().setBlocked(true);
        return game.targetPlayer().getName()+" is now blocked!";
    }

    //Check the legality of the use of the card
    public String legal(BornesGame game){
        if(game.targetPlayer().hasSafety())
            return "Target has a Safety!";
        else if(game.targetPlayer().isBlocked())
            return "Target is already blocked!";
        return "";
    }

    //Returns whether this card requires a target
    public boolean needsTarget(){
        return true;
    }

    @Override
    public String toString(){return name;}
}
