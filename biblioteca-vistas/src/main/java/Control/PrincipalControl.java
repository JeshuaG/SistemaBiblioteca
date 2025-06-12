package Control;

import componentes.Principal;


public class PrincipalControl {
    private String usuario;
    private String rolUsuario;
    private Principal vistaPrincipal;

    public PrincipalControl(String usuario, String rolUsuario) { 
        this.usuario = usuario;
        this.rolUsuario = rolUsuario;
        inicializar();
    }

    private void inicializar() {
        vistaPrincipal = new Principal(usuario, rolUsuario); 
        vistaPrincipal.setVisible(true);
    }
}

