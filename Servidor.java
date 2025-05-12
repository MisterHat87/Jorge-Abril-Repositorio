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

            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

            clientSentence = inFromClient.readLine();
            System.out.println("Mensaje recibido: " + clientSentence);

            // Separar entrada: se espera "<moneda> <monto>"
            String[] partes = clientSentence.split(" ");
            if (partes.length == 3) {

                double monto;

                try {
                    monto = Double.parseDouble(partes[1]);
                    Moneda monedaCambios = new Moneda(monto, partes[0], partes[2]);
                    double resultado = monedaCambios.cambiosMonetarios();

                    if (resultado >= 0) {
                        respuesta = "Resultado: " + resultado + " " + partes[2] + "\n";
                    } else {
                        respuesta = "Conversión no válida.\n";
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