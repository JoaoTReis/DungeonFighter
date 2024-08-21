package boardGame;

import pieces.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//gera a posicao da peca especifica no tabuleiro
public class Piece {

    List<Characters> characters = new ArrayList<>();
    List<Items> items = new ArrayList<>();

    public List<Characters> getCharacters() {
        return characters;
    }

    public void addCharacter(Characters character){
        characters.add(character);
    }

    public void addItems(Items item){
        items.add(item);
    }


    public void removePiece(Object object){
        characters.remove(object);
    }



    public boolean hasPiece() {
        return characters.isEmpty() && items.isEmpty();
    }

    public boolean hasElixir() {
        for(Items object : items){
            if(object instanceof Elixir){
                return true;
            }
        }
        return false;
    }

    public boolean hasNormalMonster() {
        for(Characters object : characters){
            if(object instanceof NormalMonster){
                return true;
            }
        }
        return false;
    }

    public boolean hastrapFixed() {
        for(Items object : items){
            if(object instanceof Fixed){
                return true;
            }
        }
        return false;
    }

    public boolean hasTrapVariable() {
        for(Items object : items){
            if(object instanceof Variable){
                return true;
            }
        }
        return false;
    }

    public boolean hasBoss() {
        for(Characters object : characters){
            if(object instanceof Boss){
                return true;
            }
        }
        return false;
    }

    public void printPieces(){
        if(characters.isEmpty() && items.isEmpty()){
            System.out.printf("| %-4s"," ");
        }else {
            for(Characters object : characters){
                System.out.printf("|  %-3s",object);
            }
            for(Items object : items){
                System.out.printf("|  %-3s",object);
            }
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(characters.toArray())+
                Arrays.toString(items.toArray()); // Converte a lista em um array e usa o toString
    }

}
