/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.fraga.renata.view;


import br.fraga.renata.DAO.impl_bd.BDException;
import br.fraga.renata.DAO.impl_bd.FilmeDAOBD;
import br.fraga.renata.DAO.impl_bd.GeneroDAOBD;
import br.fraga.renata.dominio.Filme;
import br.fraga.renata.dominio.Genero;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Renata-Samsung
 */
public class FilmePainelController implements Initializable {
    
    
    @FXML
    private TextField tfTitulo;
    
    
    @FXML
    private TextField tfCodigoGenero;
      
    @FXML
    private AnchorPane painelCadastro;
    
    @FXML
    private TextArea taSinopse;
    
    
    @FXML
    private Button btCadastrar;
    
    
    @FXML
    private Button btLimpar;
    
    @FXML
    private VBox filmePainel;
    
    @FXML
    private ToolBar toolMenu;
    
    @FXML
    private TableView<Filme> tableViewFilme;
    @FXML
    private TableColumn<Filme, Integer> tableCCodigo;
    @FXML
    private TableColumn<Filme, String> tableCTitulo;
    
      
    @FXML
    private TableColumn<Filme, String> tableCSinopse;
    
    
    
    private List<Filme> listaFilmes;
    private Filme filmeSelecionado;
    private List<Genero> listageneros;
    
    private ObservableList<String> observableListaGeneros;
    private ObservableList<Filme> observableListaFilmes;
    private FilmeDAOBD filmeDAOBD;
    private GeneroDAOBD generoDAOBD;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        filmeDAOBD = new FilmeDAOBD();
        generoDAOBD = new GeneroDAOBD();

     
        
     
        
        //Codigo meio redundante - por isso as vezes é melhor um controller para cada view 
        if (tableViewFilme != null) {
            carregarTableViewFilme();
        }
        
        

    }        

    
    
    @FXML
    public void tratarInserir(ActionEvent event) throws IOException {
        filmeSelecionado = null;
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(this.getClass().getResource("CadastroFilme.fxml"));
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(filmePainel.getScene().getWindow());
        stage.showAndWait();
        carregarTableViewFilme();
        
   }



    private void carregarTableViewFilme() {
        tableCCodigo.setCellValueFactory(new PropertyValueFactory<Filme, Integer>("codigo"));
        tableCTitulo.setCellValueFactory(new PropertyValueFactory<Filme, String>("titulo"));
        tableCSinopse.setCellValueFactory(new PropertyValueFactory<Filme, String>("sinopse"));
        
        listaFilmes = filmeDAOBD.listar();

        observableListaFilmes = FXCollections.observableArrayList(listaFilmes);
        tableViewFilme.setItems(observableListaFilmes);
        

    }
    
    
     @FXML
    public void tratarEditar(ActionEvent event) throws IOException {
        Filme filmeSel = tableViewFilme.getSelectionModel().getSelectedItem();
        if (filmeSel != null) {
            FXMLLoader loader = new FXMLLoader(FilmePainelController.class.getResource("CadastroFilme.fxml"));
            Parent root = (Parent) loader.load();

            FilmePainelController filmeController = (FilmePainelController) loader.getController();
            filmeController.setFilmeSelecionado(filmeSel);

            Stage dialogStage = new Stage();
            dialogStage.setScene(new Scene(root));
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.initOwner(filmePainel.getScene().getWindow());
            dialogStage.showAndWait();
            carregarTableViewFilme();
        } else {
           JOptionPane.showMessageDialog(null, "kddkdk");
        }
    }

    @FXML
    public void tratarRemover(ActionEvent event) throws IOException {
       Filme filmeSel = tableViewFilme.getSelectionModel().getSelectedItem();
        if (filmeSel != null) {
            try {
                filmeDAOBD.remover(filmeSel);
                this.carregarTableViewFilme();
            } catch (BDException ex) {
               JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        } else {
             JOptionPane.showMessageDialog(null,"voce rpecisa seleconar essa oplcao");
        }
    }
    
    public void setFilmeSelecionado(Filme filmeSelecionado) {
        this.filmeSelecionado = filmeSelecionado;
        tfTitulo.setText(filmeSelecionado.getTitulo());
        tfCodigoGenero.setText(String.valueOf(filmeSelecionado.getGenero().getCodigo()));
        taSinopse.setText(filmeSelecionado.getSinopse());
       
    }
    
    
      
    @FXML
    public void cadastrarFilme() {
           
        
        Stage stage = (Stage) painelCadastro.getScene().getWindow();
        
        if(filmeSelecionado == null) //Se for cadastrar
        {
           
                if(tfTitulo.getText().equals("") || tfCodigoGenero.getText().equals("") ||
                      taSinopse.getText().equals("")) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Campos vazios!");
                        alert.setContentText("Preencha todos os campos");
                        alert.showAndWait();
                } else {
                    try {
                        filmeDAOBD.cadastrar(new Filme(
                                  tfTitulo.getText(), 
                                  generoDAOBD.buscarPorCodigo(Integer.parseInt(tfCodigoGenero.getText())), 
                                 taSinopse.getText()));                
                          stage.close();
                          Alert alert = new Alert(Alert.AlertType.INFORMATION);
                          alert.setTitle("Sucesso!");
                          alert.setContentText("Filme "+tfTitulo.getText()+" cadastrado com sucesso!");
                          alert.showAndWait();
                    } catch (BDException ex) {
                            System.out.println(ex.getMessage());
                    }
                }
           
            
        }
        else //Se for editar
        {
               if(tfTitulo.getText().equals("") || tfCodigoGenero.getText().equals("") ||
                      taSinopse.getText().equals("")) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Campos vazios!");
                        alert.setContentText("Preencha todos os campos");
                        alert.showAndWait();
                } else {
                        try {

                            filmeSelecionado.setTitulo(tfTitulo.getText());
                            filmeSelecionado.setGenero(generoDAOBD.buscarPorCodigo(Integer.parseInt(tfCodigoGenero.getText())));
                            filmeSelecionado.setSinopse(taSinopse.getText());


                            filmeDAOBD.alterar(filmeSelecionado);
                            stage.close();

                            stage.close();
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Sucesso!");
                            alert.setContentText("Filme "+tfTitulo.getText()+" alterado com sucesso!");
                            alert.showAndWait();
                        } catch (BDException ex) {
                            System.out.println(ex.getMessage());
                        }
               }
            
        
      }
    }

     public Filme getFilmeSelecionado() {
        return filmeSelecionado;
    }
     
     
    @FXML
    public void tratarVoltar(ActionEvent event) throws IOException {
        Stage stage = (Stage) filmePainel.getScene().getWindow();
        stage.setTitle("Cinema");
        Parent painelFilme = FXMLLoader.load(this.getClass().getResource("/view/MainPainel.fxml"));
        stage.setScene(new Scene(painelFilme));

    }
    
   @FXML
   public void limpar(ActionEvent event) throws IOException {
       tfCodigoGenero.clear();
       tfTitulo.clear();
       taSinopse.clear();
   }
}
