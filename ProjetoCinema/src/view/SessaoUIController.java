package view;

import DAO.impl_bd.BDException;
import DAO.impl_bd.FilmeDAOBD;
import DAO.impl_bd.SalaDAOBD;
import DAO.impl_bd.SessaoDAOBD;
import dominio.Filme;
import dominio.Sala;
import dominio.Sessao;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import util.DateUtil;

/**
 * FXML Controller class
 *
 * @author renat
 */
public class SessaoUIController implements Initializable {

     /* painel Sessão */
    
    @FXML
    private AnchorPane painelSessao;
  
    @FXML 
    private Button btInserir;
    
    @FXML 
    private Button btAlterar;
    
    @FXML 
    private Button btRemover;
    
    @FXML 
    private TableView<Sessao> tableViewSessao;
    
    @FXML 
    private TableColumn<Sessao, Integer> tableCCodigo;
    
    @FXML 
    private TableColumn<Sessao, String> tableCHorario;
    
    @FXML
    private TableColumn<Sessao, String> tableCFilme;
    
    @FXML
    private TableColumn<Sessao, Integer> tableCSala;
    
    
    @FXML
    private AnchorPane painelCadastroSessao;
    
    @FXML
    private TextField tfHorario;
    
    @FXML
    private TextField tfSala;
    
    @FXML
    private TextField tfFilme;
    
   
    
    private List<Sessao> listaSessoes;
    private Sessao sessaoSelecionada;

    private ObservableList<Sessao> observableListaSessoes;
   
    
    private SessaoDAOBD sessaoDAOBD;
    private FilmeDAOBD filmeDAOBD;
    private SalaDAOBD salaDAOBD;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        filmeDAOBD = new FilmeDAOBD();
        salaDAOBD = new SalaDAOBD();
        sessaoDAOBD = new SessaoDAOBD();
        
          if (tableViewSessao != null) {
            carregarTableViewSessao();
        }
          
        
    }    
    
    @FXML
    public void tratarInserir(ActionEvent event) throws IOException {
        sessaoSelecionada = null;
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/CadastroSessao.fxml"));
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(painelSessao.getScene().getWindow());
        stage.showAndWait();
        carregarTableViewSessao();
        
   }

    private void carregarTableViewSessao() {
            
        
        tableCCodigo.setCellValueFactory(new PropertyValueFactory<Sessao,Integer>("codigo"));
        tableCHorario.setCellValueFactory(new PropertyValueFactory<Sessao, String>("horario"));
        tableCFilme.setCellValueFactory(new PropertyValueFactory<Sessao, String>("filme.titulo"));
        tableCSala.setCellValueFactory(new PropertyValueFactory<Sessao, Integer>("sala.numero"));
        
        
            
        
        listaSessoes = sessaoDAOBD.listar();
        
        

        observableListaSessoes = FXCollections.observableArrayList(listaSessoes);
        tableViewSessao.setItems(observableListaSessoes);
     
    }
    
    
     @FXML
    public void tratarEditar(ActionEvent event) throws IOException {
        Sessao sessaoSel = tableViewSessao.getSelectionModel().getSelectedItem();
        if (sessaoSel!= null) {
            FXMLLoader loader = new FXMLLoader(SessaoUIController.class.getResource("CadastroSessao.fxml"));
            Parent root = (Parent) loader.load();

            SessaoUIController controller = (SessaoUIController) loader.getController();
            controller.setSessaoSelecionada(sessaoSel);

            Stage dialogStage = new Stage();
            dialogStage.setScene(new Scene(root));
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.initOwner(painelSessao.getScene().getWindow());
            dialogStage.showAndWait();
            carregarTableViewSessao();
        } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERRO");
                alert.setContentText("Erro ao alterar a sessao.");
                alert.showAndWait();
        }
    }

    @FXML
    public void tratarRemover(ActionEvent event) throws IOException {
       Sessao sessaoSel = tableViewSessao.getSelectionModel().getSelectedItem();
        if (sessaoSel != null) {
            try {
                sessaoDAOBD.remover(sessaoSel);
                this.carregarTableViewSessao();
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
    
    public void setSessaoSelecionada(Sessao sessaoSelecionada) {
        this.sessaoSelecionada= sessaoSelecionada;
        tfHorario.setText(DateUtil.dateHourToString(sessaoSelecionada.getHorario()));
        
    }
    
          
    @FXML
    public void cadastrarSessao() throws ParseException {
                   
        Stage stage = (Stage) painelCadastroSessao.getScene().getWindow();
        
        if(sessaoSelecionada == null) {
        
            try {
                                            
                tratarCadastroData(tfHorario.getText(),Integer.parseInt(tfSala.getText()),
                        salaDAOBD.buscarPorCodigo(Integer.parseInt(tfSala.getText())),
                filmeDAOBD.buscarPorCodigo(Integer.parseInt(tfFilme.getText())));
                
                
            } catch (BDException ex) {
               Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERRO");
                alert.setContentText("Erro ao inserir sessão.");
                alert.showAndWait();
            }
            
        } else {
            try {
                        
                tratarEditarData(tfHorario.getText(),Integer.parseInt(tfSala.getText()),
                        salaDAOBD.buscarPorCodigo(Integer.parseInt(tfSala.getText())), 
                        filmeDAOBD.buscarPorCodigo(Integer.parseInt(tfFilme.getText())));
                
                stage.close();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Alteração");
                alert.setContentText("Sessão alterada com sucesso!");
                alert.showAndWait();
            } catch (BDException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

     public Sessao getSessaoSelecionada() {
        return sessaoSelecionada;
    }
     
     public void tratarCadastroData(String dataHora,int codigoSala,Sala sala,Filme filme) {
         Date horario;
         Date hoje = new Date();
         
         try {
                horario = DateUtil.stringToDateHour(dataHora);
                
                
                if(horario.compareTo(hoje)==-1) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("DATA INVÁLIDA");
                    alert.setContentText("Data anterior a data atual");
                    alert.showAndWait();
                } else {
                    
                    if(sessaoDAOBD.seHaSessao(horario,codigoSala)) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("DATA INVÁLIDA");
                        alert.setContentText("Ja ha um filme inserido nesta mesma sessao e neste mesmo horario.");
                        alert.showAndWait();
                    } else {

                        sessaoDAOBD.cadastrar(new Sessao(horario,sala,filme));
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Sucesso!");
                        alert.setContentText("Sessão cadastrada com sucesso!");
                        alert.showAndWait();
                    }
                }
            } catch (ParseException ex) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("DATA INVÁLIDA");
                    alert.setContentText("Data e hora em formato inválido.");
                    alert.showAndWait();           
            }
        }
   
     
     public void tratarEditarData(String dataHora,int codigoSala,Sala sala,Filme filme) {
         Date horario;
         Date hoje = new Date();
         
         try {
                horario = DateUtil.stringToDateHour(dataHora);
                
                
                if(horario.compareTo(hoje)==-1) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("DATA INVÁLIDA");
                    alert.setContentText("Data anterior a data atual");
                    alert.showAndWait();
                } else {
                    
                    if(sessaoDAOBD.seHaSessao(horario,codigoSala)) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("DATA INVÁLIDA");
                        alert.setContentText("Ja ha um filme inserido nesta mesma sessao e neste mesmo horario.");
                        alert.showAndWait();
                    } else {

                        sessaoDAOBD.alterar(new Sessao(horario,sala,filme));
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Sucesso!");
                        alert.setContentText("Sessão cadastrada com sucesso!");
                        alert.showAndWait();
                    }
                }
            } catch (ParseException ex) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("DATA INVÁLIDA");
                    alert.setContentText("Data e hora em formato inválido.");
                    alert.showAndWait();           
            }
        }
     
     
    @FXML
    public void tratarVoltar(ActionEvent event) throws IOException {
        Stage stage = (Stage) painelSessao.getScene().getWindow();
        stage.setTitle("Cinema");
        Parent painelFilme = FXMLLoader.load(this.getClass().getResource("/view/MainPainel.fxml"));
        stage.setScene(new Scene(painelFilme));

    }
}
