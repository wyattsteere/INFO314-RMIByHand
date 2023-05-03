package org.example;

import java.net.*;
import java.io.*;

public class Client {
    private static Socket socket;
    private static ObjectOutputStream out;
    private static ObjectInputStream in;

    /**
     * This method name and parameters must remain as-is
     */
    public static int add(int lhs, int rhs) {
        return -1;
    }
    /**
     * This method name and parameters must remain as-is
     */
    public static int divide(int num, int denom) {
        return -1;
    }
    /**
     * This method name and parameters must remain as-is
     */
    public static String echo(String message) {
        return "";
    }


    private static void sendRequest(String methodName, Object[] args) throws Exception {
        out.writeObject(methodName);
        out.writeObject(args);
        out.flush();

        Object result = in.readObject();
        System.out.println("Result of " + methodName + ": " + result);
    }

    // Do not modify any code below this line
    // --------------------------------------
    String server = "localhost";
    public static final int PORT = 10314;

    public static void main(String... args) {
        try {
            socket = new Socket("localhost", 10314);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            System.out.println("Connected to server on port 10314...");


            //RemoteMethod add = new RemoteMethod("add", new Object[]{ (Object) 2, (Object) 4});
           // OutputStream os = socket.getOutputStream();
           // ObjectOutputStream oos = new ObjectOutputStream(os);
           // oos.writeObject(add);
           // oos.flush();

            sendRequest("add", new Object[]{ (Object) 2, (Object) 4});
            sendRequest("divide", new Object[]{ (Object)10, (Object) 2});
            sendRequest("echo", new Object[]{"Hello"});



            in.close();
            out.close();
            socket.close();
            System.out.println("Disconnected from server.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }



        // All of the code below this line must be uncommented
        // to be successfully graded.
        System.out.print("Testing... ");
        System.out.println(add(2,4));
        if (add(2, 4) == 6)
            System.out.print(".");
        else
            System.out.print("X");

        try {
            divide(1, 0);
            System.out.print("X");
        }
        catch (ArithmeticException x) {
            System.out.print(".");
        }

        if (echo("Hello").equals("You said Hello!"))
            System.out.print(".");
        else
            System.out.print("X");

        System.out.println(" Finished");
    }
}