package org.example;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.Mockito.*;

import java.util.Optional;

public class MageControllerTest {
    private MageRepository mockRepository;
    private MageController mageController;

    @BeforeEach
    public void setUp() {
        mockRepository = mock(MageRepository.class);
        mageController = new MageController(mockRepository);
    }

    @Test
    public void testFindExistingMage() {
        String name = "Gandalf";
        int level = 50;
        Mage foundMage = new Mage(name, level);
        when(mockRepository.find(name)).thenReturn(Optional.of(foundMage));

        String result = mageController.find(name);

        assertEquals(foundMage.toString(), result);
    }

    @Test
    public void testFindNonExistingMage() {
        String name = "Merlin";
        when(mockRepository.find(name)).thenReturn(Optional.empty());

        String result = mageController.find(name);

        assertEquals("not found", result);
    }

    @Test
    public void testDeleteExistingMage() {
        String name = "Gandalf";
        int level = 50;
        Mage foundMage = new Mage(name, level);
        when(mockRepository.find(name)).thenReturn(Optional.of(foundMage));

        String result = mageController.delete(name);

        assertEquals("done", result);
        Mockito.verify(mockRepository, Mockito.times(1)).delete(name);
    }

    @Test
    public void testDeleteNonExistingMage() {
        String name = "Merlin";
        when(mockRepository.find(name)).thenReturn(Optional.empty());

        String result = mageController.delete(name);

        assertEquals("not found", result);
        Mockito.verify(mockRepository, Mockito.never()).delete(name);
    }

    @Test
    public void testSaveNewMage() {
        String name = "Gandalf";
        int level = 50;
        when(mockRepository.save(new Mage(name, level))).thenReturn("done");

        String result = mageController.save(name, level);

        assertEquals("done", result);
    }

    @Test
    public void testSaveExistingMage() {
        Mage mage = new Mage("Gandalf" , 50);
        MageRepository repositoryMock = mock(MageRepository.class);

        when(repositoryMock.find("Gandalf")).thenReturn(Optional.of(mage));

        MageController controller = new MageController(repositoryMock);

        String result = controller.save("Gandalf", 50);

        assertEquals("bad request", result);
    }
}

