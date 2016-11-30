/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Renata-Samsung
 */
public class MainPainelController implements Initializable {
    
    @FXML
    private AnchorPane mainPanel;
    
    @FXML
    private Button btFilme;
    
    @FXML
    private Button btGenero;
        
    @FXML
    private Button btSala;
    
    @FXML
    private Button btSessao;
    
    @FXML
    private Button btVenda;
    
    @FXML
    private Button btRelatorio;
    
    
    @FXML
    private void tratarTelaFilme(ActionEvent event) throws IOException {
        Stage stage = (Stage) mainPanel.getScene().getWindow();
        stage.setTitle("Filmes");
        Parent painelTelaProxima = FXMLLoader.load(this.getClass().getResource("/view/FilmePainel1.fxml"));
        stage.setScene(new Scene(painelTelaProxima));

    }
    
    @FXML
    private void tratarTelaGenero(ActionEvent event) throws IOException {
        Stage stage = (Stage) mainPanel.getScene().getWindow();
        stage.setTitle("Gênero");
        Parent painelTelaProxima = FXMLLoader.load(this.getClass().getResource("/view/GeneroPrincipal.fxml"));
        stage.setScene(new Scene(painelTelaProxima));

    }
    
     @FXML
    private void tratarTelaSala(ActionEvent event) throws IOException {
        Stage stage = (Stage) mainPanel.getScene().getWindow();
        stage.setTitle("Salas");
        Parent painelTelaProxima = FXMLLoader.load(this.getClass().getResource("/view/SalaUI.fxml"));
        stage.setScene(new Scene(painelTelaProxima));

    }
    
    @FXML
    private void tratarTelaSessao(ActionEvent event) throws IOException {
        Stage stage = (Stage) mainPanel.getScene().getWindow();
        stage.setTitle("Sessões");
        Parent painelTelaProxima = FXMLLoader.load(this.getClass().getResource("/view/SessaoUI.fxml"));
        stage.setScene(new Scene(painelTelaProxima));

    }
    
    @FXML
    private void tratarTelaVenda(ActionEvent event) throws IOException {
        Stage stage = (Stage) mainPanel.getScene().getWindow();
        stage.setTitle("Vendas");
        Parent painelTelaProxima = FXMLLoader.load(this.getClass().getResource("/view/VendaUI.fxml"));
        stage.setScene(new Scene(painelTelaProxima));

    }
    
    @FXML
    private void tratarTelaRelatorio(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(this.getClass().getResource("RelatorioUI1.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("Relatórios");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(mainPanel.getScene().getWindow());
        stage.showAndWait();

        
    
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
       
            
}
