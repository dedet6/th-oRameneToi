package unilim.info.ihm.fil_rouge;

import java.io.File;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import unilim.info.ihm.fil_rouge.view.MenuPane;

public class Main extends Application {
	
	// Déclaration des composants
	
	private static Stage secondaryStage;
	private static Media sourceMusique = new Media(new File("musiques/son_menu.wav").toURI().toString());
	private static MediaPlayer musique	= new MediaPlayer(sourceMusique);

	@Override
	public void start(Stage primaryStage) {
		try {
			
			// Affectation de la Stage de jeu

			secondaryStage = primaryStage;

			// Instanciation de la Scene du menu
			
			MenuPane menu = new MenuPane();
			Scene SceneMenu = new Scene(menu,1930,1010);
			
			// Gestion de la musique de fond
			
			musique.setVolume(0.05);
			musique.setCycleCount(10);
			musique.play();
			musique.setOnEndOfMedia(new Runnable() {
		        @Override
		        public void run() {
		            musique.seek(Duration.ZERO);
		            musique.play();
		        }
		    });

			// Gestion de la Stage
			
			primaryStage.setScene(SceneMenu);
			primaryStage.setTitle("Pokemon Trading Card Memory Game !");
			primaryStage.show();
			primaryStage.setResizable(false);
			primaryStage.getIcons().add(new Image(new File("images/icone.jpg").toURI().toString()));	
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	// Getters
	
	public static Stage getSecondaryStage() {
		return secondaryStage;
	}

	public static MediaPlayer getMusique() {
		return musique;
	}
	
	
}
