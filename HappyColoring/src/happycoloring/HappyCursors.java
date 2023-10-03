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

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

/**
 *
 * @author Tobiasz Stamborski <tstamborski@outlook.com>
 */
public class HappyCursors {
    private static final int CURSOR_SIZE = 32;
    
    private static final int HOTSPOT_UPPER_LEFT = 0;
    private static final int HOTSPOT_LOWER_LEFT = 1;
    
    private static Cursor pencilCursor, rubberCursor;
    private static Cursor sprayCursor, brushCursor, bucketCursor;
    private static Cursor pipetteCursor;
    
    public static Cursor getPencilCursor() {
        if (pencilCursor == null)
            pencilCursor = createCursor("cursors/pencil32.png", 
                    HappyCursors.HOTSPOT_LOWER_LEFT, "Pencil");
        
        return pencilCursor;
    }
    
    public static Cursor getRubberCursor() {
        if (rubberCursor == null)
            rubberCursor = createCursor("cursors/rubber32.png", 
                    HOTSPOT_LOWER_LEFT, "Rubber");
        
        return rubberCursor;
    }
    
    public static Cursor getSprayCursor() {
        if (sprayCursor == null)
            sprayCursor = createCursor("cursors/spray32.png", 
                    HOTSPOT_UPPER_LEFT, "Spray");
        
        return sprayCursor;
    }
    
    public static Cursor getBrushCursor() {
        if (brushCursor == null)
            brushCursor = createCursor("cursors/brush32.png", 
                    HOTSPOT_LOWER_LEFT, "Brush");
        
        return brushCursor;
    }
    
    public static Cursor getBucketCursor() {
        if (bucketCursor == null)
            bucketCursor = createCursor("cursors/bucket32.png", 
                    HOTSPOT_LOWER_LEFT, "Bucket");
        
        return bucketCursor;
    }
    
    public static Cursor getPipetteCursor() {
        if (pipetteCursor == null)
            pipetteCursor = createCursor("cursors/pipette32.png", 
                    HOTSPOT_LOWER_LEFT, "Pipette");
        
        return pipetteCursor;
    }
    
    private static Cursor createCursor(String resPath, int hotspotType, String name) {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension dim = kit.getBestCursorSize(CURSOR_SIZE, CURSOR_SIZE);
        Point hotspot;
        
        if (dim.width == 0 || dim.height == 0)
            return Cursor.getDefaultCursor();
        
        if (hotspotType == HappyCursors.HOTSPOT_LOWER_LEFT)
            hotspot = new Point(0, dim.height-1);
        else
            hotspot = new Point(0, 0);
        
        Image img = kit.getImage(HappyCursors.class.getResource(resPath));
        if (img == null)
            return Cursor.getDefaultCursor();
        
        return kit.createCustomCursor(img, hotspot, name);
    }
}
