/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import DAO.impl_bd.SessaoDAOBD;
import DAO.impl_bd.VendaDAOBD;
import dominio.Filme;
import dominio.Sala;
import dominio.Sessao;
import dominio.Venda;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author renat
 */
public class VendaUIController implements Initializable {

    @FXML
    private AnchorPane vendaPainel;
    
    @FXML
    private Button btComprarIngresso;
    
    @FXML
    private Button btVenderIngresso;
    
    @FXML
    private Button btVoltar;
    
    @FXML
    private TableView<Venda> tableViewVenda;
    
    @FXML
    private TableColumn<Venda, Integer> tableCCodVenda;
    
    @FXML
    private TableColumn<Venda, Integer> tableCCodSessao;
    
    @FXML
    private TableColumn<Venda, String> tableCHorario;
    
    @FXML
    private TableColumn<Venda, String> tableCFilme;
    
    @FXML
    private TableColumn<Venda, Integer> tableCCapacidade;
    
    
    
    @FXML
    private AnchorPane painelEfetuarVenda;
    
    @FXML
    private Button btEfetuarVenda;
    
    @FXML
    private TextField tfSessao;
    
    private List<Venda> listaVendas;
    private Venda vendaSelecionada;
    
    private VendaDAOBD vendaDAOBD;
    private SessaoDAOBD sessaoDAOBD;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sessaoDAOBD = new SessaoDAOBD();
        vendaDAOBD = new VendaDAOBD();
    }    
    
    @FXML
    public void tratarVoltar(ActionEvent event) throws IOException {
        Stage stage = (Stage) vendaPainel.getScene().getWindow();
        stage.setTitle("Cinema");
        Parent painelFilme = FXMLLoader.load(this.getClass().getResource("/view/MainPainel.fxml"));
        stage.setScene(new Scene(painelFilme));

    }
    
    @FXML
    public void tratarComprarIngresso(ActionEvent event) throws IOException {
        vendaSelecionada = null;
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/CadastroVenda1.fxml"));
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(vendaPainel.getScene().getWindow());
        stage.showAndWait();
        carregarTableViewVenda();
    }
    
    private void carregarTableViewVenda() {
        tableCCodVenda.setCellValueFactory(new PropertyValueFactory<Venda, Integer>("codigo"));
        tableCCodSessao.setCellValueFactory(new PropertyValueFactory<Venda, Integer>("codsessao"));
        tableCHorario.setCellValueFactory(new PropertyValueFactory<Venda, String>("horario"));
        tableCFilme.setCellValueFactory(new PropertyValueFactory<Venda, String>("titulo"));
        tableCCapacidade.setCellValueFactory(new PropertyValueFactory<Venda, Integer>("quantidade"));
        
       

      /*  observableListaSessoes = FXCollections.observableArrayList(listaSessoes);
        tableViewSessao.setItems(observableListaSessoes);*/
     
    }
    
}
