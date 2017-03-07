/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.fraga.renata.view;

import br.fraga.renata.DAO.impl_bd.BDException;
import br.fraga.renata.DAO.impl_bd.GeneroDAOBD;
import br.fraga.renata.dominio.Genero;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author renat
 */
public class GeneroUIController implements Initializable {
 
    /* painel Genero */
    
    @FXML
    private VBox painelGeneroPrincipal;
  
    @FXML 
    private Button btInserir;
    
    @FXML 
    private Button btAlterar;
    
    @FXML 
    private Button btRemover;
    
    @FXML 
    private TableView<Genero> tableViewGenero;
    
    @FXML 
    private TableColumn<Genero, Integer> tableCCodigo;
    
    @FXML 
    private TableColumn<Genero, String> tableCGenero;
    
    @FXML
    private TableColumn<Genero, String> tableCDescricao;
    
    
    
    @FXML
    private AnchorPane painelCadastroGenero;
    @FXML
    private TextField tfGenero;
    @FXML
    private TextArea taDescricao;
     
    
    private List<Genero> listaGeneros;
    private Genero generoSelecionado;

    private ObservableList<Genero> observableListaGeneros;
    private GeneroDAOBD generoDaobd;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        generoDaobd = new GeneroDAOBD();

        if (tableViewGenero != null) {
            carregarTableViewGenero();
        }
        
    }        

    
    @FXML
    public void tratarInserir(ActionEvent event) throws IOException {
        generoSelecionado = null;
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/CadastroGenero.fxml"));
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(painelGeneroPrincipal.getScene().getWindow());
        stage.showAndWait();
        carregarTableViewGenero();
        
   }

    private void carregarTableViewGenero() {
        tableCCodigo.setCellValueFactory(new PropertyValueFactory<Genero, Integer>("codigo"));
        tableCGenero.setCellValueFactory(new PropertyValueFactory<Genero, String>("nome"));
        tableCDescricao.setCellValueFactory(new PropertyValueFactory<Genero, String>("descricao"));
        
        listaGeneros = generoDaobd.listar();

        observableListaGeneros = FXCollections.observableArrayList(listaGeneros);
        tableViewGenero.setItems(observableListaGeneros);
     
    }
    
    
     @FXML
    public void tratarEditar(ActionEvent event) throws IOException {
        Genero genSel = tableViewGenero.getSelectionModel().getSelectedItem();
        if (genSel != null) {
            FXMLLoader loader = new FXMLLoader(GeneroUIController.class.getResource("CadastroGenero.fxml"));
            Parent root = (Parent) loader.load();

            GeneroUIController controller = (GeneroUIController) loader.getController();
            controller.setGeneroSelecionado(genSel);

            Stage dialogStage = new Stage();
            dialogStage.setScene(new Scene(root));
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.initOwner(painelGeneroPrincipal.getScene().getWindow());
            dialogStage.showAndWait();
            carregarTableViewGenero();
        } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERRO");
                alert.setContentText("Erro ao alterar o gênero.");
                alert.showAndWait();
        }
    }

    @FXML
    public void tratarRemover(ActionEvent event) throws IOException {
       Genero genSel = tableViewGenero.getSelectionModel().getSelectedItem();
        if (genSel != null) {
            try {
                generoDaobd.remover(genSel);
                this.carregarTableViewGenero();
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
    
    public void setGeneroSelecionado(Genero generoSelecionado) {
        this.generoSelecionado= generoSelecionado;
        tfGenero.setText(generoSelecionado.getNome());
        taDescricao.setText(generoSelecionado.getDescricao());
    }
    
          
    @FXML
    public void cadastrarGenero() {
                   
        Stage stage = (Stage) painelCadastroGenero.getScene().getWindow();
        
        if(generoSelecionado == null) {
            if(tfGenero.getText().equals("") || taDescricao.getText().equals("")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Campos vazios!");
                alert.setContentText("Preencha todos os campos");
                alert.showAndWait();
            } else {
                try {
                    generoDaobd.cadastrar(new Genero(
                            tfGenero.getText(), taDescricao.getText()));                
                    stage.close();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Sucesso!");
                    alert.setContentText("Gênero "+tfGenero.getText()+" inserido com sucesso!");
                    alert.showAndWait();
                } catch (BDException ex) {
                   Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERRO");
                    alert.setContentText("Erro ao cadastrar gênero.");
                    alert.showAndWait();
                }
            }
        } else {
            if(tfGenero.getText().equals("") || taDescricao.getText().equals("")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Campos vazios!");
                alert.setContentText("Preencha todos os campos");
                alert.showAndWait();
            } else {
                try {

                   generoSelecionado.setNome(tfGenero.getText());
                   generoSelecionado.setDescricao(taDescricao.getText());


                    generoDaobd.alterar(generoSelecionado);
                    stage.close();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Alteração");
                    alert.setContentText("Gênero alterado com sucesso!");
                    alert.showAndWait();
                } catch (BDException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
    }

     public Genero getGeneroSelecionado() {
        return generoSelecionado;
    }
     
     @FXML
    public void tratarVoltar(ActionEvent event) throws IOException {
        Stage stage = (Stage) painelGeneroPrincipal.getScene().getWindow();
        stage.setTitle("Cinema");
        Parent painelFilme = FXMLLoader.load(this.getClass().getResource("/view/MainPainel.fxml"));
        stage.setScene(new Scene(painelFilme));

    }
    
    @FXML
    public void limpar(ActionEvent event) throws IOException {
       tfGenero.clear();
       taDescricao.clear();
       
   }
    
}
      

    

