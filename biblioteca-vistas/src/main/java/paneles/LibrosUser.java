package paneles;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;

public class LibrosUser extends JPanel {
    private String usuarioActual;
    private JTable table;
    private DefaultTableModel model;
    private JTextField searchField;
    private TableRowSorter<DefaultTableModel> sorter;
    private JButton prestarButton;

    public LibrosUser() {
        setLayout(new BorderLayout());
        initComponents();
    }

    private void initComponents() {
        JPanel topPanel = new JPanel(new BorderLayout(5, 5));
        
        JPanel searchPanel = new JPanel(new BorderLayout(5, 5));
        searchPanel.add(new JLabel("Buscar:"), BorderLayout.WEST);
        searchField = new JTextField();
        searchPanel.add(searchField, BorderLayout.CENTER);
        
        prestarButton = new JButton("Prestar");
        
        topPanel.add(searchPanel, BorderLayout.CENTER);
        topPanel.add(prestarButton, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);

        model = new DefaultTableModel(
            new Object[]{"Título", "Autor", "Género", "ISBN", "Año", "Rating"}, 
            0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };
        
        table = new JTable(model);
        sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);
        
        add(new JScrollPane(table), BorderLayout.CENTER);
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
    
    public JButton getPrestarButton() { 
        return prestarButton; 
    }
    
    public String getUsuarioActual() { 
        return usuarioActual; 
    }
    
    public void setUsuarioActual(String usuario) { 
        this.usuarioActual = usuario; 
    }
    
}