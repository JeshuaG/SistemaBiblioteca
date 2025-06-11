package paneles;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;

public class UsuarioAdmin extends JPanel {
    public JTable userTable;
    public DefaultTableModel model;
    public TableRowSorter<DefaultTableModel> sorter;
    public JTextField searchField;
    public JComboBox<String> roleFilter;
    public JLabel pageLabel;
    public JButton prevButton, nextButton;
    public JButton addButton, editButton, deleteButton, exportButton, importButton;

    public UsuarioAdmin() {
        setLayout(new BorderLayout());
        initComponents();
    }

    private void initComponents() {
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.add(new JLabel("Buscar:"));

        searchField = new JTextField(20);
        searchField.setToolTipText("Buscar por ID, nombre o email");
        searchPanel.add(searchField);

        searchPanel.add(new JLabel("Filtrar por Rol:"));
        roleFilter = new JComboBox<>(new String[]{"Todos", "Administrador", "Bibliotecario", "Miembro"});
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
        deleteButton = new JButton("Eliminar");
        exportButton = new JButton("Exportar CSV");
        importButton = new JButton("Importar JSON");
        prevButton = new JButton("<");
        pageLabel = new JLabel("Página 1");
        nextButton = new JButton(">");

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(exportButton);
        buttonPanel.add(importButton);
        buttonPanel.add(prevButton);
        buttonPanel.add(pageLabel);
        buttonPanel.add(nextButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }
}