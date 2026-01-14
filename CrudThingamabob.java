package MyFirstGUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CrudThingamabob extends JFrame {

    // === DATA ===
    ArrayList<crudOptions> coffeeListData = new ArrayList<>();
    DefaultListModel<crudOptions> listModel = new DefaultListModel<>();

    // === FIELDS ===
    JTextField nameField;
    JTextField toppingsField;
    JTextField priceField;

    JComboBox<String> sizeCombo;
    JList<crudOptions> coffeeList;

    public CrudThingamabob() {
        setTitle("☕ The Coffee Cove Menu Maker");
        setSize(973, 380);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        // === INPUT PANEL ===
        JPanel inputPanel = new JPanel();
        inputPanel.setBounds(476, 202, 466, 130);
        inputPanel.setLayout(null);

        JLabel lblName = new JLabel("Coffee Name:");
        lblName.setBounds(0, 0, 111, 28);
        lblName.setFont(new Font("Berlin Sans FB", Font.BOLD, 13));
        inputPanel.add(lblName);

        nameField = new JTextField();
        nameField.setBounds(98, 0, 367, 28);
        inputPanel.add(nameField);

        JLabel lblToppings = new JLabel("Toppings:");
        lblToppings.setBounds(0, 33, 88, 28);
        lblToppings.setFont(new Font("Berlin Sans FB", Font.BOLD, 13));
        inputPanel.add(lblToppings);

        toppingsField = new JTextField();
        toppingsField.setBounds(98, 33, 367, 28);
        inputPanel.add(toppingsField);

        JLabel lblPrice = new JLabel("Price:");
        lblPrice.setBounds(0, 66, 88, 28);
        lblPrice.setFont(new Font("Berlin Sans FB", Font.BOLD, 13));
        inputPanel.add(lblPrice);

        priceField = new JTextField();
        priceField.setBounds(98, 66, 367, 28);
        inputPanel.add(priceField);

        JLabel lblSize = new JLabel("Size:");
        lblSize.setBounds(0, 99, 88, 28);
        lblSize.setFont(new Font("Berlin Sans FB", Font.BOLD, 13));
        inputPanel.add(lblSize);

        String[] sizes = {"Small", "Medium", "Large"};
        sizeCombo = new JComboBox<>(sizes);
        sizeCombo.setBounds(98, 99, 367, 28);
        inputPanel.add(sizeCombo);

        panel.add(inputPanel);

        // === LIST ===
        coffeeList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(coffeeList);
        scrollPane.setBounds(0, 77, 466, 235);
        panel.add(scrollPane);

        // === BUTTONS ===
        JPanel buttonPanel = new JPanel(new GridLayout(1, 4, 5, 5));
        buttonPanel.setBounds(0, 322, 466, 21);

        JButton addBtn = new JButton("Add");
        JButton updateBtn = new JButton("Update");
        JButton deleteBtn = new JButton("Delete");
        JButton clearBtn = new JButton("Clear");

        buttonPanel.add(addBtn);
        buttonPanel.add(updateBtn);
        buttonPanel.add(deleteBtn);
        buttonPanel.add(clearBtn);

        panel.add(buttonPanel);

        JLabel title = new JLabel("The Coffee Cove Menu Maker");
        title.setFont(new Font("Berlin Sans FB", Font.BOLD, 21));
        title.setBounds(86, 21, 350, 35);
        panel.add(title);

        getContentPane().add(panel);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon("C:\\Users\\ricar\\Downloads\\a-black-silhouette-of-a-steaming-coffee-cup-cutouts-png.png"));
        lblNewLabel.setBounds(546, 10, 370, 182);
        panel.add(lblNewLabel); 

        // === ACTIONS ===
        addBtn.addActionListener(e -> addCoffee());
        updateBtn.addActionListener(e -> updateCoffee());
        deleteBtn.addActionListener(e -> deleteCoffee());
        clearBtn.addActionListener(e -> clearFields());
        coffeeList.addListSelectionListener(e -> showSelectedCoffee());
    }

    // === CREATE ===
    void addCoffee() {
        try {
            String name = nameField.getText();
            String toppings = toppingsField.getText();
            String size = (String) sizeCombo.getSelectedItem();
            double price = Double.parseDouble(priceField.getText());

            if (name.isEmpty() || toppings.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Fill all fields!");
                return;
            }
            crudOptions coffee = new crudOptions(name, toppings, size, price);
            coffeeListData.add(coffee);
            listModel.addElement(coffee);

            clearFields();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Price must be a number!");
        }
    }

    // === READ ===
    void showSelectedCoffee() {
        int index = coffeeList.getSelectedIndex();
        if (index != -1) {
            crudOptions coffee = coffeeListData.get(index);
            nameField.setText(coffee.getName());
            toppingsField.setText(coffee.getToppings());
            sizeCombo.setSelectedItem(coffee.getSize());
            priceField.setText(String.valueOf(coffee.getPrice()));
        }
    }

    // === UPDATE ===
    void updateCoffee() {
        int index = coffeeList.getSelectedIndex();
        if (index == -1) return;

        try {
            crudOptions coffee = coffeeListData.get(index);
            coffee.setName(nameField.getText());
            coffee.setToppings(toppingsField.getText());
            coffee.setSize((String)sizeCombo.getSelectedItem());
            coffee.setPrice(Double.parseDouble(priceField.getText()));
            
            listModel.set(index, coffee);
            JOptionPane.showMessageDialog(this, "Coffee updated!");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid price!");
        }
    }

    // === DELETE ===
    void deleteCoffee() {
        int index = coffeeList.getSelectedIndex();
        if (index != -1) {
            coffeeListData.remove(index);
            listModel.remove(index);
            clearFields();
        }
    }
    
    // === CLEAR ===
    void clearFields() {
        nameField.setText("");
        toppingsField.setText("");
        priceField.setText("");
        sizeCombo.setSelectedIndex(0);
        coffeeList.clearSelection();
    }
 // Entry point of the program
 // Launches The Coffee Cove Menu Maker GUI
    public static void main(String[] args) {
        new CrudThingamabob().setVisible(true);
    }
}
