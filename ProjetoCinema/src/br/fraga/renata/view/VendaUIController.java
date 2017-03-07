/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.fraga.renata.view;

import br.fraga.renata.DAO.impl_bd.BDException;
import br.fraga.renata.DAO.impl_bd.SessaoDAOBD;
import br.fraga.renata.DAO.impl_bd.VendaDAOBD;
import br.fraga.renata.dominio.Filme;
import br.fraga.renata.dominio.Sala;
import br.fraga.renata.dominio.Sessao;
import br.fraga.renata.dominio.Venda;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

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
    
    private ObservableList<Venda> observableListVendas;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sessaoDAOBD = new SessaoDAOBD();
        vendaDAOBD = new VendaDAOBD();
        
        
        if (tableViewVenda != null) {
            carregarTableViewVenda();
        }
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
        Parent root = FXMLLoader.load(this.getClass().getResource("CadastroVenda1.fxml"));
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(vendaPainel.getScene().getWindow());
        stage.showAndWait();
        carregarTableViewVenda();
    }
    
    
     @FXML
    public void tratarRemover(ActionEvent event) throws IOException {
       Venda vendSel = tableViewVenda.getSelectionModel().getSelectedItem();
        if (vendSel != null) {
            try {
                vendaDAOBD.remover(vendSel);
                this.carregarTableViewVenda();
            } catch (BDException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERRO");
                alert.setContentText("Não foi possivel remover.");
                alert.showAndWait();
            }
        } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("ERRO");
                alert.setContentText("Você precisa selecionar uma linha.");
                alert.showAndWait();
        }
    }
    
    private void carregarTableViewVenda() {
        tableCCodVenda.setCellValueFactory(new PropertyValueFactory<Venda, Integer>("codigo"));
        tableCCodSessao.setCellValueFactory(new PropertyValueFactory<Venda, Integer>("sessao"));
        tableCHorario.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Venda, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Venda, String> cell) {
                final Venda venda = cell.getValue();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy  hh:mm");
                final SimpleObjectProperty<String> simpleObject = new SimpleObjectProperty(dateFormat.format(venda.getSessao().getHorario()));
                return simpleObject;
            }

        });
       
        tableCFilme.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Venda, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Venda, String> cell) {
                final Venda venda = cell.getValue();
                final SimpleObjectProperty<String> simpleObject = new SimpleObjectProperty(String.format(venda.getSessao().getFilme().getTitulo()));
                return simpleObject;
            }

        });
        tableCCapacidade.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Venda, Integer>, ObservableValue<Integer>>() {
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Venda, Integer> cell) {
                final Venda venda = cell.getValue();
                final SimpleObjectProperty<Integer> simpleObject = new SimpleObjectProperty(venda.getSessao().getQuantidade());
                return simpleObject;
                }
        });
        
        listaVendas = vendaDAOBD.listar();

        observableListVendas = FXCollections.observableArrayList(listaVendas);
        tableViewVenda.setItems(observableListVendas);
     
    }
    
    
    @FXML
    public void efetuarVenda() throws ParseException {
                   
        Stage stage = (Stage) painelEfetuarVenda.getScene().getWindow();
        
        if(vendaSelecionada == null) {
            if(tfSessao.getText().equals("")) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Campos vazios!");
                    alert.setContentText("Preencha todos os campos");
                    alert.showAndWait();
            } else { 
                try {
                    vendaDAOBD.venderIngresso(Integer.parseInt(tfSessao.getText()),
                            sessaoDAOBD.buscarPorCodigo(Integer.parseInt(tfSessao.getText())));                   
                    vendaDAOBD.cadastrar(new Venda(sessaoDAOBD.buscarPorCodigo(Integer.parseInt(tfSessao.getText()))));


                    stage.close();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Sucesso!");
                    alert.setContentText("Venda efetuada com sucesso!");
                    alert.showAndWait();

                } catch (BDException ex) {
                   Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERRO");
                    alert.setContentText("Erro ao inserir sessão.");
                    alert.showAndWait();
                }
            }
            
        }
    }
}
