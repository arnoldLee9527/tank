package utils;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 *
 * <strong>Description : 图片处理工具类</strong><br>
 * <strong>Create on : 2021/3/15 13:29<br>
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
public class ImageUtil {
    public static BufferedImage rotateImage(final BufferedImage bufferedImage,
                                            final int degree) {
        int w = bufferedImage.getWidth();
        int h = bufferedImage.getHeight();
        int type = bufferedImage.getColorModel().getTransparency();
        BufferedImage img;
        Graphics2D graphics2D;
        (graphics2D = (img = new BufferedImage(w, h, type)).createGraphics()).setRenderingHint(
                RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR
        );
        graphics2D.rotate(Math.toRadians(degree), w / 2.0, h / 2.0);
        graphics2D.drawImage(bufferedImage, 0, 0, null);
        graphics2D.dispose();
        return img;
    }
}
