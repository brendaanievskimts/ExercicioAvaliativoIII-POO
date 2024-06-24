package dados;

public class Agricola extends Robo {
    private int id;
    private String modelo;
    private double valorDiario;
    private double area;
    private String uso;

    public Agricola(int id, String modelo, double valorDiario, double area, String uso) {
        super(id, modelo, valorDiario);
        this.area = area;
        this.uso = uso;
    }


    @Override
    public double calculaLocacao(int dias) {
        return 0;
    }

    @Override
    public String toString() {
        return "Robo Agricola\n" +
                "id=" + id + ", modelo: " + modelo + ", valorDiario: " + valorDiario + ", area: " + area + ", uso: " + uso;
    }
}
