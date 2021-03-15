package bean;

import base.Dir;
import base.Group;
import base.ResourceManager;
import windows.TankFrame;

import java.awt.*;

/**
 *
 * <strong>Description : 爆炸效果类</strong><br>
 * <strong>Create on : 2021/2/26 15:33<br>
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
public class Explosion {
    private Integer x;
    private Integer y;
    private Integer step = 0;
    public static final int EXPLOSION_WIDTH = ResourceManager.explosionList[0].getWidth();
    public static final int EXPLOSION_HEIGHT = ResourceManager.explosionList[0].getHeight();
    
    private boolean living = true;

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Explosion(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public void print(Graphics graphics) {
        graphics.drawImage(ResourceManager.explosionList[step++], x, y, EXPLOSION_WIDTH, EXPLOSION_HEIGHT, null);
        if (step >= ResourceManager.explosionList.length) {
            TankFrame.getExplosionList().remove(this);
        }
    }

}
