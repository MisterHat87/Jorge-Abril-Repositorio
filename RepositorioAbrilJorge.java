
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
        setTitle("Convertidor de Divisas");// Título de la ventana
        setSize(700, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        // COLORES
        Color fondo = new Color(240, 248, 255);    
        Color campos = new Color(255, 255, 255);    
        Color acento = new Color(100, 149, 237);     
        Color borde = new Color(200, 200, 200);      

        getContentPane().setBackground(fondo);// Color de fondo

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
        JLabel lblDe = new JLabel("Convertir de:");// Convertir de:
        lblDe.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridy = 1;
        gbc.gridx = 0;
        add(lblDe, gbc);

        comboEntrada = new JComboBox<>(new String[]{"CRC", "USD", "EUR"});// Opciones de monedas
        estiloCombo(comboEntrada, campos, borde);
        gbc.gridx = 1;
        add(comboEntrada, gbc);

        JLabel lblMonto = new JLabel("Monto:");// Monto:
        lblMonto.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridy = 2;
        gbc.gridx = 0;
        add(lblMonto, gbc);

        txtMonto = new JTextField(10);
        estiloCampo(txtMonto, campos, borde);
        gbc.gridx = 1;
        add(txtMonto, gbc);

        JLabel lblA = new JLabel("A:");//
        lblA.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridy = 3;
        gbc.gridx = 0;
        add(lblA, gbc);

        comboSalida = new JComboBox<>(new String[]{"CRC", "USD", "EUR"});// Opciones de monedas
        estiloCombo(comboSalida, campos, borde);
        gbc.gridx = 1;
        add(comboSalida, gbc);

        // BOTÓN
        JButton btnConvertir = new JButton("Convertir");// boton  Convertir
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

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.setBackground(new Color(220, 53, 69)); // Rojo suave
        btnCerrar.setForeground(Color.WHITE);
        btnCerrar.setFocusPainted(false);
        btnCerrar.setFont(new Font("Segoe UI", Font.BOLD, 13)); // Fuente más pequeña
        btnCerrar.setBorder(BorderFactory.createEmptyBorder(8, 36, 8, 36)); // Bordes más compactos

        // Colocar botón en panel auxiliar para evitar ocupar espacio vertical completo
        JPanel panelCerrar = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelCerrar.setOpaque(false); // Fondo transparente para mantener el estilo general
        panelCerrar.add(btnCerrar);

        gbc.gridy = 6;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        add(panelCerrar, gbc);

        // Acción del botón Cerrar
        btnCerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cierra la ventana actual
            }
        });

        // Acción del botón
        btnConvertir.addActionListener(new ActionListener() {//
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarDato();
            }
        });

        setVisible(true);
    }// fin del metodo constructor

    private void agregarDato() {// Agregar dato
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
    }// fin del metodo agregarDato

    private void estiloCampo(JTextField campo, Color bg, Color border) {
        campo.setBackground(bg);
        campo.setBorder(new LineBorder(border, 1, true)); // Bordes redondeados
        campo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    }

    private void estiloCombo(JComboBox<String> combo, Color bg, Color border) {
        combo.setBackground(bg);
        combo.setBorder(new LineBorder(border, 1, true));
        combo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    }// fin del metodo estiloCombo

    public static void main(String[] args) {
        new RepositorioAbrilJorge();
    }// fin del main
}// fin de la clase
