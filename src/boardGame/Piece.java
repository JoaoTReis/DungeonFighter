package boardGame;

import pieces.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//gera a posicao da peca especifica no tabuleiro
public class Piece {

    private List<Characters> characters ;
    private List<Items> items ;

    public Piece() {
        this.characters = new ArrayList<>();;
        this.items = new ArrayList<>();
    }

    public List<Characters> getCharacters() {
        return characters;
    }

    public List<Items> getItems() {
        return items;
    }

    public Monster getMonster() {
        for (Characters object : characters) {
            if (object instanceof NormalMonster) {
                return (NormalMonster) object;
            }
            if (object instanceof Boss) {
                return (Boss) object;
            }
        }
        return null; // Retorna null se nenhum monstro for encontrado
    }

    public void addCharacter(Characters character){
        characters.add(character);
    }

    public void addItems(Items item){
        items.add(item);
    }


    public void removePieceCharecters(Characters object) {
        if (object != null) {
            characters.remove(object);
        }
    }
    public void removePieceItems(Items object){
        if(object != null){
            items.remove(object);
        }
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
        return getMonster() != null;
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
