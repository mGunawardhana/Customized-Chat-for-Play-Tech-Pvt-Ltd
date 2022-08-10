package tester;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class sim_clin {
    public static void main(String argv[]) {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        Socket socket = null;
        java.util.Date date = null;
        try {

            socket = new Socket("localhost", 8006);

            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());

            oos.flush();
            oos.writeObject(new String("Desert.jpg"));

            oos.flush();
            oos.reset();
            int sz=(Integer )ois.readObject();
            System.out.println ("Receving "+(sz/1024)+" Bytes From Sever");

            byte b[]=new byte [sz];
            int bytesRead = ois.read(b, 0, b.length);
            for (int i = 0; i<sz; i++)
            {
                System.out.print(b[i]);
            }
            FileOutputStream fos=new FileOutputStream(new File("demo.jpg"));
            fos.write(b,0,b.length);
            System.out.println ("From Server : "+ois.readObject());
            oos.close();
            ois.close();
        } catch(Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
