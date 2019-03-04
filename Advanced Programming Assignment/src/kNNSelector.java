import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

public class kNNSelector {

	File fp;
    BufferedImage selectedFile;

    public void SelectFile() {
        //TODO set default path for the file selector? Default to the path where a bunch of stock images are stored
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        jfc.setDialogTitle("Select an image");
        jfc.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG and jpeg images", "png", "jpg", "jpeg");
        jfc.addChoosableFileFilter(filter);

        int returnValue = jfc.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            fp = jfc.getSelectedFile();
            System.out.println(fp);

        }

    }

    public BufferedImage createBufferedImage() throws IOException {
        if (fp.isFile() && fp.exists()) {
            selectedFile = ImageIO.read(fp);
            System.out.println(selectedFile);
        }

        BufferedImage bimage = new BufferedImage(28, 28, BufferedImage.TYPE_INT_ARGB);
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(selectedFile, 0, 0, null);
        System.out.println(bimage);
        bGr.dispose();
        
        return bimage;
    }
}
