package View;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;
import Model.Plat;

public class Table_Plats extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private List<Plat> plats = new ArrayList<>();
    private final String[] columnNames = {"ID", "Nom", "Description", "Disponibilité", "Prix"};

    public void loadData(List<Plat> newPlats) {
        this.plats = newPlats;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return plats.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Plat plat = plats.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return plat.getId();
            case 1:
                return plat.getNom_p();
            case 2:
                return plat.getDescription();
            case 3:
                return plat.getDisponibilite();
            case 4:
                return plat.getPrix();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public Plat getPlatAt(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < plats.size()) {
            return plats.get(rowIndex);
        }
        return null;
    }
}