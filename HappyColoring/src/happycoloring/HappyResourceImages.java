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

/**
 *
 * @author Tobiasz Stamborski <tstamborski@outlook.com>
 */
public class HappyResourceImages extends ArrayList<HappyImage> {
    private static HappyResourceImages instance;
    
    private HappyResourceImages() {
        add(new HappyImage(getClass().getResource("images/hyenas.png"), "Hyenas"));
        add(new HappyImage(getClass().getResource("images/lion.png"), "Lion"));
        add(new HappyImage(getClass().getResource("images/hippo.png"), "Hippo"));
        add(new HappyImage(getClass().getResource("images/gnu.png"), "Gnu"));
    }
    
    public static HappyResourceImages getInstance() {
        if (instance == null)
            return instance = new HappyResourceImages();
        else
            return instance;
    }
}

class HappyImage {
    private final URL url;
    private final String name;
    
    public HappyImage(URL url, String name) {
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