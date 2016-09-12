/**
 * Created by pawan on 2/5/2016.
 */
public class Ticket {
    public String name,source,destination,triptype,destinationdate,destinationtime,returndate,returntime;

    public Ticket(String name, String source, String destination, String triptype, String destinationdate, String destinationtime, String returndate, String returntime) {
        this.name = name;
        this.source = source;
        this.destination = destination;
        this.triptype = triptype;
        this.destinationdate = destinationdate;
        this.destinationtime = destinationtime;
        this.returndate = returndate;
        this.returntime = returntime;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "name='" + name + '\'' +
                ", source='" + source + '\'' +
                ", destination='" + destination + '\'' +
                ", triptype='" + triptype + '\'' +
                ", destinationdate='" + destinationdate + '\'' +
                ", destinationtime='" + destinationtime + '\'' +
                ", returndate='" + returndate + '\'' +
                ", returntime='" + returntime + '\'' +
                '}';
    }
}
