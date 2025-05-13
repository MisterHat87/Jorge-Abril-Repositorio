public class Moneda {//Cambio 1
                    // Creacion de la clase Moneda

    private double monto;
    private String valorEntrada;
    private String valorSalida;

    public Moneda(double monto, String valorEntrada, String valorSalida) { // Constructor de moneda
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

    public double cambiosMonetarios() { //Cambio 2 
                                      //Se agregan las distintas monedas

        if (valorEntrada.equals(valorSalida))
            return monto;

        if (valorEntrada.equals("CRC")) {
            if (valorSalida.equals("USD"))
                return monto / 500;
            if (valorSalida.equals("EUR"))
                return monto / 540;
        } else if (valorEntrada.equals("USD")) {
            if (valorSalida.equals("CRC"))
                return monto * 500;
            if (valorSalida.equals("EUR"))
                return monto * 0.92;
        } else if (valorEntrada.equals("EUR")) {
            if (valorSalida.equals("USD"))
                return monto / 0.92;
            if (valorSalida.equals("CRC"))
                return monto * 540;
        }

        return -1;
    }
}
