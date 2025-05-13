
//Jorge Salazar Navarro C4J653
//Abril Chacón Araya C4E107 
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class RepositorioAbrilJorge extends JFrame {
    private JTextArea textArea;
    private JTextField txtMonto;
    private JComboBox<String> comboEntrada;
    private JComboBox<String> comboSalida;
    private int contadorResultados = 1;

    public RepositorioAbrilJorge() {
        setTitle("Convertidor de Divisas");
        setSize(700, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        // COLORES
        Color fondo = new Color(240, 248, 255);     // Azul claro
        Color campos = new Color(255, 255, 255);    // Blanco
        Color acento = new Color(100, 149, 237);    // Azul acento
        Color borde = new Color(200, 200, 200);     // Gris claro

        getContentPane().setBackground(fondo);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // TÍTULO
        JLabel titulo = new JLabel("Convertidor de Divisas", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titulo.setForeground(acento);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titulo, gbc);
        gbc.gridwidth = 1;

        // ETIQUETAS Y CAMPOS
        JLabel lblDe = new JLabel("Convertir de:");
        lblDe.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridy = 1;
        gbc.gridx = 0;
        add(lblDe, gbc);

        comboEntrada = new JComboBox<>(new String[]{"CRC", "USD", "EUR"});
        estiloCombo(comboEntrada, campos, borde);
        gbc.gridx = 1;
        add(comboEntrada, gbc);

        JLabel lblMonto = new JLabel("Monto:");
        lblMonto.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridy = 2;
        gbc.gridx = 0;
        add(lblMonto, gbc);

        txtMonto = new JTextField(10);
        estiloCampo(txtMonto, campos, borde);
        gbc.gridx = 1;
        add(txtMonto, gbc);

        JLabel lblA = new JLabel("A:");
        lblA.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridy = 3;
        gbc.gridx = 0;
        add(lblA, gbc);

        comboSalida = new JComboBox<>(new String[]{"CRC", "USD", "EUR"});
        estiloCombo(comboSalida, campos, borde);
        gbc.gridx = 1;
        add(comboSalida, gbc);

        // BOTÓN
        JButton btnConvertir = new JButton("Convertir");
        btnConvertir.setBackground(acento);
        btnConvertir.setForeground(Color.WHITE);
        btnConvertir.setFocusPainted(false);
        btnConvertir.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnConvertir.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(btnConvertir, gbc);
        gbc.gridwidth = 1;

        // ÁREA DE RESULTADOS
        textArea = new JTextArea(10, 45);
        textArea.setEditable(false);
        textArea.setBackground(campos);
        textArea.setFont(new Font("Consolas", Font.PLAIN, 13));
        textArea.setBorder(new LineBorder(borde));
        textArea.setMargin(new Insets(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(textArea); 
        gbc.gridy = 5;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        add(scrollPane, gbc);

        // Acción del botón
        btnConvertir.addActionListener(new ActionListener() {//
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

    private void estiloCampo(JTextField campo, Color bg, Color border) {
        campo.setBackground(bg);
        campo.setBorder(new LineBorder(border, 1, true)); // Bordes redondeados
        campo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    }

    private void estiloCombo(JComboBox<String> combo, Color bg, Color border) {
        combo.setBackground(bg);
        combo.setBorder(new LineBorder(border, 1, true));
        combo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    }

    public static void main(String[] args) {
        new RepositorioAbrilJorge();
    }
}
