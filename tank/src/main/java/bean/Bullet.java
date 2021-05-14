package bean;

import base.Dir;
import base.Group;
import base.ResourceManager;
import modle.GameModel;
import modle.GameObject;
import windows.TankFrame;

import java.awt.*;

/**
 *
 * <strong>Description : 子弹类</strong><br>
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
public class Bullet extends GameObject {
    private Integer x;
    private Integer y;
    private Dir dir;
    private Group group = Group.GOOD;
    private static final Integer SPEED = 10;
    public static final int BULLET_WIDTH = ResourceManager.bulletD.getWidth();
    public static final int BULLET_HEIGHT = ResourceManager.bulletD.getHeight();
    public Rectangle rectangle = new Rectangle();
    
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

    public Bullet(Integer x, Integer y, Dir dir, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        rectangle.x = this.x;
        rectangle.y = this.y;
        rectangle.width = BULLET_WIDTH;
        rectangle.height = BULLET_HEIGHT;
        GameModel.getInstance().add(this);
    }

    public void print(Graphics graphics) {
        if (!living) {
            GameModel.getInstance().remove(this);
        }
        //Color color = graphics.getColor();
        //graphics.setColor(Color.RED);
        //graphics.fillOval(x, y, TANK_D_WIDTH, HEIGHT);
        //graphics.setColor(color);
        switch (dir) {
            case UP:
                graphics.drawImage(ResourceManager.bulletU, x, y, null);
                break;
            case DOWN:
                graphics.drawImage(ResourceManager.bulletD, x, y, null);
                break;
            case LEFT:
                graphics.drawImage(ResourceManager.bulletL, x, y, null);
                break;
            case RIGHT:
                graphics.drawImage(ResourceManager.bulletR, x, y, null);
                break;
            default:
                break;
        }
        move();
    }

    private void move() {
        switch (dir) {
            case UP:
                y -= SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
            case LEFT:
                x -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            default:
                break;
        }
        if (x < 0 || y < 0 || x > TankFrame.getGameWidth() || y > TankFrame.getGameHeight()){
            living = false;
        }
        rectangle.x = this.x;
        rectangle.y = this.y;
    }

    public boolean collideWith(Tank tank) {
        if (this.group == tank.getGroup()) return true;
        
        // 用一个Rectangle
        if (rectangle.intersects(tank.rectangle)) {
            tank.die();
            this.die();
            int eX = tank.getX() + Tank.TANK_WIDTH / 2 - Explosion.EXPLOSION_WIDTH / 2;
            int eY = tank.getY() + Tank.TANK_HEIGHT / 2 - Explosion.EXPLOSION_HEIGHT / 2;
            GameModel.getInstance().add(new Explosion(eX, eY));
            return false;
        }
        return true;
    }

    public void die() {
        this.living = false;
    }
}
