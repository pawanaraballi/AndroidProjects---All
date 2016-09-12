package ticketreservation.hw_2.group5.com.ticketreservation;
import android.app.Application;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * Created by pawan on 2/8/2016.
 */
public class LinkedListClass extends Ticket implements Serializable {

    static LinkedList<Ticket> ticketLinkedList = new LinkedList<Ticket>();

    public static LinkedList<Ticket> getTicketLinkedList() {
        return ticketLinkedList;
    }
}
