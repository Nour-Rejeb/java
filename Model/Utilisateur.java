package Model;

import java.util.Objects;

public class Utilisateur {
	protected int cin;
	protected String nom;
	protected String prénom;
	protected String login;
	protected String mdp;
	protected String type;
	public Utilisateur(int cin, String nom, String prénom, String login, String mdp, String type) {
		this.cin = cin;
		this.nom = nom;
		this.prénom = prénom;
		this.login = login;
		this.mdp = mdp;
		this.type = type;
	}
	public int getCin() {
		return cin;
	}
	public void setCin(int cin) {
		this.cin = cin;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrénom() {
		return prénom;
	}
	public void setPrénom(String prénom) {
		this.prénom = prénom;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getMdp() {
		return mdp;
	}
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public int hashCode() {
		return Objects.hash(cin, login, mdp, nom, prénom, type);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Utilisateur other = (Utilisateur) obj;
		return cin == other.cin && Objects.equals(login, other.login) && Objects.equals(mdp, other.mdp)
				&& Objects.equals(nom, other.nom) && Objects.equals(prénom, other.prénom)
				&& Objects.equals(type, other.type);
	}
	@Override
	public String toString() {
		return "Utilisateur [cin=" + cin + ", nom=" + nom + ", prénom=" + prénom + ", login=" + login + ", mdp=" + mdp
				+ ", type=" + type + "]";
	}
	

}
