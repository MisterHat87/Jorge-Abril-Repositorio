public class Moneda {
    
    private double monto;
    private String  valorEntrada;
    private String  valorSalida;

     public Moneda(double monto , String valorEntrada, String valorSalida) {
        this.monto = monto;
        this.valorEntrada = valorEntrada;
        this.valorSalida = valorSalida;

 
}

public double getMonto() {
        return monto;
    }

    public String getValorEntrada() {
        return valorEntrada;
    }

    public String getValorSalida() {
        return valorSalida;
    }

    public double cambiosMonetarios (){


     return -1;
    }
}
