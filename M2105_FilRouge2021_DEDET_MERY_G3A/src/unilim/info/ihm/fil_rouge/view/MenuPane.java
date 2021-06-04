package unilim.info.ihm.fil_rouge.view;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import unilim.info.ihm.fil_rouge.controller.JouerController;
import unilim.info.ihm.fil_rouge.controller.QuitterController;

public class MenuPane extends BorderPane{
	
	// Instanciation des sources des diférentes images
	
	private String urlFondMenu = new File("images/fond_menu.jpg").toURI().toString();
	private String urlFondCarteFacile = new File("images/fond_menu_facile.png").toURI().toString();
	private String urlFondCarteMoyen = new File("images/fond_menu_moyen.png").toURI().toString();
	private String urlFondCarteDifficile = new File("images/fond_menu_difficile.png").toURI().toString();
	private String urlFondGauche = new File("images/fond_menu_gauche.jpg").toURI().toString();
	private String urlFondDroite = new File("images/fond_menu_droite.jpg").toURI().toString();
	private StackPane zoneCentre;
	
	public MenuPane() {
		
		// Instanciation des composants
		
		Button btnJouer = new Button("Jouer");
		btnJouer.setStyle("-fx-font-weight: bold; -fx-font-size: 22px; -fx-background-color: #617BCD; -fx-text-fill: #353131;");
		
		ComboBox<String> cbDifficulte = new ComboBox<String>();
		cbDifficulte.getItems().addAll("Facile", "Moyen", "Difficile");
		cbDifficulte.setStyle("-fx-font-weight: bold; -fx-font-size: 22px; -fx-background-color: #617BCD");
		
		Button btnQuitter = new Button("Quitter");
		btnQuitter.setStyle("-fx-font-weight: bold; -fx-font-size: 22px; -fx-background-color: #617BCD; -fx-text-fill: #353131;");
		
		// Instanciation des images
		
		Image imgFond = new Image(urlFondMenu);
		Rectangle zoneImgFond = new Rectangle(730,380);
		zoneImgFond.setTranslateY(-215);
		zoneImgFond.setTranslateX(-10);
		Rectangle zoneImgGauche = new Rectangle(500,1010);
		zoneImgGauche.setFill(new ImagePattern(new Image(urlFondGauche)));
		Rectangle zoneImgDroite = new Rectangle(500,1010);
		zoneImgDroite.setFill(new ImagePattern(new Image(urlFondDroite)));
		
		// Gestion du Layout Pane
		
		HBox hboxMenu = new HBox(btnJouer,cbDifficulte,btnQuitter);
		hboxMenu.setAlignment(Pos.CENTER_RIGHT);
		hboxMenu.setSpacing(100);
		hboxMenu.setTranslateX(-10);
		hboxMenu.setAlignment(Pos.CENTER);
		hboxMenu.setTranslateY(200);
		this.zoneCentre = new StackPane(zoneImgFond, hboxMenu);
		zoneCentre.setBackground(new Background(new BackgroundImage(imgFond, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, null, new BackgroundSize(870,1010,false,false,false,false))));
		this.setCenter(zoneCentre);
		this.setLeft(zoneImgGauche);
		this.setRight(zoneImgDroite);
		BorderPane.setMargin(zoneCentre, new Insets(0,0,0,30));
		BorderPane.setMargin(hboxMenu, new Insets(0,200,50,0));
		BorderPane.setMargin(zoneImgFond, new Insets(103,0,0,85));
		
		// Changement du fond d'écran sur le côté haut du menu
		
		cbDifficulte.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
            	if (difficulteFacile(cbDifficulte)) {
            		zoneImgFond.setFill(new ImagePattern(new Image(urlFondCarteFacile)));
            	}
            	if (difficulteMoyen(cbDifficulte)) {
            		zoneImgFond.setFill(new ImagePattern(new Image(urlFondCarteMoyen)));
            	}
            	if (difficulteDifficile(cbDifficulte)) {
            		zoneImgFond.setFill(new ImagePattern(new Image(urlFondCarteDifficile)));
            	}
        		RootPane.setDifficulte(cbDifficulte.getValue());
            }

			private boolean difficulteDifficile(ComboBox<String> cbDifficulte) {
				return cbDifficulte.getValue().equals("Difficile");
			}

			private boolean difficulteMoyen(ComboBox<String> cbDifficulte) {
				return cbDifficulte.getValue().equals("Moyen");
			}

			private boolean difficulteFacile(ComboBox<String> cbDifficulte) {
				return cbDifficulte.getValue().equals("Facile");
			}});
		
		// Convenience methods
		
		btnQuitter.setOnMouseClicked(new QuitterController());
		
		btnJouer.setOnMouseClicked(new JouerController());

	}

}
