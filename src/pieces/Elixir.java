package pieces;

public class Elixir extends Items{
    protected static int qtdElixir = 0;
    private final static int maxLimitBag = 3;

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
