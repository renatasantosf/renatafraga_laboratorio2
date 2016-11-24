/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import DAO.impl_bd.VendaDAOBD;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Renata-Samsung
 */
public class RelatorioController implements Initializable {

    
    @FXML
    private AnchorPane painelRelatorio;
    
    @FXML
    private Button btBuscar;
    
    
    @FXML
    private ComboBox<String> cbBusca;
    
    @FXML
    private ListView listViewResultado;
    
    private VendaDAOBD vendaDAOBD;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         vendaDAOBD = new VendaDAOBD();
        
        cbBusca.setEditable(true);
        cbBusca.getItems().addAll("VENDAS POR FILME",
                "VENDAS POR HORÁRIO",
                "VENDAS POR SALAS",
                "VENDAS POR SESSÕES");
    }    
    
  
   /* private void tratarBuscas(ActionEvent event) throws IOException {
        if(cbBusca.getValue().equals("VENDAS POR FILME")) {
            listViewResultado.getItems().addAll(vendaDAOBD.vendasPorFilme());
        } else {
            if(cbBusca.getValue().equals("VENDAS POR HORÁRIO")) {
                listViewResultado.getItems().addAll(vendaDAOBD.vendasPorHorario());
            } else {
                if(cbBusca.getValue().equals("VENDAS POR SALAS")) {
                    listViewResultado.getItems().addAll(vendaDAOBD.vendasPorSalas());
                } else {
                    if(cbBusca.getValue().equals("VENDAS POR SESSÕES")) {
                          listViewResultado.getItems().addAll(vendaDAOBD.vendasPorSessoes());
                    }
                }
            }
        }
    }*/
    
    
    
    
}