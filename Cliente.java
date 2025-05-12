
import java.io.*;
import java.net.*;

public class Cliente {

    public static String enviar(String mensaje) {
        try (
                Socket socket = new Socket("localhost", 6789);
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            out.writeBytes(mensaje + "\n"); //Convierte el mensaje a bytes
            out.flush(); 

            String respuesta = in.readLine();
            return (respuesta != null) ? respuesta : "* El servidor no devolvió respuesta.";

        } catch (IOException e) {
            return "* Error de conexión con el servidor.";
        }
    }

}
