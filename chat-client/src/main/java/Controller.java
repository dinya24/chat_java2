import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    public TextField textField;
    public TextArea TeArea;


    public void Send(ActionEvent actionEvent) {
        String str = textField.getText();
        try{
            out.writeUTF(str);
            textField.clear();
            textField.requestFocus();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try{
            socket = new Socket("localhost",8189);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            new Thread (()->{
                try{
                    while (true){
                        String str = in.readUTF();
                        TeArea.appendText(str + "\n");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }).start();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
