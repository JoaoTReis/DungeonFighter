package boardGame;

import pieces.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//gera a posicao da peca especifica no tabuleiro
public class Piece {

    private Board board;

    protected Position position;
    List<Object> allClass = new ArrayList<>();
    private Object currentPiece;

    public void addHero(Object object){
        allClass.add(object);
    }

    public void addNormalMonster(Object object){
        allClass.add(object);
    }

    public void addBoss(Object object){
        allClass.add(object);
    }

    public void addElixir(Object object){
        allClass.add(object);
    }

    public void addTrapFixed(Object object){
        allClass.add(object);
    }

    public void addTrapVariable(Object object){
        allClass.add(object);
    }

    public boolean hasPiece() {
        return allClass.isEmpty();
    }

    public boolean hasElixir() {
        for(Object object : allClass){
            if(object instanceof Elixir){
                return true;
            }
        }
        return false;
    }

    public void removePiece(Object object){
        allClass.remove(object);
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

    @Override
    public String toString() {
        return Arrays.toString(allClass.toArray()); // Converte a lista em um array e usa o toString
    }

}
