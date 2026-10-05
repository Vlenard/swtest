package com.anonymous;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TableTest {

    private Table standardTable;
    private Table adjustableTable;

    @BeforeEach
    void setUp() {
        // Alapértelmezett nem állítható asztal: 80 cm széles, 120 cm hosszú, 75 cm magas
        standardTable = new Table(80, 120, 75);
        // Állítható asztal: 80 cm széles, 160 cm hosszú, 75 cm alapmagasság, 85 cm aktuális magasság
        adjustableTable = new Table(80, 160, 75, 85);
    }

    @Test
    @DisplayName("3 paraméteres konstruktor helyesen állítja be az attribútumokat")
    void testThreeArgConstructor() {
        assertAll(
            () -> assertEquals(80, standardTable.getWidth()),
            () -> assertEquals(120, standardTable.getLength()),
            () -> assertEquals(75, standardTable.getHeight()),
            () -> assertEquals(75, standardTable.getCurrentHeight()),
            () -> assertFalse(standardTable.isAdjustable())
        );
    }

    @Test
    @DisplayName("4 paraméteres konstruktor helyesen állítja be az attribútumokat és az isAdjustable értéket")
    void testFourArgConstructor() {
        assertAll(
            () -> assertEquals(80, adjustableTable.getWidth()),
            () -> assertEquals(160, adjustableTable.getLength()),
            () -> assertEquals(75, adjustableTable.getHeight()),
            () -> assertEquals(85, adjustableTable.getCurrentHeight()),
            () -> assertTrue(adjustableTable.isAdjustable())
        );
    }

    @Test
    @DisplayName("area() metódus a szélesség és hossz szorzatát adja vissza")
    void testArea() {
        assertEquals(80 * 120, standardTable.area());
        assertEquals(80 * 160, adjustableTable.area());
    }

    @ParameterizedTest
    @CsvSource({
        "100, 200, 20000",
        "50, 50, 2500",
        "80, 120, 9600"
    })
    @DisplayName("area() paraméterezett tesztje különböző méretekkel")
    void testAreaParameterized(int width, int length, int expectedArea) {
        Table table = new Table(width, length, 75);
        assertEquals(expectedArea, table.area());
    }

    @Test
    @DisplayName("getPerimeter() az asztal kerületét adja vissza: 2 * (szélesség + hossz)")
    void testGetPerimeter() {
        assertEquals(2 * (80 + 120), standardTable.getPerimeter());
        assertEquals(2 * (80 + 160), adjustableTable.getPerimeter());
    }

    @Test
    @DisplayName("getCapacity() 60 cm-enként számol egy személyt a kerület alapján")
    void testGetCapacity() {
        // Kerület: 2 * (80 + 120) = 400 cm -> 400 / 60 = 6 személy
        assertEquals(6, standardTable.getCapacity());

        // Kerület: 2 * (80 + 160) = 480 cm -> 480 / 60 = 8 személy
        assertEquals(8, adjustableTable.getCapacity());
    }

    @ParameterizedTest
    @CsvSource({
        "60, 120, 6",    // kerület: 360 cm -> 6 személy
        "90, 180, 9",    // kerület: 540 cm -> 9 személy
        "60, 60, 4",     // kerület: 240 cm -> 4 személy
        "50, 50, 3"      // kerület: 200 cm -> 3 személy
    })
    @DisplayName("getCapacity() paraméterezett tesztje")
    void testGetCapacityParameterized(int width, int length, int expectedCapacity) {
        Table table = new Table(width, length, 75);
        assertEquals(expectedCapacity, table.getCapacity());
    }

    @Test
    @DisplayName("setHeight() érvényes magasságra állítja az állítható asztalt a [0, 200] intervallumban")
    void testSetHeightValid() {
        adjustableTable.setHeight(105);
        assertEquals(105, adjustableTable.getCurrentHeight());
        assertEquals(105, adjustableTable.getHeight());
    }

    @Test
    @DisplayName("setHeight() határértékek tesztelése (0 és 200)")
    void testSetHeightBoundaryValues() {
        adjustableTable.setHeight(0);
        assertEquals(0, adjustableTable.getCurrentHeight());

        adjustableTable.setHeight(200);
        assertEquals(200, adjustableTable.getCurrentHeight());
    }

    @Test
    @DisplayName("setHeight() kivételt dob, ha az asztal nem állítható (isAdjustable == false)")
    void testSetHeightThrowsWhenNotAdjustable() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> standardTable.setHeight(100)
        );
        assertTrue(exception.getMessage().contains("isAdjustable == true"));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -50, 201, 300})
    @DisplayName("setHeight() kivételt dob, ha az új magasság a [0, 200] intervallumon kívül esik")
    void testSetHeightThrowsWhenOutOfRange(int invalidHeight) {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> adjustableTable.setHeight(invalidHeight)
        );
        assertTrue(exception.getMessage().contains("[0, 200]"));
    }

    @Test
    @DisplayName("repaint() módosítja az asztal színét")
    void testRepaint() {
        standardTable.repaint("barna");
        assertEquals("barna", standardTable.getColor());

        standardTable.repaint("fehér");
        assertEquals("fehér", standardTable.getColor());
    }

    @ParameterizedTest
    @CsvSource({
        "0, false",
        "1, false",
        "2, false",
        "3, true",
        "4, true",
        "6, true"
    })
    @DisplayName("isStable() igaz, ha legalább 3 lába van")
    void testIsStable(int legs, boolean expectedStable) {
        Table table = new Table(80, 120, 75);
        table.setNumberOfLegs(legs);
        assertEquals(expectedStable, table.isStable());
    }

    @ParameterizedTest
    @CsvSource({
        "true, 4, true",    // állítható és 4 lábú -> foldable
        "true, 5, true",    // állítható és >4 lábú -> foldable
        "true, 3, false",   // állítható de <4 lábú -> nem foldable
        "false, 4, false",  // nem állítható de 4 lábú -> nem foldable
        "false, 2, false"   // nem állítható és <4 lábú -> nem foldable
    })
    @DisplayName("isFoldable() igaz, ha isAdjustable == true és numberOfLegs >= 4")
    void testIsFoldable(boolean adjustable, int legs, boolean expectedFoldable) {
        Table table = new Table(80, 120, 75, 75, adjustable);
        table.setNumberOfLegs(legs);
        assertEquals(expectedFoldable, table.isFoldable());
    }
}
