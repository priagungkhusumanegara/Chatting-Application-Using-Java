/****************************************************************
*	Version		:	1.0
*	Date		:	02/03/2007
*	
*	Description
*	This is a client side of chat application.
*	This application is used to sending and receiving the messages
*	and in this we can maintain the list of all online users
*	
*	Remarks
*	Before running the client application make sure the server is 
*	running.If server is running then only you can execute your
*	application.
******************************************************************/
import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

//create the GUI of the client side
public class MyClient extends WindowAdapter implements ActionListener{
	
	JFrame frame;
	JList list;
	JList list1;
	JTextField tf;
	DefaultListModel model;
	DefaultListModel model1;
	JButton button;
	JButton lout;
	JButton warna;
	JButton kirim;
	JScrollPane scrollpane;
	JScrollPane scrollpane1;
	JLabel label;
	
	JLabel gambar1;
	Socket s,s1,s2;
	//Socket sock;

	DataInputStream din;
	DataOutputStream dout;
	DataOutputStream dlout;
	DataOutputStream dout1;
	DataInputStream din1;
	
	String name;
	String ip;
	int port11;
	JPanel panel = new JPanel();

	//===========================================================================
	private String filePath;
    private String filePathNew = "";
	Penerima PENERIMA;
    Pengirim PENGIRIM;
    
    JPanel panelFileTransfer = new JPanel();
    JButton pengirim = new JButton("Pengirim");
    JButton penerima = new JButton("Penerima");
    JLabel labelTransfer = new JLabel("Pilih jadi Pengirim atau Penerima :");
    
    JPanel panelPengirim = new JPanel();
    JFileChooser fileChose = new JFileChooser();
    JLabel portFT = new JLabel("Port File Transfer : ");
    JTextField PORTFT = new JTextField("6000");
    JButton kirimFile = new JButton("Kirim File");
    JButton stopKirim = new JButton("Stop Kirim");
    
    JPanel panelPenerima = new JPanel();
    JFileChooser taroFile = new JFileChooser();
    JLabel portFTpen = new JLabel("Port File Transfer : ");
    JTextField PORTFTPEN = new JTextField("6000");
    JButton terimaFile = new JButton("Terima File");
    JLabel namaFile = new JLabel("Nama File : ");
    JTextField NAMAFILE = new JTextField("contoh.pdf");
    JLabel hostFile = new JLabel("Host Pengirim : ");
    JTextField HOSTFILE = new JTextField("127.0.0.1");
    
//===========================================================
	@SuppressWarnings("deprecation")
	MyClient(String name, String ip, String port1)throws IOException{
//	MyClient(String name)throws IOException{

		//================================================
		
		panelFileTransfer.setLayout(null);
        panelFileTransfer.setBackground(Color.cyan);
        panelFileTransfer.add(pengirim).setBounds(50, 40, 100, 25);
        panelFileTransfer.add(penerima).setBounds(250, 40, 100, 25);
        panelFileTransfer.add(labelTransfer).setBounds(50, 0, 500, 25);
        
        panelPengirim.setLayout(null);
        panelPengirim.setBackground(Color.cyan);
        panelPengirim.add(portFT).setBounds(0, 0, 150, 25);
        panelPengirim.add(PORTFT).setBounds(150, 0, 100, 25);
        panelPengirim.add(fileChose).setBounds(0, 40, 100, 25);
        panelPengirim.add(kirimFile).setBounds(150, 40, 100, 25);
        panelPengirim.add(stopKirim).setBounds(50, 40, 100, 25);
        
        panelPenerima.setLayout(null);
        panelPenerima.setBackground(Color.cyan);
        panelPenerima.add(portFTpen).setBounds(0, 0, 150, 25);
        panelPenerima.add(PORTFTPEN).setBounds(150, 0, 100, 25);
        panelPenerima.add(taroFile).setBounds(0, 40, 100, 25);
        panelPenerima.add(terimaFile).setBounds(150, 40, 100, 25);
        panelPenerima.add(namaFile).setBounds(270, 40, 150, 25);
        panelPenerima.add(NAMAFILE).setBounds(370, 40, 100, 25);
        panelPenerima.add(hostFile).setBounds(270, 0, 150, 25);
        panelPenerima.add(HOSTFILE).setBounds(370, 0, 100, 25);
     
        pengirim.addActionListener(this);
        penerima.addActionListener(this);
        fileChose.addActionListener(this);
        kirimFile.addActionListener(this);
        stopKirim.addActionListener(this);
        terimaFile.addActionListener(this);
        //===============================================================================
		frame = new JFrame("Client Side");
		tf=new JTextField();
		model=new DefaultListModel();
		model1=new DefaultListModel();
		label=new JLabel("Message");
	
		list=new JList(model);
		list1=new JList(model1);
		button=new JButton("Send");
		lout=new JButton("Logout");
		warna=new JButton("Ganti Warna");
		kirim=new JButton("Kirim File");
		scrollpane=new JScrollPane(list);
		scrollpane1=new JScrollPane(list1);
		panel.setBackground(Color.BLUE);
		button.addActionListener(this);
		lout.addActionListener(this);
		warna.addActionListener(this);
		
		kirim.addActionListener(this);
		panel.add(tf);panel.add(button);panel.add(scrollpane);
		panel.add(label);panel.add(lout);panel.add(warna);
		panel.add(scrollpane1);panel.add(kirim);
		scrollpane.setBounds(10,20,180,150);
		scrollpane1.setBounds(250,20,100,150);
		label.setBounds(20,180,80,30);
		tf.setBounds(100,180,140,30);
		button.setBounds(260,180,90,30);
		lout.setBounds(260,230,90,30);
		warna.setBounds(220,290,150,30);
		kirim.setBounds(220, 340, 150, 30);
	tf.requestFocus();
			 
	ImageIcon gambar = new ImageIcon(getClass().getResource("3x4.jpg"));
		Image img;
		img = gambar.getImage();
		img = img.getScaledInstance(150, 200, java.awt.Image.SCALE_SMOOTH);
		gambar.setImage(img);
		JLabel label1 = new JLabel();
	label1.setIcon(gambar);
	
		panel.add(label1);
		 
		label1.setBounds(20,250,200,200);
		
	     
		frame.add(panel);
		
		panel.setLayout(null);
		frame.setSize(500, 500); //ukuran frame
	   	frame.setVisible(true);
		frame.getRootPane().setDefaultButton(button);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.name=name;
		frame.addWindowListener(this);
		frame.setResizable(false);
		this.ip = ip;
		this.port11 = Integer.parseInt(port1);
		//s=new Socket("localhost",1004);	//creates a socket object
		//s1=new Socket("localhost",1004);
		//s2=new Socket("localhost",1004);
		s=new Socket(ip,port11);	//creates a socket object
		s1=new Socket(ip,port11);
		s2=new Socket(ip,port11);
//		sock=new Socket("localhost",1004);

		
	    //create inputstream for a particular socket
		din=new DataInputStream(s.getInputStream());
		//create outputstream
		dout=new DataOutputStream(s.getOutputStream());
		//sending a message for login
		dout.writeUTF(name+" has Logged in");	
		dlout=new DataOutputStream(s1.getOutputStream());
		dout1=new DataOutputStream(s2.getOutputStream());
		din1=new DataInputStream(s2.getInputStream());

		
// creating a thread for maintaning the list of user name
		My1 m1=new My1(dout1,model1,name,din1);
		Thread t1=new Thread(m1);
		t1.start();			
	//creating a thread for receiving a messages
		My m=new My(din,model);
		Thread t=new Thread(m);
		t.start();
		//FileInputStream fin;
		
  	}
	


	public void actionPerformed(ActionEvent e){
		// ketika tombol send ditekan	
		if(e.getSource()==button){	
			String str="";
			str=tf.getText();
			tf.setText("");
			str=name+": "+str; //menampilkan di Jlist
			try{
			dout.writeUTF(str);
			System.out.println(str);
			dout.flush();
			}catch(IOException ae){System.out.println(ae);}
		}
		// jika tombol logout ditekan
		if (e.getSource()==lout){
			frame.dispose();
			try{
			dout.writeUTF(name+" has Logged out");
			dlout.writeUTF(name);
			dlout.flush();
			Thread.currentThread().sleep(1000);
			System.exit(1);
			}catch(Exception oe){}
		}
		//untuk mengganti warna gui
		if (e.getSource()==warna){
			Color selectedColor = JColorChooser.showDialog(frame, "Pick a Color"
	                , Color.WHITE);
		  
			panel.setBackground(selectedColor);

		}
/*		if (e.getSource()==kirimfile){
		
			        JFileChooser fileChooser = new JFileChooser();
			        int returnValue = fileChooser.showOpenDialog(null);
			          File selectedFile = fileChooser.getSelectedFile();
			          
			          System.out.println(selectedFile.getPath());
		          
			  		JLabel namafile=new JLabel (selectedFile.getName());
			  		
			  		panel.add(namafile);
	
		
		}*/
		//===============================================================
		
		if(e.getSource()==kirim)
        {
            frame.setSize(500,620);
            panel.add(panelFileTransfer).setBounds(0, 510, 500, 100);
          

        }
        if(e.getSource()==pengirim)
        {
        	
        	panel.remove(panelFileTransfer);
        	panel.add(panelPengirim).setBounds(0, 510, 500, 100);
            JFileChooser fileChose = new JFileChooser();
	        int hasil = fileChose.showOpenDialog(null);
	          
           // fileChose.setFileSelectionMode(JFileChooser.FILES_ONLY);
            //int hasil = fileChose.showOpenDialog(null);
            if(hasil ==JFileChooser.CANCEL_OPTION)
            {
                System.exit(1);
            }
            File file = fileChose.getSelectedFile();
           filePath="";
            filePath = file.getPath();
            
            StringTokenizer st = new StringTokenizer(filePath,"\\");
            
            int jumlahToken = st.countTokens();
            for(int i = 0; i<jumlahToken-1;i++){
                filePathNew = filePathNew+st.nextToken()+"\\\\"; 
            }
            filePathNew = filePathNew+file.getName();
            System.out.println(filePathNew);
            
            
        }
        if(e.getSource()==kirimFile)
        {
            
            PENGIRIM = new Pengirim(Integer.parseInt(PORTFT.getText()),filePathNew);
            try {
				dout.writeUTF("Pengiriman File Selesai");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
           // list.append("Pengiriman File Selesai");
           panel.remove(panelPengirim);
            frame.setSize(500,500);
            
               
            
        }
        if(e.getSource()==stopKirim)
        {
            
            
        }
        if(e.getSource()==penerima)
        {
            panel.remove(panelFileTransfer);
            panel.add(panelPenerima).setBounds(0, 510, 500, 100);
            taroFile.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int hasil = taroFile.showOpenDialog(null);
            if(hasil ==taroFile.CANCEL_OPTION)
            {
                System.exit(1);
            }
            File file = fileChose.getCurrentDirectory();
            filePath = file.getPath();
            StringTokenizer st = new StringTokenizer(filePath,"\\");
            
            int jumlahToken = st.countTokens();
            for(int i = 0; i<jumlahToken;i++){
                filePathNew = filePathNew+st.nextToken()+"\\\\"; 
            }
                    
            
        }
        if(e.getSource()==terimaFile)
        {
            filePathNew = filePathNew+NAMAFILE.getText();
            System.out.println(filePathNew);
            try {
                PENERIMA = new Penerima(HOSTFILE.getText(),Integer.parseInt(PORTFTPEN.getText()),filePathNew);
            } catch (IOException ex) {
               
            }
            try {
				dout.writeUTF("File Diterima");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            //isi.append("File Diterima");
           panel.remove(panelPenerima);
            frame.setSize(500,500);
        }
        //===============================================================================
	}
	


	

//langsung di close sama dengan logout
	public void windowClosing(WindowEvent w){
		try{
			dlout.writeUTF(name);
			dlout.flush();	
			Thread.currentThread().sleep(1000);
			System.exit(1);
			}catch(Exception oe){}
		}

	

	   
}
//=======================================================
class Penerima{
	  public Penerima(String host,int port,String path) throws IOException {
	    int filesize=6022386; // filesize temporary hardcoded

	    long start = System.currentTimeMillis();
	    int bytesRead;
	    int current = 0;
	    // localhost for testing
	    Socket sock = new Socket(host,port);
	    System.out.println("Connecting...");

	    // receive file
	    byte [] mybytearray  = new byte [filesize];
	    InputStream is = sock.getInputStream();
	    FileOutputStream fos = new FileOutputStream(path);
	    BufferedOutputStream bos = new BufferedOutputStream(fos);
	    bytesRead = is.read(mybytearray,0,mybytearray.length);
	    current = bytesRead;

	    
	    do {
	       bytesRead =
	          is.read(mybytearray, current, (mybytearray.length-current));
	       if(bytesRead >= 0) current += bytesRead;
	    } while(bytesRead > -1);

	    bos.write(mybytearray, 0 , current);
	    bos.flush();
	    long end = System.currentTimeMillis();
	    System.out.println(end-start);
	    bos.close();
	    sock.close();
	  }
	}

class Pengirim {
	  ServerSocket servsock;
	  
	  public Pengirim(int port, String path){
	        try {
	            // create socket
	            servsock = new ServerSocket(port);
	            while (true) {
	              System.out.println("Waiting...");
	              
	              Socket sock = servsock.accept();
	              System.out.println("Accepted connection : " + sock);

	              // sendfile
	              File myFile = new File (path);
	              byte [] mybytearray  = new byte [(int)myFile.length()];
	              FileInputStream fis = new FileInputStream(myFile);
	              BufferedInputStream bis = new BufferedInputStream(fis);
	              bis.read(mybytearray,0,mybytearray.length);
	              OutputStream os = sock.getOutputStream();
	              System.out.println("Sending...");
	              os.write(mybytearray,0,mybytearray.length);
	              os.flush();
	              sock.close();
	              servsock.close();
	              
	              }
	        } catch (IOException ex) {
	            
	        }
	    
	    }
	  public void stop() throws IOException
	  {
	      servsock.close();
	  }

	}


//===================================================================================
// class is used to maintaning the list of user name
class My1 implements Runnable{
	DataOutputStream dout1;
	DefaultListModel model1;	
	DataInputStream din1;
	String name,lname;
	ArrayList alname=new ArrayList(); //stores the list of user names
	ObjectInputStream obj; // read the list of user names
	int i=0;
	My1(DataOutputStream dout1,DefaultListModel model1,String name,DataInputStream din1){
		this.dout1=dout1;
		this.model1=model1;
		this.name=name;
		this.din1=din1;
	}
	public void run(){
		try{
		dout1.writeUTF(name);  // write the user name in output stream
		while(true){
			obj=new ObjectInputStream(din1);
			//read the list of user names
			alname=(ArrayList)obj.readObject(); 
			if(i>0)
			model1.clear(); 
			Iterator i1=alname.iterator();
			System.out.println(alname);
			while(i1.hasNext()){
				lname=(String)i1.next();
				i++;
				 //add the user names in list box
				model1.addElement(lname);
				}
			}
		}catch(Exception oe){}
	}
}


//class is used to received the messages
class My implements Runnable{
	DataInputStream din;
	DefaultListModel model;
	My(DataInputStream din, DefaultListModel model){
		this.din=din;
		this.model=model;
	}
	public void run(){
		String str1="";
		while(true){
			try{
				str1=din.readUTF(); // receive the message
				// add the message in list box
				model.addElement(str1);
			}catch(Exception e){}
		}
	}
}
