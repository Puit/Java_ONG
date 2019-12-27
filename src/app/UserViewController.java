/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import Controladores.fxmlController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Cristian
 */
public class UserViewController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    //tabla donde introduciremos los datos leidos en la DB
    @FXML
    private TableView<?> table;

    //AnchorPane de la vista
    @FXML
    private AnchorPane anchorPane;

    //Boton para mostrar los PContratados 
    @FXML
    private Button showPC;

    //Acción que mostrará todos los pcontratados de la db en ese momento
    @FXML
    void showPContratados(ActionEvent event) {
        //Creamos una variable fxmlController para comunicarnos con la DB
        fxmlController controller  = new fxmlController();
        //Limpiamos la tabla y borramos todas sus columnas
        table.getItems().clear();
        table.getColumns().clear();
        //Añadimos las columnas necesarias y los datos que recibimos de la DB en la tabla
        controller.buildData(table, "SELECT idPContratado, usuario, rol from pcontratado");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
