/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;

/**
 *
 * @author Administrator
 */
public class Server {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)  throws IOException 
    {
        // TODO code application logic here
        new Server().begin(4444);
    }
    
        ServerSocket serverSocket;

    public void begin(int port) throws IOException 
    {
        serverSocket = new ServerSocket(port);
        while (true) {
            System.out.println("Waiting for clients to connect on port " + port + "...");
            new ProtocolThread(serverSocket.accept()).start();
            //Thread.start() calls Thread.run()
        }
    }
    
        class ProtocolThread extends Thread {

        Socket socket;
        PrintWriter out_socket;
        BufferedReader in_socket;
        Connection conn;

        public ProtocolThread(Socket socket) 
        {
            System.out.println("Accepting connection from " + socket.getInetAddress() + "...");
            this.socket = socket;
            try {
                out_socket = new PrintWriter(socket.getOutputStream(), true);
                in_socket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            try
            {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                String url = "jdbc:mysql://localhost:3306/piecetheatre";
                conn = DriverManager.getConnection(url, "root", "isep");
            //  conn.close();
            }
            catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException ex) {System.err.println(ex.getMessage());}
        }

/*        @Override
        public void run() // no connection to db
        {

            try
            {
                //load the names of theate
                int nplace[] = new int[10];
                for(int i = 0; i != 10; ++i)
                {
                    nplace[i] = 6;
                    out_socket.println(i);
                }
                
                out_socket.println("end of list");
                String read_client;
                int np, num;
                read_client = in_socket.readLine();
                while(! read_client.equals("Client quit"))
                {
                    np = Integer.valueOf(read_client);
                    read_client = in_socket.readLine();
                    num = Integer.valueOf(read_client);
                    read_client = in_socket.readLine();
                   if (nplace[np] - num >= 0)
                    {
                        nplace[np] -= num;
                        read_client += ", you have reserved successfully!";
                        out_socket.println(read_client);      
                    }
                    else
                    {
                        read_client += ", sorry but we have no enough places!";
                        out_socket.println(read_client);
                    }
                    read_client = in_socket.readLine();
                }
                System.out.println(read_client);
            }catch (Exception e) {
                    e.printStackTrace();
            } finally {
                    try {
                        System.out.println("Closing connection.");
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }//end catch
                }//end finally 
        }//end run*/
        @Override
        public void run() 
        {

            try
            {
                //print the names of theate
                System.out.println("read the names from DB");
                String query = "SELECT name_p FROM pieces";
                try
                {
                    Statement st = conn.createStatement();
                    ResultSet rs = st.executeQuery(query);
                    while (rs.next())
                    {
                    //    System.out.println(rs);
                        String s = rs.getString("name_p");
                        out_socket.println(s);
                    }//end of while
                }//end of try
                catch (SQLException ex)
                {
                  System.err.println(ex.getMessage());
                }
                out_socket.println("end of list");
                
                String read_client, np;
                int num, sum = 0;
                read_client = in_socket.readLine();
                while(! read_client.equals("Client quit"))
                {
                    np = read_client;
                    read_client = in_socket.readLine();
                    num = Integer.valueOf(read_client);
                    read_client = in_socket.readLine();
                    query = "SELECT number_p FROM pieces WHERE name_p='"+np+"'";
                    try
                    {
                        Statement st = conn.createStatement();
                        ResultSet rs = st.executeQuery(query);
                        rs.next();
                        sum = rs.getInt("number_p");
                    //    System.out.println(sum);
                    }//end of try
                    catch (SQLException ex)
                    {
                      System.err.println(ex.getMessage());
                    }
                   if (sum - num >= 0)
                    {
                        sum-=num;
                        query = "UPDATE pieces SET number_p="+sum+" WHERE name_p='"+np+"'";
                        System.out.println(query);
                        try
                        {
                            Statement st = conn.createStatement();
                            st.executeUpdate(query);
                        }                    
                        catch (SQLException ex)
                        {
                            System.err.println(ex.getMessage());
                        }
                        read_client += ", you have reserved successfully!";
                        out_socket.println(read_client);      
                    }
                    else
                    {
                        read_client += ", sorry but we have no enough places!";
                        out_socket.println(read_client);
                    }
                    read_client = in_socket.readLine();
                }
                System.out.println(read_client);
                conn.close();
            }catch (Exception e) {
                    e.printStackTrace();
            } finally {
                    try {
                        System.out.println("Closing connection.");
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }//end finally 
        }//end run
    }//end class protocol thread
    
}
