package InventoryView;

import InventoryController.*;
import InventoryModel.*;
import java.awt.*;
import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Vector;

public class InventoryView{
    private JFrame frame;
    //Text display of inventory.
    private JTable textArea;
    //Display area.
    private JPanel panel;
    //Button area.
    private JPanel buttonPanel;
    private JButton addItem;
    private JButton removeItem;
    private JButton editItem;
    private JButton itemSearch;
    private JButton exit;
    private GridBagConstraints gridBag = new GridBagConstraints();
    //Button press choice.
    private String choice = "";
    //Holds item information if an item is to be added.
    Item newItem = new Item();
    private static DefaultTableModel tableModel = new DefaultTableModel();

    //Edit item menu buttons
    private JPopupMenu editMenu;
    private JMenuItem editName;
    private JMenuItem editCount;
    private JMenuItem editDescription;
    private JMenuItem editLocation;

    public InventoryView(String title) throws Exception {
        frame = new JFrame(title);
        mainMenu();
    }

    public InventoryView() {
    }

    public void mainMenu() throws Exception {
        panel = new JPanel(new GridBagLayout());
        buttonPanel = new JPanel(new GridBagLayout());
        tableModel = tableSetup();
        textArea = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(textArea);
        addItem = new JButton("Add item");
        removeItem = new JButton("Remove item");
        editItem = new JButton("Edit item");
        itemSearch = new JButton("Search for item");
        exit = new JButton("Exit");

        //Set up scrollPane.
        textArea.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        textArea.setAutoCreateColumnsFromModel(true);
        textArea.setAutoCreateRowSorter(true);
        textArea.getColumnModel().getColumn(0).setMinWidth(140);
        textArea.getColumnModel().getColumn(1).setMinWidth(50);
        textArea.getColumnModel().getColumn(2).setMinWidth(50);
        textArea.getColumnModel().getColumn(3).setMinWidth(240);
        textArea.getColumnModel().getColumn(4).setMinWidth(240);

        //Set scrollPane row count and adds scrollPane to panel.
        Dimension d = textArea.getPreferredSize();
        scrollPane.setPreferredSize(new Dimension(d.width,textArea.getRowHeight()*20+1));
        scrollPane.createVerticalScrollBar();
        scrollPane.setVisible(true);
        panel.add(scrollPane);

        //Set button dimensions
        addItem.setPreferredSize(new Dimension(150,30));
        removeItem.setPreferredSize(new Dimension(150,30));
        editItem.setPreferredSize(new Dimension(150,30));
        itemSearch.setPreferredSize(new Dimension(150,30));
        exit.setPreferredSize(new Dimension(150,30));

        //Align and add buttons
        gridBag.gridx = 0;
        gridBag.gridy = 1;
        buttonPanel.add(addItem, gridBag);
        gridBag.gridx = 1;
        gridBag.gridy = 1;
        buttonPanel.add(removeItem, gridBag);
        gridBag.gridx = 2;
        gridBag.gridy = 1;
        buttonPanel.add(editItem, gridBag);
        gridBag.gridx = 3;
        gridBag.gridy = 1;
        buttonPanel.add(itemSearch, gridBag);
        gridBag.gridx = 5;
        gridBag.gridy = 1;
        buttonPanel.add(exit, gridBag);

        //Edit item menu buttons
        editMenu = new JPopupMenu();
        editName = new JMenuItem("Edit name");
        editCount = new JMenuItem("Edit count");
        editDescription = new JMenuItem("Edit description");
        editLocation = new JMenuItem("Edit location");
        editMenu.add(editName);
        editMenu.add(editCount);
        editMenu.add(editDescription);
        editMenu.add(editLocation);

        //Inventory list border.
        panel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 5));

        //Add panels and show frame.
        frame.add(panel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
        frame.setSize(800, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        //Change frame icon.
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(
                "C:\\Users\\Adam\\Desktop\\CS Projects\\Icons\\Hot Giraffe.png"));
        //Button interactions
        addItem.addActionListener(InventoryController.getInstance());
        removeItem.addActionListener(InventoryController.getInstance());
        editItem.addActionListener(InventoryController.getInstance());
        itemSearch.addActionListener(InventoryController.getInstance());
        exit.addActionListener(InventoryController.getInstance());
    }

    //Used to create initial table and to update table after changes were made in database.
    public static DefaultTableModel tableSetup() throws Exception {
        //Retrieve current MySQL connection.
        Connection conn = InventoryModel.getInstance().getConnection();
        //Statement to be passed to MySQL.
        String sql = "SELECT * FROM inventory_items;";
        PreparedStatement pst = conn.prepareStatement(sql);
        //Creates a set for the rows stored in current table.
        ResultSet results = pst.executeQuery(sql);

        ResultSetMetaData metaData = results.getMetaData();

        //Put column names into vector.
        Vector<String> columnNames = new Vector<>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }

        //Put data from MySQL table into vector.
        Vector<Vector<Object>> data = new Vector<>();
        while (results.next()) {
            Vector<Object> vector = new Vector<>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                vector.add(results.getObject(columnIndex));
            }
            data.add(vector);
        }

        tableModel.setDataVector(data, columnNames);

        return tableModel;
    }

    public String getViewName() {
        return this.getClass().getSimpleName();
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    //Main menu button getters.
    public JButton getAddButton() {
        return addItem;
    }

    public JButton getRemoveButton() {
        return removeItem;
    }

    public JButton getEditButton() {
        return editItem;
    }

    public JButton getSearchButton() {
        return itemSearch;
    }

    public JButton getExitButton() {
        return exit;
    }

    public JFrame getFrame() {
        return frame;
    }

    //Edit item menu button getters.
    public JPopupMenu getEditMenu() {
        return editMenu;
    }

    public JMenuItem getEditName() {
        return editName;
    }

    public JMenuItem getEditCount() {
        return editCount;
    }

    public JMenuItem getEditDescription() {
        return editDescription;
    }

    public JMenuItem getEditLocation() {
        return editLocation;
    }
}
