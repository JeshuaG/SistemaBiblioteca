package Control;

import controladores.Aut;
import componentes.Login;
import javax.swing.*;

public class LoginControl {
    private Login loginVista;
    private Timer cierreTimer; 

    public LoginControl(Login loginVista) {
        this.loginVista = loginVista;
        initEventos();
        iniciarTimerCierre(); 
    }

    private void initEventos() {
        loginVista.getBtnLogin().addActionListener(e -> autenticar());
    }

    private void autenticar() {
        String usuario = loginVista.getTxtUsuario().getText();
        String password = new String(loginVista.getTxtContraseña().getPassword());
        String rol = (String) loginVista.getCbRol().getSelectedItem();

        if (Aut.autenticar(usuario, password, rol)) {
            
            if (cierreTimer != null && cierreTimer.isRunning()) {
                cierreTimer.stop();
            }

            SwingUtilities.invokeLater(() -> {
                PrincipalControl principalControl = new PrincipalControl(usuario, rol);
            });
            loginVista.dispose();  
        } else {
            JOptionPane.showMessageDialog(loginVista, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void iniciarTimerCierre() {
        cierreTimer = new Timer(60000, e -> {
            JOptionPane.showMessageDialog(loginVista, "Tiempo de espera agotado. Cerrando aplicación.");
            loginVista.dispose();
            System.exit(0);
        });
        cierreTimer.setRepeats(false); 
        cierreTimer.start();
    }
}
