import Files.Data;
import Frames.SignInFrame;
import javax.swing.*;
import java.io.*;

public class Main {
    Data data;

    public Main() {
        File file = new File("data.txt");
        try {
            if (file.exists()) {
                ObjectInputStream applicationDataGetter = new ObjectInputStream(new FileInputStream(file));
                data = (Data) applicationDataGetter.readObject();
            } else {
                file.createNewFile();
                data = new Data();
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "There is an IOEXCEPTION \n" + ex.getMessage());
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "There is a CLASS NOT FOUND EXCEPTION \n" + e.getMessage());
        }
        new SignInFrame(data);

    }

    public static void main(String args[]) {
            new Main();

    }
}









