package View;

import javax.swing.table.AbstractTableModel;
import java.text.SimpleDateFormat;
import java.util.List;

import Model.Commande;

public class Table_Commandes extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private List<Commande> commandes;
    private String[] columnNames = {"num_c", "date_c", "total_c", "statut"};
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public void loadData(List<Commande> commandes) {
        this.commandes = commandes;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return commandes != null ? commandes.size() : 0;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Commande commande = commandes.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return commande.getNum_c();
            case 1:
                return dateFormat.format(commande.getDate_c());
            case 2:
                return commande.getTotal_c();
            case 3:
                return commande.getStatut();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public Commande getCommandeAt(int row) {
        return commandes.get(row);
    }
}