
package happycoloring;

import java.awt.Component;
import java.io.File;
import javax.swing.JOptionPane;

/**
 *
 * @author Tobiasz Stamborski <tstamborski@outlook.com>
 */
public class Util {
    public static boolean hasExtension(File f, String[] ext) {
        for (String ext1 : ext) {
            if (f.getAbsolutePath().endsWith("." + ext1)) {
                return true;
            }
        }
        
        return false;
    }
    
    public static boolean hasExtension(File f, String ext) {
        return f.getAbsolutePath().endsWith("." + ext);
    }
    
    public static String removeExtension(String filename){
        return filename.substring(0, filename.lastIndexOf('.'));
    }
    
    public static void showError(Component parent, String str) {
        JOptionPane.showMessageDialog(parent, str, "Error!", JOptionPane.ERROR_MESSAGE);
    }
}
