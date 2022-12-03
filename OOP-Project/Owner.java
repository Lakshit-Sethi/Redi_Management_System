import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import javax.swing.*;

import java.awt.Font;
import java.awt.event.*;

public class Owner extends UserType implements Runnable, ActionListener {
    String name;
    String num;
    String password;
    ArrayList<ordering> orders = new ArrayList<ordering>();
    Thread t;
    private JPanel ownerLoginpanel;
    private JButton ownerOrder;
    private JButton ownerSummary;
    private JButton ownerLogout;
    private JButton ownerChangePass;
    private JLabel ownerLabel;
    private JLabel passLabel;
    private JPasswordField newPass;
    private JPasswordField Pass;
    private JButton ownerChangePassButton;
    private JButton ownerbacktowindowButton;
    private JLabel failure;
    private JLabel ownerTotalExpense;
    private JLabel ownerMonthlyExpense;
    private JButton ownerAdd;
    private JButton ownerDelete;
    private JButton ownerUpdate;
    private Order o;
    private JLabel orderLabel;
    private JLabel showingorders;
    private JButton deliverButton;
    private JButton orderDoneButton;
    private JTextArea orderSummary;
    private JLabel Mostordereditem;

    Owner(String name, String num, String password) {
        t = new Thread(this, name + num);
        this.name = name;
        this.num = num;
        this.password = password;
    }

    public static String fileToString(String filePath) throws FileNotFoundException {
        String input = null;
        Scanner sc = new Scanner(new File(filePath));
        StringBuffer sb = new StringBuffer();
        while (sc.hasNextLine()) {
            input = sc.nextLine();
            sb.append(input + "\n");
        }
        return sb.toString();
    }

    public void OwnerWindow() {
        ownerLoginpanel = new JPanel();
        setTitle(name);
        setSize(500, 500);
        // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // ownerLoginpanel.setLayout(new LayoutManager());
        // ownerLoginpanel.setLayout(new GridLayout(3,1));
        ownerOrder = new JButton("Manage Redi");
        ownerOrder.setSize(100, 25);
        ownerOrder.addActionListener(this);
        ownerSummary = new JButton("Summary");
        ownerSummary.setSize(100, 25);
        ownerSummary.addActionListener(this);
        ownerLogout = new JButton("Logout");
        ownerLogout.setSize(100, 25);
        ownerLogout.addActionListener(this);
        ownerChangePass = new JButton("Change Password");
        ownerChangePass.setSize(100, 25);
        ownerChangePass.addActionListener(this);

        ownerLoginpanel.add(ownerOrder);
        ownerLoginpanel.add(ownerSummary);
        ownerLoginpanel.add(ownerLogout);
        ownerLoginpanel.add(ownerChangePass);
        add(ownerLoginpanel);
        setVisible(true);
    }

    public void Summary(int x, int y) {
        ownerLoginpanel = new JPanel();
        setTitle("Summary");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ownerLoginpanel.setLayout(null);
        ownerLabel = new JLabel("Total expense");
        ownerLabel.setBounds(10, 20, 150, 25);
        passLabel = new JLabel("Monthly expense");
        passLabel.setBounds(10, 50, 150, 25);
        ownerTotalExpense = new JLabel(y + "");
        ownerTotalExpense.setBounds(180, 20, 165, 25);
        ownerMonthlyExpense = new JLabel(x + "");
        ownerMonthlyExpense.setBounds(180, 50, 165, 25);
        ownerbacktowindowButton = new JButton("Back");
        ownerbacktowindowButton.setBounds(380,10,100,25);
        ownerbacktowindowButton.addActionListener(this);
        failure = new JLabel("");
        failure.setBounds(130, 80, 200, 25);
        // ownerLoginButton.setBounds(10,110,80,25);
        String input[] = new String[2];
        try {
            input = amountperitem();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // Show the most ordered item presen in s[1];
        Mostordereditem = new JLabel();
        Mostordereditem.setBounds(10, 80, 400, 25);
        Mostordereditem.setText("Most ordered item is " + input[1]);
        System.out.println(input[1]);
        ownerLoginpanel.add(Mostordereditem);
        orderSummary = new JTextArea();
        orderSummary.setBounds(10, 110, 500, 300);
        orderSummary.setText(input[0]);
        //increase the size of font
       
        Font font = orderSummary.getFont();
        float size = font.getSize() + 2.0f;
        orderSummary.setFont( font.deriveFont(size) );
        //add scroll option to the text area
        JScrollPane scroll = new JScrollPane (orderSummary);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setBounds(10, 110, 400, 300);
        ownerLoginpanel.add(scroll);
        orderSummary.setEditable(false);
        ownerLoginpanel.add(ownerLabel);
        ownerLoginpanel.add(ownerTotalExpense);
        ownerLoginpanel.add(passLabel);
        ownerLoginpanel.add(ownerMonthlyExpense);
        ownerLoginpanel.add(ownerbacktowindowButton);
        ownerLoginpanel.add(failure);
        add(ownerLoginpanel);
        setVisible(true);
    }

    public void run() {
        OwnerWindow();
    }

    public void changePassword(String password) throws FileNotFoundException {
        String result = fileToString("Owner.txt");
        result.replace(this.name + " " + " " + this.num + " " + this.password,
                this.name + " " + this.num + " " + password);
        this.password = password;
        PrintWriter writer = new PrintWriter(new File("Owner.txt"));
        writer.append(result + "\n");
        writer.flush();
    }

    public void refreshorder() throws FileNotFoundException {
        ownerLoginpanel = new JPanel();
        ownerLoginpanel.setLayout(null);
        setTitle("Your Orders");
        setSize(500, 200);
        orderLabel = new JLabel("Orders");
        orderLabel.setBounds(10, 20, 80, 25);
        showingorders = new JLabel();
        showingorders.setBounds(10, 50, 400, 25);
        deliverButton = new JButton("Deliver");
        deliverButton.setBounds(10, 80, 150, 25);
        deliverButton.addActionListener(this);
        ownerLoginpanel.add(showingorders);
        ownerLoginpanel.add(orderLabel);
        
        orderDoneButton = new JButton("Back");
        orderDoneButton.setBounds(10, 80, 150, 25);
        orderDoneButton.addActionListener(this);
        
        // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       int flip=0;
        File Obj = new File(this.name + this.num + ".txt");
        Scanner Reader = new Scanner(Obj);
        while (Reader.hasNextLine()) {
            String input = Reader.nextLine();
            String s[] = input.split(" ");
            if (s[5].equals("N")) {
                showingorders.setText(s[0]+" "+s[1]+" "+s[4]);
                // if redi owners says that he has delivered
                flip=1;
                break;
            }
        }
        Reader.close();
        if(flip==0) {
            showingorders.setText("No More orders");
            ownerLoginpanel.add(orderDoneButton);
        }
        else {
            ownerLoginpanel.add(deliverButton);
        }
        add(ownerLoginpanel);
        setVisible(true);
    }

    public void menu() throws FileNotFoundException {
        File Obj = new File(this.name + this.num + "menu.txt");
        Scanner Reader = new Scanner(Obj);
        while (Reader.hasNextLine()) {
            // String s[] = Reader.nextLine().split(" ");
            // Print s[0],s[1],s[2](If it isnt -1 exists)
        }
        Reader.close();
        // Shows the menu
    }

    public int totalexpense() throws FileNotFoundException {
        int m = 0;
        File Obj = new File(this.name + this.num + ".txt");
        Scanner Reader = new Scanner(Obj);
        while (Reader.hasNextLine()) {
            ;
            String s[] = Reader.nextLine().split(" ");
            m += Integer.parseInt(s[3]);
        }
        Reader.close();
        // Print m out
        return m;
    }

    public int monthlyexpense() throws FileNotFoundException {
        int m = 0;
        String s = java.time.LocalDate.now().toString();
        int current_month = Integer.parseInt(s.substring(s.indexOf('-') + 1, s.indexOf('-') + 3));
        File Obj = new File(this.name + this.num + ".txt");
        Scanner Reader = new Scanner(Obj);
        while (Reader.hasNextLine()) {
            String str[] = Reader.nextLine().split(" ");
            if (current_month == Integer.parseInt(str[2])) {
                m += Integer.parseInt(str[3]);
            }
        }
        Reader.close();
        // System.out.println("Monthly expense of the year is:"+m);
        return m;
    }

    public void ManageRediWindow() {
        ownerLoginpanel = new JPanel();
        setTitle("Manage Redi");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // ownerLoginpanel.setLayout(new LayoutManager());
        // ownerLoginpanel.setLayout(new GridLayout(3,1));
        ownerAdd = new JButton("Add To Menu");
        ownerAdd.setSize(100, 25);
        ownerAdd.addActionListener(this);
        ownerDelete = new JButton("Remove From Menu");
        ownerDelete.setSize(100, 25);
        ownerDelete.addActionListener(this);
        ownerUpdate = new JButton("Take Orders");
        ownerUpdate.setSize(100, 25);
        ownerUpdate.addActionListener(this);
        ownerbacktowindowButton = new JButton("Back");
        ownerbacktowindowButton.setSize(100, 25);
        ownerbacktowindowButton.addActionListener(this);
        ownerLoginpanel.add(ownerAdd);
        ownerLoginpanel.add(ownerDelete);
        ownerLoginpanel.add(ownerUpdate);
        ownerLoginpanel.add(ownerbacktowindowButton);
        add(ownerLoginpanel);
        setVisible(true);
    }

    public void ownerChangePass() {
        ownerLoginpanel = new JPanel();
        setTitle("Change Password");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ownerLoginpanel.setLayout(null);
        ownerLabel = new JLabel("Enter Old Password");
        ownerLabel.setBounds(10, 20, 150, 25);
        passLabel = new JLabel("New Password");
        passLabel.setBounds(10, 50, 150, 25);
        newPass = new JPasswordField();
        newPass.setBounds(180, 50, 165, 25);
        Pass = new JPasswordField();
        Pass.setBounds(180, 20, 165, 25);
        ownerChangePassButton = new JButton("Change Password");
        ownerChangePassButton.setBounds(10, 80, 200, 25);
        ownerChangePassButton.addActionListener(this);
        ownerbacktowindowButton = new JButton("Back");
        ownerbacktowindowButton.setBounds(10, 110, 100, 25);
        ownerbacktowindowButton.addActionListener(this);
        failure = new JLabel("");
        failure.setBounds(130, 80, 200, 25);
        // ownerLoginButton.setBounds(10,110,80,25);
        ownerLoginpanel.add(ownerLabel);
        ownerLoginpanel.add(newPass);
        ownerLoginpanel.add(passLabel);
        ownerLoginpanel.add(Pass);
        ownerLoginpanel.add(ownerbacktowindowButton);
        ownerLoginpanel.add(ownerChangePassButton);
        ownerLoginpanel.add(failure);
        add(ownerLoginpanel);
        setVisible(true);
    }
    public String[] amountperitem()throws FileNotFoundException{
        ArrayList<String[]> arr=new ArrayList<String[]>();
        String output="";
        File Obj = new File(this.name+this.num+".txt");
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
        String answer="";
        int x=0;
        for(int i=0;i<n;i++){
            if(a[i]==false){
                a[i]=true;
                String str[]=arr.get(i);
                int m=0;
                int quan=0;
                for(int j=0;j<n;j++){
                    String str2[]=arr.get(j);
                    if(str[1].equals(arr.get(j)[1])){
                        m+=Integer.parseInt(str2[3]);
                        quan+=Integer.parseInt(str2[4]);
                        a[j]=true;
                    }
                }
                output+=(str[1]+" "+m+" "+quan+"\n");
                if(quan>x){
                    x=quan;
                    answer=str[1];
                }
                //Output the str[0] str[1] m and quan;
            }
        }
        String ans[]=new String[2];
        ans[0]=output;
        ans[1]=answer;
        return ans;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if (e.getSource() == ownerLogout) {
            this.dispose();
        } else if (e.getSource() == ownerChangePass) {
            remove(ownerLoginpanel);
            ownerChangePass();
        } else if (e.getSource() == ownerChangePassButton) {
            String s = newPass.getText();
            try {
                changePassword(s);
                failure.setText("Password Changed");
            } catch (Exception j) {
                // TODO: handle exception
                System.out.println("Error");
                System.out.println(j);
            }
        } else if (e.getSource() == ownerbacktowindowButton) {
            remove(ownerLoginpanel);
            OwnerWindow();
        } else if (e.getSource() == ownerSummary) {
            remove(ownerLoginpanel);
            int x = 0, y = 0;
            try {
                y = totalexpense();
                x = monthlyexpense();
            } catch (Exception j) {
                // TODO: handle exception
            }

            Summary(x, y);
        } else if (e.getSource() == ownerOrder) {
            remove(ownerLoginpanel);
            ManageRediWindow();
            // remove(ownerLoginpanel);
            // Order o = new Order(this.name,this.num,this.password,"1");
            // o.t.start();
            // try {
            // o.t.join();
            // } catch (InterruptedException e1) {
            // // TODO Auto-generated catch block
            // e1.printStackTrace();
            // }
        } else if (e.getSource() == ownerAdd) {
            // ownerLoginpanel.setVisible(false);
            o = new Order(this.name, this.num, this.password, "1");
            o.t.start();
            // try {
            // o.t.join();
            // } catch (InterruptedException e1) {
            // // TODO Auto-generated catch block
            // e1.printStackTrace();
            // }
        } else if (e.getSource() == ownerDelete) {
            // remove(ownerLoginpanel);
            o = new Order(this.name, this.num, this.password, "2");
            o.t.start();
            try {
                o.t.join();
            } catch (InterruptedException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        } else if (e.getSource() == deliverButton) {
            try {
                remove(ownerLoginpanel);
                int flip=0;
                File Obj = new File(this.name + this.num + ".txt");
                Scanner Reader = new Scanner(Obj);
                String result = "";
                while (Reader.hasNextLine()) {
                    String input = Reader.nextLine();
                    String s[] = input.split(" ");
                    if (s[5].equals("N")&&flip==0) {
                        // if redi owners says that he has delivered
                        File Obj1 = new File(s[0] + ".txt");
                        Scanner Reader1 = new Scanner(Obj1);
                        String result1 = "";
                        while (Reader1.hasNextLine()) {
                            String input1 = Reader1.nextLine();
                            String s1[] = input1.split(" ");
                            if (s1[0].equals(this.name) && s1[1].equals(s[1]) && s1[5].equals(s[5])) {
                                result1 += (s1[0] + " " + s1[1] + " " + s1[2] + " " + s1[3] + " " + s1[4] + " D\n");
                                continue;
                            }
                            result1 += (input1 + "\n");
                        }
                        Reader1.close();
                        PrintWriter writer = new PrintWriter(new File(s[0] + ".txt"));
                        writer.append(result1);
                        writer.flush();
                        writer.close();
                        result += (s[0] + " " + s[1] + " " + s[2] + " " + s[3] + " " + s[4] + " D\n");
                        flip=1;
                        continue;
                    }
                    result += (input + "\n");
                }
                Reader.close();
                PrintWriter writer = new PrintWriter(new File(this.name + this.num + ".txt"));
                writer.append(result);
                writer.flush();
                writer.close();
                refreshorder();
            } catch (FileNotFoundException f) {
                System.out.println("File not found");
            }
        } else if (e.getSource() == ownerUpdate) {
            remove(ownerLoginpanel);

            try {
                refreshorder();
            } catch (FileNotFoundException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
        else if(e.getSource()==orderDoneButton){
            remove(ownerLoginpanel);
            ManageRediWindow();
        }
        // else if(e.getSource()==ownerAddButton){
        // String s = ownerAddText.getText();
        // try {
        // addMenu(s);
        // failure.setText("Added to Menu");
        // } catch (Exception j) {
    }
}
