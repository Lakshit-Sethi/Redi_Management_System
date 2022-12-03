import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.Font;
import java.awt.Window;
// import java.awt.*;
import java.awt.event.*;
//import java.time.LocalDate;  
//import java.time.format.DateTimeFormatter;  
public class User extends UserType implements Runnable,ActionListener,ListSelectionListener{
    String name,emailid,password;
    String num,bitsid;
    //ArrayList<ordering> or=new ArrayList<ordering>();
    Thread t;
    private JPanel userLoginpanel;
    private JButton userOrder;
    private JButton userSummary;
    private JButton userLogout;
    private JButton userChangePass;
    private JLabel userLabel;
    private JLabel passLabel;
    private JPasswordField newPass;
    private JPasswordField Pass;
    private JButton userChangePassButton;
    private JButton userbacktowindowButton;
    private JLabel failure;
    private JLabel userTotalExpense;
    private JLabel userMonthlyExpense;
    private JList redilist;
    private JTextArea undeliveredOrders;
    private static Owner o1;
    public void UserWindow() throws FileNotFoundException{
        userLoginpanel = new JPanel();
        setTitle(name);
        setSize(500,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // userLoginpanel.setLayout(new LayoutManager());
        // userLoginpanel.setLayout(new GridLayout(3,1));
        userOrder = new JButton("Order");
        userOrder.setSize(100,25);
        userOrder.addActionListener(this);
        userSummary = new JButton("Summary");
        userSummary.setSize(100,25);
        userSummary.addActionListener(this);
        userLogout = new JButton("Logout");
        userLogout.setSize(100,25);
        userLogout.addActionListener(this);
        userChangePass = new JButton("Change Password");
        userChangePass.setSize(100,25);
        userChangePass.addActionListener(this);
        userLoginpanel.add(userOrder);
        userLoginpanel.add(userSummary);
        
        userLoginpanel.add(userChangePass);
        userLoginpanel.add(userLogout);
        undeliveredOrders = new JTextArea();
        undeliveredOrders.setBounds(50, 50, 200, 300);
        String input2 = new String("Orders not delivered yet: \n Redi:    Dish:    Quantity: \n");
        File Obj = new File(this.name+this.bitsid+".txt");
        Scanner Reader = new Scanner(Obj);
        int flip=0;
        while(Reader.hasNextLine()){
            String input=Reader.nextLine();
            String s[]=input.split(" ");
            if(s[5].equals("N")){
                //Show the details
                String s2[] = input.split(" "); 
                input2+=(s2[0]+" "+s2[1]+" "+s2[4]);
                input2+="\n";
                flip=1;
            }
        }
        if(flip==1) {
            undeliveredOrders.setText(input2);
            undeliveredOrders.setEditable(false);
            userLoginpanel.add(undeliveredOrders);
        }
        
        add(userLoginpanel);
        setVisible(true);
        
    }
    public void UserChangePass(){
        userLoginpanel = new JPanel();
        setTitle("Change Password");
        setSize(500,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        userLoginpanel.setLayout(null);
        userLabel = new JLabel("Enter Old Password");
        userLabel.setBounds(10,20,150,25);
        passLabel = new JLabel("New Password");
        passLabel.setBounds(10,50,150,25);   
        newPass = new JPasswordField();
        newPass.setBounds(180,50,165,25);
        Pass = new JPasswordField();
        Pass.setBounds(180,20,165,25);
        userChangePassButton = new JButton("Change Password");
        userChangePassButton.setBounds(10,80,200,25);
        userChangePassButton.addActionListener(this);
        userbacktowindowButton = new JButton("Back");
        userbacktowindowButton.setBounds(10,110,100,25);
        userbacktowindowButton.addActionListener(this);
        failure = new JLabel("");
        failure.setBounds(130,80,200, 25);
        // userLoginButton.setBounds(10,110,80,25);
        userLoginpanel.add(userLabel);
        userLoginpanel.add(newPass);
        userLoginpanel.add(passLabel);
        userLoginpanel.add(Pass);
        userLoginpanel.add(userbacktowindowButton);
        userLoginpanel.add(userChangePassButton);
        userLoginpanel.add(failure);
        add(userLoginpanel);
        setVisible(true);
    }
    public void userSummary(int x, int y){
        userLoginpanel = new JPanel();
        setTitle("Summary");
        setSize(500,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        userLoginpanel.setLayout(null);
        userLabel = new JLabel("Total expense");
        userLabel.setBounds(10,20,150,25);
        passLabel = new JLabel("Monthly expense");
        passLabel.setBounds(10,50,150,25);   
        userTotalExpense = new JLabel(y+"");
        userTotalExpense.setBounds(180,20,165,25);
        userMonthlyExpense = new JLabel(x+"");
        userMonthlyExpense.setBounds(180,50,165,25);
        userbacktowindowButton = new JButton("Back");
        userbacktowindowButton.setBounds(10,110,100,25);
        userbacktowindowButton.addActionListener(this);
        String input="";
        try {
            input = amountperitem();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        undeliveredOrders = new JTextArea();
        undeliveredOrders.setBounds(10, 80, 500, 300);
        undeliveredOrders.setText(input);
        //increase the size of font
       
        Font font = undeliveredOrders.getFont();
        float size = font.getSize() + 2.0f;
        undeliveredOrders.setFont( font.deriveFont(size) );
        //add scroll option to the text area
        JScrollPane scroll = new JScrollPane (undeliveredOrders);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setBounds(10, 80, 200, 300);
        userLoginpanel.add(scroll);
        undeliveredOrders.setEditable(false);
        failure = new JLabel("");
        failure.setBounds(130,80,200, 25);
        // userLoginButton.setBounds(10,110,80,25);
        userLoginpanel.add(userLabel);
        userLoginpanel.add(userTotalExpense);
        userLoginpanel.add(passLabel);
        userLoginpanel.add(userMonthlyExpense);
        userLoginpanel.add(userbacktowindowButton);
        userLoginpanel.add(failure);
        add(userLoginpanel);
        setVisible(true);
    }
    User(String name,String emailid,String num,String bitsid,String password){
        t=new Thread(this,name+bitsid);
        this.name=name;
        this.emailid=emailid;
        this.num=num;
        this.bitsid=bitsid;
        this.password=password; 
    }

    public void run(){
        try {
            UserWindow();
        } catch (Exception e) {
            // TODO: handle exception
        }
        
    }

    public static String fileToString(String filePath)throws FileNotFoundException{
        String input = null;
        Scanner sc = new Scanner(new File(filePath));
        StringBuffer sb = new StringBuffer();
        while (sc.hasNextLine()) {
           input = sc.nextLine();
           sb.append(input+"\n");
        }
        return sb.toString();
    }

    public void UserFunction(){
        //Give User functionalities
        //Give option to change password,summary
        
    }

    public void changePassword(String password)throws FileNotFoundException{
        this.password=password;
        String result =null;
        String input = null;
        Scanner sc = new Scanner(new File("User.txt"));
        while (sc.hasNextLine()) {
           input = sc.nextLine();
           String str[]=input.split(" ");
           if(str[0].equals(this.name)){
            result+=(this.name+" "+this.emailid+" "+this.num+" "+this.bitsid+" "+this.password+"\n");
           }
           else{
            result+=(input+"\n");
           }
        }
        PrintWriter writer = new PrintWriter(new File("User.txt"));
        writer.append(result);
        writer.flush();
    }
    public void summary(){
        //Give option for total expense,expense of the month,Most eaten item,amount on each item in a redi
    }

    public int totalexpense()throws FileNotFoundException{
        int m=0;
        File Obj = new File(this.name+this.bitsid+".txt");
        Scanner Reader = new Scanner(Obj);
        while(Reader.hasNextLine()){
        String s[]=Reader.nextLine().split(" ");
        m+=Integer.parseInt(s[3]);
        }
        Reader.close();
        //System.out.println("Total expense of the year is:"+m);
        return m;

    }

    public int monthlyexpense()throws FileNotFoundException{
        int m=0;
        String s=java.time.LocalDate.now().toString();
        int current_month=Integer.parseInt(s.substring(s.indexOf('-')+1,s.indexOf('-')+3));
        File Obj = new File(this.name+this.bitsid+".txt");
        Scanner Reader = new Scanner(Obj);
        while(Reader.hasNextLine()){
            String str[]=Reader.nextLine().split(" ");
            if(current_month==Integer.parseInt(str[2])){
                m+=Integer.parseInt(str[3]);
            }
        }
        Reader.close();
        //System.out.println("Total expense of the year is:"+m);
        return m;
    }

    public String amountperitem()throws FileNotFoundException{
        ArrayList<String[]> arr=new ArrayList<String[]>();
        String output="";
        File Obj = new File(this.name+this.bitsid+".txt");
        Scanner Reader = new Scanner(Obj);
        int n=0;
        while(Reader.hasNextLine()){
            String str[]=Reader.nextLine().split(" ");
            n++;
            arr.add(str);
        }
        boolean a[]=new boolean[n];
        for(int i=0;i<n;i++){
            a[i]=false;
        }
        Reader.close();
        for(int i=0;i<n;i++){
            if(a[i]==false){
                a[i]=true;
                String str[]=arr.get(i);
                int m=0;
                int quan=0;
                for(int j=0;j<n;j++){
                    String str2[]=arr.get(j);
                    if(str[0].equals(arr.get(j)[0]) && str[1].equals(arr.get(j)[1])){
                        m+=Integer.parseInt(str2[3]);
                        quan+=Integer.parseInt(str2[4]);
                        a[j]=true;
                    }
                }
                output+=(str[0]+" "+str[1]+" "+m+" "+quan+"\n");
                //Output the str[0] str[1] m and quan;
            }
        }
        return output;
    }

    public void UserOrder()throws FileNotFoundException{
        File Obj = new File("Owner.txt");
        Scanner Reader = new Scanner(Obj);
        String input;
        StringBuffer sb = new StringBuffer();
        while(Reader.hasNextLine()){
            //Print redi name
            //Take input for which redi's menu to show
            input = Reader.nextLine();
            String str[]=input.split(" ");
            sb.append(str[0]+"\n");

        }
        input = sb.toString();

        Reader.close();
        userLoginpanel = new JPanel();
        userLoginpanel.setLayout(null);
        userLabel = new JLabel("Select Redi");
        userLabel.setBounds(10,20,80,25);
        redilist = new JList(input.split("\n"));
        redilist.setBounds(100,20,165,400);
        redilist.addListSelectionListener(this);
        userbacktowindowButton = new JButton("Back");
        userbacktowindowButton.setBounds(400, 10, 80, 25);
        userbacktowindowButton.addActionListener(this);
        userLoginpanel.add(userLabel);
        userLoginpanel.add(redilist);
        userLoginpanel.add(userbacktowindowButton);
        add(userLoginpanel);
        setVisible(true);


        //For selected redi take that redi's object and call menu in owner class
        //Take order request

        //Send order to Order class to place it
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if(e.getSource()==userLogout){
            this.dispose();
        }
        else if(e.getSource()==userChangePass){
            remove(userLoginpanel);
            UserChangePass();
        }
        else if(e.getSource()==userChangePassButton){
            String s = newPass.getText();
            try {
                changePassword(s);
                failure.setText("Password Changed");
            } catch (Exception j) {
                // TODO: handle exception
                System.out.println("Never Gonna Give you up ");
                System.out.println(j);
            }
        }
        else if(e.getSource()==userbacktowindowButton){
            remove(userLoginpanel); 
            try {
                UserWindow();
            } catch (Exception j) {
                // TODO: handle exception
            }
            
        }
        else if(e.getSource()==userSummary){
            remove(userLoginpanel);
            int x=0,y=0;
            try {
                y = totalexpense();
                x = monthlyexpense();
            } catch (Exception j) {
                // TODO: handle exception
            }
            
            userSummary(x,y);
        }
        else if(e.getSource()==userOrder){
            remove(userLoginpanel);
            try {
                UserOrder();
            } 
            catch (Exception j) {
                
            }
    }
    }
    @Override
    public void valueChanged(ListSelectionEvent e){
        // TODO Auto-generated method stub
        String s;
        try{
            if(e.getSource()==redilist){
                s = redilist.getSelectedValue().toString();
                System.out.println(s);                    
                remove(userLoginpanel);
                File Obj = new File("Owner.txt");
                Scanner Reader = new Scanner(Obj);
                String input="";
                StringBuffer sb = new StringBuffer();
                while(Reader.hasNextLine()){
                    //Print redi name
                    //Take input for which redi's menu to show
                    input = Reader.nextLine();
                    String str[]=input.split(" ");
                    if(str[0].equals(s)){
                        break;
                    }

                }
                String str[]=input.split(" ");
                Order o=new Order(str[0],str[1],str[2],this.name+this.bitsid);
                o.t.start();
                o.t.join();
                Reader.close();
                UserOrder();
                //Call menu of the selected redi
            }
        }
        catch(Exception j){
            ;
        }
        
    }
    
}