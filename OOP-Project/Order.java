import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import javax.swing.*;

import java.awt.Window;
import java.awt.event.*;
public class Order extends JFrame implements Runnable,ActionListener{
    String username;
    Thread t;
    private JComboBox userdish;
    private JTextField dishquantity;
    private JButton placeOrder;
    private JLabel quantityLabel;
    private JLabel dishlabel;
    private JTextField dishname;
    private JTextField dishprice;
    private JLabel priceLabel;
    private JButton addDish;
    private JTextField quantitytextfield;
    private JButton removeDish;
    private JButton backbutton;
    private JFrame frame;
    private JButton admrefreshbutton;
    private JLabel orderLabel;
    private JLabel showingorders;
    private JButton deliverButton;
    String name,num,password;
    Order(String name,String num,String password,String username){
        t=new Thread(this,"New Thread");
        this.name=name;
        this.num=num;
        this.password=password;
        this.username=username;
    }

    public void run(){
        if(username.equals("1")){
            ShowAddMenu();

        }
        else if(username.equals("2")){
            ShowRemoveMenu();
        }
        else{
            ShowMenu();
            
        }
    }
    public void ShowMenu(){
        frame = new JFrame("Menu");
        frame.setSize(500,500);
        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        frame.add(panel);
        panel.setLayout(null);
        JLabel userLabel = new JLabel("Menu");
        userLabel.setBounds(10,20,80,25);
        panel.add(userLabel);
        JTextArea menu = new JTextArea();
        menu.setBounds(10,50,300,300);
        String s="";
        try{
            s = fileToString(this.name+this.num+"menu.txt");
            menu.setText(s);
            System.out.println(s);
        }
        catch(FileNotFoundException e){
            System.out.println("File not found");
        }
        String input = null;
        StringBuffer sb = new StringBuffer();
        try {
            Scanner sc = new Scanner(new File(this.name+this.num+"menu.txt"));
            while (sc.hasNextLine()) {
                input = sc.nextLine();
                sb.append(input+"\n");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } 
        input=sb.toString();
        userdish = new JComboBox(input.split("\n"));
        userdish.setBounds(10,360,250,25);
        menu.setEditable(false);
        quantityLabel = new JLabel("Quantity");
        quantityLabel.setBounds(270,330,80,25);
        panel.add(quantityLabel);
        admrefreshbutton = new JButton("Refresh");
        admrefreshbutton.setBounds(330,100,120,25);
        admrefreshbutton.addActionListener(this);
        panel.add(admrefreshbutton);
        dishquantity = new JTextField();
        dishquantity.setBounds(270,360,165,25);
        placeOrder = new JButton("Place Order");
        placeOrder.setBounds(10,390,425,25);
        placeOrder.addActionListener(this);
        panel.add(dishquantity);
        panel.add(placeOrder);
        panel.add(menu);
        panel.add(userdish);
        frame.setVisible(true);
    }
    public void ShowAddMenu(){
        frame = new JFrame("Menu");
        frame.setSize(500,500);
        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        frame.add(panel);
        panel.setLayout(null);
        JLabel userLabel = new JLabel("Menu");
        userLabel.setBounds(10,20,80,25);
        panel.add(userLabel);
        JTextArea menu = new JTextArea();
        menu.setBounds(10,50,300,300);
        String s="";
        try{
            s = fileToString(this.name+this.num+"menu.txt");
            menu.setText(s);
            System.out.println(s);
        }
        catch(FileNotFoundException e){
            System.out.println("File not found");
        }
        dishlabel = new JLabel("Dish");
        dishlabel.setBounds(10,360,80,25);
        panel.add(dishlabel);
        dishname = new JTextField();
        dishname.setBounds(100,360,100,25);
        dishprice = new JTextField();
        dishprice.setBounds(100,390,100,25);
        priceLabel = new JLabel("Price");
        priceLabel.setBounds(10,390,80,25);
        panel.add(priceLabel);
        addDish = new JButton("Add Dish");
        addDish.setBounds(300,420,150,25);
        addDish.addActionListener(this);
        backbutton = new JButton("Back");
        backbutton.setBounds(330,100,150,25);
        backbutton.addActionListener(this);
        menu.setEditable(false);
        quantityLabel = new JLabel("Quantity");
        quantityLabel.setBounds(10,420,80,25);
        quantitytextfield = new JTextField();
        quantitytextfield.setBounds(100,420,100,25);
        panel.add(quantitytextfield);
        panel.add(dishprice);
        panel.add(dishname);
        panel.add(addDish);
        panel.add(quantityLabel);
        panel.add(menu);
        panel.add(backbutton);
        frame.setVisible(true);

    }
    public void ShowRemoveMenu(){
        frame = new JFrame("Menu");
        frame.setSize(500,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        frame.add(panel);
        panel.setLayout(null);
        JLabel userLabel = new JLabel("Menu");
        userLabel.setBounds(10,20,80,25);
        panel.add(userLabel);
        JTextArea menu = new JTextArea();
        menu.setBounds(10,50,300,300);
        String s="";
        try{
            s = fileToString(this.name+this.num+"menu.txt");
            menu.setText(s);
            System.out.println(s);
        }
        catch(FileNotFoundException e){
            System.out.println("File not found");
        }
        dishlabel = new JLabel("Dish");
        dishlabel.setBounds(10,360,80,25);
        panel.add(dishlabel);
        dishname = new JTextField();
        dishname.setBounds(100,360,100,25);
        removeDish = new JButton("Remove Dish");
        removeDish.setBounds(300,420,150,25);
        removeDish.addActionListener(this);
        backbutton = new JButton("Back");
        backbutton.setBounds(330,100,150,25);
        backbutton.addActionListener(this);
        menu.setEditable(false);
        quantityLabel = new JLabel("Quantity");
        quantityLabel.setBounds(10,420,80,25);
        quantitytextfield = new JTextField();
        quantitytextfield.setBounds(100,420,100,25);
        panel.add(dishname);
        panel.add(removeDish);
        panel.add(menu);
        panel.add(backbutton);
        frame.setVisible(true);
    }
    public void SuccessWindow(){
        JFrame frame = new JFrame("Success");
        frame.setSize(200,300);
        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        frame.add(panel);
        panel.setLayout(null);
        JLabel userLabel = new JLabel("Task Completed");
        userLabel.setBounds(10,20,150,25);
        panel.add(userLabel);
        frame.setVisible(true);
    }
    public void FailureWindow(){
        JFrame frame = new JFrame("Failure");
        frame.setSize(200,300);
        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        frame.add(panel);
        panel.setLayout(null);
        JLabel userLabel = new JLabel("Task Failed");
        userLabel.setBounds(10,20,150,25);
        panel.add(userLabel);
        frame.setVisible(true);
    }
    public static String fileToString(String filePath)throws FileNotFoundException{
        String input = null;
        Scanner sc = new Scanner(new File(filePath));
        StringBuffer sb = new StringBuffer();
        while (sc.hasNextLine()) {
           input = sc.nextLine();
           if(input.equals("")){
            return sb.toString();
           }
           sb.append(input+"\n");
        }
        return sb.toString();
    }

    public static String RemoveMenu(String filePath,String n)throws FileNotFoundException{
        String input=null;
        Scanner sc = new Scanner(new File(filePath));
        StringBuffer sb = new StringBuffer();
        while (sc.hasNextLine()) {
           input = sc.nextLine();
           if(input.split(" ")[0].equals(n)){
            continue;
           }
           sb.append(input+"\n");
        }
        return sb.toString();
    }

    public synchronized void UpdateMenu(String result,String n)throws FileNotFoundException{
        //Give option to add or remove from menu
        //If remove
        //Take as input
        result=RemoveMenu(this.name+this.num+"menu.txt",n);
        PrintWriter writer = new PrintWriter(new File(name+num+"menu.txt"));
        writer.append(result+"\n");
        writer.flush();
        writer.close();
        //If add

    }
    public synchronized int AddMenu(String dish,String rate,String quantity)throws FileNotFoundException{
        String result=fileToString(this.name+this.num+"menu.txt");
        result+=(dish+" "+rate+" "+quantity+"\n");
        PrintWriter writer = new PrintWriter(new File(this.name+this.num+"menu.txt"));
        writer.append(result+"\n");
        writer.flush();
        writer.close();
        return 1;
    }

    public synchronized int order(String dish,String qt)throws FileNotFoundException{
        //Take input of order and quantity
        int quantity = Integer.parseInt(qt);
        String s2[]=dish.split(" ");
        dish=s2[0];
        System.out.println("aaya");
        String input=null;
        Scanner sc = new Scanner(new File(this.name+this.num+"menu.txt"));
        String r="";
        //System.out.println(dish);
        while (sc.hasNextLine()) {
            String x=java.time.LocalDate.now().toString();
            int current_month=Integer.parseInt(x.substring(x.indexOf('-')+1,x.indexOf('-')+3));
            input = sc.nextLine();
            String s[]=input.split(" ");
            //System.out.println(s[0]);
            if(s[0].equals(dish)){
                if(!s[2].equals("-1") && Integer.parseInt(s[2])<quantity){
                    //Nhi ho payega
                    System.out.println("Not enough quantity");
                    return 0;
                }
                else{
                    int expense=quantity*Integer.parseInt(s[1]);
                    System.out.println("aaya2");
                    String result = fileToString(this.name+this.num+".txt");
                    result+=(this.username+" "+dish+" "+current_month+" "+expense+" "+quantity+" "+"N"+"\n");
                    PrintWriter writer = new PrintWriter(new File(this.name+this.num+".txt"));
                    writer.append(result);
                    writer.flush();
                    String result2 = fileToString(this.username+".txt");
                    result2+=(this.name+" "+dish+" "+current_month+" "+expense+" "+quantity+" "+"N"+"\n");
                    PrintWriter writer2 = new PrintWriter(new File(this.username+".txt"));
                    writer2.append(result2);
                    writer2.flush();
                    if(!s[2].equals("-1")){
                        r+=(s[0]+" "+s[1]+" "+(Integer.parseInt(s[2])-quantity)+"\n");
                        continue;

                    }
                }
            }
            r+=(input+"\n");
        }
        PrintWriter writer = new PrintWriter(new File(this.name+this.num+"menu.txt"));
        writer.append(r);
        writer.flush();
        writer.close();
        return 1;
    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==placeOrder){
            String dish = (String)userdish.getSelectedItem();
            String qt = dishquantity.getText();
            int i=0;
            try{
                i = order(dish,qt);
            }
            catch(FileNotFoundException f){
                System.out.println("File not found");
            }
            if(i==1){
                SuccessWindow();
            }
            else{
                FailureWindow();
            }
            frame.dispose();
        }
        else if(e.getSource()==addDish){
            String dish = dishname.getText();
            String rate = dishprice.getText();
            String quantity = quantitytextfield.getText();
            int i=0;
            try{
                i = AddMenu(dish,rate,quantity);
            }
            catch(FileNotFoundException f){
                System.out.println("File not found");
            }
            frame.dispose();
            ShowAddMenu();
        }
        else if(e.getSource()==removeDish){
            String dish = dishname.getText();
            String result="";
            try{
                UpdateMenu(result,dish);
            }
            catch(FileNotFoundException f){
                System.out.println("File not found");
            }
            frame.dispose();
            ShowRemoveMenu();
        }
        else if(e.getSource()==backbutton){

            t.stop();
            frame.dispose();
            
        }
        else if(e.getSource()==admrefreshbutton){
            frame.dispose();
            ShowMenu();
        }
        
    }
}
