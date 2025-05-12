import java.io.*;
import java.net.*;

class Servidor {

    public static void main(String argv[]) throws Exception {
        String clientSentence;
        String respuesta;

        ServerSocket welcomeSocket = new ServerSocket(6789);

        System.out.println("Servidor iniciado... Esperando conexiones en el puerto 6789");

        while (true) {
            Socket connectionSocket = welcomeSocket.accept();

            BufferedReader inFromClient = new BufferedReader(
                new InputStreamReader(connectionSocket.getInputStream()));

            DataOutputStream outToClient =
                new DataOutputStream(connectionSocket.getOutputStream());

            clientSentence = inFromClient.readLine();
            System.out.println("Mensaje recibido: " + clientSentence);

            // Separar entrada: se espera "<moneda> <monto>"
            String[] partes = clientSentence.split(" ");
            if (partes.length == 2) {
                String moneda = partes[0].toUpperCase();
                double monto;

                try {
                    monto = Double.parseDouble(partes[1]);

                    if (moneda.equals("CRC")) {
                        double resultado = monto / 500.0;
                        respuesta = "Equivalente en USD: $" + resultado + "\n";
                    } else if (moneda.equals("USD")) {
                        double resultado = monto * 500.0;
                        respuesta = "Equivalente en CRC: ₡" + resultado + "\n";
                    } else {
                        respuesta = "Moneda no válida. Use 'USD' o 'CRC'.\n";
                    }

                } catch (NumberFormatException e) {
                    respuesta = "Formato de monto inválido.\n";
                }

            } else {
                respuesta = "Formato incorrecto. Use: <moneda> <monto>\n";
            }

            outToClient.writeBytes(respuesta);
        }
    }
}