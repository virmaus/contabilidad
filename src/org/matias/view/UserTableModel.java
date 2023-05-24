package org.matias.view;

import org.matias.model.User;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class UserTableModel extends AbstractTableModel {
    private static final String[] COLUMN_NAMES = {"ID", "Nombre", "Apellido", "RUT", "Debe", "Clave"};
    private List<User> userList;

    public UserTableModel(List<User> userList) {
        this.userList = userList;
    }

    @Override
    public int getRowCount() {
        return userList.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    @Override
    public String getColumnName(int column) {
        return COLUMN_NAMES[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        User user = userList.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return user.getId();
            case 1:
                return user.getNombre();
            case 2:
                return user.getApellido();
            case 3:
                return user.getRut();
            case 4:
                return user.isDebe();
            case 5:
                return user.getClave();
            default:
                return null;
        }
    }
}

