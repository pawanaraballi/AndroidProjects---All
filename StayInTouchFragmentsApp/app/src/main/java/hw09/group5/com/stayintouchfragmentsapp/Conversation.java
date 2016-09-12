package hw09.group5.com.stayintouchfragmentsapp;

import android.util.Log;

/**
 * Created by Truong Pham on 4/26/2016.
 */
public class Conversation {
    private String conversationID;
    private String participant1;
    private String participant2;
    private String deletedBy;
    private boolean archieved_by_participant1;
    private boolean archieved_by_participant2;

    public Conversation() {

    }

    public void setConversationID(String conversationID) {
        this.conversationID = conversationID;
    }

    public void setParticipant1(String participant1) {
        this.participant1 = participant1;
    }

    public void setParticipant2(String participant2) {
        this.participant2 = participant2;
    }

    public void setDeletedBy(String deletedBy) {
        this.deletedBy = deletedBy;
    }

    public void setIsArchieved_by_participant1(boolean isArchieved_by_participant1) {
        this.archieved_by_participant1 = isArchieved_by_participant1;
    }

    public void setIsArchieved_by_participant2(boolean isArchieved_by_participant2) {
        this.archieved_by_participant2 = isArchieved_by_participant2;
    }

    public String getConversationID() {

        return conversationID;
    }

    public String getParticipant1() {
        return participant1;
    }

    public String getParticipant2() {
        return participant2;
    }

    public String getDeletedBy() {
        return deletedBy;
    }

    public boolean isArchieved_by_participant1() {
        return archieved_by_participant1;
    }

    public boolean isArchieved_by_participant2() {
        return archieved_by_participant2;
    }

    public Conversation(String conversationID, String participant1, String participant2, String deletedBy, boolean isArchieved_by_participant1, boolean isArchieved_by_participant2) {

        this.conversationID = conversationID;
        this.participant1 = participant1;
        this.participant2 = participant2;
        this.deletedBy = deletedBy;
        this.archieved_by_participant1 = isArchieved_by_participant1;
        this.archieved_by_participant2 = isArchieved_by_participant2;
    }

    @Override
    public boolean equals(Object object) {
        Conversation conversationObj = (Conversation) object;
        //conversation is equal if they have the same participants
        if (this.getParticipant1().equals(conversationObj.getParticipant1())
                && this.getParticipant2().equals(conversationObj.getParticipant2())
                ||
                this.getParticipant1().equals(conversationObj.getParticipant2())
                        && this.getParticipant2().equals(conversationObj.getParticipant1())) {
            Log.d("itcs4180", this.getParticipant1() + this.getParticipant2() +
                    conversationObj.getParticipant1() + conversationObj.getParticipant2());
            return true;
        }
        Log.d("itcs4180", this.getParticipant1() + ", " + this.getParticipant2() +", " +
                conversationObj.getParticipant1() +", " + conversationObj.getParticipant2());
        return false;
    }
}

