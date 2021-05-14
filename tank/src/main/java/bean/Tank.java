package bean;

import base.Dir;
import base.Group;
import base.PropertiesManager;
import base.ResourceManager;
import interfaces.FireStrategy;
import interfaces.interfaceImpl.DefaultFireStrategy;
import modle.GameModel;
import modle.GameObject;
import windows.TankFrame;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

/**
 * <strong>Description : 坦克类</strong><br>
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
public class Tank extends GameObject {
    private Integer x;
    private Integer y; 
    private Integer oldX;
    private Integer oldY;
    private static final Integer SPEED = 5;
    private Dir dir = Dir.DOWN;
    private Group group = Group.GOOD;
    private boolean moving = false;
    private int numberOfLives = 5;
    public boolean living = true;
    public static final int TANK_WIDTH = ResourceManager.gTankD.getWidth();
    public static final int TANK_HEIGHT = ResourceManager.gTankD.getHeight();
    public Rectangle rectangle = new Rectangle();
    private FireStrategy fs = new DefaultFireStrategy();
    private PropertiesManager property = PropertiesManager.getInstance();

    private Random random = new Random();

    public Tank() {
    }

    public Tank(Integer x, Integer y, Dir dir, Group group) {
        this.x = x;
        this.y = y;
        this.oldX = x;
        this.oldY = y;
        this.dir = dir;
        this.group = group;
        rectangle.x = this.x;
        rectangle.y = this.y;
        rectangle.width = TANK_WIDTH;
        rectangle.height = TANK_HEIGHT;
        if (this.getGroup() == Group.GOOD) {
            String goodFSName = (String) property.getProperty("goodFS");
            getFireStrategyByProperties(goodFSName);
        }else {
            String badFS = (String) property.getProperty("badFS");
            getFireStrategyByProperties(badFS);
        }
    }

    private void getFireStrategyByProperties(String fs) {
        try {
            this.fs = (FireStrategy) Class.forName(fs).getDeclaredConstructor().newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
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

    public int getNumberOfLives() {
        return numberOfLives;
    }

    public void setNumberOfLives(int numberOfLives) {
        this.numberOfLives = numberOfLives;
    }

    public void print(Graphics graphics) {
        if (!living) {
            GameModel.getInstance().remove(this);
        }
        printTank(graphics, x, y);
        tankMove();
    }

    private void printTank(Graphics graphics, int x, int y) {
        switch (dir) {
            case UP:
                graphics.drawImage(Group.GOOD == group ? ResourceManager.gTankU : ResourceManager.bTankU, x, y, null);
                break;
            case DOWN:
                graphics.drawImage(Group.GOOD == group ? ResourceManager.gTankD : ResourceManager.bTankD, x, y, null);
                break;
            case LEFT:
                graphics.drawImage(Group.GOOD == group ? ResourceManager.gTankL : ResourceManager.bTankL, x, y, null);
                break;
            case RIGHT:
                graphics.drawImage(Group.GOOD == group ? ResourceManager.gTankR : ResourceManager.bTankR, x, y, null);
                break;
            default:
                break;
        }
    }

    private void tankMove() {
        //if (this.x.equals(this.oldX) | this.y.equals(this.oldY)) {
            this.oldX = this.x;
            this.oldY = this.y;
        //}
        if (!moving && Group.GOOD == group) return;
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
        if (Group.BAD == group && random.nextInt(100) > 95) {
            fire();
        }
        if (Group.BAD == group && random.nextInt(10) > 8) {
            this.changeDirection();
        }
        boundsCheck();
        rectangle.x = this.x;
        rectangle.y = this.y;
    }

    private void boundsCheck() {
        if (x < 0) {
            x = 0;
        }
        if (x > TankFrame.getGameWidth() - TANK_WIDTH) {
            x = TankFrame.getGameWidth() - TANK_WIDTH;
        }
        
        if (y < 30) {
            y = 30;
        }
        if (y > TankFrame.getGameHeight() - TANK_HEIGHT) {
            y = TankFrame.getGameHeight() - TANK_HEIGHT;
        }
    }

    private void changeDirection() {
        this.dir = Dir.values()[random.nextInt(4)];
    }


    public void fire() {
       //defaultFS.fire(this);
        fs.fire(this);
    }

    public void die() {
        this.living = false;
    }

    public boolean collideWith(Tank tank) {
        if (this.group != tank.getGroup() && this.rectangle.intersects(tank.rectangle)) {
            tank.die();
            this.die();
            GameModel.getInstance().add(new Explosion(tank.getX(), tank.getY()));
            GameModel.getInstance().add(new Explosion(this.x, this.y));
            if (tank.group == Group.GOOD || this.group == Group.GOOD) {
                GameModel.getInstance().setMyTank(new Tank(100, 100, Dir.DOWN, Group.GOOD));
                numberOfLives --;
            }
            return false; 
        }
        return true;
    }
    public void back() {
        //相同坦克相撞退回上一步
        this.x = this.oldX;
        this.y = this.oldY;
    }
}
