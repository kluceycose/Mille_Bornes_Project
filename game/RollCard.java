package game;

class RollCard implements Card {
    private String name;

    //Constructor//
    RollCard(){
        name = "Roll";
    }

    //Apply the affects of the card to the Player as appropriate//
    public String play(BornesGame game){
        game.getPlayer().setGo(true);
        return "Roll card played! Let's go, "+game.getPlayer().getName()+"!";
    }
    //Check the legality of the use of the card
    public String legal(BornesGame game){
        if(game.getPlayer().isBlocked())
            return game.getPlayer().getName()+" is blocked by a Hazard!";
        else if(game.getPlayer().canGo())
            return game.getPlayer().getName()+" has already played a Roll card!";
        return "";
    }
    //Returns whether this card requires a target
    public boolean needsTarget(){
        return false;
    }
    @Override
    //Getter for name//
    public String toString(){
        return name;
    }
}
