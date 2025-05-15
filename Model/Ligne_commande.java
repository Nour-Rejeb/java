package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Ligne_commande {
	protected int id_ligne;
	protected int quantite;
	protected int num_table;
	protected double prix_uni;
	protected String remarques;
	protected int num_c;
	protected List<Plat>plt=new ArrayList<>();
	protected List<Commande>com=new ArrayList<>();
	public Ligne_commande(int id_ligne, int quantite, int num_table, double prix_uni, String remarques, int num_c,
			List<Plat> plt, List<Commande> com) {
		this.id_ligne = id_ligne;
		this.quantite = quantite;
		this.num_table = num_table;
		this.prix_uni = prix_uni;
		this.remarques = remarques;
		this.num_c = num_c;
		this.plt = plt;
		this.com = com;
	}
	public int getId_ligne() {
		return id_ligne;
	}
	public void setId_ligne(int id_ligne) {
		this.id_ligne = id_ligne;
	}
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	public int getNum_table() {
		return num_table;
	}
	public void setNum_table(int num_table) {
		this.num_table = num_table;
	}
	public double getPrix_uni() {
		return prix_uni;
	}
	public void setPrix_uni(double prix_uni) {
		this.prix_uni = prix_uni;
	}
	public String getRemarques() {
		return remarques;
	}
	public void setRemarques(String remarques) {
		this.remarques = remarques;
	}
	public int getNum_c() {
		return num_c;
	}
	public void setNum_c(int num_c) {
		this.num_c = num_c;
	}
	public List<Plat> getPlt() {
		return plt;
	}
	public void setPlt(List<Plat> plt) {
		this.plt = plt;
	}
	public List<Commande> getCom() {
		return com;
	}
	public void setCom(List<Commande> com) {
		this.com = com;
	}
	@Override
	public int hashCode() {
		return Objects.hash(com, id_ligne, num_c, num_table, plt, prix_uni, quantite, remarques);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ligne_commande other = (Ligne_commande) obj;
		return Objects.equals(com, other.com) && id_ligne == other.id_ligne && num_c == other.num_c
				&& num_table == other.num_table && Objects.equals(plt, other.plt)
				&& Double.doubleToLongBits(prix_uni) == Double.doubleToLongBits(other.prix_uni)
				&& quantite == other.quantite && Objects.equals(remarques, other.remarques);
	}
	@Override
	public String toString() {
		return "Ligne_commande [id_ligne=" + id_ligne + ", quantite=" + quantite + ", num_table=" + num_table
				+ ", prix_uni=" + prix_uni + ", remarques=" + remarques + ", num_c=" + num_c + ", plt=" + plt + ", com="
				+ com + "]";
	}
	
	

}
