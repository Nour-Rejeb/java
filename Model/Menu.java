package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Menu {
	protected int id_menu;
	protected String nom_m;
	protected List<Plat> plat=new ArrayList<>();
	public Menu(int id_menu, String nom_m, List<Plat> plat) {
		this.id_menu = id_menu;
		this.nom_m = nom_m;
		this.plat = plat;
	}
	public int getId_menu() {
		return id_menu;
	}
	public void setId_menu(int id_menu) {
		this.id_menu = id_menu;
	}
	public String getNom_m() {
		return nom_m;
	}
	public void setNom_m(String nom_m) {
		this.nom_m = nom_m;
	}
	public List<Plat> getplat() {
		return plat;
	}
	public void setIplat( List<Plat> plat) {
		this.plat = plat;
	}
	@Override
	public int hashCode() {
		return Objects.hash(plat, id_menu, nom_m);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Menu other = (Menu) obj;
		return plat == other.plat && id_menu == other.id_menu && Objects.equals(nom_m, other.nom_m);
	}
	@Override
	public String toString() {
		return "Menu [id_menu=" + id_menu + ", nom_m=" + nom_m + ", id=" + plat + "]";
	}
	
}
