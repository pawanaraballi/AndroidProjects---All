package ticketreservation.hw_2.group5.com.ticketreservation;


import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by pawan on 2/5/2016.
 */
public class Ticket implements Parcelable {
    public String name,source,destination,triptype,destinationdate,destinationtime,returndate,returntime;

    public Ticket(String name, String source, String destination, String triptype, String destinationdate, String destinationtime) {
        this.name = name;
        this.source = source;
        this.destination = destination;
        this.triptype = triptype;
        this.destinationdate = destinationdate;
        this.destinationtime = destinationtime;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReturntime() {
        return returntime;
    }

    public void setReturntime(String returntime) {
        this.returntime = returntime;
    }

    public String getReturndate() {
        return returndate;
    }

    public void setReturndate(String returndate) {
        this.returndate = returndate;
    }

    public String getDestinationtime() {
        return destinationtime;
    }

    public void setDestinationtime(String destinationtime) {
        this.destinationtime = destinationtime;
    }

    public String getDestinationdate() {
        return destinationdate;
    }

    public void setDestinationdate(String destinationdate) {
        this.destinationdate = destinationdate;
    }

    public String getTriptype() {
        return triptype;
    }

    public void setTriptype(String triptype) {
        this.triptype = triptype;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Ticket() {
    }

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

    protected Ticket(Parcel in) {
        name = in.readString();
        source = in.readString();
        destination = in.readString();
        triptype = in.readString();
        destinationdate = in.readString();
        destinationtime = in.readString();
        returndate = in.readString();
        returntime = in.readString();
    }

    public static final Creator<Ticket> CREATOR = new Creator<Ticket>() {
        @Override
        public Ticket createFromParcel(Parcel in) {
            return new Ticket(in);
        }

        @Override
        public Ticket[] newArray(int size) {
            return new Ticket[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(source);
        dest.writeString(destination);
        dest.writeString(triptype);
        dest.writeString(destinationdate);
        dest.writeString(destinationtime);
        dest.writeString(returndate);
        dest.writeString(returntime);
    }


}
