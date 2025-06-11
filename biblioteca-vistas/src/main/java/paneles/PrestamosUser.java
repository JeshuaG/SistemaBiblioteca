package paneles;

    
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class PrestamosUser extends JPanel {
    public JTable table;
    public DefaultTableModel model;
    public TableRowSorter<DefaultTableModel> sorter;
    public JTextField searchField;
    public JComboBox<String> statusCombo;
    public JLabel pageLabel;
    public JButton prevButton, nextButton;
    public JButton addButton, editButton, deleteButton, exportCsvButton, importJsonButton;

    public PrestamosUser() {
        setLayout(new BorderLayout());
        initComponents();
    }

    private void initComponents() {
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.add(new JLabel("Buscar:"));
        searchField = new JTextField(20);
        searchPanel.add(searchField);

        searchPanel.add(new JLabel("Estado:"));
        statusCombo = new JComboBox<>(new String[]{"Todos", "Activo", "Vencido"});
        searchPanel.add(statusCombo);

        add(searchPanel, BorderLayout.NORTH);

        String[] columns = {"Libro", "Miembro", "F. Préstamo", "F. Devolución", "Días Restantes", "Multa", "Estado"};
        model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
            
            @Override 
            public Class<?> getColumnClass(int column) {
                return column == 4 ? Integer.class : String.class;
            }
        };
        
        table = new JTable(model);
        table.setRowHeight(30);
        sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);
        table.setDefaultRenderer(Integer.class, new DiasRestantesRenderer());
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        addButton = new JButton("Agregar Préstamo");
        editButton = new JButton("Registrar Devolución");
        deleteButton = new JButton("Eliminar");
        exportCsvButton = new JButton("Exportar CSV");
        importJsonButton = new JButton("Importar JSON");
        prevButton = new JButton("<");
        nextButton = new JButton(">");
        pageLabel = new JLabel("Página 1");

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(exportCsvButton);
        buttonPanel.add(importJsonButton);
        buttonPanel.add(prevButton);
        buttonPanel.add(pageLabel);
        buttonPanel.add(nextButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    class DiasRestantesRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, 
                                                     boolean hasFocus, int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if (value instanceof Integer) {
                int dias = (Integer) value;
                if (dias < 0) {
                    c.setBackground(Color.RED);
                } else if (dias < 3) {
                    c.setBackground(Color.ORANGE);
                } else {
                    c.setBackground(Color.GREEN);
                }
            }
            return c;
        }
    }

    public JTable getTable() {
        return table;
    }

    public DefaultTableModel getModel() {
        return model;
    }

    public JTextField getSearchField() {
        return searchField;
    }

    public JComboBox<String> getStatusCombo() {
        return statusCombo;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getEditButton() {
        return editButton;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public JButton getExportCsvButton() {
        return exportCsvButton;
    }

    public JButton getImportJsonButton() {
        return importJsonButton;
    }

    public JButton getPrevButton() {
        return prevButton;
    }

    public JButton getNextButton() {
        return nextButton;
    }

    public JLabel getPageLabel() {
        return pageLabel;
    }
}