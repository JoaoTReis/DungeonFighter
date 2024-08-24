package pieces;

public class Elixir extends Items{
    private static int qtdElixir = 0;
    private final static int maxLimitBag = 3;

    private Heroes heroes;

    public Elixir(Heroes heroes) {
        this.heroes=heroes;
    }

    public int getQtdElixir() {
        return qtdElixir;
    }

    public void setQtdElixir(int qtdElixir) {
        Elixir.qtdElixir = qtdElixir;
    }

    public static boolean scholarshipLimit(){
        return qtdElixir < maxLimitBag;
    }

    public static void elixirToBag(){
        System.out.println("Quantidade de elixir na bolsa: " + qtdElixir);
        if(scholarshipLimit()) {
            qtdElixir++;
            System.out.println("Quantidade de elixir na bolsa: " + qtdElixir);
        }else{
            System.out.println("Bolsa cheia nao é possivel pegar o elixir!");
        }
    }

    @Override
    public String toString() {
        return "E";
    }
}
