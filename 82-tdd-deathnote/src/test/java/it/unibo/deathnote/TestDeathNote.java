package it.unibo.deathnote;

import org.junit.jupiter.api.BeforeEach;

import it.unibo.deathnote.api.DeathNote;
import it.unibo.deathnote.impl.DeathNoteImplementation;

class TestDeathNote {

    private DeathNote deathNote;

    @BeforeEach
    void setUp() {
        this.deathNote = new DeathNoteImplementation();
    }

}