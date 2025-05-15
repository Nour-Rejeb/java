package Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Commande {
	protected int num_c;
	protected Date date_c;
	protected int total_c;
	protected String statut;
	protected List<Ligne_commande>ligne_com=new ArrayList<>();
	public Commande(int num_c, Date date_c, int total_c, String statut,List<Ligne_commande>ligne_com) {
		this.num_c = num_c;
		this.date_c = date_c;
		this.total_c = total_c;
		this.statut = statut;
		this.ligne_com=ligne_com;
		}
	public List<Ligne_commande> getLigne_com() {
		return ligne_com;
	}
	public void setLigne_com(List<Ligne_commande> ligne_com) {
		this.ligne_com = ligne_com;
	}
	public int getNum_c() {
		return num_c;
	}
	public void setNum_c(int num_c) {
		this.num_c = num_c;
	}
	public Date getDate_c() {
		return date_c;
	}
	public void setDate_c(Date date_c) {
		this.date_c = date_c;
	}
	public int getTotal_c() {
		return total_c;
	}
	public void setTotal_c(int total_c) {
		this.total_c = total_c;
	}
	public String getStatut() {
		return statut;
	}
	public void setStatut(String statut) {
		this.statut = statut;
	}
	@Override
	public int hashCode() {
		return Objects.hash(date_c, ligne_com, num_c, statut, total_c);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Commande other = (Commande) obj;
		return Objects.equals(date_c, other.date_c) && Objects.equals(ligne_com, other.ligne_com)
				&& num_c == other.num_c && Objects.equals(statut, other.statut) && total_c == other.total_c;
	}
	@Override
	public String toString() {
		return "Commande [num_c=" + num_c + ", date_c=" + date_c + ", total_c=" + total_c + ", statut=" + statut
				+ ", ligne_com=" + ligne_com + "]";
	}
}
