/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import Controladores.fxmlController;
import DAO.DBContratadoDAO;
import P5.PContratado;
import P5.PContratados;
import P5.Rol;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Cristian
 */
public class AdminViewController implements Initializable {

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

    //TextField donde introduciremos el nombre del empleado
    @FXML
    private TextField name;
    
    //TextField donde introduciremos el apellido del empleado
    @FXML
    private TextField surname;

    //TextField donde introduciremos el DNI del empleado
    @FXML
    private TextField dni;

    //TextField donde introduciremos el usuario del empleado
    @FXML
    private TextField user;

    //PasswordField donde introduciremos la contraseña de dicho usuario
    @FXML
    private PasswordField pass;

    //Boton para añadir el usuario con todos los datos requeridos a la DB 
    @FXML
    private Button addPC;
    //Label que mostrará un error en caso de que ocurra uno
    @FXML
    private Label lbl_Error;

    //Acción que añade un pcontratado a la DB en caso de que todos los datos requeridos sean correctos y muestra un error en caso contrario
    @FXML
    void addPContratados(ActionEvent event) {
        //Comprobamos que no hay parametros vacios
        if (!name.getText().trim().isEmpty() && !surname.getText().trim().isEmpty() && !dni.getText().trim().isEmpty() && !user.getText().trim().isEmpty() && !pass.getText().trim().isEmpty()){
            //Creamos un pcontratado
            PContratado pcontratado1 = new PContratado(name.getText(), surname.getText(), dni.getText(), user.getText(), pass.getText(), Rol.usuario);
            //Creamos una variable pcontratados que sera necesaria para añadir el pcontratado a la DB
            PContratados pcontratados = new PContratados();
            //Añadimos el pcontratado1 a la variable pcontratados
            pcontratados.add(pcontratado1);
        try {
            //Creamos la variable DBContratadosDAO para poder trabajar con la clase PContratados y la base de datos
            DBContratadoDAO DBContratadosDAO = new DBContratadoDAO();
            //Guardamos los contratados almacenados en la variable pcontratados en la base de datos
            DBContratadosDAO.guardar(pcontratados);
            //Vaciamos todos los TextFields y PasswordFields
            name.setText("");
            surname.setText("");
            dni.setText("");
            user.setText("");
            pass.setText("");
            //Mostramos un mensaje para demostrar que se ha añadido correctamente
            lbl_Error.setText("Añadido correctamente");
            } catch (Exception e) {
            System.out.println(e);
        }
        }else{
            //Mostramos un mensaje diciendo que algo ha ido mal
        lbl_Error.setText("Debes escribir en TODOS los campos");
        }
    }
    
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
