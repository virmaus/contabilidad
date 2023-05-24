package org.matias.view;


import javax.swing.*;

import org.matias.controller.UserController;
import org.matias.model.User;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class UserView {
    private JFrame frame;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtRut;
    private JCheckBox chkDebe;
    private JPasswordField txtClave;
    private JButton btnGuardar;
    private JButton btnBuscar;
    private JButton btnModificar;
    private JTable tblUsuarios;
    private JButton btnRefrescar; // Agregado
    private JButton btnEliminar;

    private UserController userController;

    public UserView() {
        userController = new UserController();

        initialize();
        loadUsers();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(10, 10, 100, 20);
        frame.getContentPane().add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(120, 10, 150, 20);
        frame.getContentPane().add(txtNombre);
        txtNombre.setColumns(10);

        JLabel lblApellido = new JLabel("Apellido:");
        lblApellido.setBounds(10, 40, 100, 20);
        frame.getContentPane().add(lblApellido);

        txtApellido = new JTextField();
        txtApellido.setBounds(120, 40, 150, 20);
        frame.getContentPane().add(txtApellido);
        txtApellido.setColumns(10);

        JLabel lblRut = new JLabel("RUT:");
        lblRut.setBounds(10, 70, 100, 20);
        frame.getContentPane().add(lblRut);

        txtRut = new JTextField();
        txtRut.setBounds(120, 70, 150, 20);
        frame.getContentPane().add(txtRut);
        txtRut.setColumns(10);

        JLabel lblDebe = new JLabel("Debe:");
        lblDebe.setBounds(10, 100, 100, 20);
        frame.getContentPane().add(lblDebe);

        chkDebe = new JCheckBox();
        chkDebe.setBounds(120, 100, 20, 20);
        frame.getContentPane().add(chkDebe);

        JLabel lblClave = new JLabel("Clave:");
        lblClave.setBounds(10, 130, 100, 20);
        frame.getContentPane().add(lblClave);

        txtClave = new JPasswordField();
        txtClave.setBounds(120, 130, 150, 20);
        frame.getContentPane().add(txtClave);

        btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(10, 160, 100, 30);
        frame.getContentPane().add(btnGuardar);
        btnGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveUser();
            }
        });
        
        btnModificar = new JButton("Modificar");
        btnModificar.setBounds(230, 160, 100, 30);
        frame.getContentPane().add(btnModificar);
        btnModificar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                modifyUser();
            }
        });
        
        btnRefrescar = new JButton("Refrescar");
        btnRefrescar.setBounds(340, 160, 100, 30);
        frame.getContentPane().add(btnRefrescar);
        btnRefrescar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadUsers();
                clearFields();
            }
        });
        
        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(450, 160, 100, 30);
        frame.getContentPane().add(btnEliminar);
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteSelectedUser();
                clearFields();
            }
        });

        btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(120, 160, 100, 30);
        frame.getContentPane().add(btnBuscar);
        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchUsers();
            }
        });

        tblUsuarios = new JTable();
        JScrollPane scrollPane = new JScrollPane(tblUsuarios);
        scrollPane.setBounds(10, 200, 560, 150);
        frame.getContentPane().add(scrollPane);
    }

    public void show() {
        frame.setVisible(true);
    }

    private void loadUsers() {
        List<User> userList = userController.getAllUsers();
        UserTableModel model = new UserTableModel(userList);
        tblUsuarios.setModel(model);
    }

    private void saveUser() {
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String rut = txtRut.getText();
        boolean debe = chkDebe.isSelected();
        String clave = new String(txtClave.getPassword());

        User user = new User();
        user.setNombre(nombre);
        user.setApellido(apellido);
        user.setRut(rut);
        user.setDebe(debe);
        user.setClave(clave);

        userController.insertUser(user);
        loadUsers();

        clearFields();
    }
    private void modifyUser() {
        int selectedRow = tblUsuarios.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(frame, "Seleccione un usuario para modificar", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String rut = txtRut.getText();
        boolean debe = chkDebe.isSelected();
        String clave = new String(txtClave.getPassword());

        User user = new User();
        user.setId((int) tblUsuarios.getValueAt(selectedRow, 0));
        user.setNombre(nombre);
        user.setApellido(apellido);
        user.setRut(rut);
        user.setDebe(debe);
        user.setClave(clave);

        userController.updateUser(user);
        loadUsers();

        clearFields();
    }

    
    private void deleteSelectedUser() {
        int selectedRow = tblUsuarios.getSelectedRow();
        if (selectedRow >= 0) {
            int userId = (int) tblUsuarios.getValueAt(selectedRow, 0);
            userController.deleteUser(userId);
            loadUsers();
        } else {
            JOptionPane.showMessageDialog(frame, "Selecciona un usuario para eliminar.", "Eliminar Usuario", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void searchUsers() {
        String name = txtNombre.getText();
        List<User> userList = userController.searchUsersByName(name);
        UserTableModel model = new UserTableModel(userList);
        tblUsuarios.setModel(model);
    }

    private void clearFields() {
        txtNombre.setText("");
        txtApellido.setText("");
        txtRut.setText("");
        chkDebe.setSelected(false);
        txtClave.setText("");
    }
}


  

