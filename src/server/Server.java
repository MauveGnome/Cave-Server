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
    private PrintWriter toClient;
    private BufferedReader fromClient;
    
    // use a high numbered non-dedicated port
    private static final int PORT_NUMBER = 3000;
    private Set<Vote> votes;
    private boolean keepRunning;

    /*
     * Constructor
     */
    public Server()
    {
        //Test vote collection.
        votes = new HashSet<Vote>();
        votes.add(new Vote("Issue 1"));
        votes.add(new Vote("Issue 2"));
        votes.add(new Vote("Issue 3"));
        
        keepRunning = true;
        System.out.println("...Server starting up");

        try
        {
            // create a ServerSocket object to listen on the port
            serverSocket = new ServerSocket(PORT_NUMBER);
        }
        catch (IOException e)
        {
            System.out.println("Trouble with ServerSocket on port " + PORT_NUMBER
                    + ": " + e);
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
                toClient.println("You requested votes");
            }
            
            if (request.equalsIgnoreCase("SUBMIT")) {
                toClient.println("You tried to submit a vote");
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
        System.out.println("...Streams closed down");
    }
    
    /**
     * Ends server thread.
     */
    public void quit() {
        keepRunning = false;
    }
    
    /**
     * Returns the vote collection.
     */
    public Set<Vote> getVotes() {
        return votes;
    }
    
    /**
     * Inner class to define a client thread.
     */
    private class ClientThread extends Thread {
        
    }
}

