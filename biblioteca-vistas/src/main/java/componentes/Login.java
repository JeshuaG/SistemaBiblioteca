package componentes;

import Control.LoginControl;
import javax.swing.*;
import java.awt.*;

public class Login extends JFrame {
    private JTextField txtUsuario;
    private JPasswordField txtContraseña;
    private JComboBox<String> cbRol;
    private JCheckBox chkRecordar;
    private JButton btnLogin;

    public Login() {
        setTitle("Inicio de Sesión - Sistema Biblioteca");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 350);
        setLocationRelativeTo(null);
        setResizable(false);

        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        JLabel lblTitle = new JLabel("Sistema Biblioteca", JLabel.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(lblTitle, gbc);

        gbc.gridwidth = 1;

        gbc.gridy = 1;
        panel.add(new JLabel("Usuario:"), gbc);

        gbc.gridx = 1;
        txtUsuario = new JTextField(15);
        panel.add(txtUsuario, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Contraseña:"), gbc);

        gbc.gridx = 1;
        txtContraseña = new JPasswordField(15);
        panel.add(txtContraseña, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Rol:"), gbc);

        gbc.gridx = 1;
        cbRol = new JComboBox<>(new String[]{"Administrador", "Bibliotecario", "Miembro"});
        panel.add(cbRol, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        chkRecordar = new JCheckBox("Recordar usuario");
        panel.add(chkRecordar, gbc);

        gbc.gridy = 5;
        btnLogin = new JButton("Ingresar");
        btnLogin.setPreferredSize(new Dimension(100, 30));
        panel.add(btnLogin, gbc);

        add(panel);
    }

    public JTextField getTxtUsuario() {
        return txtUsuario;
    }

    public JPasswordField getTxtContraseña() {
        return txtContraseña;
    }

    public JComboBox<String> getCbRol() {
        return cbRol;
    }

    public JCheckBox getChkRecordar() {
        return chkRecordar;
    }

    public JButton getBtnLogin() {
        return btnLogin;  
    }

public static void main(String[] args) {
    try {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (Exception e) {
        System.err.println("Error al establecer el look and feel: " + e.getMessage());
    } 

    SwingUtilities.invokeLater(() -> {
        Login login = new Login();
        new LoginControl(login);
        login.setVisible(true);
    });
}}
