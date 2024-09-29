import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main (String[] args) throws FileNotFoundException
    {
        LogEntryProcess<String> queue = new LogEntryProcess<>();
        LogEntryProcess<String> stack = new LogEntryProcess<>();

        Scanner sc = new Scanner(new File("log-data.csv"));
        sc.useDelimiter("\n");
        sc.next();
        while(sc.hasNext())
        {
            queue.enqueue(sc.next());
        }
        sc.close();


        while(!queue.isEmpty())
        {
            if (queue.logLevel(queue.getItem()).equals("ERROR"))
            {
                stack.push(queue.getItem());
            }
            queue.dequeue();
        }

        queue.printLabels();

        StdOut.println("Last 100 errors:");
        for(int i = 0; i < 100; i++)
        {
            StdOut.println(stack.pop());
        }
    }
}