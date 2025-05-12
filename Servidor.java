import java.io.*;
import java.net.*;



public class Servidor {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(6789);

        while (true) {
            Socket socket = serverSocket.accept();

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            String linea = in.readLine();
            String[] partes = linea.split(" ");
            String respuesta;

            if (partes.length == 3) {
                try {
                    double monto = Double.parseDouble(partes[1]);
                    Moneda moneda = new Moneda(monto, partes[0], partes[2]);
                    double resultado = moneda.cambiosMonetarios();

                    if (resultado >= 0) {
                        respuesta = resultado + " " + partes[2];
                    } else {
                        respuesta = "* Conversión no válida.";
                    }
                } catch (NumberFormatException e) {
                    respuesta = "* Monto no numérico.";
                }
            } else {
                respuesta = "* Formato incorrecto. Use: <origen> <monto> <destino>";
            }

            out.writeBytes(respuesta + "\n");
            socket.close();
        }
    }
}