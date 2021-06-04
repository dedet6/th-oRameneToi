package unilim.info.ihm.fil_rouge.view;

import java.io.File;

import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import unilim.info.ihm.fil_rouge.controller.QuitterController;
import unilim.info.ihm.fil_rouge.model.Model;

public class RootPane extends BorderPane {

	// Instanciation des composants
	
	private static String difficulte = "";
	private CartePaire grid = new CartePaire();
	private String urlgauche = new File("images/fond_gauche.jpg").toURI().toString();
	private String urldroite = new File("images/fond_droite.jpg").toURI().toString();
	private String urlfond = new File("images/fond_ecran.png").toURI().toString();

	
	public RootPane() {
		
		// Instanciation des composants de la HBox
		
		Model valeurScore = new Model();
		Button btnQuitter = new Button("Quitter");
		btnQuitter.setStyle("-fx-font-weight: bold; -fx-font-size: 22px; -fx-background-color: #425b8a; -fx-text-fill: WHITE;");
		Label lblScore = new Label("");
		lblScore.textProperty().bind(Bindings.convert(valeurScore.valueProperty()));
		lblScore.setStyle("-fx-font-weight: bold; -fx-font-size: 22px; -fx-background-color: #425b8a; -fx-text-fill: WHITE;");
		Label lblDifficulte = new Label("Difficulté : " + difficulte);
		lblDifficulte.setStyle("-fx-font-weight: bold; -fx-font-size: 22px; -fx-background-color: #425b8a; -fx-text-fill: WHITE;");
		
		// Instanciation des images sur les côtés et en fond
		
		Rectangle rectGauche = new Rectangle(400, 900);
		Rectangle rectDroite = new Rectangle(400, 900);
		Image imgGauche = new Image(urlgauche);
		rectGauche.setFill(new ImagePattern(imgGauche));
		rectGauche.setStroke(Color.BLACK);
		Image imgDroite = new Image(urldroite);
		rectDroite.setFill(new ImagePattern(imgDroite));
		rectDroite.setStroke(Color.BLACK);
		Image imgFond = new Image(urlfond);
		this.setBackground(new Background(new BackgroundImage(imgFond, null, null, null, new BackgroundSize(100,100,false,false,true,true))));

		// Gestion du Layout Pane
		
		HBox hbox = new HBox(btnQuitter, lblScore, lblDifficulte);
		BorderPane.setMargin(grid, new Insets(15,50,50,75));
		this.setTop(hbox);
		this.setCenter(grid);
		this.setLeft(rectGauche);
		this.setRight(rectDroite);
		hbox.setSpacing(650);
		BorderPane.setMargin(hbox, new Insets(50,0,50,150));
		
		// Convenience method
		
		btnQuitter.setOnMouseClicked(new QuitterController());

	}
	
	// Getters/Setters
	
	public static String getDifficulte() {
		return difficulte;
	}

	public static void setDifficulte(String difficulte) {
		RootPane.difficulte = difficulte;
	}
}
