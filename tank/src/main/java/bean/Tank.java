package bean;

import abstractFactory.BaseTank;
import base.Dir;
import base.Group;
import base.PropertiesManager;
import base.ResourceManager;
import interfaces.FireStrategy;
import interfaces.interfaceImpl.DefaultFireStrategy;
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
public class Tank extends BaseTank{
    private Integer x;
    private Integer y;
    private static final Integer SPEED = 5;
    private Dir dir = Dir.DOWN;
    private Group group = Group.GOOD;
    private boolean moving = false;
    private int numberOfLives = 5;
    private TankFrame tankFrame;
    public boolean living = true;
    public static final int TANK_WIDTH = ResourceManager.gTankD.getWidth();
    public static final int TANK_HEIGHT = ResourceManager.gTankD.getHeight();
    
    private FireStrategy fs = new DefaultFireStrategy();
    private PropertiesManager property = PropertiesManager.getInstance();

    private Random random = new Random();

    public Tank() {
    }

    public Tank(Integer x, Integer y, Dir dir, Group group, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tankFrame = tankFrame;
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

    public TankFrame getTankFrame() {
        return tankFrame;
    }

    public void setTankFrame(TankFrame tankFrame) {
        this.tankFrame = tankFrame;
    }

    public void print(Graphics graphics) {
        if (!living) {
            tankFrame.getEnemyTankList().remove(this);
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

    public void collideWith(BaseTank goodTank) {
        if (this.group == goodTank.getGroup()) return;
        //用一个Rectangle
        if (this.rectangle.intersects(goodTank.rectangle)) {
            goodTank.die();
            this.die();
            TankFrame.getExplosionList().add(new Explosion(goodTank.getX(), goodTank.getY()));
            TankFrame.getExplosionList().add(new Explosion(this.x, this.y));
            if (goodTank.getGroup() == Group.GOOD) {
                tankFrame.setTank(new Tank(100, 100, Dir.DOWN, Group.GOOD, tankFrame));
                numberOfLives --;
            }
        }
    }
}
