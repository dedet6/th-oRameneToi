package unilim.info.ihm.fil_rouge.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Model {
	
	// Contient la valeur du score
	
    private static SimpleIntegerProperty valeurScore = new SimpleIntegerProperty("value", null);

    // Constructeur
    
    public Model() {
        valeurScore.set(0);
    }

    // Getters/Setters
    
    public int getValue(){
        return valeurScore.get();
    }

    public void setValue(int valeur){
        Model.valeurScore.set(valeur);
    }
    
    // Property
    
    public IntegerProperty valueProperty(){
        return valeurScore;
    }
}
