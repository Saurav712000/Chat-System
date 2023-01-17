package com.projectNetwork;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.*;


public class ServerChat implements ActionListener 
{
	 Frame f;
	 Button b1,b2;
	 TextField t1;
	 TextArea ta;
	 DataInputStream din;
	 DataOutputStream dout;
     ServerSocket ss;
     Socket s;
	
	 
	  
	  ServerChat()
	  {
		  f= new Frame();
		  f.setVisible(true);
		  f.setSize(500, 400);
		  f.setBackground(Color.CYAN);
		  f.setTitle("Rosey");
		  ta = new TextArea(30,40);
		  t1= new TextField(20);
		  b1 = new Button("SEND");
		  b2= new Button("CLOSE");
		  f.setLayout(new FlowLayout());
		  f.add(t1);
		  f.add(b1);
		  f.add(b2);
	      f.add(ta);
	      b1.addActionListener(this);
	      b2.addActionListener(this);
	      try 
	      {
	      ss = new ServerSocket(3535);
	      s= ss.accept();
	      
	      din = new DataInputStream(s.getInputStream());
	      dout = new DataOutputStream(s.getOutputStream());
	      while(true)
	      {
	    	  ta.append("\nSam:"+din.readUTF());
	      }
	      
	  }
	      catch(Exception e)
	      {
	    	  System.out.println(e);
	      }
	  }



	public static void main(String[] args) 
	{
		ServerChat sh = new ServerChat();

	}
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource()==b1)
		{
			try
			{
				dout.writeUTF(t1.getText());
				ta.append("\nRosey:"+t1.getText());
				t1.setText("");
			}
			catch(Exception e1)
			{
				System.out.println(e1);
			}
		}
		
		if(e.getSource()==b2)
		{
		  f.dispose();
		}
	}


}
