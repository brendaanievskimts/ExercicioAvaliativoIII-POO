package dados;

public class Domestico extends Robo {
    private int id;
    private String modelo;
    private double valorDiario;
    private int nivel;

    public Domestico(int id, String modelo, double valorDiario, int nivel){
        super(id, modelo, valorDiario);
        this.nivel = nivel;

    }

    @Override
    public double calculaLocacao(int dias) {
        return 0;
    }

    @Override
    public String toString() {
        return "Robo Domestico\n" +
                "id: " + id + ", modelo: " + modelo + ", valorDiario: " + valorDiario + ", nivel: " + nivel;
    }
}
