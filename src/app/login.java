/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Cristian
 */
public class login extends Application {
    
    //Acción que se llama al inicializar la aplicación, se encarga de abrir la escena inicial
    @Override
    public void start(Stage stage) throws Exception {
        //Cramos una variable de tipo loginPane
        loginPane root = new loginPane();
        
        //Le indicamos a la escena que sea de tipo loginPane
        Scene scene = new Scene(root);
        
        //Cargamos la escena en el stage
        stage.setScene(scene);
        //Mostramos el stage por pantalla
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
