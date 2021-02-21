/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testingfxmlchildrien;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Alawi
 */
public class TestingFXMLChildrien extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));

        Scene scene = new Scene(root);
        stage.setTitle("Writing Alpha");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public static void writeInFile(String fileName, String value) throws IOException {
        PrintWriter p = new PrintWriter(new FileWriter(fileName, true));//PrintWriter out = new PrintWriter(new FileWriter("student.txt", true))
        p.println(value);
        p.flush();
        p.close();
        System.out.println("done");
    }


    public static HashMap<String,String> ReadFile(String filename) throws FileNotFoundException {
        
        HashMap<String,String> d=new HashMap<>();
        File file = new File(System.getProperty("user.dir")+"//Fonts//"+filename);//puting the file path and name
        Scanner s = new Scanner(file);
        String g = "";
        while(s.hasNext()){
            g=s.nextLine();
            String[] temp=g.split("=");  
            d.put(temp[0], temp[1]);
        }
        System.out.println(d.get("a"));
        return d;
    }
}
