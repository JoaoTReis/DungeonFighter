package gameActions;

import boardGame.Piece;

public interface Actions {
    void fight(Piece hero);
    void getElixir(Piece hero);
    void damageTrap(Piece hero);
}
