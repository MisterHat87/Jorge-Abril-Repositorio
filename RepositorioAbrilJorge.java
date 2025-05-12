
//Jorge Salazar Navarro C4J653
//Abril Chacón Araya C4E107 
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class RepositorioAbrilJorge extends JFrame {
    private JTextArea textArea;
    private JTextField txtMonto;
    private JComboBox<String> comboEntrada;
    private JComboBox<String> comboSalida;
    private int contadorResutados = 1;

    public RepositorioAbrilJorge() {
        setTitle("Convertidor de Divisas");
        setSize(600, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setLocationRelativeTo(null);

        add(new JLabel("Convertir de:"));
        comboEntrada = new JComboBox<>(new String[] { "CRC", "USD", "EUR" });
        add(comboEntrada);

        add(new JLabel("Monto:"));
        txtMonto = new JTextField(10);
        add(txtMonto);

        add(new JLabel("A:"));
        comboSalida = new JComboBox<>(new String[] { "CRC", "USD", "EUR" });
        add(comboSalida);

        JButton btnConvertir = new JButton("Convertir");
        add(btnConvertir);

        textArea = new JTextArea(10, 45);
        textArea.setEditable(false);
        add(new JScrollPane(textArea));

        btnConvertir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarDato();
            }
        });

        setVisible(true);
    }

    private void agregarDato() {
        String origen = (String) comboEntrada.getSelectedItem();
        String destino = (String) comboSalida.getSelectedItem();
        String montoTexto = txtMonto.getText().trim();

        if (montoTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe ingresar un monto.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double monto = Double.parseDouble(montoTexto);
            String mensaje = origen + " " + monto + " " + destino;
            String respuesta = Cliente.enviar(mensaje);

            textArea.append("Resultado #" + contadorResutados + ": " + respuesta + "\n");
            contadorResutados++;
            txtMonto.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El monto debe ser numérico.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new RepositorioAbrilJorge();
    }
}