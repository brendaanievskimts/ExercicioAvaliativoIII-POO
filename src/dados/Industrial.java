package dados;

public class Industrial extends Robo {
    private int id;
    private String modelo;
    private double valorDiario;
    private String setor;

    public Industrial(int id, String modelo, double valorDiario, String setor){
        super(id, modelo, valorDiario);
        this.setor = setor;
    }

    public String getSetor() {
        return setor;
    }

    @Override
    public double calculaLocacao(int dias) {
        return 0;
    }

    @Override
    public String toString() {
        return "Robo Insdutrial\n" +
                "id: " + id + ", modelo: " + modelo + ", valorDiario: " + valorDiario + ", setor: " + setor;
    }
}
