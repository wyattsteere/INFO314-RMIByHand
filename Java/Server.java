package  org.example;

import java.net.*;

import java.io.*;

public class Server {
    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(10314);
        System.out.println("Server listening on port 10314...");

        while (true) {
            Socket socket = server.accept();
            System.out.println("Client connected: " + socket);
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

            try {
                while (true) {

                    String methodName = (String) in.readObject();
                    Object[] argsArray = (Object[]) in.readObject();
                    System.out.println(methodName);

                    Object result;
                    switch (methodName) {
                        case "add":
                            result = add((int) argsArray[0], (int) argsArray[1]);
                            break;
                        case "divide":
                            result = divide((int) argsArray[0], (int) argsArray[1]);
                            break;
                        case "echo":
                            String message = (String) argsArray[0];
                            result = echo(message);
                            break;
                        default:
                            throw new IllegalArgumentException("Unknown method: " + methodName);
                    }
                    out.writeObject(result);
                    out.flush();
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            } finally {
                in.close();
                out.close();
                socket.close();
                System.out.println("Client disconnected.");
            }
        }
    }

    // Do not modify any code below tihs line
    // --------------------------------------
    public static String echo(String message) {
        System.out.println(message);
        return "You said " + message + "!";
    }
    public static int add(int lhs, int rhs) {
        System.out.println(lhs + rhs);
        return lhs + rhs;
    }
    public static int divide(int num, int denom) {
        System.out.println(num / denom);
        if (denom == 0)
            throw new ArithmeticException();

        return num / denom;
    }
}