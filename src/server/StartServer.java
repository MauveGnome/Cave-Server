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
      server1.start();
      
         /**
          * Sets up initial database. 
          */
//        HashMap<String, Map<String, Integer>> votes = new
//                HashMap<String, Map<String, Integer>>();
//        HashMap<String, Integer> answersA = new HashMap<String,Integer>();
//        answersA.put("Answer A", 12);
//        answersA.put("Answer B", 6);
//        answersA.put("Answer C", 32);
//        votes.put("Question A", answersA);
//        HashMap<String, Integer> answersB = new HashMap<String,Integer>();
//        answersB.put("Answer A", 12);
//        answersB.put("Answer B", 6);
//        answersB.put("Answer C", 32);
//        answersB.put("Answer D", 2);
//        votes.put("Question B", answersB);
//        HashMap<String, Integer> answersC = new HashMap<String,Integer>();
//        answersC.put("Answer A", 12);
//        answersC.put("Answer B", 6);
//        votes.put("Question C", answersC);
//        
//        File f = new File("votedatabase.sav");
//        f.delete();
//        
//        FileOutputStream fOut = new FileOutputStream(f);
//        ObjectOutputStream objOut = new ObjectOutputStream(fOut);
//        
//        System.out.println(votes.toString());
//        objOut.writeObject(votes);
   }
}
