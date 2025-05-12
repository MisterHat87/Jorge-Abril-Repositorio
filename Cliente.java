
import java.io.*; 
import java.net.*;  
public class Cliente {
    public static void main(String[] args) throws IOException {
        String sentence="";          
        String modifiedSentence;  
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

        //Uso del socket remotamente. La ip va a cambiar de acuerdo al lugar donde esté conectado.
        //Socket clientSocket = new Socket("10.153.159.246", 6789); 

        // Uso del socket localmente.
        Socket clientSocket = new Socket("localhost", 6789);
        try 
        {
            System.out.println("Ingrese un valor"); 
            sentence = inFromUser.readLine();
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());   
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));  
            System.out.println("Enviando valor al servidor");    
            outToServer.writeBytes(sentence + '\n');
            System.out.println("Valor enviado al servidor");         
            modifiedSentence = inFromServer.readLine(); 
            System.out.println("FROM SERVER: " + modifiedSentence);         
        }
        catch (Exception e) {
            e.getMessage();
        }  
        clientSocket.close();    
} 
}