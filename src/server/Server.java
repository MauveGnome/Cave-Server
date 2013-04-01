package server;

/**
 * Title:        Server class
 * Description:  Server that sends a single message to a client
 *               and then terminates.
 *
 * This class does not have any input streams set up yet, because at
 * the moment it only sends a message to the client.
 *
 * @author Sam Beed B0632953
 */

import java.io.*;
import java.net.*;
import java.util.*;

public class Server extends Thread
{
    private ServerSocket serverSocket;
    private Socket socket;
    
    // streams for communication to client
    private InputStream is;
    private OutputStream os;
    private ObjectOutputStream objOS;
    private PrintWriter toClient;
    private BufferedReader fromClient;
    
    private static final int PORT_NUMBER = 3000;
    private HashMap<String, HashMap<String, Integer>> votes;
    private boolean keepRunning;

    /*
     * Constructor
     */
    public Server()
    {
        keepRunning = true;
        System.out.println("...Server starting up");

        try
        {
            // create a ServerSocket object to listen on the port
            serverSocket = new ServerSocket(PORT_NUMBER);
            
            loadVotes();
        }
        catch (FileNotFoundException ex) {
            System.out.println("File not found: " + ex.getMessage());
        }
        catch (IOException ex)
        {
            System.out.println("IO Error: " + ex.getMessage());
        }
        catch (ClassNotFoundException ex) {
            System.out.println("Class not found :" + ex.getMessage());
        }
    }

    /**
     * This method is used to start the server and perform a single
     * interaction with a single client using the processHello method.
     */
    @Override
    public void run()
    {
        try
        {
            while (keepRunning) {
                // wait for a connection request
                socket = serverSocket.accept();
                System.out.println("...Connection established");

                openStreams();

                processClientRequest();

                closeStreams();
                socket.close();
            }
        }
        catch (IOException e)
        {
            System.out.println("Trouble with a connection " + e);
        }
    }

    /**
     * 
     */
    private void processClientRequest() throws IOException
    {
        System.out.println("Server is processing client request");
        
        while (keepRunning) {
            String request = fromClient.readLine();
            
            if (request.equalsIgnoreCase("HELLO")) {
                toClient.println("Hello client, how are you?");
            }
            
            if (request.equalsIgnoreCase("GET_VOTES")) {
                //toClient.println("You requested votes");
                sendVotes();
            }
            if (request.startsWith("SUBMIT")) {
                toClient.println("You tried to submit a vote");
                
                saveVotes();
            }
            
            if (request.equalsIgnoreCase("QUIT")) {
                quit();
            }
        }
        
        System.out.println("Done processing client request");
    }

    /**
     * Set up streams for communicating with the client
     */
    private void openStreams() throws IOException
    {
        final boolean AUTO_FLUSH = true;
        is = socket.getInputStream();
        os = socket.getOutputStream();
        objOS = new ObjectOutputStream(os);
        toClient = new PrintWriter(os, AUTO_FLUSH);
        fromClient = new BufferedReader(new InputStreamReader(is));
        
        System.out.println("...Streams set up");
    }

    /**
     * close output streams to client
     */
    private void closeStreams() throws IOException
    {
        toClient.close();
        fromClient.close();
        os.close();
        is.close();
        objOS.close();
        System.out.println("...Streams closed down");
    }
    
    /**
     * 
     */
    private void saveVotes() throws FileNotFoundException, IOException {
        //Deletes file before saving new data.
        File f = new File("votedatabase.sav");
        f.delete();
        
        FileOutputStream fOut = new FileOutputStream(f);
        ObjectOutputStream objOut = new ObjectOutputStream(fOut);
                
        objOut.writeObject(votes);
    }
    
    /**
     * 
     */
    private void loadVotes() throws FileNotFoundException, IOException, ClassNotFoundException {
        File f = new File("votedatabase.sav");
        FileInputStream fIn = new FileInputStream(f);
        ObjectInputStream objIn = new ObjectInputStream(fIn);
        
        votes = (HashMap<String, HashMap<String, Integer>>) objIn.readObject();
    }
    
    /**
     * 
     */
    private void sendVotes() {
        try {
            objOS.writeObject(votes);
        }
        catch (IOException ex) {
            System.out.println("IO Exception: " + ex.getMessage());
        }
    }
    
    /**
     * 
     */
    private void submitAnswer(String question, String answer) {
        HashMap<String, Integer> answerMap = votes.get(question);
        answerMap.put(answer, answerMap.get(answer) + 1);
        votes.put(question, answerMap);
    }
    
    /**
     * Returns the vote collection.
     */
    public HashMap<String, HashMap<String, Integer>> getVotes() {
        return votes;
    }
    
     /**
     * Ends server thread.
     */
    public void quit() {
        keepRunning = false;
    }   
    
    /**
     * Inner class to define a client thread.
     */
    private class ClientThread extends Thread {
        
    }
}

