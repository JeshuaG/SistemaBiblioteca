package Control;

import controladores.Aut;
import componentes.Login;
import javax.swing.*;

public class LoginControl {
    private Login loginVista;

    public LoginControl(Login loginVista) {
        this.loginVista = loginVista;
        initEventos();
    }

    private void initEventos() {
        loginVista.getBtnLogin().addActionListener(e -> autenticar());
    }

    private void autenticar() {
        String usuario = loginVista.getTxtUsuario().getText();
        String password = new String(loginVista.getTxtContraseña().getPassword());
        String rol = (String) loginVista.getCbRol().getSelectedItem();

       if (Aut.autenticar(usuario, password, rol)) { 
            SwingUtilities.invokeLater(() -> {
                PrincipalControl principalControl = new PrincipalControl(usuario, rol);
            });
            loginVista.dispose();  
        } else {
            JOptionPane.showMessageDialog(loginVista, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
    }
}
}
