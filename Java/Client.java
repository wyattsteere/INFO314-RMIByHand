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
    public static int add(int lhs, int rhs) throws Exception {
        Object result = sendRequest("add", new Object[]{lhs,rhs});
        int intResult = (int) result;
        return intResult;
    }
    /**
     * This method name and parameters must remain as-is
     */
    public static int divide(int num, int denom) throws Exception{
        Object result = sendRequest("divide", new Object[]{num, denom});
        int intResult = (int) result;

        return intResult;
    }
    /**
     * This method name and parameters must remain as-is
     */
    public static String echo(String message) throws Exception{
        Object result = sendRequest("echo", new Object[]{(message)});
       String stresult = (String) result;
        return stresult;
    }


    private static Object sendRequest(String methodName, Object[] args) throws Exception {
        Object result = "";
        out.writeObject(methodName);
        out.writeObject(args);
        out.flush();

        try {
            result = in.readObject();
        } catch (Exception e) {
            throw new ArithmeticException();
        }
//        System.out.println("Result of " + methodName + ": " + result);
        return result;
    }

    // Do not modify any code below this line
    // --------------------------------------
    String server = "localhost";
    public static final int PORT = 10314;

    public static void main(String... args) throws Exception {

            socket = new Socket("localhost", 10314);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            System.out.println("Connected to server on port 10314...");


            //RemoteMethod add = new RemoteMethod("add", new Object[]{ (Object) 2, (Object) 4});
            // OutputStream os = socket.getOutputStream();
            // ObjectOutputStream oos = new ObjectOutputStream(os);
            // oos.writeObject(add);
            // oos.flush();


//            sendRequest("echo", new Object[]{"Hello"});
            System.out.print("Testing... ");
//            System.out.println(add(2, 4));
            if (add(2, 4) == 6)
                System.out.print(".");
            else
                System.out.print("X");

            try {
                divide(1, 0);
                System.out.print("X");
            } catch (ArithmeticException x) {
                System.out.print(".");
            }

            if (echo("Hello").equals("You said Hello!"))
                System.out.print(".");
            else
                System.out.print("X");

            System.out.println(" Finished");


            in.close();
            out.close();
            socket.close();
            System.out.println("Disconnected from server.");


    }



        // All of the code below this line must be uncommented
        // to be successfully graded.

}