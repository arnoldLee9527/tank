package bean;

import base.Dir;
import base.Group;
import base.ResourceManager;
import windows.TankFrame;

import java.awt.*;

/**
 *
 * <strong>Description : TODO</strong><br>
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
public class Bullet {
    private Integer x;
    private Integer y;
    private Dir dir;
    private Group group = Group.GOOD;
    private TankFrame tankFrame;
    private static final Integer SPEED = 10;
    public static final int BULLET_WIDTH = ResourceManager.bulletD.getWidth();
    public static final int BULLET_HEIGHT = ResourceManager.bulletD.getHeight();
    
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

    public Bullet(Integer x, Integer y, Dir dir, Group group, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tankFrame = tankFrame;
    }

    public void print(Graphics graphics) {
        if (!living) {
            tankFrame.getBulletList().remove(this);
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
    }

    public void collideWith(Tank tank) {
        if (this.group == tank.getGroup()) return;
        
        //TODO 用一个Rectangle
        Rectangle bR = new Rectangle(this.x, this.y, BULLET_WIDTH, BULLET_HEIGHT);
        Rectangle tR = new Rectangle(tank.getX(), tank.getY(), Tank.TANK_WIDTH, Tank.TANK_HEIGHT);
        if (bR.intersects(tR)) {
            tank.die();
            this.die();
            tankFrame.getExplosionList().add(new Explosion(tank.getX(), tank.getY()));
        }
    }

    private void die() {
        this.living = false;
    }
}
