//Jorge Salazar Navarro C4J653
//Abril Chacón Araya C4E107 
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;


public class RepositorioAbrilJorge extends JFrame {


 
 
    private JTextArea textArea;
 

    public RepositorioAbrilJorge() { 
        
        
        
        setTitle("Agregar titulo");//Nombre de la ventana
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setLocationRelativeTo(null);

        JButton btnAgregar = new JButton("Agregar");//Boton Agregar
        add(btnAgregar);

        JButton btnMostrar = new JButton("Mostrar");//Boton Mostrar
        add(btnMostrar);

        JButton btnBuscar = new JButton("Buscar");//Boton buscar
        add(btnBuscar);

        JButton btnEliminar = new JButton("Eliminar");//Boton eliminar
        add(btnEliminar);

        textArea = new JTextArea(20, 50);//Se define el txtArea
        textArea.setEditable(false);
        add(new JScrollPane(textArea));

        btnAgregar.addActionListener(new ActionListener() {//Se le da función al botón
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarNodo();
            }
        });

        }

    private void agregarNodo() {//metodo que despliega formulario de alquiler
        JFrame ventanaAgregar = new JFrame("Agregar Alquiler");//Nombre de ventana
        ventanaAgregar.setSize(400, 350);
        ventanaAgregar.setLayout(new FlowLayout());
        ventanaAgregar.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        ventanaAgregar.setLocationRelativeTo(null);

        // Nombre de los txtField
        JTextField txtNombre = new JTextField(20); 
        JTextField txtDNI = new JTextField(20);
        JTextField txtNacionalidad = new JTextField(20);
       
        JComboBox<String> comboTipo = new JComboBox<>(new String[]{"Velero", "Yate", "Lancha"});

        // Se toma la información de los espacios
        ventanaAgregar.add(new JLabel("Nombre:"));
        ventanaAgregar.add(txtNombre);

        ventanaAgregar.add(new JLabel("DNI (Máximo 9 dígitos):"));
        ventanaAgregar.add(txtDNI);

        ventanaAgregar.add(new JLabel("Nacionalidad:"));
        ventanaAgregar.add(txtNacionalidad);

        ventanaAgregar.add(new JLabel("Tipo de embarcación:"));
        ventanaAgregar.add(comboTipo);

        JButton btnGuardar = new JButton("Guardar"); ///Botón de guardar
        ventanaAgregar.add(btnGuardar);

        btnGuardar.addActionListener(new ActionListener() {//boton de guardar
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = txtNombre.getText();
                String dni = txtDNI.getText();
                String nacionalidad = txtNacionalidad.getText();
                String tipo = (String) comboTipo.getSelectedItem();

                // Validaciones básicas
                if (nombre.trim().isEmpty() || dni.trim().isEmpty() || nacionalidad.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(ventanaAgregar, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (dni.length() > 9) {///En caso de dni con numeros fijos
                    JOptionPane.showMessageDialog(ventanaAgregar, "El DNI no puede tener más de 9 dígitos", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    // Validación del tipo de embarcación
                    switch (tipo) {
                        case "Velero":
                        case "Yate":
                        case "Lancha":
                            // Agregar a la lista enlazada
                         
                            break;
                        default:
                            JOptionPane.showMessageDialog(ventanaAgregar, "Tipo de embarcación inválido.", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                    }

                    JOptionPane.showMessageDialog(ventanaAgregar, "Registro Realizado", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    ventanaAgregar.dispose();

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(ventanaAgregar, "Error detectado.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        ventanaAgregar.setVisible(true);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RepositorioAbrilJorge ventana = new RepositorioAbrilJorge();
            ventana.setVisible(true);
        });
    }
}