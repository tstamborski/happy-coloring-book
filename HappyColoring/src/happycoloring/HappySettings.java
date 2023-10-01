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

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javax.imageio.ImageIO;

/**
 *
 * @author Tobiasz Stamborski <tstamborski@outlook.com>
 */
public class HappySettings {
    private File settingsDirectory;
    private Preferences prefs;
    
    private final String LOAD_PATH_KEY = "LoadPath";
    private final String SAVE_PATH_KEY = "SavePath";
    private final String DEFAULT_PALETTE_KEY = "DefaultPalette";
    private final String PASTEL_PALETTE_KEY = "PastelPalette";
    private final String JUICY_PALETTE_KEY = "JuicyPalette";
    private final String DIRTY_PALETTE_KEY = "DirtyPalette";
    
    public HappySettings() throws IOException {
        this(new File(System.getProperty("user.home") + File.separator + "." + HappyColoring.class.getSimpleName()));
    }
    
    public HappySettings(File dir) throws IOException {
        if (dir.exists() && dir.isDirectory()) {
            settingsDirectory = dir;
        }
        else {
            if (dir.exists() && !dir.isDirectory()) {
                throw new IOException(dir.getName() + " exist and is not directory!");
            }
            else {
                dir.mkdir();
                Files.setAttribute(Paths.get(dir.toURI()), "dos:hidden", Boolean.TRUE, LinkOption.NOFOLLOW_LINKS);
                settingsDirectory = dir;
                if (!settingsDirectory.exists()) 
                    throw new IOException("Cannot create " + dir.getName() + " directory!");
            }
        }
        
        prefs = Preferences.userRoot().node(HappyColoring.class.getSimpleName());
    }
    
    public void save(ColoringPageList list) {
        list.forEach(page -> {
            File file = new File(getPath(page));
            BufferedImage img = new BufferedImage(page.getCanvasImage().getWidth(),
                    page.getCanvasImage().getHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = img.createGraphics();
            
            g2d.drawImage(page.getCanvasImage(), 0, 0, null);
            try {
                ImageIO.write(img, "png", file);
            } catch (IOException ex) {
                Logger.getLogger(HappySettings.class.getName()).log(Level.SEVERE, ex.getMessage(), this);
            }
        });
    }
    
    public void save(HappyLoadDialog loadDialog, HappySaveDialog saveDialog) {
        prefs.put(LOAD_PATH_KEY, loadDialog.getCurrentDirectory().getAbsolutePath());
        prefs.put(SAVE_PATH_KEY, saveDialog.getCurrentDirectory().getAbsolutePath());
    }
    
    public void save(HappyMenuBar menu) {
        prefs.putBoolean(DIRTY_PALETTE_KEY, menu.getDirtyItem().isSelected());
        prefs.putBoolean(JUICY_PALETTE_KEY, menu.getJuicyItem().isSelected());
        prefs.putBoolean(PASTEL_PALETTE_KEY, menu.getPastelItem().isSelected());
        prefs.putBoolean(DEFAULT_PALETTE_KEY, menu.getDefaultItem().isSelected());
    }
    
    public void load(ColoringPageList list) {
        list.forEach(page -> {
            BufferedImage img = null;
            
            try {
                img = ImageIO.read(new File(getPath(page)));
            } catch (IOException ex) {
                Logger.getLogger(HappySettings.class.getName()).log(Level.INFO, ex.getMessage(), this);
            }
            
            Graphics2D g2d = page.getCanvasImage().createGraphics();
            g2d.drawImage(img, 0, 0, null);
            
            page.clearHistory();
        });
    }
    
    public void load(HappyLoadDialog loadDialog, HappySaveDialog saveDialog) {
        loadDialog.setCurrentDirectory(new File(prefs.get(LOAD_PATH_KEY, System.getProperty("user.home"))));
        saveDialog.setCurrentDirectory(new File(prefs.get(SAVE_PATH_KEY, System.getProperty("user.home"))));
    }
    
    public void load(HappyMenuBar menu) {
        menu.getDirtyItem().setSelected(prefs.getBoolean(DIRTY_PALETTE_KEY, true));
        menu.getJuicyItem().setSelected(prefs.getBoolean(JUICY_PALETTE_KEY, true));
        menu.getPastelItem().setSelected(prefs.getBoolean(PASTEL_PALETTE_KEY, true));
        menu.getDefaultItem().setSelected(prefs.getBoolean(DEFAULT_PALETTE_KEY, true));
        
        if (menu.getDefaultItem().isSelected())
            menu.getDefaultItem().doClick();
        else if (menu.getPastelItem().isSelected())
            menu.getPastelItem().doClick();
        else if (menu.getJuicyItem().isSelected())
            menu.getJuicyItem().doClick();
        else
            menu.getDirtyItem().doClick();
    }
    
    private String getPath(ColoringPage page) {
        return settingsDirectory.getPath() + File.separator + page.getName() + ".sav";
    }
}
