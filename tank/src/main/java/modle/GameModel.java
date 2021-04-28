package modle;

import base.Dir;
import base.Group;
import base.PropertiesManager;
import bean.Bullet;
import bean.Explosion;
import bean.Tank;
import interfaces.Collider;
import interfaces.interfaceImpl.TankAndBulletCollide;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * <strong>Description : TODO</strong><br>
 * <strong>Create on : 2021/4/19 16:08<br>
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
public class GameModel {
    
    private Tank myTank = new Tank(100, 100, Dir.DOWN, Group.GOOD, this);
    
    private List<GameObject> gameObjects = new ArrayList<>();

    private Collider collider = new TankAndBulletCollide();

    public GameModel() {
        PropertiesManager propertiesManager = PropertiesManager.getInstance();
        int initBadTankCount = Integer.parseInt((String) propertiesManager.getProperty("initBadTankCount"));
        for (int i = 0; i < initBadTankCount ; i++) {
            Tank tank = new Tank(50 * i, 300, Dir.UP, Group.BAD, this);
            gameObjects.add(tank);
        }
    }
    
    public void add(GameObject gameObject){
        gameObjects.add(gameObject);
    }
    
    public void remove(GameObject gameObject) {
        gameObjects.remove(gameObject);
    }

    public Tank getMyTank() {
        return myTank;
    }

    public void setMyTank(Tank myTank) {
        this.myTank = myTank;
    }

    public List<GameObject> getGameObjects() {
        return gameObjects;
    }

    public void setGameObjects(List<GameObject> gameObjects) {
        this.gameObjects = gameObjects;
    }

    public void print(Graphics graphics) {
        Color color = graphics.getColor();
        graphics.setColor(Color.white);
        //graphics.drawString("子弹数量：" + bulletList.size(), 30, 50);
        //graphics.drawString("敌人数量：" + enemyTankList.size(), 30, 70);
        //graphics.drawString("爆炸数量：" + explosionList.size(), 30, 90);
        graphics.drawString("剩余生命：" + myTank.getNumberOfLives(), 30, 110);
        //graphics.drawRect(5, 30, GAME_WIDTH - 15, GAME_HEIGHT - 35);
        graphics.setColor(color);

        myTank.print(graphics);

        //for (int i = 0; i < enemyTankList.size(); i++) {
        //    Tank enemyTank = enemyTankList.get(i);
        //    enemyTank.print(graphics);
        //    enemyTank.collideWith(myTank);
        //}

        for (int i = 0; i < gameObjects.size(); i++) {
            gameObjects.get(i).print(graphics);
        }

        for (int i = 0; i < gameObjects.size(); i++) {
            for (int j = i + 1; j < gameObjects.size(); j++) {
                GameObject g1 = gameObjects.get(i);
                GameObject g2 = gameObjects.get(j);
                collider.collideWith(g1, g2);
                //if (result){
                //    explosion.print(graphics);
                //}
            }
        }
        //
        //for (int i = 0; i < explosionList.size(); i++) {
        //    explosionList.get(i).print(graphics);
        //}
    }
}
