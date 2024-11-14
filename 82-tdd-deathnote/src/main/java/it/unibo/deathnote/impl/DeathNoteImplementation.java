package it.unibo.deathnote.impl;

import java.util.HashMap;
import java.util.Map;

import it.unibo.deathnote.api.DeathNote;

public class DeathNoteImplementation implements DeathNote {

    private Map<String, PairCauseDetails> map = new HashMap<>();
    private String lastNameWrote;

    private static final String CAUSE_OF_DEATH = "hearth attack";

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
    }

    @Override
    public boolean writeDeathCause(String cause) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'writeDeathCause'");
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isNameWritten'");
    }
    
}

