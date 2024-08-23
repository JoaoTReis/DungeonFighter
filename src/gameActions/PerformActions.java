package gameActions;

import boardGame.Board;
import boardGame.Piece;
import pieces.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ThreadLocalRandom;

public class PerformActions implements Actions{

    private JPanel actionsPanel;
    private JFrame frame;
    private JButton attackButton;
    private JButton specialAbilityButton;
    private JButton useElixirButton;
    private JButton runButton;  // Adiciona um JFrame para a interface gráfica
    private JLabel heroInfoLabel;
    private JLabel monsterInfoLabel;
    private JLabel match;
    private JLabel turn;

    private Board board;

    public PerformActions(Board board) {
        this.board = board;
    }

    private void initializeUI() {
        // Inicializa o frame
        frame = new JFrame("Jogo de Combate");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Criação e configuração dos botões
        attackButton = new JButton("Ataque");
        specialAbilityButton = new JButton("Habilidade Especial");
        useElixirButton = new JButton("Usar Elixir");
        runButton = new JButton("Fugir");

        heroInfoLabel = new JLabel("Informações do Herói: ");
        heroInfoLabel.setPreferredSize(new Dimension(500, 50));

        monsterInfoLabel = new JLabel("Informacoes do Monstro: ");
        monsterInfoLabel.setPreferredSize(new Dimension(500, 50));

        match = new JLabel("Match: "+ board.getTurnInfo());
        match.setPreferredSize(new Dimension(500, 50));

        turn = new JLabel("Turn: ");
        turn.setPreferredSize(new Dimension(500, 50));

        // Configurar painel de ações
        JPanel actionsPanel = new JPanel();
        actionsPanel.setLayout(new BoxLayout(actionsPanel, BoxLayout.Y_AXIS));
        actionsPanel.add(heroInfoLabel); // Adiciona o JLabel ao painel
        actionsPanel.add(monsterInfoLabel);
        actionsPanel.add(attackButton);
        actionsPanel.add(specialAbilityButton);
        actionsPanel.add(useElixirButton);
        actionsPanel.add(runButton);
        actionsPanel.add(match);
        actionsPanel.add(turn);

        // Adicionar o painel ao frame
        frame.add(actionsPanel);
        frame.setSize(400, 300); // Defina o tamanho do frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        attackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performAttack();
            }
        });

        // Adicionar o painel ao frame
        frame.getContentPane().add(actionsPanel);
        frame.setVisible(true);
    }

    public void performAttack(){
        Piece currentPiece = board.getPieces(board.getHeroiRow(), board.getHeroiColumn());
        if (currentPiece.hasNormalMonster()) {
            for (Characters monster : currentPiece.getCharacters()) {
                if (monster instanceof NormalMonster) {
                    printHeroAttributes();
                    //board.getMatchInfo();
                    board.nextTurn();
                     // Inicializa a interface gráfica quando a luta começa
                    System.out.println(board.getHero().printAttributes());
                    System.out.println(monster.printAttributes());

                    while (board.getHero().getLife() > 0 && monster.getLife() > 0) {
                        //board.getMatchInfo();
                        board.nextTurn();
                        if (board.getCurrentPlayer().toString().equals(board.getHero().toString())) {
                            int dadoAtaque = ThreadLocalRandom.current().nextInt(1, 7);
                            int dadoDefesa = ThreadLocalRandom.current().nextInt(1, 7);
                            System.out.println("Ataque Heroi" + (board.getHero().getAttack() + dadoAtaque) + "Defesa monstro" + (monster.getDefense() + dadoDefesa));
                            if (board.getHero().getAttack() + dadoAtaque > monster.getDefense() + dadoDefesa) {
                                monster.setLifeSubtract(board.getHero().getAttack() + dadoAtaque - monster.getDefense() + dadoDefesa);
                                System.out.println(board.getHero().printAttributes());
                                System.out.println(monster.printAttributes());
                            }
                        }
                        if (board.getCurrentPlayer().toString().equals(monster.toString())) {
                            int dadoAtaque = ThreadLocalRandom.current().nextInt(1, 7);
                            int dadoDefesa = ThreadLocalRandom.current().nextInt(1, 7);
                            System.out.println("Ataque Monstro" + (monster.getAttack() + dadoAtaque) + "Defesa Heroi" + (board.getHero().getDefense() + dadoDefesa));
                            if (monster.getAttack() + dadoAtaque > board.getHero().getDefense() + dadoDefesa) {
                                board.getHero().setLifeSubtract(monster.getAttack() + dadoAtaque - board.getHero().getDefense() + dadoDefesa);
                                System.out.println(board.getHero().printAttributes());
                                System.out.println(monster.printAttributes());
                            }
                        }
                        ((Heroes) board.getHero()).heroAlive();
                        if (!monster.isAlive()) {
                            System.out.println("Monstro morto!");
                            System.out.println(((Heroes) board.getHero()).ganhaPontos());
                            board.getPieces(board.getHeroiRow(), board.getHeroiColumn()).removePieceCharecters(monster);
                            break;
                        }
                    }
                }
            }
        }
    }

    private void printHeroAttributes() {
        // Atualiza o JLabel com as informações atuais do herói
        heroInfoLabel.setText("Informações do Herói: " + board.getHero().printAttributes());
    }

    private void printMonsterAttributes() {
        // Atualiza o JLabel com as informações atuais do monstro
        Piece currentPiece = board.getPieces(board.getHeroiRow(), board.getHeroiColumn());
        if (currentPiece.hasNormalMonster()) {
            for (Characters monster : currentPiece.getCharacters()) {
                if (monster instanceof NormalMonster) {
                    monsterInfoLabel.setText("Informações do Monstro: " + monster.printAttributes());
                }
            }
        }
    }

    @Override
    public void fight(Piece hero) {

        Piece currentPiece = board.getPieces(board.getHeroiRow(), board.getHeroiColumn());
        if (currentPiece.hasNormalMonster()) {
            for (Characters monster : currentPiece.getCharacters()) {
                if (monster instanceof NormalMonster) {
                    initializeUI();
                    printHeroAttributes();
                    printMonsterAttributes();
                    //board.getMatchInfo();
                    board.nextTurn();
                    //while (board.getHero().getLife() > 0 && monster.getLife() > 0) {

                    //}

                    // Exiba os botões necessários
                    attackButton.setVisible(true);
                    specialAbilityButton.setVisible(false);
                    useElixirButton.setVisible(false);
                    runButton.setVisible(false);
                }
            }
        }

//        specialAbilityButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//               // performSpecialAbility();
//            }
//        });
//
//        useElixirButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//               // useElixir();
//            }
//        });
//
//        runButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//               // runAway();
//            }
//        });

//        Piece currentPiece = board.getPieces(board.getHeroiRow(), board.getHeroiColumn());
//
//        if (hero.hasBoss()) {
//            if (currentPiece.hasNormalMonster()) {
//                for (Characters monster : currentPiece.getCharacters()) {
//                    if (monster instanceof Boss) {
//                        System.out.println("Boss Fight!!");
//                        System.out.println(board.getHero().printAttributes());
//                        System.out.println(board.getBoss().printAttributes());
//                        System.out.println("Opções de luta:");
//                        System.out.print("|1 atacar| |2 habilidade especial| |3 usar elixir| |4 Correr|");
//
//                        while (board.getHero().getLife() > 0 && monster.getLife() > 0) {
//                            board.printMatch();
//                            board.nextTurn();
//                            if (board.getCurrentPlayer().toString().equals(board.getHero().toString())) {
//                                int dadoAtaque = ThreadLocalRandom.current().nextInt(1, 7);
//                                int dadoDefesa = ThreadLocalRandom.current().nextInt(1, 7);
//                                System.out.println("Ataque Heroi" + (board.getHero().getAttack() + dadoAtaque) + "Defesa monstro" + (monster.getDefense() + dadoDefesa));
//                                if (board.getHero().getAttack() + dadoAtaque > monster.getDefense() + dadoDefesa) {
//                                    monster.setLifeSubtract(board.getHero().getAttack() + dadoAtaque - monster.getDefense() + dadoDefesa);
//                                    System.out.println(board.getHero().printAttributes());
//                                    System.out.println(monster.printAttributes());
//                                }
//                                if (!monster.isAlive()) {
//                                    System.out.println("Monstro morto!");
//                                    System.out.println(((Heroes) board.getHero()).ganhaPontos());
//                                    board.getPieces(board.getHeroiRow(), board.getHeroiColumn()).removePieceCharecters(monster);
//                                    break;
//                                }
//                            }
//                            if (board.getCurrentPlayer().toString().equals(monster.toString())) {
//                                int dadoAtaque = ThreadLocalRandom.current().nextInt(1, 7);
//                                int dadoDefesa = ThreadLocalRandom.current().nextInt(1, 7);
//                                System.out.println("Ataque Monstro" + (monster.getAttack() + dadoAtaque) + "Defesa Heroi" + (board.getHero().getDefense() + dadoDefesa));
//                                if (monster.getAttack() + dadoAtaque > board.getHero().getDefense() + dadoDefesa) {
//                                    board.getHero().setLifeSubtract(monster.getAttack() + dadoAtaque - board.getHero().getDefense() + dadoDefesa);
//                                    System.out.println(board.getHero().printAttributes());
//                                    System.out.println(monster.printAttributes());
//                                }
//                            }
//                            ((Heroes) board.getHero()).heroAlive();
//                        }
//
//                        // Lógica de fuga
//                        int dadoCorre1 = ThreadLocalRandom.current().nextInt(1, 3);
//                        int dadoCorre2 = ThreadLocalRandom.current().nextInt(1, 3);
//                        if (dadoCorre1 == dadoCorre2) {
//                            // Supondo que board.moveHero(KeyEvent) faz algo quando o herói foge
//                        }
//                    }
//                }
//            }
//        }
    }

    @Override
    public void getElixir(Piece hero) {

    }

    @Override
    public void damageTrap(Piece hero) {

    }
}
//
//    @Override
//    public void getElixir(Piece hero) {
//        if(hero.hasElixir())
//            for (int i = 0; i < board.getPieces(board.getHeroiRow(),board.getHeroiColumn()).getItems().size(); i++) {
//                Items item = board.getPieces(board.getHeroiRow(),board.getHeroiColumn()).getItems().get(i);
//                    System.out.println("Elixir!");
////                    System.out.println("Deseja pegar o elixir?");
////                    System.out.println("1 sim 2 nao");
////                    int choice = sc.nextInt();
////                    switch (choice){
////                        case 1:
//                            if(item instanceof Elixir) {
//                                if(Elixir.scholarshipLimit()) {
//                                    Elixir.elixirToBag();
//                                    board.getPieces(board.getHeroiRow(), board.getHeroiColumn()).removePieceItems(board.getPieces(board.getHeroiRow(), board.getHeroiColumn()).getItems().get(i));
//                                }else{
//                                    Elixir.elixirToBag();
//                                }
//                            }
////                        case 2:
////
////                    }
//
//            }
//    }

//    @Override
//    public void damageTrap(Piece hero) {
//        if(hero.hastrapFixed()){
//            for (int i = 0; i < board.getPieces(board.getHeroiRow(),board.getHeroiColumn()).getItems().size(); i++) {
//                Items trap = board.getPieces(board.getHeroiRow(), board.getHeroiColumn()).getItems().get(i);
//                System.out.println("Trap Fixed!!");
//                if(trap instanceof Fixed){
//                    ((Fixed) trap).damageHero((Heroes) board.getHero(),(Trap) trap);
//                    System.out.println(board.getHero().getLife());
//                }
//            }
//        }
//        if(hero.hasTrapVariable()){
//            for (int i = 0; i < board.getPieces(board.getHeroiRow(),board.getHeroiColumn()).getItems().size(); i++) {
//                Items trap = board.getPieces(board.getHeroiRow(), board.getHeroiColumn()).getItems().get(i);
//                System.out.println("Trap Variable!!");
//                if(trap instanceof Variable){
//                    ((Variable) trap).damageHero((Heroes) board.getHero(),(Trap) trap);
//                    System.out.println("Dano da trap variavel:"+((Variable) trap).getDano());
//                }
//            }
//        }
//    }
//}
