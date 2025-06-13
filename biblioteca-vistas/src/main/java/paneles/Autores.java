package paneles;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Autores extends JPanel {
    
    public JTable autorTable;
    public DefaultTableModel model;
    public TableRowSorter<DefaultTableModel> sorter;
    public JTextField searchField;
    public JLabel pageLabel;
    public JButton prevButton, nextButton;
    public JButton addButton, editButton, deleteButton, importJsonButton, exportCsvButton;

    public Autores() {
        setLayout(new BorderLayout());
        initComponents();
    }

    private void initComponents() {
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.add(new JLabel("Buscar:"));

        searchField = new JTextField(20);
        searchPanel.add(searchField);
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filtrarTabla();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filtrarTabla();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filtrarTabla();
            }

            private void filtrarTabla() {
                String texto = searchField.getText();
                if (texto.trim().length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + texto));
                }
            }
        });

        add(searchPanel, BorderLayout.NORTH);

        String[] columnNames = {"Nombre", "Biografía", "País", "Libros Escritos"};
        model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
            
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 3 ? JList.class : String.class;
            }
        };

        autorTable = new JTable(model);
        sorter = new TableRowSorter<>(model);
        autorTable.setRowSorter(sorter);
        autorTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        autorTable.setDefaultRenderer(JList.class, new JListCellRenderer());
        
        autorTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = autorTable.rowAtPoint(e.getPoint());
                    int col = autorTable.columnAtPoint(e.getPoint());
                    
                    if (row >= 0 && col == 1) {
                        String biografiaCompleta = (String) model.getValueAt(row, col);
                        JTextArea textArea = new JTextArea(biografiaCompleta);
                        textArea.setWrapStyleWord(true);
                        textArea.setLineWrap(true);
                        textArea.setEditable(false);
                        textArea.setColumns(50);
                        textArea.setRows(20);
                        
                        JScrollPane scrollPane = new JScrollPane(textArea);
                        JOptionPane.showMessageDialog(Autores.this, scrollPane, 
                            "Biografía completa de " + model.getValueAt(row, 0), 
                            JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });
        
        autorTable.getColumnModel().getColumn(0).setPreferredWidth(150);
        autorTable.getColumnModel().getColumn(1).setPreferredWidth(500);
        autorTable.getColumnModel().getColumn(2).setPreferredWidth(150);
        autorTable.getColumnModel().getColumn(3).setPreferredWidth(200);

        add(new JScrollPane(autorTable), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        addButton = new JButton("Agregar");
        editButton = new JButton("Editar");
        deleteButton = new JButton("Eliminar");
        exportCsvButton = new JButton("Exportar CSV");
        importJsonButton = new JButton("Importar JSON");

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(exportCsvButton);
        buttonPanel.add(importJsonButton);
        
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    private static class JListCellRenderer implements javax.swing.table.TableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            
            JList<?> list = (JList<?>) value;
            list.setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
            list.setForeground(isSelected ? table.getSelectionForeground() : table.getForeground());
            list.setFont(table.getFont());
            
            if (list.getModel().getSize() > 0) {
                int alturaFila = list.getPreferredSize().height;
                if (table.getRowHeight(row) < alturaFila) {
                    table.setRowHeight(row, alturaFila);
                }
            }
            
            return list;
        }
    }
}