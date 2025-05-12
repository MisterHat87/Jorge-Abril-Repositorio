
//Jorge Salazar Navarro C4J653
//Abril Chacón Araya C4E107 
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class RepositorioAbrilJorge extends JFrame {

    private JTextArea textArea;
    private JTextField txtNombre;
    private JComboBox<String> comboTipo;

    public RepositorioAbrilJorge() {

        setTitle("Agregar Divisa");
        setSize(580, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setLocationRelativeTo(null);

        add(new JLabel("Divisa de ingreso:"));
        comboTipo = new JComboBox<>(new String[] { "CRC", "USD", "EUR" });
        add(comboTipo);

        add(new JLabel("Monto:"));
        txtNombre = new JTextField(10);
        add(txtNombre);

          add(new JLabel("Divisa de cambio:"));
        comboTipo = new JComboBox<>(new String[] { "CRC", "USD", "EUR" });
        add(comboTipo);

        JButton btnEnviar = new JButton("Enviar");
        add(btnEnviar);

        textArea = new JTextArea(10, 40);
        textArea.setEditable(false);
        add(new JScrollPane(textArea));

        btnEnviar.addActionListener(new ActionListener() { // Acción del botón
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarDato();
            }
        });

        setVisible(true);
    }

    private void agregarDato() {
        String tipo = (String) comboTipo.getSelectedItem();
        String montoTexto = txtNombre.getText().trim();

        if (montoTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe ingresar un monto", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double monto = Double.parseDouble(montoTexto);
            String mensaje = "Se ingresó " + monto + " " + tipo + "\n";
            textArea.append(mensaje);
            JOptionPane.showMessageDialog(this, "Registro Realizado", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            txtNombre.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El monto debe ser numérico", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new RepositorioAbrilJorge();
    }
}