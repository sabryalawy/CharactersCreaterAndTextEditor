/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testingfxmlchildrien;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import static testingfxmlchildrien.TestingFXMLChildrien.ReadFile;

/**
 * FXML Controller class
 *
 * @author Alawi
 */
public class TextEditorController implements Initializable {

    HashMap<String, String> alphabit;

    static int offsetx = 0;
    static int offsety = 0;
    static int offsetCounter = 0;
    static int offsetCounterv = 0;
    @FXML
    private Canvas id;

    @FXML
    private CheckBox Ulne1;

    @FXML
    private ColorPicker colorbi;

    @FXML
    private AnchorPane ank;

    @FXML
    private ChoiceBox<String> Box;
    @FXML
    private CheckBox Ulne;

    @FXML
    void clic(KeyEvent event) {
        try {
            if (event.getCode() == KeyCode.SPACE) {
                System.out.println("tes");
            }

        } catch (Exception e) {
            System.out.println("updated");

        }
    }

    @FXML
    void SaveAct(ActionEvent event) {

    }

    @FXML
    void OpenAct(ActionEvent event) {

    }

    @FXML
    void CloseAct(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void ColorAct(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        getfiles();
        offsetx = 50;
        offsety = 100;
        ank.setOnKeyTyped(e -> {
            if (Box.getValue() == null) {
                try {
                    alphabit = ReadFile("casetest.font");
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(TextEditorController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    alphabit = ReadFile(Box.getValue());
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(TextEditorController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (e.getCharacter().equals("1")) {
                offsetCounter++;
            } else if (e.getCharacter().equals("2")) {
                System.out.println("testingfxmlchildrien.TextEditorController.initialize()");
                offsetCounter--;
                Canvas temp = new Canvas();
                temp.setLayoutX(offsetx + (16 * offsetCounter));
                temp.setLayoutY(offsety + (16 * offsetCounterv));
                temp.setHeight(16);
                temp.setWidth(16);
                ank.getChildren().add(temp);
                GraphicsContext g = temp.getGraphicsContext2D();
                g.setFill(Color.BLACK);
                int[][] binary1 = new int[16][16];
                g.setFill(Color.WHITE);
                g.fillRect(0, 0, 200, 200);
            } else if (e.getCharacter().equals("3")) {
                offsetCounter = 0;
                offsetCounterv++;
            } else {
                System.out.println(e.getCharacter());
                int[][] binary = hexToBinaryArray(alphabit.get(e.getCharacter()));
                Canvas temp = new Canvas();
                temp.setLayoutX(offsetx + (16 * offsetCounter));
                offsetCounter++;
                temp.setLayoutY(offsety + (16 * offsetCounterv));
                if (ank.getWidth() < offsetx) {
                    offsetCounterv++;
                }
                temp.setHeight(16);
                temp.setWidth(16);
                ank.getChildren().add(temp);
                GraphicsContext g = temp.getGraphicsContext2D();
                g.setFill(Color.BLACK);
                fillCanvis(binary, g, colorbi.getValue());
//                g.fillRect(0, 0, 400, 400);
            }
        });

    }

    static String hexToBinary(String hex) {

        // variable to store the converted 
        // Binary Sequence 
        String binary = "";

        // converting the accepted Hexadecimal 
        // string to upper case 
        hex = hex.toUpperCase();

        // initializing the HashMap class 
        HashMap<Character, String> hashMap
                = new HashMap<Character, String>();

        // storing the key value pairs 
        hashMap.put('0', "0000");
        hashMap.put('1', "0001");
        hashMap.put('2', "0010");
        hashMap.put('3', "0011");
        hashMap.put('4', "0100");
        hashMap.put('5', "0101");
        hashMap.put('6', "0110");
        hashMap.put('7', "0111");
        hashMap.put('8', "1000");
        hashMap.put('9', "1001");
        hashMap.put('A', "1010");
        hashMap.put('B', "1011");
        hashMap.put('C', "1100");
        hashMap.put('D', "1101");
        hashMap.put('E', "1110");
        hashMap.put('F', "1111");

        int i;
        char ch;

        // loop to iterate through the length 
        // of the Hexadecimal String 
        for (i = 0; i < hex.length(); i++) {
            // extracting each character 
            ch = hex.charAt(i);

            // checking if the character is 
            // present in the keys 
            if (hashMap.containsKey(ch)) // adding to the Binary Sequence 
            // the corresponding value of 
            // the key 
            {
                binary += hashMap.get(ch);
            } // returning Invalid Hexadecimal 
            // String if the character is 
            // not present in the keys 
            else {
                binary = "Invalid Hexadecimal String";
                return binary;
            }
        }

        // returning the converted Binary 
        return binary;
    }

    public static int[][] hexToBinaryArray(String hex) {
        int[][] rez = new int[16][16];
        String binary = hexToBinary(hex);
//        System.out.println(hex);
        char[] temp = binary.toCharArray();
        int counter = 0;
//        System.out.println(temp.length);
        for (int j = 0; j < 16; j++) {
            for (int i = 0; i < 16; i++) {
                rez[i][j] = Character.getNumericValue(temp[counter]);
                counter++;
            }
        }
//        for (int j = 0; j < 16; j++) {
//            for (int i = 0; i < 16; i++) {
//                System.out.println(rez[i][j]);
//            }
//        }
        return rez;
    }

    private void fillCanvis(int[][] binary, GraphicsContext g, Color c) {
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                if (binary[i][j] == 1) {
                    if (Ulne1.isSelected()) {
                        g.setFill(c);
                        g.fillRect(i, j, 2, 2);
                    } else {
                        g.setFill(c);
                        g.fillRect(i, j, 1, 1);
                    }
                }
            }
        }
    }

    void getfiles() {
        File folder = new File(System.getProperty("user.dir") + "//Fonts");
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                Box.getItems().add(listOfFiles[i].getName());
            } else if (listOfFiles[i].isDirectory()) {
                Box.getItems().add(listOfFiles[i].getName());
            }
        }
    }
}
