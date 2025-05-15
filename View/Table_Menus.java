package View;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import Model.Menu;

public class Table_Menus extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private List<Menu> menus;
    private final String[] columnNames = { "ID Menu", "Nom Menu" };

    public void loadData(List<Menu> menus) {
        this.menus = menus;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return menus != null ? menus.size() : 0;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Menu menu = menus.get(rowIndex);
        switch (columnIndex) {
            case 0: return menu.getId_menu();
            case 1: return menu.getNom_m();
            default: return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public Menu getMenuAt(int rowIndex) {
        return menus.get(rowIndex);
    }
}