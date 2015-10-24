/****************************************************************
*	Version		:	1.0
*	Date		:	02/03/2007
*	
*	Description
*	This is a Login frame of client side application in chat System.
*	It is used to just take the user name 
*	
*	Remarks
*	Before running the Login application make sure the server is running.
*	If server is running then only you can execute your application.
******************************************************************/


import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

// Login class which takes a user name and passed it to client class
public class Login implements ActionListener{
	JFrame frame1;
	JTextField tf;
	
	JButton button;
	JLabel heading;
	JLabel label;
	JLabel labelpass;
	JLabel label1;
	JLabel ipadd;
	JLabel port;
	JTextField tfip;
	JTextField tfport;
	JPasswordField password;
	public static void main(String[] args){
		new Login();
	}
	public Login(){
		frame1 = new JFrame("Login Page");
	

		tf=new JTextField();
		password=new JPasswordField();
		button=new JButton("Login");
		heading=new JLabel("Aplikasi Chat");
		heading.setFont(new Font("Impact", Font.BOLD,40));
		label=new JLabel("Username");
		labelpass=new JLabel("Password");
		label.setFont(new Font("Serif", Font.PLAIN, 24));
		labelpass.setFont(new Font("Serif", Font.PLAIN, 24));
		ipadd = new JLabel("IP Address: ");
		port = new JLabel ("Port :");
		tfip = new JTextField("127.0.0.1");
		tfport = new JTextField("1004");
		ipadd.setBounds(50,400,250,60);
		port.setBounds(250,400,250,60);
		tfip.setBounds(140,410,100,30);
		tfport.setBounds(300,410,100,30);
		JPanel panel = new JPanel();
		button.addActionListener(this);
		panel.add(heading);panel.add(tf);panel.add(label);
		panel.add(button);
		panel.add(password);
		panel.add(labelpass);
		panel.add(tfip);
		panel.add(tfport);
		panel.add(ipadd);
		panel.add(port);
		heading.setBounds(30,20,280,80);
		label.setBounds(20,100,250,60);
		tf.setBounds(50,150,150,30);
		labelpass.setBounds(20,200,250,60);
		password.setBounds(50,250,150,30);
		button.setBounds(70,300,90,30);
		frame1.add(panel);
		panel.setLayout(null);
		frame1.setSize(500, 500);
	    frame1.setVisible(true);
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.getRootPane().setDefaultButton(button);
		frame1.setResizable(false);
		ImageIcon gambar = new ImageIcon(getClass().getResource("images.jpg"));
		Image img;
		img = gambar.getImage();
		img = img.getScaledInstance(250, 200, java.awt.Image.SCALE_SMOOTH);
		gambar.setImage(img);
		
	JLabel label1 = new JLabel();
	label1.setIcon(gambar);
	
		panel.add(label1);
		 
		label1.setBounds(220,150,300,200);

	}
	// pass the user name to MyClient class
	public void actionPerformed(ActionEvent e){
	
		String name = new String(tf.getText());
		 String password1 = new String(password.getText());
		String ip = new String(tfip.getText());
		String port1 = new String(tfport.getText());
		 if(name.equals("") || password1.equals("")){
			 JOptionPane .showMessageDialog(null, "Lengkapi isian");
		 }
		 else if(name.equals("andika")|| password1.equals("rahasia")){
	try{
			name=tf.getText();
			password1=password.getText();
			ip = tfip.getText();
			port1 = tfport.getText();
			frame1.dispose();
		MyClient mc=new MyClient(name,ip,port1);
	//MyClient mc=new MyClient(name);
			
	}	
		catch (IOException te){}
	}
		 else{
			 JOptionPane .showMessageDialog(null, "User dan Pass Salah");

		 }
	}
}