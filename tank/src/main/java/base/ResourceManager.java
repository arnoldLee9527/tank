package base;

import utils.ImageUtil;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * <strong>Description : 图片资源管理类</strong><br>
 * <strong>Create on : 2021/3/8 20:17<br>
 * </strong>
 * <p>
 * 
 * @author LiXuyang <br>
 * @email arnoldlee9527@163.com<br>
 * @version <strong> </strong><br>
 *          <br>
 *          <strong>修改历史:</strong><br>
 *          修改人 | 修改日期 | 修改描述<br>
 *          -------------------------------------------<br>
 *          <br>
 *          <br>
 */ 
public class ResourceManager {
    
    public static BufferedImage gTankU, gTankD, gTankL, gTankR, bTankU, bTankD, bTankL, bTankR,
    bulletU, bulletD, bulletL, bulletR, squareR;

    public static BufferedImage[] explosionList = new BufferedImage[16];
    
    static {
        try {
            gTankU = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("images/GoodTank1.png"));
            gTankD = ImageUtil.rotateImage(gTankU, 180);
            gTankL = ImageUtil.rotateImage(gTankU, 270);
            gTankR = ImageUtil.rotateImage(gTankU, 90);
            
            bTankU = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("images/BadTank1.png"));
            bTankD = ImageUtil.rotateImage(bTankU, 180);
            bTankL = ImageUtil.rotateImage(bTankU, 270);
            bTankR = ImageUtil.rotateImage(bTankU, 90);

            bulletU = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("images/bulletU.png"));
            bulletD = ImageUtil.rotateImage(bulletU, 180);
            bulletL = ImageUtil.rotateImage(bulletU, 270);
            bulletR = ImageUtil.rotateImage(bulletU, 90);

            squareR = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("images/square0.jpg"));
            
            for (int i = 0; i < 16; i++){
                explosionList[i] = (ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("images/e" + (i+1) + ".gif")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
