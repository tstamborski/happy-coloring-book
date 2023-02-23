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

import java.awt.Composite;
import java.awt.CompositeContext;
import java.awt.RenderingHints;
import java.awt.image.ColorModel;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;

/**
 *
 * @author Tobiasz Stamborski <tstamborski@outlook.com>
 */

class ANDCompositeContext implements CompositeContext {

    @Override
    public void dispose() {
    }

    @Override
    public void compose(Raster src, Raster dstIn, WritableRaster dstOut) {
        int srcPixels[] = src.getPixels(0, 0, src.getWidth(), src.getHeight(), (int[])null);
        int dstPixels[] = dstIn.getPixels(0, 0, dstIn.getWidth(), dstIn.getHeight(), (int[])null);
        
        for (int i = 0; i < dstPixels.length; i++) {
            dstPixels[i] = dstPixels[i] & srcPixels[i];
        }
        
        dstOut.setPixels(0, 0, dstOut.getWidth(), dstOut.getHeight(), dstPixels);
    }
    
}

class ANDComposite implements Composite {
    private final ANDCompositeContext andContext;
    
    public ANDComposite() {
        andContext = new ANDCompositeContext();
    }
    
    @Override
    public CompositeContext createContext(ColorModel srcColorModel, ColorModel dstColorModel, RenderingHints hints) {
        return andContext;
    }
    
}
