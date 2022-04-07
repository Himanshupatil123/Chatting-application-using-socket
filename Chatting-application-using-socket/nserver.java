import java.net.*;
import java.io.*;
import java.util.*;

class nserver implements Runnable{

    Socket socket;

    public static Vector client = new Vector();
    public static Vector temp = new Vector();


    boolean bo=false;
    String sender;
    String receiver;
    String msg;

    public nserver(Socket socket){
            try{
                this.socket = socket;
            }catch(Exception e){}
        }


        public void run(){
            try{
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                client.add(writer);
                System.out.println("IIPIIIIIIIIIII");

                while(true){
    				System.out.println("JJJJ");
    				String data11= reader.readLine();//.trim();
				String data;

				String checkmsg=data11.substring(0,2);
								if(checkmsg.equals("**"))
								{
									 data=data11.substring(2);
								}
								else
								{
									data=data11.substring(0);

													int i1=data.indexOf(',');
													 sender=data.substring(0,i1);
													System.out.println("Sender="+sender);
													String ss1=data.substring(i1+1);
													int i2=ss1.indexOf(',');
													receiver=ss1.substring(0,i2);
													System.out.println("Receiver="+receiver);
													msg=ss1.substring(i2+1);
													System.out.println("MSG="+msg);




								}

				                System.out.println("Checkmsg="+checkmsg);

				                System.out.println(data);
				                System.out.println("Received "+data);
                System.out.println("AGSHHHHD");

                if(checkmsg.equals("**"))
		                {
							if(temp.isEmpty())
							{
								System.out.println("System");
								temp.add(data);

							}

							for(int m=0;m<temp.size();m++)
							{
							  if(temp.get(m).equals(data)&&m<temp.size())
							  {
								System.out.println("Ia ma in temperoary"+temp.get(m));
								bo=false;
								break;
							  }
							  System.out.println("kdjhf");
							  if(m==temp.size()-1)
							  {
								System.out.println("lolkikju=mmm---"+m);
								bo=true;
								break;
							  }
							System.out.println(m);

							}

							if(bo==true)
							{
							System.out.println("DOll");
							temp.add(data);



							}

						for(int j=0;j<temp.size();j++)
						{
							try{

								String user=temp.get(j).toString();
								System.out.println("Username="+user);
								for(int o=0;o<client.size();o++)
								{
									BufferedWriter bw=(BufferedWriter)client.get(o);
									System.out.println("ID="+client.get(o));
									bw.write("online,"+user);
									bw.write("\r\n");
									bw.flush();
									System.out.println("temp="+user);
								}
							}catch(Exception e)
							{
								e.printStackTrace();

							}
				}

				}
							else
							{
				          		 for(int i = 0; i < client.size(); i++)
								 {
									try{
										BufferedWriter bw = (BufferedWriter)client.get(i);
										System.out.println(bw);

									   //bw.write(data);
									   // bw.write("\r\n");
									   // bw.flush();
									}catch(Exception e){}
                }


                System.out.println("Online lists");
						//System.out.println("dd="+temp.size());
		               /* for(int j=0;j<temp.size();j++)
		                {
							BufferedWriter bw=(BufferedWriter)client.get(j);
		                	System.out.println(client.get(j));
		                //	System.out.println("KKKKK");
		                	String user=temp.get(j).toString();

		                	bw.write("online=,"+user);
		                	bw.write("\r\n");
		                    bw.flush();
		                	System.out.println("/////////////////");
		                	System.out.println("Send");
		                	System.out.println("/////////////////");
		                	System.out.println("online,"+user);
		                	System.out.println("User="+user);


						}*/

				//////



				for(int j=0;j<client.size();j++)
								 {
										if(temp.get(j).equals(receiver))
										{
											try{

												System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
											BufferedWriter bw=(BufferedWriter)client.get(j);
											System.out.println("Client+"+bw);
											System.out.println("receiver+"+receiver);

												bw.write(sender+","+receiver+","+msg);
												System.out.println(sender+","+receiver+","+msg);
												bw.write("\r\n");
												bw.flush();
												}
												catch(Exception e){}


										   }
								   }
							  }
						  }
		  }catch(Exception e){}

		  }


		      public static void main(String[] args) throws Exception{
		          ServerSocket s = new ServerSocket(5555);
		          while(true){
		              Socket socket = s.accept();
		              nserver server = new nserver(socket);
		              Thread thread = new Thread(server);
		              thread.start();
		          }
		      }
}