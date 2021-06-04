package unilim.info.ihm.fil_rouge.controller;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class QuitterController implements EventHandler<MouseEvent> {

	// Arrête l'application (utilisé lors du clic sur un bouton "Quitter")
	
	@Override
	public void handle(MouseEvent event) {
		Platform.exit();
	}

}
