package hu.bme.mit.spaceship;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class GT4500Test {

    private GT4500 ship;
    private TorpedoStore primary;
    private TorpedoStore secondary;

    @BeforeEach
    public void init() {
        primary = mock(TorpedoStore.class);
        secondary = mock(TorpedoStore.class);
    }

    @Test
    public void fireTorpedo_Single_Success() {
        // Arrange
        this.ship = new GT4500(primary, secondary, false);

        when(primary.fire(1)).thenReturn(true);
        when(primary.isEmpty()).thenReturn(false);

        when(secondary.fire(1)).thenReturn(false);

        // Act
        boolean result = ship.fireTorpedo(FiringMode.SINGLE);

        // Assert
        verify(primary, times(1)).isEmpty();
        verify(secondary, never()).isEmpty();
        verify(primary, times(1)).fire(1);
        assertEquals(true, result);
    }

    @Test
    public void fireTorpedo_All_Success() {
        // Arrange
        this.ship = new GT4500(primary, secondary, false);

        when(primary.fire(1)).thenReturn(true);
        when(primary.isEmpty()).thenReturn(false);

        when(secondary.fire(1)).thenReturn(true);
        when(secondary.isEmpty()).thenReturn(false);

        // Act
        boolean result = ship.fireTorpedo(FiringMode.ALL);

        // Assert
        verify(primary, times(1)).isEmpty();
        verify(secondary, times(1)).isEmpty();
        verify(primary, times(1)).fire(1);
        verify(secondary, times(1)).fire(1);
        assertEquals(true, result);
    }

    @Test
    public void fireTorpedo_Secondary_Success() {
        // Arrange
        this.ship = new GT4500(primary, secondary, true);

        when(primary.isEmpty()).thenReturn(true);
        when(secondary.isEmpty()).thenReturn(false);
        when(secondary.fire(1)).thenReturn(true);

        // Act
        boolean result = ship.fireTorpedo(FiringMode.SINGLE);

        // Assert
        verify(primary, never()).isEmpty();
        verify(secondary, times(1)).isEmpty();
        verify(primary, never()).fire(1);
        verify(secondary, times(1)).fire(1);
        assertEquals(true, result);

    }

    @Test
    public void fireTorepdo_Primary_Success_Again() {
        // Arrange
        this.ship = new GT4500(primary, secondary, true);

        when(primary.isEmpty()).thenReturn(false);
        when(secondary.isEmpty()).thenReturn(true);
        when(primary.fire(1)).thenReturn(true);

        // Act
        boolean result = ship.fireTorpedo(FiringMode.SINGLE);

        // Assert
        verify(primary, times(1)).isEmpty();
        verify(secondary, times(1)).isEmpty();
        verify(primary, times(1)).fire(1);
        verify(secondary, never()).fire(1);
        assertEquals(true, result);

    }

    @Test
    public void fireTorpedo_Secondary_Primary_Fail() {
        // Arrange
        this.ship = new GT4500(primary, secondary, true);

        when(primary.isEmpty()).thenReturn(true);
        when(secondary.isEmpty()).thenReturn(true);

        // Act
        boolean result = ship.fireTorpedo(FiringMode.SINGLE);

        // Assert
        verify(primary, times(1)).isEmpty();
        verify(secondary, times(1)).isEmpty();
        verify(primary, never()).fire(1);
        verify(secondary, never()).fire(1);
        assertEquals(false, result);

    }
    @Test
    public void fireTorpedo_Primary_Success() {
        // Arrange
        this.ship = new GT4500(primary, secondary, false);

        when(primary.isEmpty()).thenReturn(false);
        when(primary.fire(1)).thenReturn(true);

        // Act
        boolean result = ship.fireTorpedo(FiringMode.SINGLE);

        // Assert
        verify(primary, times(1)).isEmpty();
        verify(secondary,never()).isEmpty();
        verify(primary, times(1)).fire(1);
        verify(secondary, never()).fire(1);
        assertEquals(true, result);
    }

    @Test
    public void fireTorpedo_Secondary_Success_Again() {
        // Arrange
        this.ship = new GT4500(primary, secondary, false);

        when(primary.isEmpty()).thenReturn(true);
        when(secondary.isEmpty()).thenReturn(false);
        when(secondary.fire(1)).thenReturn(true);

        // Act
        boolean result = ship.fireTorpedo(FiringMode.SINGLE);

        // Assert
        verify(primary, times(1)).isEmpty();
        verify(secondary,times(1)).isEmpty();
        verify(primary, never()).fire(1);
        verify(secondary, times(1)).fire(1);
        assertEquals(true, result);
    }

    @Test
    public void fireTorpedo_Primary_Secondary_Fail() {
        // Arrange
        this.ship = new GT4500(primary, secondary, false);

        when(primary.isEmpty()).thenReturn(true);
        when(secondary.isEmpty()).thenReturn(true);

        // Act
        boolean result = ship.fireTorpedo(FiringMode.SINGLE);

        // Assert
        verify(primary, times(1)).isEmpty();
        verify(secondary,times(1)).isEmpty();
        verify(primary, never()).fire(1);
        verify(secondary, never()).fire(1);
        assertEquals(false, result);
    }

    @Test
    public void fireTorpedo_All_Fail() {
        // Arrange
        this.ship = new GT4500(primary, secondary, false);

        when(primary.isEmpty()).thenReturn(true);
        when(secondary.isEmpty()).thenReturn(true);

        // Act
        boolean result = ship.fireTorpedo(FiringMode.SINGLE);

        // Assert
        verify(primary, times(1)).isEmpty();
        verify(secondary,times(1)).isEmpty();
        verify(primary, never()).fire(1);
        verify(secondary, never()).fire(1);
        assertEquals(false, result);
    }

}
