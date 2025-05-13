
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
    private int contadorResultados = 1;

    public RepositorioAbrilJorge() {
        setTitle("Convertidor de Divisas");
        setSize(650, 350);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        
        // Fila 1: Combo de origen
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Convertir de:"), gbc);

        gbc.gridx = 1;
        comboEntrada = new JComboBox<>(new String[]{"CRC", "USD", "EUR"});
        add(comboEntrada, gbc);

    
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Monto:"), gbc);

        gbc.gridx = 1;
        txtMonto = new JTextField(10);
        add(txtMonto, gbc);

    
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("A:"), gbc);

        gbc.gridx = 1;
        comboSalida = new JComboBox<>(new String[]{"CRC", "USD", "EUR"});
        add(comboSalida, gbc);

        // Fila 4: Botón
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton btnConvertir = new JButton("Convertir");
        add(btnConvertir, gbc);

       
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        textArea = new JTextArea(10, 45);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, gbc);

        // Acción del botón
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

            textArea.append("Resultado #" + contadorResultados + ": " + respuesta + "\n");
            contadorResultados++;
            txtMonto.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El monto debe ser numérico.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new RepositorioAbrilJorge();
    }
}