package paneles;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

public class LibrosAdmin extends JPanel {
    private JTable table;
    private DefaultTableModel model;
    private JTextField searchField;
    private JLabel pageLabel;
    private JButton searchButton, addButton, editButton, deleteButton, 
                   exportCsvButton, importJsonButton, prevButton, nextButton,
                   showReviewsButton;
    private TableRowSorter<DefaultTableModel> sorter;
    public LibrosAdmin() {
        setLayout(new BorderLayout());
        initComponents();
    }
    private void initComponents() {
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        searchPanel.add(new JLabel("Buscar:"));
        searchField = new JTextField(20);
        searchPanel.add(searchField);

        add(searchPanel, BorderLayout.NORTH);

        String[] columnNames = {"Portada","Título", "Autor", "Género", "ISBN", "Publicación", "Rating"};
        model = new DefaultTableModel(columnNames, 0) {
            @Override
            public Class<?> getColumnClass(int column) {
                if (column == 5) return Double.class;
                return String.class;
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(model);
        sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);
        table.setAutoCreateRowSorter(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        addButton = new JButton("Agregar");
        buttonPanel.add(addButton);

        editButton = new JButton("Editar");
        buttonPanel.add(editButton);

        deleteButton = new JButton("Eliminar");
        buttonPanel.add(deleteButton);

        showReviewsButton = new JButton("Ver Reseñas");
        buttonPanel.add(showReviewsButton);

        buttonPanel.add(Box.createHorizontalStrut(20));

        exportCsvButton = new JButton("Exportar");
        buttonPanel.add(exportCsvButton);

        importJsonButton = new JButton("Importar");
        buttonPanel.add(importJsonButton);

        buttonPanel.add(Box.createHorizontalStrut(20));

        prevButton = new JButton("<");
        buttonPanel.add(prevButton);

        pageLabel = new JLabel("Página 1");
        buttonPanel.add(pageLabel);

        nextButton = new JButton(">");
        buttonPanel.add(nextButton);

        add(buttonPanel, BorderLayout.SOUTH);
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
    
    public JLabel getPageLabel() { 
        return pageLabel; 
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
    
    public JButton getShowReviewsButton() { 
        return showReviewsButton; 
    }

}