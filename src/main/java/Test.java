import InputsReaders.InputReview;
import InputsReaders.OutputReview;
import InputsReaders.OutputUsers;
import LoginRegister.LoginRegister;
import utils.Utils;
import Momentan.User;

import static utils.Utils.isNumeric;

public class Test {
    public static void main(String[] args) {

        OutputReview outputReview = new OutputReview("src/main/MyInput/Input.txt");
        outputReview.MyOutput();
    }
}
