package paneles;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import Modelos.Usuario;
import controladores.UsuarioController;

public class UsuarioBiblio extends JPanel {
    public JTable userTable;
    public DefaultTableModel model;
    public TableRowSorter<DefaultTableModel> sorter;
    public JTextField searchField;
    public JComboBox<String> roleFilter;
    public JLabel pageLabel;
    public JButton prevButton, nextButton;
    public JButton addButton, editButton, exportButton, importButton;

    public UsuarioBiblio() {
        setLayout(new BorderLayout());
        initComponents();
        cargarDatos();

        searchField.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { actualizarFiltro(); }
            public void removeUpdate(DocumentEvent e) { actualizarFiltro(); }
            public void changedUpdate(DocumentEvent e) { actualizarFiltro(); }
        });

        roleFilter.addActionListener(e -> actualizarFiltro());
    }

    private void initComponents() {
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.add(new JLabel("Buscar:"));
        
        searchField = new JTextField(20);
        searchField.setToolTipText("Buscar por ID, nombre o email");
        searchPanel.add(searchField);

        searchPanel.add(new JLabel("Filtrar por Rol:"));
        roleFilter = new JComboBox<>(new String[]{"Todos", "Bibliotecario", "Miembro"});
        searchPanel.add(roleFilter);

        add(searchPanel, BorderLayout.NORTH);

        String[] columns = {"ID", "Nombre", "Email", "Rol", "Fecha de Registro"};
        model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        userTable = new JTable(model);
        userTable.setRowHeight(30);
        sorter = new TableRowSorter<>(model);
        userTable.setRowSorter(sorter);

        add(new JScrollPane(userTable), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        addButton = new JButton("Agregar");
        editButton = new JButton("Editar");
        exportButton = new JButton("Exportar CSV");
        importButton = new JButton("Importar JSON");

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(exportButton);
        buttonPanel.add(importButton);

        prevButton = new JButton("<");
        buttonPanel.add(prevButton);

        pageLabel = new JLabel("Página 1");
        buttonPanel.add(pageLabel);

        nextButton = new JButton(">");
        buttonPanel.add(nextButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    public void cargarDatos() {
        UsuarioController.cargarUsuarios(model);
    }

    private void actualizarFiltro() {
        String textoBusqueda = searchField.getText().trim();
        String rolSeleccionado = (String) roleFilter.getSelectedItem();
        Usuario.Rol rol = null;

        if (!"Todos".equalsIgnoreCase(rolSeleccionado)) {
            try {
                rol = Usuario.Rol.valueOf(rolSeleccionado.toUpperCase());
            } catch (IllegalArgumentException e) {
                rol = null;
            }
        }

        UsuarioController.filtrarUsuarios(model, textoBusqueda, rol);
    }
}