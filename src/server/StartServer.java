package server;

/**
 * Title:        StartServer class
 * Description:  Tests the Server class

 * @author Sam Beed B0632953
 */

public class StartServer
{
   // Create a server and have it greet the client
   public static void main(String[] args)
   {
      Server server1 = new Server();
      server1.run();
   }
}
