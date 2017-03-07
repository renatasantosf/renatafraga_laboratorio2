package br.fraga.renata.view;

import br.fraga.renata.DAO.impl_bd.BDException;
import br.fraga.renata.DAO.impl_bd.SalaDAOBD;
import br.fraga.renata.dominio.Sala;
import java.awt.Button;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Renata Fraga
 */
public class SalaUIController implements Initializable {

    @FXML 
    private AnchorPane painelSala; 
    
    @FXML
    private Button btInserir;
    
    @FXML
    private Button btRemover;
    
    @FXML
    private TableView<Sala> tableViewSala;
    
    @FXML
    private TableColumn<Sala, Integer> tableCNumero;
    
    @FXML
    private TableColumn<Sala, Integer> tableCQuantidade;
    
    private List<Sala> listaSalas;
    private Sala salaSelecionada;

    private ObservableList<Sala> observableListaSalas;
    private SalaDAOBD salaDAOBD;
   
      
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        salaDAOBD = new SalaDAOBD();
      
        //Codigo meio redundante - por isso as vezes é melhor um controller para cada view 
        if (tableViewSala != null) {
            carregarTableViewSala();
        }
    }    
    
    
    @FXML
    public void tratarInserir(ActionEvent event) throws IOException {
        salaSelecionada = null;
        cadastrarSala();
        carregarTableViewSala();
        
   }

    
    private void carregarTableViewSala() {
        tableCNumero.setCellValueFactory(new PropertyValueFactory<Sala, Integer>("numero"));
        tableCQuantidade.setCellValueFactory(new PropertyValueFactory<Sala, Integer>("quantidade"));
        listaSalas = salaDAOBD.listar();
        observableListaSalas = FXCollections.observableArrayList(listaSalas);
        tableViewSala.setItems(observableListaSalas);
    }
    
    
    @FXML
    public void tratarRemover(ActionEvent event) throws IOException {
       Sala salaSel = tableViewSala.getSelectionModel().getSelectedItem();
        if (salaSel != null) {
            try {
                salaDAOBD.remover(salaSel);
                this.carregarTableViewSala();
            } catch (BDException ex) {
               
            }
        } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Removido");
                alert.setContentText("Sala removida com sucesso");
                alert.showAndWait();
        }
    }
    
        
    @FXML
    public void cadastrarSala() {
           
        if(salaSelecionada == null) //Se for cadastrar
        {
            try {
                salaDAOBD.cadastrar(new Sala());                
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sucesso!");
                alert.setContentText("Sala inserida sucesso!");
                alert.showAndWait();
                
            } catch (BDException ex) {
                System.out.println(ex.getMessage());
            }
            
        }
            
    }

     public Sala getSalaSelecionada() {
        return salaSelecionada;
    }
     
     @FXML
    public void tratarVoltar(ActionEvent event) throws IOException {
        Stage stage = (Stage) painelSala.getScene().getWindow();
        stage.setTitle("Cinema");
        Parent painelFilme = FXMLLoader.load(this.getClass().getResource("/view/MainPainel.fxml"));
        stage.setScene(new Scene(painelFilme));

    }
}
