package unilim.info.ihm.fil_rouge.view;

import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import unilim.info.ihm.fil_rouge.controller.Controller;

public class Carte extends Pane {
	
	// Déclaration des composants
	
	private Rectangle zoneCarte;
	private String urlDosCarte = new File("images/carte_dos.png").toURI().toString();
	private Image imgDos;
	private Image imgFace;
	
	public Carte(Image image) {
		
		// Instanciation des composants
		
		imgDos = new Image(urlDosCarte);
		this.imgFace = image;
		zoneCarte = new Rectangle(200, 250);
		zoneCarte.setFill(new ImagePattern(imgDos));
		getChildren().addAll(zoneCarte);	
		
		// Convenience method
		
		this.setOnMouseClicked(new Controller(this));

	}

	// Getters/Setters

	public Rectangle getZoneCarte() {
		return zoneCarte;
	}

	public Image getImgDos() {
		return imgDos;
	}

	public Image getImgFace() {
		return imgFace;
	}
	
}
