package com.anonymous;

/**
 * 2. feladat - Table osztály
 */
public class Table {
    private int width;
    private int length;
    private int height;
    private int currentHeight;
    private boolean isAdjustable;

    // 2. feladat gyakorlás attribútumok
    private String color;
    private int numberOfLegs;

    public Table() {
        this(0, 0, 0);
    }

    /**
     * Konstruktor: isAdjustable és currentHeight opcionális (alapértelmezetten nem állítható,
     * aktuális magassága megegyezik az alapmagassággal).
     */
    public Table(int width, int length, int height) {
        this(width, length, height, height, false, null, 4);
    }

    /**
     * Konstruktor: megadható aktuális magasság (isAdjustable alapértelmezetten true).
     */
    public Table(int width, int length, int height, int currentHeight) {
        this(width, length, height, currentHeight, true, null, 4);
    }

    public Table(int width, int length, int height, int currentHeight, boolean isAdjustable) {
        this(width, length, height, currentHeight, isAdjustable, null, 4);
    }

    public Table(int width, int length, int height, String color, int numberOfLegs) {
        this(width, length, height, height, false, color, numberOfLegs);
    }

    public Table(int width, int length, int height, int currentHeight, boolean isAdjustable, String color, int numberOfLegs) {
        this.width = width;
        this.length = length;
        this.height = height;
        this.currentHeight = currentHeight;
        this.isAdjustable = isAdjustable;
        this.color = color;
        this.numberOfLegs = numberOfLegs;
    }

    // --- Getterek és Setterek ---

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getHeight() {
        return height;
    }

    public int getCurrentHeight() {
        return currentHeight;
    }

    public void setCurrentHeight(int currentHeight) {
        setHeight(currentHeight);
    }

    public boolean isAdjustable() {
        return isAdjustable;
    }

    public boolean getIsAdjustable() {
        return isAdjustable;
    }

    public void setAdjustable(boolean adjustable) {
        this.isAdjustable = adjustable;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getNumberOfLegs() {
        return numberOfLegs;
    }

    public void setNumberOfLegs(int numberOfLegs) {
        this.numberOfLegs = numberOfLegs;
    }

    // --- Funkciók ---

    /**
     * Magasság csak akkor állítható, ha isAdjustable == true,
     * és az új magasság a [0, 200] intervallumban található.
     */
    public void setHeight(int newHeight) {
        if (!this.isAdjustable) {
            throw new IllegalArgumentException("Magasság csak akkor állítható, ha isAdjustable == true");
        }
        if (newHeight < 0 || newHeight > 200) {
            throw new IllegalArgumentException("Az új magasság a [0, 200] intervallumban kell legyen: " + newHeight);
        }
        this.currentHeight = newHeight;
        this.height = newHeight;
    }

    public void setHeight() {
        setHeight(this.height);
    }

    /**
     * Terület: szélesség * hossz
     */
    public int area() {
        return this.width * this.length;
    }

    /**
     * Kerület: 2 * (szélesség + hossz)
     */
    public int getPerimeter() {
        return 2 * (this.width + this.length);
    }

    /**
     * Kapacitás: 60 cm-enként egy személy (kerület alapján)
     */
    public int getCapacity() {
        return getPerimeter() / 60;
    }

    /**
     * Módosítja a színt
     */
    public void repaint(String color) {
        this.color = color;
    }

    /**
     * Igaz, ha az asztalnak legalább 3 lába van
     */
    public boolean isStable() {
        return this.numberOfLegs >= 3;
    }

    /**
     * Igaz, ha isAdjustable == true és numberOfLegs >= 4
     */
    public boolean isFoldable() {
        return this.isAdjustable && this.numberOfLegs >= 4;
    }
}
