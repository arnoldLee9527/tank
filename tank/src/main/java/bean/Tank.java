package bean;

import base.Dir;
import base.Group;
import base.ResourceManager;
import windows.TankFrame;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

/**
 * <strong>Description : TODO</strong><br>
 * <strong>Create on : 2021/2/26 14:08<br>
 * </strong>
 * <p>
 *
 * @author LiXuyang <br>
 * @version <strong> </strong><br>
 * <br>
 * <strong>修改历史:</strong><br>
 * 修改人 | 修改日期 | 修改描述<br>
 * -------------------------------------------<br>
 * <br>
 * <br>
 * @email arnoldlee9527@163.com<br>
 */
public class Tank {
    private Integer x;
    private Integer y;
    private static final Integer SPEED = 10;
    private Dir dir = Dir.DOWN;
    private Group group = Group.GOOD;
    private boolean moving = true;
    private TankFrame tankFrame;
    public boolean living = true;
    
    public static final int TANK_WIDTH = ResourceManager.tankD.getWidth();
    public static final int TANK_HEIGHT = ResourceManager.tankD.getHeight();
    
    private Random random = new Random();

    public Tank() {
    }

    public Tank(Integer x, Integer y, Dir dir, Group group, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tankFrame = tankFrame;
    }

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

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void print(Graphics graphics, boolean enemyFlag) {
        if (!living) {
            tankFrame.getEnemyTankList().remove(this);
        }
        if (enemyFlag){
            //Double e = Math.random() * 100;
            //int x = e.intValue();
            //int y = (int) (Math.random() * 100);
            printTank(graphics, x, y);
        }else {
            printTank(graphics, x, y);
        }
        tankMove();
    }

    private void printTank(Graphics graphics, int x, int y) {
        switch (dir) {
            case UP:
                graphics.drawImage(ResourceManager.tankU, x, y, null);
                break;
            case DOWN:
                graphics.drawImage(ResourceManager.tankD, x, y, null);
                break;
            case LEFT:
                graphics.drawImage(ResourceManager.tankL, x, y, null);
                break;
            case RIGHT:
                graphics.drawImage(ResourceManager.tankR, x, y, null);
                break;
            default:
                break;
        }
    }

    private void tankMove() {
        if (!moving) return;
        switch (dir) {
            case UP:
                y -= SPEED;
                if (y <= 0) {
                    y = Tank.TANK_HEIGHT + 30;
                }
                break;
            case DOWN:
                y += SPEED;
                if (y >= TankFrame.getGameHeight()) {
                    y = TankFrame.getGameHeight() - 30;
                }
                break;
            case LEFT:
                x -= SPEED;
                if (x <= 0) {
                    x = Tank.TANK_WIDTH + 5;
                }
                break;
            case RIGHT:
                x += SPEED;
                if (x >= TankFrame.getGameWidth()) {
                    x = TankFrame.getGameWidth() - 15;
                }
                break;
            default:
                break;
        }
        if (random.nextInt(10) > 9) {
            fire();
        }
    }

    public void fire() {
        int bX = this.x + Tank.TANK_WIDTH / 2 - Bullet.BULLET_WIDTH / 2;
        int bY = this.y + Tank.TANK_HEIGHT / 2 - Bullet.BULLET_HEIGHT / 2;
        tankFrame.getBulletList().add(new Bullet(bX, bY, this.dir, this.group, this.tankFrame));
    }

    public void die() {
        this.living = false;
    }

}
