package com.esgi.al.cleancode.project.Super_Cards.domain;

import java.util.ArrayList;
import java.util.Date;

public class Session {
    private SessionId sessionId;
    private ArrayList<PlayerId> playersId;
    private Date timestamp;

    private Session(SessionId sessionId, ArrayList<PlayerId> playersId) {
        this.sessionId = sessionId;
        this.playersId = playersId;
        this.timestamp = new Date();//TODO Verify if gives the current timestamp
    }

    public static Session of(SessionId sessionId, ArrayList<PlayerId> playersId){
        return new Session(sessionId, playersId);
    }

    @Override
    public String toString() {
        return "Session{" +
                "sessionId=" + sessionId.value() +
                ", playersId=" + playersId +
                '}';
    }
}
