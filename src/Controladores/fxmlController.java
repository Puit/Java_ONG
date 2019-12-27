/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

//import Entidades.Pcontratado;
import P5.utilidad; 
//import java.util.List;
//import javafx.scene.control.TableView;
//import javax.swing.JOptionPane;
//import javax.swing.table.DefaultTableModel;
//import java.sql.Connection;
import java.sql.ResultSet;
//import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
//import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
//import javafx.stage.Stage;
import javafx.util.Callback;

/**
 *
 * @author Cristian
 */

public class fxmlController {
    
    //ObservableList para añadir a la tabla
    private ObservableList<ObservableList> data;

    //Conexión a la DB y ejecución del comando SQL y visualización en la tabla tableview
    public void buildData(TableView tableview, String SQL){
        //Creamos una variable utilidad para tener conexión con la DB
          utilidad u = new utilidad();
          data = FXCollections.observableArrayList();
          try{
            //Nos conectamos la DB
            u.conectar();
            //Ejecutamos el comando SQL y almacenamos el ResultSet en una variable
            ResultSet rs = u.runQuery(SQL);

            //Añadimos las columnas de forma dinamica
            for(int i=0 ; i<rs.getMetaData().getColumnCount(); i++){
                
                final int j = i; 
                //Creamos la columna con el nombre que se ha almacenado en el rs
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){                    
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {                                                                                              
                        return new SimpleStringProperty(param.getValue().get(j).toString());                        
                    }                    
                });
                //Añadimos la columna a la tabla
                tableview.getColumns().addAll(col); 
                System.out.println("Column ["+i+"] ");
            }

            //Añadimos los datos recibidos a la variabld data
            while(rs.next()){
                //Creamos una row (una fila)
                ObservableList<String> row = FXCollections.observableArrayList();
                for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
                    //añadimos dentro de la fila los datos que corresponden a cada columna.
                    row.add(rs.getString(i));
                }
                System.out.println("Row [1] added "+row );
                data.add(row);

            }

            //Añadimos los datos almacenados en data en la tabla
            tableview.setItems(data);
          }catch(Exception e){
              e.printStackTrace();
              System.out.println("Error on Building Data");             
          }
      }

    public String login(String user, String password){
        //Creamos una variable utilidad para tener conexión con la DB
        utilidad u = new utilidad();
        //Creamos una String para almacenar el rol del usuario
        String rol = "nada";
        //Creamos una string donde almacenaremos la Queri
        String SQL = "SELECT rol FROM pcontratado WHERE usuario = '"+user+"' AND password = '"+password+"'";
          data = FXCollections.observableArrayList();
          try{
              //Nos conectamos a la DB
            u.conectar();
            //Creamos una variable ResultSet donde almacenamos la respuesta de la Query "SQL"
            ResultSet rs = u.runQuery(SQL);
            System.out.println(rs.next());
            //Si hay respuesta le añadimos a la variable rol lo que haya encontrado en la columna "rol"
            if(rs.next()){
                rol = rs.getString("rol"); 
                System.out.println(rol);
            }
//
//      
          }catch(Exception e){
              //En caso de error enviamos un mensaje por consola y ponemos "nada" en la variable rol
              e.printStackTrace();
              System.out.println("Error en el login, comprueba que el usuario y la contraseña son correctos.");
              rol = "nada";
          }
          return rol;
    }
}