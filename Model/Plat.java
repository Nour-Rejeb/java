package Model;

import java.util.Objects;

public class Plat {
	protected int id;
	protected String nom_p;
	protected String description;
	protected String disponibilite;
	protected double prix;
	public Plat(int id, String nom_p, String description, String disponibilite, double prix) {
		this.id = id;
		this.nom_p = nom_p;
		this.description = description;
		this.disponibilite = disponibilite;
		this.prix = prix;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom_p() {
		return nom_p;
	}
	public void setNom_p(String nom_p) {
		this.nom_p = nom_p;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDisponibilite() {
		return disponibilite;
	}
	public void setDisponibilite(String disponibilite) {
		this.disponibilite = disponibilite;
	}
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
	@Override
	public int hashCode() {
		return Objects.hash(description, disponibilite, id, nom_p, prix);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Plat other = (Plat) obj;
		return Objects.equals(description, other.description) && Objects.equals(disponibilite, other.disponibilite)
				&& id == other.id && Objects.equals(nom_p, other.nom_p)
				&& Double.doubleToLongBits(prix) == Double.doubleToLongBits(other.prix);
	}
	@Override
	public String toString() {
		return "Plat [id=" + id + ", nom_p=" + nom_p + ", description=" + description + ", disponibilite="
				+ disponibilite + ", prix=" + prix + "]";
	}
	
	
}
