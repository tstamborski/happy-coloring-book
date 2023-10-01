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

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.util.ArrayList;

/**
 *
 * @author Tobiasz Stamborski <tstamborski@outlook.com>
 */
public class ColoringUndoRedo {
    private static final int SIZE = 8;
    
    private final ArrayList<BufferedImage> history;
    private int index;
    
    public ColoringUndoRedo() {
        history = new ArrayList<>();
        index = -1;
    }
    
    public boolean isUndo() {
        if (history.isEmpty())
            return false;
        
        return index > 0;
    }
    
    public boolean isRedo() {
        if (history.isEmpty())
            return false;
        
        return index < history.size()-1;
    }
    
    public BufferedImage undo() {
        if (!isUndo())
            return null;
        
        return deepCopy(history.get(--index));
    }
    
    public BufferedImage redo() {
        if (!isRedo())
            return null;
        
        return deepCopy(history.get(++index));
    }
    
    public void push(BufferedImage bi) {
        for (int i = history.size()-1; i > index; i--)
            history.remove(i);
        
        if (history.size() >= ColoringUndoRedo.SIZE)
            history.remove(0);
        
        history.add(deepCopy(bi));
        index = history.size()-1;
    }
    
    public void clear() {
        history.clear();
        index = -1;
    }
    
    private BufferedImage deepCopy(BufferedImage bi) {
        ColorModel cm = bi.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = bi.copyData(bi.getRaster().createCompatibleWritableRaster());
        
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }
}
