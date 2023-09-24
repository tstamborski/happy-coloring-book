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
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author Tobiasz Stamborski <tstamborski@outlook.com>
 */
public class ColoringPageList extends ArrayList<ColoringPage> {
    private int currentIndex;

    public static ColoringPageList fromFileArray(File files[]) throws IOException {
        ColoringPageList list = new ColoringPageList();
        BufferedImage refImg;
        
        for (File f: files) {
            refImg = ImageIO.read(f);
            list.add(new ColoringPage(refImg, f.getName()));
        }
        
        return list;
    }
    
    public static ColoringPageList fromResourceImages() throws IOException {
        ColoringPageList list = new ColoringPageList();
        
        for (ImageEntry hi: HappyResourceImages.getInstance()) {
            list.add(new ColoringPage(
                    ImageIO.read(hi.getURL()),
                    hi.getName()
            ));
        }
        
        return list;
    }
    
    public ColoringPage next() {
        setCurrentIndex(currentIndex+1);
        return getCurrent();
    }
    
    public ColoringPage previous() {
        setCurrentIndex(currentIndex-1);
        return getCurrent();
    }
    
    public boolean isNext() {
        return (currentIndex < size()-1 && !isEmpty());
    }
    
    public boolean isPrevious() {
        return (currentIndex > 0 && !isEmpty());
    }
    
    public ColoringPage getCurrent() {
        if ((currentIndex < 0 || currentIndex >= size()) || isEmpty())
            return null;
        else
            return get(currentIndex);
    }

    public void setCurrentIndex(int currentIndex) {
        if (currentIndex >= 0 && currentIndex < size())
            this.currentIndex = currentIndex;
    }
    
    public int getCurrentIndex() {
        return currentIndex;
    }
    
}
