import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import javax.swing.*;

import java.awt.Window;
import java.awt.event.*;

public class Owner extends UserType implements Runnable, ActionListener {
    String name;
    String num;
    String password;
    ArrayList<ordering> orders = new ArrayList<ordering>();
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
    private JButton userAdd;
    private JButton userDelete;
    private JButton userUpdate;
    private Order o;
    private JLabel orderLabel;
    private JLabel showingorders;
    private JButton deliverButton;
    private JButton orderDoneButton;

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
        userLoginpanel = new JPanel();
        setTitle(name);
        setSize(500, 500);
        // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // userLoginpanel.setLayout(new LayoutManager());
        // userLoginpanel.setLayout(new GridLayout(3,1));
        userOrder = new JButton("Manage Redi");
        userOrder.setSize(100, 25);
        userOrder.addActionListener(this);
        userSummary = new JButton("Summary");
        userSummary.setSize(100, 25);
        userSummary.addActionListener(this);
        userLogout = new JButton("Logout");
        userLogout.setSize(100, 25);
        userLogout.addActionListener(this);
        userChangePass = new JButton("Change Password");
        userChangePass.setSize(100, 25);
        userChangePass.addActionListener(this);

        userLoginpanel.add(userOrder);
        userLoginpanel.add(userSummary);
        userLoginpanel.add(userLogout);
        userLoginpanel.add(userChangePass);
        add(userLoginpanel);
        setVisible(true);
    }

    public void userSummary(int x, int y) {
        userLoginpanel = new JPanel();
        setTitle("Summary");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        userLoginpanel.setLayout(null);
        userLabel = new JLabel("Total expense");
        userLabel.setBounds(10, 20, 150, 25);
        passLabel = new JLabel("Monthly expense");
        passLabel.setBounds(10, 50, 150, 25);
        userTotalExpense = new JLabel(y + "");
        userTotalExpense.setBounds(180, 20, 165, 25);
        userMonthlyExpense = new JLabel(x + "");
        userMonthlyExpense.setBounds(180, 50, 165, 25);
        userbacktowindowButton = new JButton("Back");
        userbacktowindowButton.setBounds(10, 110, 100, 25);
        userbacktowindowButton.addActionListener(this);
        failure = new JLabel("");
        failure.setBounds(130, 80, 200, 25);
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
        userLoginpanel = new JPanel();
        userLoginpanel.setLayout(null);
        setTitle("Your Orders");
        setSize(500, 200);
        orderLabel = new JLabel("Orders");
        orderLabel.setBounds(10, 20, 80, 25);
        showingorders = new JLabel();
        showingorders.setBounds(10, 50, 400, 25);
        deliverButton = new JButton("Deliver");
        deliverButton.setBounds(10, 80, 150, 25);
        deliverButton.addActionListener(this);
        userLoginpanel.add(showingorders);
        userLoginpanel.add(orderLabel);
        
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
            userLoginpanel.add(orderDoneButton);
        }
        else {
            userLoginpanel.add(deliverButton);
        }
        add(userLoginpanel);
        setVisible(true);
    }

    public void menu() throws FileNotFoundException {
        File Obj = new File(this.name + this.num + "menu.txt");
        Scanner Reader = new Scanner(Obj);
        while (Reader.hasNextLine()) {
            String s[] = Reader.nextLine().split(" ");
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
        userLoginpanel = new JPanel();
        setTitle("Manage Redi");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // userLoginpanel.setLayout(new LayoutManager());
        // userLoginpanel.setLayout(new GridLayout(3,1));
        userAdd = new JButton("Add To Menu");
        userAdd.setSize(100, 25);
        userAdd.addActionListener(this);
        userDelete = new JButton("Remove From Menu");
        userDelete.setSize(100, 25);
        userDelete.addActionListener(this);
        userUpdate = new JButton("Take Orders");
        userUpdate.setSize(100, 25);
        userUpdate.addActionListener(this);
        userbacktowindowButton = new JButton("Back");
        userbacktowindowButton.setSize(100, 25);
        userbacktowindowButton.addActionListener(this);
        userLoginpanel.add(userAdd);
        userLoginpanel.add(userDelete);
        userLoginpanel.add(userUpdate);
        userLoginpanel.add(userbacktowindowButton);
        add(userLoginpanel);
        setVisible(true);
    }

    public void UserChangePass() {
        userLoginpanel = new JPanel();
        setTitle("Change Password");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        userLoginpanel.setLayout(null);
        userLabel = new JLabel("Enter Old Password");
        userLabel.setBounds(10, 20, 150, 25);
        passLabel = new JLabel("New Password");
        passLabel.setBounds(10, 50, 150, 25);
        newPass = new JPasswordField();
        newPass.setBounds(180, 50, 165, 25);
        Pass = new JPasswordField();
        Pass.setBounds(180, 20, 165, 25);
        userChangePassButton = new JButton("Change Password");
        userChangePassButton.setBounds(10, 80, 150, 25);
        userChangePassButton.addActionListener(this);
        userbacktowindowButton = new JButton("Back");
        userbacktowindowButton.setBounds(10, 110, 100, 25);
        userbacktowindowButton.addActionListener(this);
        failure = new JLabel("");
        failure.setBounds(130, 80, 200, 25);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if (e.getSource() == userLogout) {
            this.dispose();
        } else if (e.getSource() == userChangePass) {
            remove(userLoginpanel);
            UserChangePass();
        } else if (e.getSource() == userChangePassButton) {
            String s = newPass.getText();
            try {
                changePassword(s);
                failure.setText("Password Changed");
            } catch (Exception j) {
                // TODO: handle exception
                System.out.println("Never Gonna Give you up ");
                System.out.println(j);
            }
        } else if (e.getSource() == userbacktowindowButton) {
            remove(userLoginpanel);
            OwnerWindow();
        } else if (e.getSource() == userSummary) {
            remove(userLoginpanel);
            int x = 0, y = 0;
            try {
                y = totalexpense();
                x = monthlyexpense();
            } catch (Exception j) {
                // TODO: handle exception
            }

            userSummary(x, y);
        } else if (e.getSource() == userOrder) {
            remove(userLoginpanel);
            ManageRediWindow();
            // remove(userLoginpanel);
            // Order o = new Order(this.name,this.num,this.password,"1");
            // o.t.start();
            // try {
            // o.t.join();
            // } catch (InterruptedException e1) {
            // // TODO Auto-generated catch block
            // e1.printStackTrace();
            // }
        } else if (e.getSource() == userAdd) {
            // userLoginpanel.setVisible(false);
            o = new Order(this.name, this.num, this.password, "1");
            o.t.start();
            // try {
            // o.t.join();
            // } catch (InterruptedException e1) {
            // // TODO Auto-generated catch block
            // e1.printStackTrace();
            // }
        } else if (e.getSource() == userDelete) {
            // remove(userLoginpanel);
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
                remove(userLoginpanel);
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
        } else if (e.getSource() == userUpdate) {
            remove(userLoginpanel);

            try {
                refreshorder();
            } catch (FileNotFoundException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
        else if(e.getSource()==orderDoneButton){
            remove(userLoginpanel);
            ManageRediWindow();
        }
        // else if(e.getSource()==userAddButton){
        // String s = userAddText.getText();
        // try {
        // addMenu(s);
        // failure.setText("Added to Menu");
        // } catch (Exception j) {
    }
}
