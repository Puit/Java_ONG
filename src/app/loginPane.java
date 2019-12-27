/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import Controladores.fxmlController;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Cristian
 */
public class loginPane extends AnchorPane {
    //constructor, crea y muestra la ventana de Login
    public loginPane() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("loginPane.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
    
    @FXML
    private void initialize() {
    }
    
    //Variable que almacena el AnchorPane de la escena
    @FXML
    private AnchorPane anchorpane;

    //Label con el titulo (No la modificaremos)
    @FXML
    private Label lbl_Titulo;

    //Variable que almacena el TextField con el usuario añadido para acceder
    @FXML
    private TextField txtF_Usuario;

    //Variable que almacena el PasswordField con la contraseña añadida para acceder
    @FXML
    private PasswordField psF_Password;

    //Label que aparece si ha habido algún error al acceder
    @FXML
    private Label lbl_Error;

    //Boton para acceder
    @FXML
    private Button btn_acceder;

    //Acción que llama el botón para comprobar si el nombre de usuario y la contraseña son correctos, en caso afirmativo dependiendo del rol del usuario abrirá una ventana u otra.
    @FXML
    void Acceder(ActionEvent event) throws IOException {
        //Creamos una variable fxmllController que utilizaremos para hacer el login
        fxmlController controller  = new fxmlController(); 
        //Como la base de datos nos devolverá una String con el rol del usuario o una String vacía en caso de no existir la creamos
        //Y le ponemos de nombre rol
        //rol valdrá lo que nos devuelva la BD cuando llamamos a la funcion login mediante la variable controller
        String rol = controller.login(txtF_Usuario.getText(), psF_Password.getText());
        //Comprovamos que hay dentro de rol
        switch (rol) {
            case "usuario":
                //Damos un mensaje de bienvenida.
                lbl_Error.setText("Bienvenido: " +txtF_Usuario.getText());
                //En caso de que el rol sea de tipo usuario cargamos la vista de usuario;
                loadView(event, "userView.fxml");
                break;
            case "admin":
                //Damos un mensaje de bienvenida.
                lbl_Error.setText("Bienvenido: " +txtF_Usuario.getText());
                //En caso de que el rol sea de tipo admin cargamos la vista de admin;
                loadView(event, "adminView.fxml");
                break;
            default:
                //En caso de que no sea usuario ni admin consideraremos que es un error y mostraremos un mensaje en el label lbl_Error
                //En el que indica que las credenciales son incorrectas.
                lbl_Error.setText("Credenciales incorrectas");
                break;
        }
    }
    
    //Carga la vista con el nombre que introduzcamos en sceneName
    private void loadView(ActionEvent event, String sceneName) throws IOException {
        //Creamos una variable parent
        Parent view_parent = FXMLLoader.load(getClass().getResource(sceneName));
        //Creamos la escena 
        Scene view_scene = new Scene(view_parent);
        //Creamos el stage (La ventana)
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        //Introducimos la escena en el stage
        app_stage.setScene(view_scene);
        //Mostramos el stage
        app_stage.show();
    }
}
