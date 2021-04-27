/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.poweroutages;

import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

import it.polito.tdp.poweroutages.model.EventBlackOut;
import it.polito.tdp.poweroutages.model.Model;
import it.polito.tdp.poweroutages.model.Nerc;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="cmbNerc"
    private ComboBox<Nerc> cmbNerc; // Value injected by FXMLLoader

    @FXML // fx:id="txtYears"
    private TextField txtYears; // Value injected by FXMLLoader

    @FXML // fx:id="txtHours"
    private TextField txtHours; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    private Model model;
    
    @FXML
    void doRun(ActionEvent event) {
    	txtResult.clear();
    	String anniS= this.txtYears.getText();
    	String oreS= this.txtHours.getText();
    	int anniMax=0;
    	int oreMax=0;
    	try {
    		anniMax= Integer.parseInt(anniS);
    		oreMax= Integer.parseInt(oreS);
    	} catch( NumberFormatException e) {
    		this.txtResult.setText("Inserisci correttamente il numero di ore e anni!");
    	}
    	Nerc n= this.cmbNerc.getValue();
    	Set <EventBlackOut> eventi= model.calcolaSottoInsiemeEventi(n.getValue(), anniMax, oreMax);
    	String result="";
    	int clienti= this.model.calcolaNumeroClienti(eventi);
    	result=result+"Il numeroClienti afflitti e': "+clienti+"\n";
    	float contoOre= this.model.calcolaNumOre(eventi);
    	result=result+"Il numero ore totali e': "+contoOre+"\n";
    	
    	for(EventBlackOut e: eventi) {
    		result=result+ e.getAnno()+" "+e.getDate_event_began()+" "+e.getDate_event_finished()+" "+e.getOreDisservizio()+" "+e.getCustomers_affected()+"\n";
    	}
    	
    	this.txtResult.setText(result);
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert cmbNerc != null : "fx:id=\"cmbNerc\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtYears != null : "fx:id=\"txtYears\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtHours != null : "fx:id=\"txtHours\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        
        // Utilizzare questo font per incolonnare correttamente i dati;
        txtResult.setStyle("-fx-font-family: monospace");
    }
    
    public void setModel(Model model) {
    	this.model = model;
    	for(Nerc n: model.getNercList()) {
    		this.cmbNerc.getItems().add(n);
    	}
    }
}
