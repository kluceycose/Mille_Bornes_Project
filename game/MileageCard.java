package game;

class MileageCard implements Card {
    final private String name;
    private int distance;

    //Constructor//
    MileageCard(int _distance){
        distance = _distance;
        name = "Mileage " + distance;
    }

    //Apply the affects of the card to the Player as appropriate
    public String play(BornesGame game){
        game.getPlayer().addDistance(distance);
        return game.getPlayer().getName()+" traveled "+distance+"km!";
    }

    //Check the legality of the use of the card
    public String legal(BornesGame game){
        if(!game.getPlayer().canGo())
            return game.getPlayer().getName()+" hasn't played a Roll card!";
        else if((game.getPlayer().getDistance()+distance)>BornesGame.goalDistance)
            return name+" card will put "+game.getPlayer().getName()
                    +" over the "+game.getGoal()+"km goal!";
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
