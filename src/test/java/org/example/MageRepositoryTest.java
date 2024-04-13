package org.example;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public class MageRepositoryTest {
    private MageRepository mageRepository;

    @BeforeEach
    public void setUp() {
        mageRepository = new MageRepository();
    }

    @Test
    public void testFindExistingMage() {
        String name = "Gandalf";
        int level = 50;
        Mage mage = new Mage(name, level);
        mageRepository.save(mage);

        Optional<Mage> foundMage = mageRepository.find(name);

        assertTrue(foundMage.isPresent());
        assertEquals(mage, foundMage.get());
    }

    @Test
    public void testFindNonExistingMage() {
        String name = "Merlin";

        Optional<Mage> foundMage = mageRepository.find(name);

        assertTrue(foundMage.isEmpty());
    }

    @Test
    public void testDeleteExistingMage() {
        String name = "Gandalf";
        int level = 50;
        Mage mage = new Mage(name, level);
        mageRepository.save(mage);

        Optional<Mage> deletedMage = mageRepository.delete(name);

        assertTrue(deletedMage.isPresent());
        assertEquals(mage, deletedMage.get());
        assertTrue(mageRepository.find(name).isEmpty());
    }

    @Test
    public void testDeleteNonExistingMage() {
        String name = "Merlin";

        assertThrows(IllegalArgumentException.class, () -> mageRepository.delete(name));
    }

    @Test
    public void testSaveNewMage() {
        String name = "Gandalf";
        int level = 50;
        Mage mage = new Mage(name, level);

        String result = mageRepository.save(mage);

        assertEquals("done", result);
        Optional<Mage> foundMage = mageRepository.find(name);
        assertTrue(foundMage.isPresent());
        assertEquals(mage, foundMage.get());
    }

    @Test
    public void testSaveExistingMage() {
        String name = "Gandalf";
        int level = 50;
        Mage mage = new Mage(name, level);
        mageRepository.save(mage);

        assertThrows(IllegalArgumentException.class, () -> mageRepository.save(new Mage(name, 60)));
    }
}
