import javax.swing.*;
// import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;
public class LoginGui extends JFrame implements ActionListener{
    private static JPanel Typepanel;
    private JPanel userLoginpanel;
    private JPanel userRegPanel;
    private JLabel userLabel;
    private JLabel passLabel;
    private JLabel userReg;
    private JTextField UserName;
    private JPasswordField Pass;
    private JButton userLoginButton;
    private JButton userRegButton;
    private JButton Userlogin;
    private JButton Ownerlogin;
    private JButton userbackButton;
    private JButton userRegisterButton;
    private JLabel userID;
    private JLabel userMail;
    private JLabel userMobNum;
    private JTextField UserIDtext;
    private JTextField UserEIDtext;
    private JTextField UserMobNumText;
    private JButton ownerLoginButton;
    private JButton ownerRegButton;
    private JLabel failure;
    private JLabel Success;
    private JButton backToType;
    private JLabel ownerLabel;
    private JTextField mobNum;
    private JButton ownerRegisterButton;
    private JButton ownerLogButton;
	private JButton userOrder;
    private JButton userSummary;
    private JButton userLogout;
    private JButton userChangePass;
    private static User u1;
    private static Owner o1;
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
     public static int UserLogin(String name,String password)throws FileNotFoundException,InterruptedException{
        File Obj = new File("User.txt");
        Scanner Reader = new Scanner(Obj);
        int x=0;
        while(Reader.hasNextLine()){
            String s[]=Reader.nextLine().split(" ");
            if(s[0].equals(name) && s[4].equals(password)){
                u1=new User(name,s[1],s[2],s[3],password);
                x=1;
                break;
            }
        }
        
        Reader.close();
        if(x==0){
            //Wrong Login Details
            //Back to login button
            return 0;
        }
        else{
            u1.t.start();
            // u1.t.join();
            return 1;
        }
    }
    public static int OwnerLogin(String num,String password)throws FileNotFoundException,InterruptedException{
        File Obj = new File("Owner.txt");
        Scanner Reader = new Scanner(Obj);
        int x=0;
        // Owner o1;
        while(Reader.hasNextLine()){
            String s[]=Reader.nextLine().split(" ");
            if(s[1].equals(num) && s[2].equals(password)){
                o1=new Owner(s[0],num,password);
                x=1;
                break;
            }
        }
        Reader.close();
        if(x==0){
            //Wrong Login Details
            return 0;
        }
        else{
            o1.t.start();
            o1.t.join();
            return 1;
        }
                    
    }
    public static int OwnerRegister(String name,String num,String password)throws FileNotFoundException,IOException,InterruptedException{
        String result = fileToString("Owner.txt");
        result+=(name+" "+num+" "+password);
        File obj=new File(name+num+".txt");
        obj.createNewFile();
        File obj1=new File(name+num+"menu.txt");
        obj1.createNewFile();
        PrintWriter writer = new PrintWriter(new File("Owner.txt"));
        writer.append(result);
        writer.flush();
        writer.close();
        return 1;
        // Owner o1=new Owner(name,num,password);
        // o1.t.start();
        // o1.t.join();
                    
    }
    public static int UserRegister(String name,String emailid,String num,String bitsid,String password)throws FileNotFoundException,IOException,InterruptedException{
        String result = fileToString("User.txt");
        if(emailid.split("@").length==1){
            return 0;
        }
        if((emailid.split("@"))[1].equals("pilani.bits-pilani.ac.in")){
                        //u1=new User(name,emailid,num,bitsid,password);
            result+=(name+" "+emailid+" "+num+" "+bitsid+" "+password);
            File obj=new File(name+bitsid+".txt");
            obj.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter("User.txt"));
            writer.append(result);
            writer.flush();
            writer.close();
            // u1=new User(name,emailid,num,bitsid,password);
            // u1.t.start();
            // u1.t.join();
            return 1;
        }
        else{
            //Output Wrong email adress
            System.out.println("Wrong email adress");
            return 0;
            //terminate
        }
    }
    public void UserLogin(){
        userLoginpanel = new JPanel();
        setTitle("LOGIN");
        setSize(500,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        userLoginpanel.setLayout(null);
        // userLoginpanel.setLayout(new GridLayout(3,1));
        backToType = new JButton();
        backToType.setBounds(0,400,100,50);
        backToType.setText("Back");
        backToType.addActionListener(this);
        userLabel = new JLabel("Username");
        userLabel.setBounds(10,20,80,25);
        passLabel = new JLabel("Password");
        passLabel.setBounds(10,50,80,25);   
        UserName = new JTextField();
        UserName.setBounds(100,20,165,25);
        Pass = new JPasswordField();
        Pass.setBounds(100,50,165,25);
        userLoginButton = new JButton("Login");
        userLoginButton.setBounds(10,80,80,25);
        userLoginButton.addActionListener(this);
        userReg = new JLabel("Not Registered?");
        userReg.setBounds(10,110,200,25);
        userRegButton = new JButton("Register");
        userRegButton.setBounds(10,130,100,25);
        userRegButton.addActionListener(this);
        failure = new JLabel("");
        failure.setBounds(100,80,200, 25);
        Success = new JLabel("");
        Success.setSize(200,25);
        // userLoginButton.setBounds(10,110,80,25);
        userLoginpanel.add(backToType);
        userLoginpanel.add(userLabel);
        userLoginpanel.add(UserName);
        userLoginpanel.add(passLabel);
        userLoginpanel.add(Pass);
        userLoginpanel.add(userLoginButton);
        userLoginpanel.add(failure);
        userLoginpanel.add(userReg);
        userLoginpanel.add(Success);
        userLoginpanel.add(userRegButton);
        add(userLoginpanel);
        setVisible(true);

    }
    public void OwnerLogin(){
        userLoginpanel = new JPanel();
        setTitle("LOGIN");
        setSize(500,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        userLoginpanel.setLayout(null);
        backToType = new JButton();
        backToType.setBounds(0,400,100,50);
        backToType.setText("Back");
        backToType.addActionListener(this);
        // userLoginpanel.setLayout(new GridLayout(3,1));
        userLabel = new JLabel("Mobile Number");
        userLabel.setBounds(10,20,130,25);
        passLabel = new JLabel("Password");
        passLabel.setBounds(10,50,80,25);   
        UserName = new JTextField();
        UserName.setBounds(150,20,165,25);
        Pass = new JPasswordField();
        Pass.setBounds(150,50,165,25);
        ownerLoginButton = new JButton("Login");
        ownerLoginButton.setBounds(10,80,80,25);
        ownerLoginButton.addActionListener(this);
        userReg = new JLabel("Not Registered?");
        userReg.setBounds(10,110,200,25);
        ownerRegButton = new JButton("Register");
        ownerRegButton.setBounds(10,130,100,25);
        ownerRegButton.addActionListener(this);
        failure = new JLabel("");
        failure.setBounds(100,80,200, 25);
        Success = new JLabel("");
        Success.setSize(200,25);
        // userLoginButton.setBounds(10,110,80,25);
        userLoginpanel.add(backToType);
        userLoginpanel.add(userLabel);
        userLoginpanel.add(UserName);
        userLoginpanel.add(passLabel);
        userLoginpanel.add(Pass);
        userLoginpanel.add(ownerLoginButton);
        userLoginpanel.add(failure);
        userLoginpanel.add(userReg);
        userLoginpanel.add(Success);
        userLoginpanel.add(ownerRegButton);
        add(userLoginpanel);
        setVisible(true);

    }
    public void OwnerRegister(){
        userLoginpanel = new JPanel();
        setTitle("Register");
        setSize(500,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        userLoginpanel.setLayout(null);
        // userLoginpanel.setLayout(new GridLayout(3,1));
        ownerLabel = new JLabel("Name");
        ownerLabel.setBounds(10,20,130,25);
        userLabel = new JLabel("Mobile Number");
        userLabel.setBounds(10,80,130,25);
        passLabel = new JLabel("Password");
        passLabel.setBounds(10,50,80,25);   
        UserName = new JTextField();
        UserName.setBounds(150,20,165,25);
        mobNum = new JTextField();
        mobNum.setBounds(150,80,165,25);
        Pass = new JPasswordField();
        Pass.setBounds(150,50,165,25);
        ownerRegisterButton = new JButton("Register");
        ownerRegisterButton.setBounds(10,120,100,25);
        ownerRegisterButton.addActionListener(this);
        userReg = new JLabel("Already Registered?");
        userReg.setBounds(10,150,200,25);
        ownerLogButton = new JButton("Login");
        ownerLogButton.setBounds(10,180,100,25);
        ownerLogButton.addActionListener(this);
        failure = new JLabel("");
        failure.setBounds(120,120,200, 25);
        Success = new JLabel("");
        Success.setSize(200,25);
        // userLoginButton.setBounds(10,110,80,25);
        // userLoginpanel.add(backToType);
        userLoginpanel.add(userLabel);
        userLoginpanel.add(UserName);
        userLoginpanel.add(ownerLabel);
        userLoginpanel.add(mobNum);
        userLoginpanel.add(passLabel);
        userLoginpanel.add(Pass);
        userLoginpanel.add(ownerLogButton);
        userLoginpanel.add(failure);
        userLoginpanel.add(userReg);
        userLoginpanel.add(Success);
        userLoginpanel.add(ownerRegisterButton);
        add(userLoginpanel);
        setVisible(true);

    }
    public void UserRegister(){
        userRegPanel = new JPanel();
        setTitle("REGISTER");
        setSize(500,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        userRegPanel.setLayout(null);
        userLabel = new JLabel("Username");
        userLabel.setBounds(10,20,100,25);
        userID = new JLabel("BITS ID");
        userID.setBounds(10,50,100,25);
        userMail = new JLabel("BITS Email-ID");
        userMail.setBounds(10,80,100,25);
        userMobNum = new JLabel("Mobile Number");
        userMobNum.setBounds(10,110,130,25);
        passLabel = new JLabel("Password");
        passLabel.setBounds(10,140,100,25);   
        UserName = new JTextField();
        UserName.setBounds(150,20,165,25);
        UserIDtext = new JTextField();
        UserIDtext.setBounds(150,50,165,25);
        UserEIDtext = new JTextField();
        UserEIDtext.setBounds(150,80,165,25);
        UserMobNumText = new JTextField();
        UserMobNumText.setBounds(150,110,165,25);
        Pass = new JPasswordField();
        Pass.setBounds(150,140,165,25);
        userRegisterButton = new JButton("Register");
        userRegisterButton.setBounds(10,170,100,25);
        userRegisterButton.addActionListener(this);
        userReg = new JLabel("Already Registered?");
        userReg.setBounds(10,200,200,25);
        userbackButton = new JButton("Login");
        userbackButton.setBounds(10,230,100,25);
        userbackButton.addActionListener(this);
        failure = new JLabel("");
        failure.setBounds(130,170,200, 25);
        // userLoginButton.setBounds(10,110,80,25);
        userRegPanel.add(userLabel);
        userRegPanel.add(UserName);
        userRegPanel.add(userID);
        userRegPanel.add(UserIDtext);
        userRegPanel.add(userMail);
        userRegPanel.add(UserEIDtext);
        userRegPanel.add(userMobNum);
        userRegPanel.add(UserMobNumText);
        userRegPanel.add(UserName);
        userRegPanel.add(passLabel);
        userRegPanel.add(Pass);
        userRegPanel.add(userbackButton);
        userRegPanel.add(userReg);
        userRegPanel.add(userRegisterButton);
        userRegPanel.add(failure);
        add(userRegPanel);
        setVisible(true);

    }
    public void intialize(){
        Typepanel = new JPanel();
        setTitle("Please Select the type of user");
        setSize(500,500);
        // setMinimumSize(new Dimension(300,200));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Typepanel.setLayout(new GridLayout(3,1));

        Userlogin = new JButton("User");
        Userlogin.setSize(100,50);
        Userlogin.addActionListener(this);
        Ownerlogin = new JButton("Owner");
        Ownerlogin.setSize(100,50);
        Ownerlogin.addActionListener(this);
        Typepanel.add(Userlogin);
        Typepanel.add(Ownerlogin);
        add(Typepanel);
        setVisible(true);
    } 
    public void OwnerWindow(){
        userLoginpanel = new JPanel();
        setTitle(u1.name);
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
        userLoginpanel.add(userLogout);
        userLoginpanel.add(userChangePass);
        add(userLoginpanel);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==Userlogin){
            remove(Typepanel);
            UserLogin();
        }
        else if(e.getSource()==userLoginButton){
            String name =  UserName.getText();
            String pass = Pass.getText();
            System.out.print("We're no strangers to love ");
            int i=3;
            try{
                i = UserLogin(name,pass);
            }
            catch(FileNotFoundException j){
                System.out.println("You know the rules and so do I (do I) ");
            }
            catch(InterruptedException j2){
                System.out.println("A full commitment's what I'm thinking of ");
            }
            finally{
                System.out.println("You wouldn't get this from any other guy ");
            }
            if(i==0) failure.setText("Wrong Login info");
            else {
                // remove(userLoginpanel);
                // UserWindow();
            }
        }

        else if(e.getSource()==userRegisterButton){
            String name = UserName.getText();
            String pass = Pass.getText();
            String mNum = UserMobNumText.getText();
            String bitsId = UserIDtext.getText();
            String bitsEid = UserEIDtext.getText();
            int i=0;
            try{
                i = UserRegister(name,bitsEid,mNum,bitsId,pass);
            }
            catch(FileNotFoundException j){
                System.out.println("fuck");
            }
            catch(InterruptedException j2){
                System.out.println("Save me");
            }
            catch(IOException j3){
                System.out.println("Unholy");
            }
            finally{
                System.out.println("shit");
            }
            if(i==0) failure.setText("Wrong Login info");
            else {
                failure.setText("yay");
                remove(userRegPanel);
                UserLogin();
            }
        }
        else if(e.getSource()==Ownerlogin){
            remove(Typepanel);
            OwnerLogin();
        }
        else if(e.getSource()==userRegButton){
            remove(userLoginpanel);
            UserRegister();
        }
        else if(e.getSource()==userbackButton){
            remove(userRegPanel);
            UserLogin();
        }
        else if(e.getSource()==ownerLoginButton){
            String name =  UserName.getText();
            String pass = Pass.getText();
            System.out.print("We're no strangers to love ");
            int i=3;
            try{
                i = OwnerLogin(name,pass);
            }
            catch(FileNotFoundException j){
                System.out.println("You know the rules and so do I (do I) ");
            }
            catch(InterruptedException j2){
                System.out.println("A full commitment's what I'm thinking of ");
            }
            finally{
                System.out.println("You wouldn't get this from any other guy ");
            }
            if(i==0) failure.setText("Wrong Login info");
        }
        else if(e.getSource() == backToType){
            remove(userLoginpanel);
            intialize();
        }
        else if(e.getSource()==ownerRegButton){
            remove(userLoginpanel);
            OwnerRegister();
        }
        else if(e.getSource()==ownerLogButton){
            remove(userLoginpanel);
            OwnerLogin();
        }
        else if(e.getSource()==ownerRegisterButton){
            String name = UserName.getText();
            String pass = Pass.getText();
            String mNum = mobNum.getText();
            int i=0;
            try{
                i = OwnerRegister(name,mNum,pass);
            }
            catch(FileNotFoundException j){
                System.out.println("We're no strangers to love ");
            }
            catch(InterruptedException j2){
                System.out.println("You know the rules and so do I (do I) ");
            }
            catch(IOException j3){
                System.out.println("A full commitment's what I'm thinking of ");
            }
            finally{
                System.out.println("You wouldn't get this from any other guy ");
            }
            if(i==0) failure.setText("Wrong Login info");
            else {
                remove(userLoginpanel);
                OwnerLogin();
            }
        }
    }
    public static void main(String[] args) {
        LoginGui frame = new LoginGui();
        frame.intialize();
    }
}
