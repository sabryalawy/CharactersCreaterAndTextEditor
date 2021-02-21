/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testingfxmlchildrien;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

/**
 *
 * @author Alawi
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private TextField fontName;

    @FXML
    private RadioButton lower;

    @FXML
    private RadioButton upper;

    int size = 25;
    String hex = "";

    int[][] lettrBase10 = new int[16][16];

    @FXML
    private ChoiceBox<String> choseBox;

    @FXML
    private Canvas canv;

    @FXML
    void clearOnAction(ActionEvent event) {
        GraphicsContext gr = canv.getGraphicsContext2D();
        gr.clearRect(0, 0, 400, 400);
        hex = "";
        lettrBase10 = new int[16][16];
    }

    @FXML
    void saveOnAct(ActionEvent event) throws IOException {
        for (int i = 0; i < 16; i++) {

            hex += BinaryToHex(lettrBase10[0][i], lettrBase10[1][i], lettrBase10[2][i], lettrBase10[3][i]);
            hex += BinaryToHex(lettrBase10[4][i], lettrBase10[5][i], lettrBase10[6][i], lettrBase10[7][i]);
            hex += BinaryToHex(lettrBase10[8][i], lettrBase10[9][i], lettrBase10[10][i], lettrBase10[11][i]);
            hex += BinaryToHex(lettrBase10[12][i], lettrBase10[13][i], lettrBase10[14][i], lettrBase10[15][i]);
        }
        if (lower.isSelected()) {

            TestingFXMLChildrien.writeInFile((System.getProperty("user.dir") + "//Fonts//" + fontName.getText() + ".font"), choseBox.getValue() + "=" + hex);
        } else {
            TestingFXMLChildrien.writeInFile((System.getProperty("user.dir") + "//Fonts//" + fontName.getText() + ".font"), choseBox.getValue().toUpperCase() + "=" + hex);

        }

        GraphicsContext gr = canv.getGraphicsContext2D();
        gr.clearRect(0, 0, 400, 400);
        hex = "";
        lettrBase10 = new int[16][16];
    }

    @FXML
    void cancelOnAct(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void goToTextEidtorOnAct(ActionEvent event) throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource("TextEditor.fxml"));

        Scene s = new Scene(p);
        
        Stage s1 = new Stage();
        s1.setTitle("TextMa3inEditer");
        s1.setScene(s);
        s1.show();
        canv.getScene().getWindow().hide();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        FillChoiseBox(choseBox);

        canv.setOnMouseClicked(e -> {
            GraphicsContext g = canv.getGraphicsContext2D();

            g.setFill(Color.BLUE);
            double x = e.getX();
            double y = e.getY();
//            System.out.println("testingfxmlchildrien.FXMLDocumentController.initialize()" + "=> X = " + x + " ,Y = " + y);

            g.fillRect(x - (x % 25), y - (y % 25), size, size);
            lettrBase10[(int) (x - (x % 25)) / 25][(int) (y - (y % 25)) / 25] = 1;

        });

        canv.setOnMouseDragged(e -> {
            GraphicsContext g = canv.getGraphicsContext2D();

            g.setFill(Color.BLUE);
            double x = e.getX();
            double y = e.getY();
//            System.out.println("testingfxmlchildrien.FXMLDocumentController.initialize()" + "=> X = " + x + " ,Y = " + y);

            g.fillRect(x - (x % 25), y - (y % 25), size, size);
            lettrBase10[(int) (x - (x % 25)) / 25][(int) (y - (y % 25)) / 25] = 1;

        });

    }

    private void FillChoiseBox(ChoiceBox t) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        t.getItems().add("a");
        t.getItems().add("b");
        t.getItems().add("c");
        t.getItems().add("d");
        t.getItems().add("e");
        t.getItems().add("f");
        t.getItems().add("g");
        t.getItems().add("h");
        t.getItems().add("i");
        t.getItems().add("g");
        t.getItems().add("k");
        t.getItems().add("l");
        t.getItems().add("m");
        t.getItems().add("n");
        t.getItems().add("o");
        t.getItems().add("p");
        t.getItems().add("q");
        t.getItems().add("r");
        t.getItems().add("s");
        t.getItems().add("t");
        t.getItems().add("u");
        t.getItems().add("v");
        t.getItems().add("w");
        t.getItems().add("x");
        t.getItems().add("y");
        t.getItems().add("z");
    }

    String BinaryToHex(int a, int b, int c, int d) {
        String binaryStr = "" + a;
        binaryStr += b;
        binaryStr += c;
        binaryStr += d;

        binaryStr.trim();

        int decimal = Integer.parseInt(binaryStr, 2);
        String hexStr = Integer.toString(decimal, 16);
        return hexStr;
    }

}
