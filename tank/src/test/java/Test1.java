import abstractFactory.SquareTank;
import base.Dir;
import base.Group;
import base.PropertiesManager;
import bean.Tank;
import org.junit.jupiter.api.Test;
import windows.TankFrame;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 *
 * <strong>Description : 测试</strong><br>
 * <strong>Create on : 2021/2/23 17:08<br>
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
public class Test1 {
    
    public static void main(String[] args) throws InterruptedException {
        
        TankFrame tankFrame = new TankFrame();
        PropertiesManager propertiesManager = PropertiesManager.getInstance();
        int initBadTankCount = Integer.parseInt((String)propertiesManager.getProperty("initBadTankCount"));
        for (int i = 0; i < initBadTankCount ; i++) {
            SquareTank tank = new SquareTank(50 * i, 300, Dir.UP, Group.BAD, tankFrame);
            tankFrame.getEnemyTankList().add(tank);
        }
        
        while (true) {
            tankFrame.repaint();
            Thread.sleep(50);
        }
    }
    @Test
    private void testMain(){
        
    }
    
    @Test
    private void testImage(){
        try {
            BufferedImage image = ImageIO.read(new File("D:\\WorkSpace2\\tank-master\\tank\\src\\images/0.gif"));
            BufferedImage image1 = ImageIO.read(Test1.class.getClassLoader().getResourceAsStream("images/0.gif"));
            System.out.println(image);
            System.out.println(image1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
