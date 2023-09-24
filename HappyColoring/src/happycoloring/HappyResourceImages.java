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

import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author Tobiasz Stamborski <tstamborski@outlook.com>
 */
public class HappyResourceImages extends ArrayList<ImageEntry> {
    private static HappyResourceImages instance;
    
    private HappyResourceImages() {
        ResourceBundle rb = ResourceBundle.getBundle("happycoloring.images.images", Locale.ROOT);
        Enumeration<String> keys = rb.getKeys();
        
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            add(new ImageEntry(getClass().getResource(rb.getString(key)), key));
        }
    }
    
    public static HappyResourceImages getInstance() {
        if (instance == null)
            return instance = new HappyResourceImages();
        else
            return instance;
    }
}

class ImageEntry {
    private final URL url;
    private final String name;
    
    public ImageEntry(URL url, String name) {
        this.url = url;
        if (name == null)
            this.name = "Anonymous image";
        else
            this.name = name;
    }
    
    public URL getURL() {
        return url;
    }
    
    public String getName() {
        return name;
    }
}