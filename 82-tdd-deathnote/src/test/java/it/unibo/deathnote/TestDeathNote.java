package it.unibo.deathnote;

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

    @BeforeEach
    void setUp() {
        this.deathNote = new DeathNoteImplementation();
    }

    @Test
    void testGetRule() {
        assertNotNull(this.deathNote);
        try {
            this.deathNote.getRule(0);
            Assertions.fail("Return the String in position 0 or less of deathNote.RULES was possible, but should have thrown an exception");
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getMessage());
            assertFalse(e.getMessage().isBlank());
            assertTrue(e.getMessage().length() >= ACCEPTABLE_MESSAGE_LENGTH);
        }

        try {
            this.deathNote.getRule(this.deathNote.RULES.size() + 1);
            Assertions.fail("Return the String in position RULES.size() + 1 of deathNote.RULES was possible, but should have thrown an exception");
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

}