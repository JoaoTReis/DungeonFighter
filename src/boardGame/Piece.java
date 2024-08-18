package boardGame;

import pieces.Characters;
import pieces.Elixir;
import pieces.Heroes;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//gera a posicao da peca especifica no tabuleiro
public class Piece {

    private int cont = 0;

    protected Position position;
    List<Object> allClass = new ArrayList<>();
    //List<Characters> characters = new ArrayList<>();
    //List<Elixir> elixirs = new ArrayList<>();

    public void addCharacters(){
        allClass.add(new Heroes().decideHeroi());
    }

    public void addElixir(){
        allClass.add(new Elixir());
    }

    public void printPieces(){
        if(allClass.isEmpty()){
            System.out.printf("| %-4s"," ");
        }else {
            for(Object object : allClass){
                System.out.printf("|  %-3s",object);
            }
        }
    }
}
