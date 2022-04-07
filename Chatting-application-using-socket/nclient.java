import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
import javax.swing.border.*;
import java.util.Calendar;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.HashMap;

import java.net.*;//socket
import java.io.*;//



import javax.swing.border.*;
import java.util.Calendar;

import java.text.SimpleDateFormat;
import java.util.Scanner;
import javax.swing.plaf.*;					//ScrollBarUI
import javax.swing.plaf.basic.*;		//basic scrollBarUI
import javax.swing.border.AbstractBorder;
import javax.swing.border.EmptyBorder;







class nclient extends JFrame
{
	//))))))
	int r=0,g=0,bl=0;
	JPanel pan=new JPanel();
	JLabel name=new JLabel();
	 	HashMap<String,JScrollPane>spane=new HashMap<>();
	String tp;




	//list design variables
	JFrame frame1=new JFrame();
	JLabel logo=new JLabel();
	// Map<String, ImageIcon> imageMap;
	 List<String> onlineUser=new ArrayList<String>();
	 String[] nameList;//onlineUser.toArray(new String[0]);
	 DefaultListModel model=new DefaultListModel();
	 JList list = new JList(model);
	  boolean bo=false;
	  String user;
	  String user1;
	  String st;



	 //socket variables
	 Socket socket;

	 	BufferedReader br;
	 	PrintWriter pw;
	 	String str,rec;
	 	String yy;
	 	Scanner in=new Scanner(System.in);






//showing user messages variables


	//List<String> txtArray=new ArrayList<String>();
	HashMap<String,JTextArea>map=new HashMap<>();

	String tname;
	JFrame frame2=new JFrame();



			JTextField textField;
		    JTextField t;
		    JPanel mypanel=new JPanel();


		    boolean activate = false;
		    boolean pactivate = false;
		    boolean typing;
		    String sender;
			String receiver;
		    String msg;




		JTextField tf=new JTextField(15);
		JPanel panel=new JPanel();
		JButton b=new JButton("Send");

		JLabel l1=new JLabel();
		 Box vertical=Box.createVerticalBox();




	nclient()
	{

		try{

						 socket=new Socket("127.0.0.1",5555);
						 br= new BufferedReader(new InputStreamReader(socket.getInputStream()));
						pw=new PrintWriter(socket.getOutputStream());



						 System.out.println("Enter The Name");
						 str=in.nextLine();
						 pw.println("**"+str);
						 pw.flush();

						// System.out.println("Enter The Receiver Name:");
						// rec=in.nextLine();




						//writer();


						}catch(Exception e){}


		reader();



		////////////////////

			basiclistdesign();




		/////////////
		/**/

	}


	public void reader()
			{
				//using Threads
				System.out.println("reader started");
				Runnable r1=()->{		//using lambda expresssion

				try{

					while(!socket.isClosed())
					{
						//System.out.println("I am in reader");


						String data=br.readLine();
						System.out.println("DATA="+data);
						String ss=data.substring(0,6);
						System.out.println("ss="+ss);


						//String checkonline=data.substring(0,6);
						//System.out.println("//"+checkonline);
						//user=data.substring(7);
						//tname=user;
						//System.out.println("TNAME="+tname);

						//JTextArea tname=new JTextArea();
						//tname.setBackground(Color.red);
						//user1="\""+user+"\"";
						//String str1="\""+str+"\"";
						//System.out.println(str1);


					if(ss.equals("online"))
					{
						System.out.println("Hello Online");
						String checkonline=data.substring(0,6);
						user=data.substring(7);
						tname=user;
						System.out.println("TNAME="+tname);

						if(checkonline.equals("online"))
						{
							if(user.equals(str))
							{
								System.out.println("??x");
								listDesign();
							}
							/*else if(onlineUser.size()==0)
							{
								onlineUser.add(user);
								list.addMouseListener(new textAreaDesign());
								listDesign();

							}*/
							else
							{

								 if(onlineUser.contains(user))
								 {
									System.out.println("Finslly");
								 }
								 else
								 {
								 	onlineUser.add(user);
								 	JTextArea ta=new JTextArea(15,30);
								 	map.put(user,ta);

								 	list.addMouseListener(new textAreaDesign());
								 	listDesign();
								 }

						    }

						}
					}
					else if(data.substring(0,6).equals("writer"))
					{
						msg=data.substring(6);

						int i1=data.indexOf(',');
						 sender=data.substring(6,i1);
						System.out.println("Sender="+sender);
						String ss1=data.substring(i1+1);
						int i2=ss1.indexOf(',');
						receiver=ss1.substring(0,i2);
						System.out.println("Receiver="+receiver);
						msg=ss1.substring(i2+1);
						System.out.println("MSG="+msg);





						System.out.println("\nreceiver@@@@@@:"+msg);
						JTextArea t=map.get(sender);
						t.append("\n"+msg);
						//t.validate();
						System.out.println(sender);


					}

						/*if(data.isEmpty())
												{
													System.out.println("Reader MSG is empty+"+data);

												}
						else*/



						if(data.equals("exit"))
						{
							System.out.println("server terminated the chat");
							socket.close();
							break;
						}


					}
				}catch(Exception e)
				{

					System.out.println("Connection rrrrrrrrrClosed");
					e.printStackTrace();
				}

				};
				new Thread(r1).start();

		}


		public JPanel bubble(String msg)
			{
				JPanel p2=new JPanel();
				p2.setLayout(new BoxLayout(p2,BoxLayout.Y_AXIS));
				JLabel l=new JLabel("<html> <p style=\"width:150px \">"+msg+"</p></html>");
				l.setBackground(new Color(37,211,102));
				l.setOpaque(true);
				l.setBorder(new EmptyBorder(15,15,15,50));
				Calendar cal=Calendar.getInstance();
				SimpleDateFormat sdf=new SimpleDateFormat("HH:MM");
				JLabel date=new JLabel();
				date.setText(sdf.format(cal.getTime()));
				p2.add(l);
				p2.add(date);
				return p2;


	}



	/////////////////
	class textAreaDesign extends MouseAdapter
	{
		boolean flag=false;
		public void mouseClicked(MouseEvent me)
		{



			frame2.setSize(316,500);
			frame1.setVisible(false);
			frame2.setLocation(frame1.getLocation());
			//frame2.setVisible(true);



			st=list.getSelectedValue().toString();
			//JPanel spanel=new JPanel();
			//spanel.setBounds(0,50,316,410);



			frame2.setLayout(null);
			if(tp!=null||spane.containsKey(st))
			{
				if(tp!=st)
				{
				name.setText("");
				System.out.println("<<<<<<1tp>>>>>>"+tp);
				System.out.println("<<<<<<1st>>>>>>"+st);
				//JTextArea tui=map.get(tp);
				//tui.setVisible(false);
				//frame2.remove(pan);
				JScrollPane dd=spane.get(tp);
				dd.setVisible(false);
				System.out.println("1");









				//frame2.remove(dd);

				//flag=true;
				//

				//name.setText(st);
				}
				/*else
				{
					name.setText("");

					System.out.println("<<<<<<2tp>>>>>>"+tp);
					System.out.println("<<<<<<2st>>>>>>"+st);
					if(spane.containsKey(st))
					{
						JScrollPane ssl=spane.get(st);

						System.out.println("cm");
						ssl.setBounds(0,50,316,410);
						frame2.add(ssl);
					}
				name.setText(st);

				}*/

			}


			tp=st;
			System.out.println(st);

			if(map.containsKey(st))
			{
				name.setText("");

				JTextArea t=map.get(st);
				JPanel pan=new JPanel();
				pan.setLayout(new BorderLayout());
				pan.setFont(new Font("Sprint",Font.PLAIN,15));
				//pan.setLayout();
				pan.setBackground(Color.blue);




				/*Runnable someRunner=new Runnable()
							{
								public void run()
								{
									validate();
								}
							};*/


				if(st.equals("kalin"))
				{


					//new Thread(someRunner).start();
					t.update(t.getGraphics());
					System.out.println("kal");
					t.setBackground(Color.black);
					t.setForeground(Color.WHITE);

					//t.setBounds(0,50,316,410);
					if(spane.containsKey(st))
					{
						JScrollPane ssl=spane.get(st);

						System.out.println("cm");
						ssl.setBounds(0,50,316,410);
						frame2.add(ssl);
						ssl.setVisible(true);
					}
					else
					{

						pan.add(t);
						JScrollPane sp=new JScrollPane(pan);
						System.out.println("jdjd");
						sp.setBounds(0,50,316,410);
						frame2.add(sp);
						spane.put(st,sp);
					}
						//frame2.add(sp);


				}
				else if(st.equals("munna"))
				{


					//new Thread(someRunner).start();

					t.update(t.getGraphics());
					System.out.println("mun");
					t.setBackground(Color.yellow);
					t.update(t.getGraphics());
					//t.setBounds(0,50,316,410);

						if(spane.containsKey(st))
						{
							JScrollPane ssl=spane.get(st);

							System.out.println("cm");
							ssl.setBounds(0,50,316,410);
							frame2.add(ssl);
							ssl.setVisible(true);
						}
						else
						{
							pan.add(t);
							JScrollPane sp=new JScrollPane(pan);
							System.out.println("jdjd");
							sp.setBounds(0,50,316,410);
							frame2.add(sp);
							spane.put(st,sp);
						}



				}
				else if(st.equals("guddu"))
				{

					//new Thread(someRunner).start();

					t.update(t.getGraphics());
					System.out.println("gud");
					t.setBackground(Color.green);
					//t.setBounds(0,50,316,410);

						if(spane.containsKey(st))
																{
																	JScrollPane ssl=spane.get(st);

																	System.out.println("cm");
																	ssl.setBounds(0,50,316,410);
																	frame2.add(ssl);
																	ssl.setVisible(true);
											}else{

												pan.add(t);
												JScrollPane sp=new JScrollPane(pan);
												System.out.println("jdjd");
												sp.setBounds(0,50,316,410);
												frame2.add(sp);
												spane.put(st,sp);
					}


				}
				else
				{
					t.update(t.getGraphics());
					System.out.println("gud");
					t.setBackground(Color.red);
				//	t.setBounds(0,50,316,410);

					if(spane.containsKey(st))
					{
						JScrollPane ssl=spane.get(st);

						System.out.println("cm");
						ssl.setBounds(0,50,316,410);
						frame2.add(ssl);
						ssl.setVisible(true);
					}
					else
					{

						pan.add(t);
						JScrollPane sp=new JScrollPane(pan);
						System.out.println("jdjd");
						sp.setBounds(0,50,316,410);
						frame2.add(sp);
						spane.put(st,sp);
					}

				}


				/*for(Map.Entry m:map.entrySet())
				{
					System.out.println(m.getKey()+""+m.getValue());

				}*/



				name.setText(st);
				//t.setVisible(true);
				//JScrollPane sp=new JScrollPane();

				//frame2.add(t);
				//t.setBounds(0,50,316,410);

				//t.setForeground(Color.white);
				//t.setFont(new Font("Arial",Font.PLAIN,35));
				//sp.setBounds(0,50,316,410);
				//spanel.setBounds(0,50,316,410);
				//spanel.add(t);
				//sp.getViewport().add(spanel);


			//	frame2.add(spanel);

				System.out.println("ON MY WAY");





			}


			//JTextArea ta=new JTextArea();

			//ta.setText(st);
			//frame2.add(ta);
			//ta.setBackground(Color.red);
		//	ta.setBounds(0,0,316,500);





/*****************************************Dsign of showing messages*********************************/
			Box vertical=Box.createVerticalBox();


			ImageIcon img1=new ImageIcon(ClassLoader.getSystemResource("3.png"));
			Image i2=img1.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);

			ImageIcon img2=new ImageIcon(i2);
			JLabel l=new JLabel(img2);
			l.setBounds(5,8,30,30);

			panel.setBounds(0,0,300,50);
			panel.setLayout(null);
			panel.setBackground(new Color(7,94,84));
			frame2.add(panel);
			panel.add(l);
			//adding textArea to frame

			/*ta.setBounds(0,50,316,410);
			ta.setText("Hello");
			ta.setBackground(Color.black);
			ta.setForeground(Color.white);
			ta.setFont(new Font("Arial",Font.PLAIN,35));
			frame2.add(ta);*/



			//adding sachin image to panel
			ImageIcon sachin=new ImageIcon(ClassLoader.getSystemResource("dravid.jpeg"));
			Image i3=sachin.getImage().getScaledInstance(45,45,Image.SCALE_DEFAULT);
			ImageIcon img3=new ImageIcon(i3);

			l1.setIcon(img3);
			l1.setBounds(35,0,45,50);
			panel.add(l1);

			//adding name to the panel

			name.setFont(new Font("Arial",Font.BOLD,15));
			name.setForeground(Color.white);
			name.setBounds(83,5,50,20);
			panel.add(name);

			JLabel status=new JLabel("Online");
			status.setFont(new Font("Arial",Font.BOLD,15));
			status.setForeground(Color.white);
			status.setBounds(83,26,55,30);
			panel.add(status);


			//adding typing/online status
			Timer t=new Timer(1,new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					if(!typing)
					{
						status.setText("Online");
					}
				}

			});

			t.setInitialDelay(800);


			//adding textField to JFrame
			tf.setBounds(0,460,230,40);
			tf.setFont(new Font("Arial",Font.PLAIN,22));
			frame2.add(tf);


			//adding button to next of textfield
			b.setBounds(230,460,70,40);
			b.setBackground(new Color(7,94,84));
			b.setForeground(Color.white);
			b.setFont(new Font("Arial",Font.PLAIN,15));
			frame2.add(b);

			/**************************************************End Design messages************************/


			/****************************Handeling events of this frame*******************************/

			//adding keyListener to textfield

					tf.addKeyListener(new KeyAdapter()
					{
						public void keyPressed(KeyEvent ke)
						{
							status.setText("typing...");
							t.stop();
							typing=true;
						}

						public void keyReleased(KeyEvent ke)
						{
							if(!t.isRunning())
							{
								typing=false;
								t.start();
							}
						}
					});



			b.addActionListener(new ActionListener()
				{
						public void actionPerformed(ActionEvent e)
						{
							String s=tf.getText();

							if(!s.isEmpty())
							{

							pw.println("writer"+str+","+st+","+s);
							pw.flush();




							JTextArea t=map.get(st);

							System.out.println("???????????"+st);
							t.append("\n"+s);
							tf.setText("");

							}



						}
				});





				l.addMouseListener(new MouseAdapter()
				{
					public void mouseClicked(MouseEvent me)
					{
						frame2.setVisible(false);
						frame1.setVisible(true);
					}
				});


			frame2.setVisible(true);



		}
	}

	public void basiclistdesign()
	{
			frame1.setLayout(null);
				frame1.setVisible(true);
				frame1.setSize(316,500);
				ImageIcon limg1=new ImageIcon(ClassLoader.getSystemResource("logo.jpg"));
				Image img2=limg1.getImage().getScaledInstance(300,50,Image.SCALE_DEFAULT);

				ImageIcon limg2=new ImageIcon(img2);

				JLabel lab1=new JLabel(limg2);
				lab1.setBounds(0,0,300,50);
				frame1.add(lab1);
				frame1.validate();
				frame1.repaint();

	}


	public void listDesign()
	{
		System.out.println("new List");
		for(int m=0;m<onlineUser.size();m++)
		{
			System.out.println(onlineUser.get(m));

		}

		model.removeAllElements();


				for(int i=0;i<onlineUser.size();i++)
				{
					model.addElement(onlineUser.get(i));

				}

				frame1.add(list);
				//list.setCellRenderer(new MarioListRenderer());
			//	frame1.add(list);
				frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame1.setLocationRelativeTo(null);



				list.setBounds(0,50,316,500);
				list.setFont(new Font("Arial",Font.PLAIN,36));

				frame1.validate();
				frame1.repaint();


	}


	/*public class MarioListRenderer extends DefaultListCellRenderer {

		        Font font = new Font("helvitica", Font.BOLD, 24);

		        @Override
		        public Component getListCellRendererComponent(
		                JList list, Object value, int index,
		                boolean isSelected, boolean cellHasFocus) {

		            JLabel label = (JLabel) super.getListCellRendererComponent(
		                    list, value, index, isSelected, cellHasFocus);
		            label.setIcon(imageMap.get((String) value));
		            label.setHorizontalTextPosition(JLabel.RIGHT);
		            label.setFont(font);
		            return label;
		        }
    }




	 private Map<String, ImageIcon> createImageMap(String[] list) {


				        Map<String, ImageIcon> map = new HashMap<>();
				       for(String obj:nameList)
					   		{

							String rr=obj;
							System.out.println("++="+obj);
							//ImageIcon img1=new ImageIcon(ClassLoader.getSystemResource("./"+rr+".jpeg"));
							ImageIcon img1=new ImageIcon("sachin.jpg");
							Image i2=img1.getImage().getScaledInstance(60,60,Image.SCALE_DEFAULT);
				            map.put(obj, new ImageIcon(i2));

				        }


				        return map;

	    }*/

//////////ending design of showing lists of users







	public static void main(String[]args)
	{
		nclient my=new nclient();


	}



}