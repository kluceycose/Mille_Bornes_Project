//Ken Lucey-Cose
//CS401 F 11-4:30
//HW2
//Simplified Mille Bornes game

package client;

import game.BornesGame;

import java.util.List;
import java.util.Scanner;

public class PlayBornes {
    private static BornesGame bornesGame;
    private static final Scanner STDIN = new Scanner(System.in);
    private static List<String> playerList; //List of Player names
    private static boolean done, legal;     //Done controls the gameplay loop,
                                            // legal controls the choice loop
    private static int cardChoice;          //Holds the player's choice of card
    private static String playChoice;       //Holds the player's choice of play
    private static List<String> hand;       //Holds the names of the cards in the player's hand
    private static String name, cardMsg;    //name is current Player's name,
                                            // cardMsg takes the messages returned by cards
    public static void main(String[] args){
        double version = 0.2;
        System.out.print("Simple Mille Bornes v"+version+"\n" +
                "Would you like to use a random deck, " +
                "or one that's always the same?\n" +
                "1) Random Deck\n2) Fixed deck\n" +
                "Please enter a choice(1-2): ");
        if(STDIN.nextInt()==2)
            bornesGame = new BornesGame("fixed");
        else
            bornesGame = new BornesGame();
        bornesGame.initializePlayers(2);
        playerList = bornesGame.playerList();
        System.out.println("First player to exactly "
                + bornesGame.getGoal() +"km wins!\n");
        done = false;
        do{
            bornesGame.draw();
            hand = bornesGame.getHand();
            name = playerList.get(bornesGame.getTurn());
            legal = false;
            System.out.println(name + " is at " +
                    bornesGame.distanceTraveled() + "km");
            System.out.println(name + "'s hand:");
            for(int i=0; i<hand.size(); i++){
                System.out.println((i+1) + ". " + hand.get(i));
            }
            while(!legal){
                System.out.print("\nOptions:\na. Play a card\nb. Discard a card\n" +
                        "Choice (a or b): ");
                playChoice = STDIN.next();
                switch (playChoice){
                    case "a":
                        System.out.print("Choose a card to play (1-7): ");
                        cardChoice = STDIN.nextInt()-1;
                        if(bornesGame.needsTarget(cardChoice)){
                            System.out.println("Choose a target player:");
                            for(int i=0; i<playerList.size(); i++){
                                System.out.println((i+1)
                                        + ". " + playerList.get(i));
                            }
                            bornesGame.setTarget(STDIN.nextInt()-1);
                        }
                        cardMsg = bornesGame.isLegal(cardChoice);
                        if(cardMsg.length()>0){
                            System.out.println(cardMsg);
                            break;
                        }
                        cardMsg = bornesGame.play(cardChoice);
                        System.out.println(cardMsg);
                        System.out.println(name + " is now at "
                                +bornesGame.distanceTraveled()+"km");
                        done = bornesGame.gameDone();
                        legal = true;
                        break;
                    case "b":
                        System.out.print("Choose a card to discard (1-7): ");
                        cardChoice = STDIN.nextInt()-1;
                        cardMsg = bornesGame.discard(cardChoice);
                        System.out.println("Discarded a "+ cardMsg +" card");
                        System.out.println(name + " is now at "
                                +bornesGame.distanceTraveled()+"km");
                        done = bornesGame.gameDone();
                        legal = true;
                        break;
                    default:
                        System.out.println("Please choose a or b");
                        break;
                }
            }
            if(!done){
                System.out.println("End of " + name + "'s turn\n");
                bornesGame.nextTurn();
            }
        }while(!done);
        name = bornesGame.getWinner();
        System.out.println("Congratulations, " + name + "! You win!");
    }
}