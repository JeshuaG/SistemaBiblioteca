package componentes;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

public class Principal extends JFrame {

    private String usuario;
    private String rolUsuario;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private String currentTheme = "FlatLaf";

    public Principal(String usuario, String role) {
        this.usuario = usuario;
        this.rolUsuario = role;
        initializeUI();
        initializeMenuBar();
        initializePanels();
        applyTheme(currentTheme);
    }

    private void initializeUI() {
        setTitle("Sistema Biblioteca - " + rolUsuario);
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
    }

    private void initializeMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenuItem homeItem = new JMenuItem("Inicio");
        homeItem.addActionListener(e -> showPanel("Inicio"));
        menuBar.add(homeItem);

        switch(rolUsuario) {
            case "Administrador":
                addMenuItem(menuBar, "Usuarios", "paneles.UsuarioAdmin");
                addMenuItem(menuBar, "Libros", "paneles.LibrosAdmin");
                addMenuItem(menuBar, "Autores", "paneles.Autores");
                addMenuItem(menuBar, "Prestamos", "paneles.PrestamosAdmin");
                break;
            case "Bibliotecario":
                addMenuItem(menuBar, "Usuarios", "paneles.UsuarioBiblio");
                addMenuItem(menuBar, "Libros", "paneles.LibrosAdmin");
                addMenuItem(menuBar, "Autores", "paneles.Autores");
                addMenuItem(menuBar, "Prestamos", "paneles.PrestamosAdmin");
                break;
            case "Miembro":
                addMenuItem(menuBar, "Libros", "paneles.LibrosUser");
                addMenuItem(menuBar, "Mis Prestamos", "paneles.PrestamosUser");
                break;
        }

        menuBar.add(Box.createHorizontalGlue());

        JMenu themeMenu = new JMenu("Tema");
        addThemeMenuItem(themeMenu, "FlatLaf Light", "FlatLaf");
        addThemeMenuItem(themeMenu, "FlatLaf Dark", "FlatDarkLaf");
        addThemeMenuItem(themeMenu, "Mac Light", "FlatMacLight");
        addThemeMenuItem(themeMenu, "Mac Dark", "FlatMacDark");
        menuBar.add(themeMenu);

        JMenuItem exitItem = new JMenuItem("Salir");
        exitItem.addActionListener(e -> System.exit(0));
        menuBar.add(exitItem);

        setJMenuBar(menuBar);
    }

    private void addMenuItem(JMenuBar menuBar, String title, String panelClass) {
        JMenuItem menuItem = new JMenuItem(title);
        menuItem.addActionListener(e -> {
            try {
                boolean panelExists = false;
                for (Component comp : mainPanel.getComponents()) {
                    if (title.equals(comp.getName())) {
                        panelExists = true;
                        break;
                    }
                }

                if (!panelExists) {
                    Class<?> panelClassObj = Class.forName(panelClass);
                    JPanel panel = (JPanel) panelClassObj.getDeclaredConstructor().newInstance();
                    panel.setName(title);
                    mainPanel.add(panel, title);
                }

                showPanel(title);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, 
                    "Error al cargar el panel: " + ex.getMessage(), 
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        menuBar.add(menuItem);
    }

    private void addThemeMenuItem(JMenu menu, String name, String theme) {
        JMenuItem item = new JMenuItem(name);
        item.addActionListener(e -> applyTheme(theme));
        menu.add(item);
    }

    private void initializePanels() {
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(createHomePanel(), "Inicio");
        add(mainPanel, BorderLayout.CENTER);
    }

    private void showPanel(String panelName) {
        cardLayout.show(mainPanel, panelName);
    }

    private void applyTheme(String theme) {
        try {
            currentTheme = theme;
            switch (theme) {
                case "FlatLaf":
                    FlatLightLaf.setup();
                    break;
                case "FlatDarkLaf":
                    FlatDarkLaf.setup();
                    break;
                case "FlatMacLight":
                    FlatMacLightLaf.setup();
                    break;
                case "FlatMacDark":
                    FlatMacDarkLaf.setup();
                    break;
            }
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private JPanel createHomePanel() {
        JPanel panel = new JPanel(new BorderLayout());

        LocalDate today = LocalDate.now();
        String dayName = today.getDayOfWeek().getDisplayName(TextStyle.FULL, new Locale("es", "ES"));
        String formattedDate = today.format(DateTimeFormatter.ofPattern("d 'de' MMMM 'de' yyyy", new Locale("es", "ES")));

        String welcomeText = "<html><div style='text-align: center; font-family: SansSerif;'>" +
                "<h1 style='font-size: 24pt; font-weight: bold;'>Bienvenido al Sistema de Biblioteca</h1>" +
                "<h2 style='font-size: 18pt;'>Usuario: " + usuario + " - Rol: " + rolUsuario + "</h2>" +
                "<p style='font-size: 16pt;'>Hoy es " + dayName + ", " + formattedDate + "</p>" +
                "<p>Seleccione una opción del menú superior</p>" +
                "</div></html>";

        JLabel welcomeLabel = new JLabel(welcomeText, JLabel.CENTER);
        panel.add(welcomeLabel, BorderLayout.CENTER);

        return panel;
    }}

