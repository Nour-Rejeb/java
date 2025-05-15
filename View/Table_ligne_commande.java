package View;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import Model.Ligne_commande;

public class Table_ligne_commande extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private List<Ligne_commande> lignesCommande;
    private final String[] columnNames = {
        "id_ligne", "quantite", "num_table", "prix_uni", "remarques", "num_c"
    };

    public void loadData(List<Ligne_commande> lignes) {
        this.lignesCommande = lignes;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return lignesCommande != null ? lignesCommande.size() : 0;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Ligne_commande ligne = lignesCommande.get(rowIndex);
        switch (columnIndex) {
            case 0: return ligne.getId_ligne();
            case 1: return ligne.getQuantite();
            case 2: return ligne.getNum_table();
            case 3: return ligne.getPrix_uni();
            case 4: return ligne.getRemarques();
            case 5: return ligne.getNum_c();
            default: return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public Ligne_commande getLigneCommandeAt(int row) {
        return lignesCommande.get(row);
    }
}
