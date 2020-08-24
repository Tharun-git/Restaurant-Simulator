
Compile: javac Restaurant.java
	javac Machine.java
	javac Order.java
	javac Orders.java
	javac Timingscs.java
	javac Table.java
	javac Tables.java
	javac Kitchen.java
	javac Schedule.java

Run: java Restaurant.java input-1.txt    
     java Restaurant.java input-2.txt 
     java Restaurant.java input-3.txt 
     java Restaurant.java input-4.txt 
     java Restaurant.java input-5.txt 

     



A Diner waits for a table from Tables Queue, puts his order in Orders Queue, waits for his order being finished from ServeOrders Queue, eats and leaves.

A cook gets a order from Orders Queue, processes the  order and puts the finished order back to ServeOrders Queue


The Table class serves as a diner thread. Each diner thread waits until the global clock matches their arrival time before adding themselves to the table queue. If there are any waiting cooks, they're popped of the queue and seated. Seated diners then wait for their order to be filled by periodically checking if they have food. If they do, they begin the process of eating which is another process that periodically checks the global clock to see if 30 minutes have passed. If 30 minutes have passed, the diner then leaves.

The Kitchen class serves as a cook thread. Each cook thread waits for a diner to come across the table queue. Once one becomes available, they seat the diner, take their order, and begin preparation. Preparation begins by polling the grill for as many burgers as need to be cooked. Then, the same is done for the fryer as well as the coke, in that order. After completing the order, the cook indicates to the diner that their food is ready before taking another order.

Finally, the Resources class is responsible for storing shared information between the cooks and diners like the global clock, the total table count, the taken table count, the total served count, and the total number of diners, and a list of active diners.


