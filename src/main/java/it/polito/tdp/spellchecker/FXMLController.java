/**
 /**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.spellchecker;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class FXMLController {
	Dictionary model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="buttonClear"
    private Button buttonClear; // Value injected by FXMLLoader

    @FXML // fx:id="comboBox"
    private ComboBox<String> comboBox; // Value injected by FXMLLoader

    @FXML // fx:id="spellButton"
    private Button spellButton; // Value injected by FXMLLoader

    @FXML // fx:id="txtInserimento"
    private TextArea txtInserimento; // Value injected by FXMLLoader

    @FXML // fx:id="txtNumErrori"
    private Label txtNumErrori; // Value injected by FXMLLoader

    @FXML // fx:id="txtRisultato"
    private TextArea txtRisultato; // Value injected by FXMLLoader

    @FXML // fx:id="txtTime"
    private Label txtTime; // Value injected by FXMLLoader

    @FXML
    void clearText(ActionEvent event) {
    	this.txtInserimento.clear();
    	this.txtNumErrori.setText("");
    	this.txtTime.setText("");
    	this.txtRisultato.clear();
    }

    @FXML
    void doCheck(ActionEvent event) {
    	long time1=System.currentTimeMillis();
    	String testoInserito=this.txtInserimento.getText();
    	// do al modello la lingua da utilizzare
    	
    	model.loadDictionary(this.comboBox.getValue());
    	
    	// do il testo inserito al modello in modo che possa controllare
    	List<RichWord> listaRisultato=model.spellCheckTextDicotomic(testoInserito);
    	String risultato="";
    	int numeroErrori=0;
    	for (RichWord parola:listaRisultato) {
    		if(!parola.isConfronto()) {
    			risultato+=parola.getParolaInput()+"\n";
    			numeroErrori++;
    		}
    	}
    	this.txtRisultato.setText(risultato);
    	this.txtNumErrori.setText("The text contains "+numeroErrori+" errors");
    	
    	long time2=System.currentTimeMillis();
    	double time=time2-time1;
    	time=time/1000;
    	this.txtTime.setText("Spell Check Completed in "+time+" seconds");
    	
    	

    }

    @FXML
    void selectedLanguage(ActionEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert buttonClear != null : "fx:id=\"buttonClear\" was not injected: check your FXML file 'Scene.fxml'.";
        assert comboBox != null : "fx:id=\"comboBox\" was not injected: check your FXML file 'Scene.fxml'.";
        assert spellButton != null : "fx:id=\"spellButton\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtInserimento != null : "fx:id=\"txtInserimento\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNumErrori != null : "fx:id=\"txtNumErrori\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTime != null : "fx:id=\"txtTime\" was not injected: check your FXML file 'Scene.fxml'.";

        
        this.comboBox.getItems().add("English");
        this.comboBox.getItems().add("Italian");
    }
    
    
    public void setModel(Dictionary model) {
    	this.model=model;
    	
    }

}
