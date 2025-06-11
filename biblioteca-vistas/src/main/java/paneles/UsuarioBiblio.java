package paneles;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;

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
        sorter = new TableRowSorter<>(model);
        userTable.setRowSorter(sorter);

        add(new JScrollPane(userTable), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        addButton = new JButton("Agregar");
        editButton = new JButton("Editar");
        exportButton = new JButton("Exportar");
        importButton = new JButton("Importar");

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
}