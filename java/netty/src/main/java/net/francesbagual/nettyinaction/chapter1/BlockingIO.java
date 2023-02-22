package net.francesbagual.nettyinaction.chapter1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CompletableFuture;

/**
 * Server using java default socket API. It is a blocking API and the code below creates one thread per client.
 * It has limitation as most threads spend their time doing nothing but waiting for IO.
 */
public class BlockingIO {
    private void handleConnection(Socket clientSocket)  {
        try{
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            String request, response;
            while((request = in.readLine()) != null) {
                System.out.println("Request: " + request);
                if("Done".equals(request)) {
                    break;
                }
                response = processRequest(request);
                System.out.println("Response: " + request);
                out.println(response);
            }

        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            try{
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    public void startServer(int port)throws IOException{
        try(ServerSocket serverSocket = new ServerSocket(port)){
            while(true){
                Socket clientSocket = serverSocket.accept();

                CompletableFuture.supplyAsync(() -> {
                    handleConnection(clientSocket);
                    return null;
                });
            }
        }
    }
    public static void main(String[] args) throws IOException {
        new BlockingIO().startServer(8080);
    }

    // echo service
    private String processRequest(String request) {
        return request;
    }
}
