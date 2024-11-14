package it.unibo.deathnote;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.deathnote.api.DeathNote;
import it.unibo.deathnote.impl.DeathNoteImplementation;

class TestDeathNote {

    private DeathNote deathNote;

    private static final int ACCEPTABLE_MESSAGE_LENGTH = 10;
    private static final String FIRST_HUMAN = "Mario Rossi";
    private static final String SECOND_HUMAN = "Andrea Bianchi";
    private static final String DEFAULT_CAUSE_OF_DEATH = "heart attack";
    private static final String CAUSE_OF_DEATH = "Karting accident";
    private static final String DETAILS_OF_DEATH = "ran for too long";
    private static final int INVALID_CAUSE_TIME = 100;
    private static final int INVALID_DETAILS_TIME = 6000 + INVALID_CAUSE_TIME;

    @BeforeEach
    void setUp() {
        this.deathNote = new DeathNoteImplementation();
    }

    @Test
    void testGetRule() {
        assertNotNull(this.deathNote);
        try {
            this.deathNote.getRule(0);
            Assertions.fail("Returned the String in position 0 or less of deathNote.RULES was possible, but should have thrown an exception");
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getMessage());
            assertFalse(e.getMessage().isBlank());
            assertTrue(e.getMessage().length() >= ACCEPTABLE_MESSAGE_LENGTH);
        }

        try {
            this.deathNote.getRule(this.deathNote.RULES.size() + 1);
            Assertions.fail("Returned the String in position RULES.size() + 1 of deathNote.RULES was possible, but should have thrown an exception");
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getMessage());
            assertFalse(e.getMessage().isBlank());
            assertTrue(e.getMessage().length() >= ACCEPTABLE_MESSAGE_LENGTH);
        }
    }

    @Test
    void testNoRulesVoidOrNull() {
        assertNotNull(deathNote.RULES);
        for(String rule : this.deathNote.RULES) {
            assertNotNull(rule);
            assertFalse(rule.isBlank());
        }
    }

    @Test
    void testWriteName() {
        assertFalse(this.deathNote.isNameWritten(FIRST_HUMAN));
        this.deathNote.writeName(FIRST_HUMAN);
        assertTrue(this.deathNote.isNameWritten(FIRST_HUMAN));
        assertFalse(this.deathNote.isNameWritten(SECOND_HUMAN));
        assertFalse(this.deathNote.isNameWritten(""));
    }

    @Test
    void testCauseOfDeath() {
        try {
            this.deathNote.writeDeathCause(CAUSE_OF_DEATH);
            Assertions.fail("Wrote the causes of death even if the name of the person is not there, but should have thrown an exception");
        } catch (IllegalStateException e) {
            assertNotNull(e.getMessage());
            assertFalse(e.getMessage().isBlank());
            assertTrue(e.getMessage().length() >= ACCEPTABLE_MESSAGE_LENGTH);
        }

        this.deathNote.writeName(FIRST_HUMAN);
        assertEquals(DEFAULT_CAUSE_OF_DEATH, this.deathNote.getDeathCause(FIRST_HUMAN));

        this.deathNote.writeName(SECOND_HUMAN);
        assertTrue(this.deathNote.writeDeathCause(CAUSE_OF_DEATH));

        assertEquals(CAUSE_OF_DEATH, this.deathNote.getDeathCause(SECOND_HUMAN));

        try {
            Thread.sleep(INVALID_CAUSE_TIME);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        assertFalse(this.deathNote.writeDeathCause(DEFAULT_CAUSE_OF_DEATH));

        assertEquals(this.deathNote.getDeathCause(SECOND_HUMAN), CAUSE_OF_DEATH);
    }

    @Test
    void testDetailsOfDeath() {
        try {
            this.deathNote.writeDetails(CAUSE_OF_DEATH);
            Assertions.fail("Wrote the details of death even if the name of the person is not there, but should have thrown an exception");
        } catch (IllegalStateException e) {
            assertNotNull(e.getMessage());
            assertFalse(e.getMessage().isBlank());
            assertTrue(e.getMessage().length() >= ACCEPTABLE_MESSAGE_LENGTH);
        }
        this.deathNote.writeName(FIRST_HUMAN);
        assertEquals("", this.deathNote.getDeathDetails(FIRST_HUMAN));
        assertTrue(this.deathNote.writeDetails(DETAILS_OF_DEATH));
        assertEquals(DETAILS_OF_DEATH, this.deathNote.getDeathDetails(CAUSE_OF_DEATH));
        this.deathNote.writeName(SECOND_HUMAN);
        try {
            Thread.sleep(INVALID_DETAILS_TIME);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        assertFalse(this.deathNote.writeDetails(DETAILS_OF_DEATH));
    }
}