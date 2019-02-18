package game;

public class RemedyCard implements Card {
    private final String name = "Remedy";
    //Apply the affects of the card to the Player as appropriate
    public String play(BornesGame game){
        game.getPlayer().setBlocked(false);
        return game.getPlayer().getName() + " is no longer blocked!";
    }

    //Check the legality of the use of the card
    public String legal(BornesGame game){
        if(!game.getPlayer().isBlocked()){
            return game.getPlayer().getName()+" isn't blocked!";
        }
        return "";
    }

    //Returns whether this card requires a target
    public boolean needsTarget(){
        return false;
    }

    @Override
    public String toString(){return name;}
}
