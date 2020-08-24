import java.io.*;
import java.util.*;

public class Restaurant {

  public static void main(String args[]) throws IOException {

        FileReader fRead= new FileReader(args[0]);
    BufferedReader bRead= new BufferedReader(fRead);
    int diners = Integer.parseInt(bRead.readLine().trim());    //number of eaters
    int tables = Integer.parseInt(bRead.readLine().trim());    //number of tables
    int cooks = Integer.parseInt(bRead.readLine().trim());     // number of cooks

    if(diners==0 || tables==0 || cooks==0)
    {
      System.out.println("Invalid Input");
    }

    Timingscs clock = new Timingscs();
    long startTime=System.currentTimeMillis();
    Kitchen kitchen = new Kitchen(clock, diners, cooks);  
    Schedule schedule = new Schedule(tables, kitchen);
    int id = 0;
        //long starttime= System.currentTimeMillis();
       

       System.out.println("\nInput:");
       System.out.println(diners);
       System.out.println(tables);
       System.out.println(cooks);
       
      try{

        
          for(int i=1;i<=diners;i++)
          {
            String line=bRead.readLine();
            System.out.println(line);
          if(line!=null)
            {
            String[] ord=line.split(",");
            int[] orderlist=new int[ord.length];
            for(int j=0;j<ord.length;j++)
              {   
                   orderlist[j]=Integer.parseInt(ord[j]);
              } 
             int arrivaltime=orderlist[0];
             orderlist = Arrays.copyOfRange(orderlist, 1, ord.length); 
             Order order = new Order(id, orderlist);
             schedule.add(order, arrivaltime);
             id=id+1;
             }
          }
          bRead.close();
        }
        catch (IOException e)
        {
          e.printStackTrace();
        }
        System.out.println("\n\nOutput:");
    
    clock.start(schedule);
    while(schedule.dinersDined() != diners);
    
    System.out.println("Last diner has left and the restaurant is to be closed.");  
    clock.stop();
    System.exit(-1);
    
  }
}


