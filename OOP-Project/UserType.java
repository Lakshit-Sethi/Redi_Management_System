import java.io.*;
//import java.util.Scanner;
import javax.swing.*;
abstract class UserType extends JFrame{
    String name;
    String num;
    String password;

    abstract void changePassword(String password)throws FileNotFoundException;
    abstract void Summary(int x,int y);

}