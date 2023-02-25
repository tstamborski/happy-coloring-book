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

/**
 *
 * @author Tobiasz Stamborski <tstamborski@outlook.com>
 */
public class ColorUtil {
    public static Color darker(Color c, int step) {
        int r, g, b, a;
        
        a = c.getAlpha();
        r = c.getRed();
        g = c.getGreen();
        b = c.getBlue();
        
        r -= step;
        g -= step;
        b -= step;
        if (r < 0)
            r = 0;
        if (g < 0)
            g = 0;
        if (b < 0)
            b = 0;
        
        return new Color(r, g, b, a);
    }
    
    public static Color brighter(Color c, int step) {
        int r, g, b, a;
        
        a = c.getAlpha();
        r = c.getRed();
        g = c.getGreen();
        b = c.getBlue();
        
        r += step;
        g += step;
        b += step;
        if (r > 0xff)
            r = 0xff;
        if (g > 0xff)
            g = 0xff;
        if (b > 0xff)
            b = 0xff;
        
        return new Color(r, g, b, a);
    }
}
