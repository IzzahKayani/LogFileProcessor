import org.w3c.dom.Node;
import java.io.*;
import java.util.Scanner;
public class LogEntryProcess<Item>
{
    private Node first;
    private Node last;
    private int infoNum;
    private int errorNum;
    private int warnNum;
    private int memoryNum;


    public LogEntryProcess()
    {

        first = null;
        last = null;
        infoNum = 0;
        errorNum = 0;
        warnNum = 0;
        memoryNum = 0;

    }
    private class Node
    {
        Item item;
        Node next;
    }

    public Item getItem()
    {
        return first.item;
    }

    public boolean isEmpty()
    {
        return first == null;
    }

    public void enqueue(Item item)
    {
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;

        if(isEmpty())
        {first = last; }
        else
        {oldlast.next = last;}
    }

    public void push(Item item)
    {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
    }

    public Item dequeue()
    {
        Item item = first.item;
        first = first.next;

        if(isEmpty())
        {last = null;}

        return item;
    }

    public Item pop()
    {
        Item item = first.item;
        first = first.next;
        return item;
    }

    public String logLevel(String log)
    {
        String[] tokens = log.split(" ");
        String logLevel = tokens[2];

        if(logLevel.equals("INFO"))
        {infoNum++;}
        else if(logLevel.equals("ERROR"))
            {errorNum++;}
            else if(logLevel.equals("WARN"))
                {
                    warnNum++;
                    if(tokens[3].equals("Memory"))
                    {memoryNum++;}
                }
        return logLevel;
    }

    public void printLabels()
    {
        StdOut.println(String.format("\nInfo labels: %s\nWarning labels: %s\nError Labels: %s\nMemory Warnings: %s\n",
                                     infoNum,errorNum, warnNum, memoryNum));
    }
}
