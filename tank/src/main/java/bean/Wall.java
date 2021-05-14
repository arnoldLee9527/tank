package bean;

import base.ResourceManager;
import modle.GameModel;
import modle.GameObject;

import java.awt.*;

/**
 *
 * <strong>Description : TODO</strong><br>
 * <strong>Create on : 2021/5/12 10:45<br>
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
public class Wall extends GameObject {
    private static final int WALL_WIDTH = ResourceManager.squareR.getWidth();
    private static final int WALL_HEIGHT = ResourceManager.squareR.getHeight();
    private Integer x;
    private Integer y;
    private boolean living = true;
    private Rectangle rectangle = new Rectangle();

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

    public Wall() {
    }

    public Wall(Integer x, Integer y) {
        this.x = x;
        this.y = y;
        rectangle.x = this.x;
        rectangle.y = this.y;
        rectangle.width = WALL_WIDTH;
        rectangle.height = WALL_HEIGHT;
    }

    @Override
    public void print(Graphics graphics) {
        if (living) graphics.drawImage(ResourceManager.squareR, x, y, null);
    }

    public boolean collideWith(Bullet bullet) {
        // 用一个Rectangle
        if (rectangle.intersects(bullet.rectangle)) {
            bullet.die();
            //this.die();
            return false;
        }
        return true;
    }

    private void die() {
        this.living = false;
    }
}
