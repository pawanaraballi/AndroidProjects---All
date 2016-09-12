package hw09.group5.com.stayintouchfragmentsapp;

import java.io.Serializable;

/**
 * Created by sujit on 4/24/2016.
 */
public class Message implements Serializable {
    String senderName;
    String receiverName;
    String messageText;
    String timeStamp;
    String pushId;
    boolean messageRead;

    @Override
    public String toString() {
        return "Message{" +
                "senderName='" + senderName + '\'' +
                ", receiverName='" + receiverName + '\'' +
                ", messageText='" + messageText + '\'' +
                ", timeStamp='" + timeStamp + '\'' +
                ", messageRead=" + messageRead +
                '}';
    }

    public Message() {
    }

    public Message(String senderName, String receiverName, String messageText, String timeStamp, boolean messageRead) {
        this.senderName = senderName;
        this.receiverName = receiverName;
        this.messageText = messageText;
        this.timeStamp = timeStamp;
        this.messageRead = messageRead;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }

    public boolean isMessageRead() {
        return messageRead;
    }

    public void setMessageRead(boolean messageRead) {
        this.messageRead = messageRead;
    }

    @Override
    public boolean equals(Object obj) {
        Message messageObj = (Message) obj;
        if (this.getPushId().equals(messageObj.getPushId())) {
            return true;
        }
        return false;
    }
}
