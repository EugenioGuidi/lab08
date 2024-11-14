package it.unibo.deathnote.impl;

import java.util.HashMap;
import java.util.Map;

import it.unibo.deathnote.api.DeathNote;

public class DeathNoteImplementation implements DeathNote {

    private Map<String, PairCauseDetails> mapOfHumanDeath = new HashMap<>();
    private String lastNameWrote = null;
    private long currentTime;

    private static final String CAUSE_OF_DEATH = "hearth attack";
    private static final long CAUSE_TIME = 40;
    private static final long DETAILS_TIME = 6000;

    static class PairCauseDetails {
        private String cause;
        private String details;

        public PairCauseDetails() {
            this.cause = CAUSE_OF_DEATH;
            this.details = "";
        }

        void setCause (String cause) {
            this.cause = cause;
        }

        void setDeatails (String details) {
            this.details = details;
        }

        String getCause () {
            return this.cause;
        }

        String getDetails () {
            return this.details;
        }
    }

    @Override
    public String getRule(int ruleNumber) {
        if(ruleNumber < 1 || ruleNumber > RULES.size()) {
            throw new IllegalArgumentException("Tried to access into a non-existent RULES location");
        }
        return RULES.get(ruleNumber - 1);
    }

    @Override
    public void writeName(String name) {
        if(name == null) {
            throw new NullPointerException("The name can't be null");
        }
        if(!isNameWritten(name)) {
            mapOfHumanDeath.put(name, new PairCauseDetails());
            this.lastNameWrote = name;
            this.currentTime = System.currentTimeMillis();
        }
    }

    @Override
    public boolean writeDeathCause(String cause) {
        if(this.lastNameWrote == null || cause == null) {
            throw new IllegalStateException("cause and name can't be null");
        }
        if(System.currentTimeMillis() - this.currentTime < CAUSE_TIME) {
            PairCauseDetails pairCauseDetails = new PairCauseDetails();
            pairCauseDetails.setCause(cause);
            mapOfHumanDeath.put(this.lastNameWrote, pairCauseDetails);
            this.currentTime = System.currentTimeMillis();
            return true;
        }
        return false;
    }

    @Override
    public boolean writeDetails(String details) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'writeDetails'");
    }

    @Override
    public String getDeathCause(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDeathCause'");
    }

    @Override
    public String getDeathDetails(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDeathDetails'");
    }

    @Override
    public boolean isNameWritten(String name) {
        if(mapOfHumanDeath.containsKey(name)) {
            return true;
        }
        return false;
    }
    
}

