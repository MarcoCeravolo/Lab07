package it.polito.tdp.dizionario.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import it.polito.tdp.dizionario.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DizionarioController {

	Model model;
	
	@FXML
	private ResourceBundle resources;
	@FXML
	private URL location;
	@FXML
	private TextArea txtResult;
	@FXML
	private TextField inputNumeroLettere;
	@FXML
	private TextField inputParola;
	@FXML
	private Button btnGeneraGrafo;
	@FXML
	private Button btnTrovaVicini;
	@FXML
	private Button btnTrovaGradoMax;

    @FXML
    private Button btnTuttiVicini;

	@FXML
	void doReset(ActionEvent event) {
		txtResult.clear();
	}

	@FXML
	void doGeneraGrafo(ActionEvent event) {
		int numeroLettere = Integer.parseInt(inputNumeroLettere.getText());
		UndirectedGraph<String, DefaultEdge> grafo = model.createGraph(numeroLettere);

		try {
			txtResult.setText(grafo.toString());
			txtResult.appendText("\n");			
		} catch (RuntimeException re) {
			txtResult.setText(re.getMessage());
		}
	}

	@FXML
	void doTrovaGradoMax(ActionEvent event) {
		int numeroLettere = Integer.parseInt(inputNumeroLettere.getText());
		String grado = model.findMaxDegree(numeroLettere);
		try {
			txtResult.appendText(grado);

		} catch (RuntimeException re) {
			txtResult.setText(re.getMessage());
		}
	}

	@FXML
	void doTrovaVicini(ActionEvent event) {
		int numeroLettere = Integer.parseInt(inputNumeroLettere.getText());
		String parola = inputParola.getText();
		if(parola.length()!=numeroLettere){
			txtResult.setText("La parola inserita non è della lunghezza inserita!");
			return;
		}
		List<String> vicini = model.displayNeighbours(parola, numeroLettere);
		try {
			txtResult.appendText("VICINI:\n");
			for(String s : vicini){
				txtResult.appendText(s+"\n");
			}
		} catch (RuntimeException re) {
			txtResult.setText(re.getMessage());
		}
	}
	
	@FXML
	void doTuttiVicini(ActionEvent event) {
		int numeroLettere = Integer.parseInt(inputNumeroLettere.getText());
		String parola = inputParola.getText();
		if(parola.length()!=numeroLettere){
			txtResult.setText("La parola inserita non è della lunghezza inserita!");
			return;
		}
		List<String> tuttiVicini = model.tuttiVicini(parola, numeroLettere);
		try {
			txtResult.appendText("TUTTI I VICINI:\n");
			for(String s : tuttiVicini){
				txtResult.appendText(s+"\n");
			}
		} catch (RuntimeException re) {
			txtResult.setText(re.getMessage());
		}
	}

	@FXML
	void initialize() {
		assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Dizionario.fxml'.";
		assert inputNumeroLettere != null : "fx:id=\"inputNumeroLettere\" was not injected: check your FXML file 'Dizionario.fxml'.";
		assert inputParola != null : "fx:id=\"inputParola\" was not injected: check your FXML file 'Dizionario.fxml'.";
		assert btnGeneraGrafo != null : "fx:id=\"btnGeneraGrafo\" was not injected: check your FXML file 'Dizionario.fxml'.";
		assert btnTrovaVicini != null : "fx:id=\"btnTrovaVicini\" was not injected: check your FXML file 'Dizionario.fxml'.";
		assert btnTrovaGradoMax != null : "fx:id=\"btnTrovaTutti\" was not injected: check your FXML file 'Dizionario.fxml'.";
		assert btnTuttiVicini != null : "fx:id=\"btnTuttiVicini\" was not injected: check your FXML file 'Dizionario.fxml'.";
	}

	/**
	 * @param model the model to set
	 */
	public void setModel(Model model) {
		this.model = model;
	}

	
}