package unilim.info.ihm.fil_rouge.controller;

import java.io.File;
import java.util.Optional;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;
import javafx.util.Duration;
import unilim.info.ihm.fil_rouge.Main;
import unilim.info.ihm.fil_rouge.model.Model;
import unilim.info.ihm.fil_rouge.view.Carte;
import unilim.info.ihm.fil_rouge.view.CartePaire;
import unilim.info.ihm.fil_rouge.view.RootPane;

public class Controller implements EventHandler<MouseEvent> {

	// Déclaration des composants
	
	private static Carte premiereCarte;
	private Carte secondeCarte;
	private static String memoireImage;
	private static String urlImage = "";
	private static boolean noSpam = true;
	private static int nbPairesTrouvees = 0;
	
	// Instanciation des composants
	
	private Model valeurScore = new Model();
	private AudioClip bruitCarte = new AudioClip(new File("musiques/son_retourner_carte.wav").toURI().toString());
	
	// Les Timeline qui permettent de laisser les cartes visibles une seconde
	
	private Timeline cacherCartes = new Timeline(
            new KeyFrame(Duration.seconds(1), sec->{
				premiereCarte.getZoneCarte().setFill(new ImagePattern(premiereCarte.getImgDos()));
        		secondeCarte.getZoneCarte().setFill(new ImagePattern(secondeCarte.getImgDos()));
				bruitCarte.play();
				noSpam = true;
            }));
	
	private Timeline disparaitreCartes = new Timeline(
            new KeyFrame(Duration.seconds(1), sec->{
				secondeCarte.setVisible(false);
				premiereCarte.setVisible(false);
			}));

	// Constructeur
	
	public Controller(Carte card) {
		this.secondeCarte = card;
	}

	// Méthode handle

	@Override
	public void handle(MouseEvent event) {
		Node selected = (Node) event.getTarget();
		
		// Clic sur une carte dans la StackPane
		
		if (clicSurCarte(selected)) {
			
			// Clic sur la première carte
			
			if (clicSurLaPremiereCarte()) {
				premiereCarte = secondeCarte;
				memoireImage = secondeCarte.getImgFace().toString();
				premiereCarte.getZoneCarte().setFill(new ImagePattern(premiereCarte.getImgFace()));
				bruitCarte.play();
				urlImage = "autre";
				
			} else {
				
				// Clic sur la seconde carte
				
				if (clicSurLaSecondeCarte()) {
					noSpam = false;
					secondeCarte.getZoneCarte().setFill(new ImagePattern(secondeCarte.getImgFace()));
					bruitCarte.play();
				    cacherCartes.playFromStart();
				    valeurScore.setValue(valeurScore.getValue() - 20);
				    
				    // Si les cartes sont identiques
				    
					if (cartesIdentiques()) {
						disparaitreCartes.playFromStart();
						nbPairesTrouvees = nbPairesTrouvees + 1;
					    valeurScore.setValue(valeurScore.getValue() + 120);
					    
					    // Lorsque la partie est finie
					    
						if(finDeLaPartie()) {
							dialogueFin();
						}
					}					
				}
				urlImage = "";
			}
		}
	}

	private boolean finDeLaPartie() {
		return nbPairesTrouvees==CartePaire.getNombrePaires();
	}

	private boolean cartesIdentiques() {
		return memoireImage.equals(secondeCarte.getImgFace().toString());
	}

	private boolean clicSurLaSecondeCarte() {
		return premiereCarte != secondeCarte;
	}

	private boolean clicSurLaPremiereCarte() {
		return urlImage.equals("") && noSpam == true;
	}

	private boolean clicSurCarte(Node selected) {
		return !(selected instanceof Pane || noSpam == false);
	}
	
	// Méthode d'affichage de la boîte de dialogue de fin de partie
	
	public void dialogueFin() {
		Alert victoire = new Alert(AlertType.INFORMATION);
		victoire.setTitle("Fin de la partie !");
		victoire.setHeaderText("Félicitations ! Vous avez retrouvé toutes les paires de cartes !");
		victoire.setContentText("Vous avez fait un score de " + valeurScore.getValue() + " ! Que souhaitez-vous faire ?");
		ButtonType boutonQuitter = new ButtonType("Quitter");
		ButtonType boutonRejouer = new ButtonType("Relancer");
		victoire.getButtonTypes().setAll(boutonQuitter,boutonRejouer);
        Optional<ButtonType> option = victoire.showAndWait();
		if(option.get()==boutonQuitter) {
			Platform.exit();
		}
		if(option.get()==boutonRejouer) {
			nbPairesTrouvees = 0;
			Main.getMusique().stop();
			RootPane.setDifficulte("");
			Main.getSecondaryStage().close();
			Platform.runLater(() -> new Main().start(new Stage()));
		}
	}
}