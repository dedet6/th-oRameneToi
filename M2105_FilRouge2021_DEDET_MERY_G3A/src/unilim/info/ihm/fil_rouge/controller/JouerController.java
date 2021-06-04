package unilim.info.ihm.fil_rouge.controller;

import java.util.Optional;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import unilim.info.ihm.fil_rouge.Main;
import unilim.info.ihm.fil_rouge.view.RootPane;

public class JouerController implements EventHandler<MouseEvent> {

	@Override
	public void handle(MouseEvent event) {
		
		// Gestion de la boîte de dialogue du menu si le joueur n'a pas choisi de difficulté
		
		if(difficulteNull()) {
			Alert erreur = new Alert(AlertType.WARNING);
			erreur.setTitle("Fin de la partie !");
			erreur.setHeaderText("ERREUR ! Vous devez saisir une difficulté avant de jouer !");
			erreur.setContentText("Que souhaitez-vous faire ?");
			ButtonType boutonQuitter = new ButtonType("Quitter");
			ButtonType boutonRejouer = new ButtonType("Relancer");
			erreur.getButtonTypes().setAll(boutonQuitter,boutonRejouer);
	        Optional<ButtonType> option = erreur.showAndWait();
			if(option.get()==boutonQuitter) {
				Platform.exit();
			}
			if(option.get()==boutonRejouer) {
				Main.getMusique().stop();
				Main.getSecondaryStage().close();
				Platform.runLater(() -> new Main().start(new Stage()));
			}
		}
		else {
			RootPane root = new RootPane();
			Main.getSecondaryStage().setScene(new Scene(root,1930,1010));
		}
	}

	private boolean difficulteNull() {
		return RootPane.getDifficulte().equals("");
	}

}
