package com.esgi.al.cleancode.project.Super_Cards.domain;

public class Round {
    public RoundId roundId;
    public SessionId sessionId;
    public PlayerId firstPlayerId;
    public PlayerId secondPlayerId;
    public HeroId firstPlayerHeroId;
    public HeroId secondPlayerHeroId;
    public String winner;
    public int firstPlayerHeroNbHit;
    public int secondPlayerHeroNbHit;

    private Round(RoundId roundId, SessionId sessionId, PlayerId firstPlayerId, PlayerId secondPlayerId,
                 HeroId firstPlayerHeroId, HeroId secondPlayerHeroId, String winner, int firstPlayerHeroNbHit,
                  int secondPlayerHeroNbHit) {
        this.roundId = roundId;
        this.sessionId = sessionId;
        this.firstPlayerId = firstPlayerId;
        this.secondPlayerId = secondPlayerId;
        this.firstPlayerHeroId = firstPlayerHeroId;
        this.secondPlayerHeroId = secondPlayerHeroId;
        this.winner = winner;
        this.firstPlayerHeroNbHit = firstPlayerHeroNbHit;
        this.secondPlayerHeroNbHit = secondPlayerHeroNbHit;
    }

    public static Round of(RoundId roundId, SessionId sessionId, PlayerId firstPlayerId, PlayerId secondPlayerId,
                           HeroId firstPlayerHeroId, HeroId secondPlayerHeroId, String winner, int firstPlayerHeroNbHit,
                           int secondPlayerHeroNbHit){
        return new Round(roundId, sessionId, firstPlayerId, secondPlayerId, firstPlayerHeroId, secondPlayerHeroId,
                winner, firstPlayerHeroNbHit, secondPlayerHeroNbHit);
    }

    @Override
    public String toString() {
        return "Round{" +
                "roundId=" + roundId.value() +
                ", sessionId=" + sessionId.value() +
                ", firstPlayerId=" + firstPlayerId.value() +
                ", secondPlayerId=" + secondPlayerId.value() +
                ", firstPlayerHeroId=" + firstPlayerHeroId.value() +
                ", secondPlayerHeroId=" + secondPlayerHeroId.value() +
                ", winner='" + winner + '\'' +
                ", firstPlayerHeroNbHit=" + firstPlayerHeroNbHit +
                ", secondPlayerHeroNbHit=" + secondPlayerHeroNbHit +
                '}';
    }
}
