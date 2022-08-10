/*
 * Developed by - mGunawardhana
 * Contact email - mrgunawardhana27368@gmail.com
 * what's app - 071 - 9043372
 */

package tester;

import java.io.*;
import java.net.*;
public class sim_sock {

    //TODO :: This is server side
    public sim_sock()
    {
        try {
            ServerSocket ss=new ServerSocket(8006);
            System.out.println ("Waiting for request");
            Socket s=ss.accept();
            System.out.println ("Connected With"+s.getInetAddress().toString());
            ObjectInputStream   ois = new ObjectInputStream(s.getInputStream());
            ObjectOutputStream  oos = new ObjectOutputStream(s.getOutputStream());

            String req=(String)ois.readObject();
            System.out.println (req);

            File f=new File(req);
            FileInputStream fin=new FileInputStream(f);

            int c;
            int sz=(int)f.length();
            byte b[]=new byte [sz];
            oos.writeObject(new Integer(sz));
            oos.flush();
            int j=0;
            while ((c = fin.read()) != -1) {

                b[j]=(byte)c;
                j++;
            }



            fin.close();
            oos.flush();
            oos.write(b,0,b.length);
            oos.flush();
            System.out.println ("Size "+sz);
            System.out.println ("buf size"+ss.getReceiveBufferSize());
            oos.writeObject(new String("Ok"));
            oos.flush();
            ss.close();
        }
        catch (Exception ex) {
            System.out.println ("Error"+ex);
        }
    }
    public static void main (String[] args) {
        sim_sock ob=new sim_sock();
    }
}
