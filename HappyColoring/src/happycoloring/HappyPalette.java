/*
 * The MIT License
 *
 * Copyright 2023 Tobiasz Stamborski <tstamborski@outlook.com>.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package happycoloring;

import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author Tobiasz Stamborski <tstamborski@outlook.com>
 */
public class HappyPalette extends ArrayList<HappyPaletteEntry> {
    private static HappyPalette instance;
    
    private HappyPalette() {
        add(new HappyPaletteEntry(new Color(0xe8,0x00,0x00), "Red"));
        add(new HappyPaletteEntry(new Color(0x00,0xe8,0x00), "Green"));
        add(new HappyPaletteEntry(new Color(0x00,0x00,0xe8), "Blue"));
        
        add(new HappyPaletteEntry(new Color(0xe8,0xe8,0x00), "Yellow"));
        add(new HappyPaletteEntry(new Color(0xff,0xaa,0x00), "Orange"));
        add(new HappyPaletteEntry(new Color(0x80,0x00,0xe8), "Violet"));
        
        add(new HappyPaletteEntry(new Color(0xe8,0x80,0x80), "Pink"));
        add(new HappyPaletteEntry(new Color(0x66,0xff,0x66), "LightGreen"));
        add(new HappyPaletteEntry(new Color(0x80,0x80,0xff), "LightBlue"));
        add(new HappyPaletteEntry(new Color(0x80,0xff,0xff), "SkyBlue"));
        
        add(new HappyPaletteEntry(new Color(0x44,0x22,0x00), "Brown"));
        add(new HappyPaletteEntry(new Color(0x22,0x66,0x00), "DarkGreen"));
        add(new HappyPaletteEntry(new Color(0xaa,0xaa,0x00), "Gold"));
        add(new HappyPaletteEntry(new Color(0x88,0x88,0x88), "Gray"));
        
        add(new HappyPaletteEntry(Color.white, "White"));
        add(new HappyPaletteEntry(Color.black, "Black"));
    }
    
    public static HappyPalette getInstance() {
        if (instance == null)
            return instance = new HappyPalette();
        else
            return instance;
    }
}

class HappyPaletteEntry {
    private final Color color;
    private final String name;

    public HappyPaletteEntry(Color c, String n) {
        color = c;
        if (n == null)
            name = "UnknownColor";
        else
            name = n;
    }
    
    /**
     * @return the color
     */
    public Color getColor() {
        return color;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
}
