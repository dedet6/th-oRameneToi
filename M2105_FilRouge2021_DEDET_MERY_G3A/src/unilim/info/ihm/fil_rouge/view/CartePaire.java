package unilim.info.ihm.fil_rouge.view;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;

public class CartePaire extends StackPane {

	// Déclaration des composants
	
	private static int nombrePaires;
	private static int nombreParLigne;
	
	// Instanciation des sources des différentes faces de cartes
	
	private String urlPair1 = new File("images/carte_pikachu.jpg").toURI().toString();
	private String urlPair2 = new File("images/carte_dracaufeu.jpg").toURI().toString();
	private String urlPair3 = new File("images/carte_tortank.jpg").toURI().toString();
	private String urlPair4 = new File("images/carte_florizarre.jpg").toURI().toString();
	private String urlPair5 = new File("images/carte_evoli.jpg").toURI().toString();
	private String urlPair6 = new File("images/carte_mew.jpg").toURI().toString();
	private String urlPair7 = new File("images/carte_rayquaza.png").toURI().toString();
	private String urlPair8 = new File("images/carte_lucario.png").toURI().toString();
	private String urlPair9 = new File("images/carte_ho-oh.jpg").toURI().toString();
	private String urlPair10 = new File("images/carte_lugia.jpg").toURI().toString();
	private String urlPair11 = new File("images/carte_ronflex.jpg").toURI().toString();
	private String urlPair12 = new File("images/carte_ectoplasma.jpg").toURI().toString();
	private String urlPair13 = new File("images/carte_leviator.png").toURI().toString();
	private String urlPair14 = new File("images/carte_alakazam.png").toURI().toString();
	private String urlPair15 = new File("images/carte_dracolosse.png").toURI().toString();

	public CartePaire() {
		
		// Instanciation des composants
		
		nombrePaires = this.difficulteNombrePaires();
		nombreParLigne = this.difficulteNombreParLigne();
		ArrayList<Carte> PairesDeCartes = new ArrayList<>();
		Image[] tableImages = new Image[nombrePaires];
		String[] tableUrls = { urlPair1, urlPair2, urlPair3, urlPair4, urlPair5, urlPair6, urlPair7, urlPair8, urlPair9, urlPair10, urlPair11, urlPair12, urlPair13, urlPair14, urlPair15};
		
		// Insertion des urls des différentes cartes dans la table des images
		
		for(int i = 0 ; i < tableImages.length; i++) {
	    	tableImages[i]= new Image(tableUrls[i]);

	    	}

		// Création des paires de cartes
		
		for (int i = 0; i < nombrePaires; i++) {

	    	PairesDeCartes.add(new Carte(tableImages[i]));
	    	PairesDeCartes.add(new Carte(tableImages[i]));
		}

		// Répartition aléatoire des paires de cartes
		
		Collections.shuffle(PairesDeCartes);

		// Positionnement des cartes dans l'espace en fonction de la difficulté choisie
		
		for (int i = 0; i < PairesDeCartes.size(); i++) {

	    	Carte carte = PairesDeCartes.get(i);

			if (difficulteFacile()) {
				carte.setTranslateX(250 * (i % nombreParLigne));
				carte.setTranslateY(290 * (i / nombreParLigne));
			}
			
			if (difficulteMoyen()) {
				carte.getZoneCarte().setHeight(190);
				carte.getZoneCarte().setWidth(175);
		    	carte.setTranslateX(200 * (i % nombreParLigne));
		    	carte.setTranslateY(210 * (i / nombreParLigne));
			}
			
			if (difficulteDifficile()) {
				carte.getZoneCarte().setHeight(150);
				carte.getZoneCarte().setWidth(145);
		    	carte.setTranslateX(165 * (i % nombreParLigne));
		    	carte.setTranslateY(170 * (i / nombreParLigne));
			}
	    	getChildren().add(carte);
		}
	}

	private boolean difficulteDifficile() {
		return RootPane.getDifficulte().equals("Difficile");
	}

	private boolean difficulteMoyen() {
		return RootPane.getDifficulte().equals("Moyen");
	}

	private boolean difficulteFacile() {
		return RootPane.getDifficulte().equals("Facile");
	}
	
	public static int getNombrePaires() {
		return nombrePaires;
	}

	public static void setNombrePaires(int nombrePaires) {
		CartePaire.nombrePaires = nombrePaires;
	}

	public int difficulteNombrePaires() {
		int x = 0;
		if (difficulteFacile()) {
			x = 6;
		}
		if (difficulteMoyen()) {
			x = 10;
		}
		if (difficulteDifficile()) {
			x = 15;
		}
		return x;
	}
	
	public int difficulteNombreParLigne() {
		int y = 0;
		if (difficulteFacile()) {
			y = 4;
		}
		if (difficulteMoyen()) {
			y = 5;
		}
		if (difficulteDifficile()) {
			y = 6;
		}
		return y;
	}
}
