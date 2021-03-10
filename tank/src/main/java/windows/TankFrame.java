package windows;

import base.Dir;
import base.Group;
import base.ResourceManager;
import bean.Bullet;
import bean.Explosion;
import bean.Tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * <strong>Description : TODO</strong><br>
 * <strong>Create on : 2021/2/23 17:00<br>
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
public class TankFrame extends Frame {
    private static final int GAME_WIDTH = 500;
    private static final int GAME_HEIGHT = 500;
    private Image offScreenImage = null;
    private Tank tank = new Tank(100, 100, Dir.DOWN, Group.GOOD, this);
    private List<Bullet> bulletList = new ArrayList<>();
    private List<Tank> enemyTankList = new ArrayList<>(8);
    private static List<Explosion> explosionList = new ArrayList<>();



    public static int getGameWidth() {
        return GAME_WIDTH;
    }

    public static int getGameHeight() {
        return GAME_HEIGHT;
    }

    public List<Bullet> getBulletList() {
        return bulletList;
    }

    public void setBulletList(List<Bullet> bulletList) {
        this.bulletList = bulletList;
    }

    public List<Tank> getEnemyTankList() {
        return enemyTankList;
    }

    public void setEnemyTankList(List<Tank> enemyTankList) {
        this.enemyTankList = enemyTankList;
    }

    public static List<Explosion> getExplosionList() {
        return explosionList;
    }

    public static void setExplosionList(List<Explosion> explosionList) {
        TankFrame.explosionList = explosionList;
    }

    public TankFrame() {
        this.setSize(GAME_WIDTH, GAME_HEIGHT);
        this.setResizable(false);
        this.setTitle("Tank War");
        this.setVisible(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        addKeyListener(new MyKeyListener());

    }

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics graphics = offScreenImage.getGraphics();
        Color color = graphics.getColor();
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        graphics.setColor(color);
        print(graphics);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    @Override
    public void paint(Graphics graphics) {
        Color color = graphics.getColor();
        graphics.setColor(Color.white);
        graphics.drawString("子弹数量：" + bulletList.size(), 30, 50);
        graphics.drawString("敌人数量：" + enemyTankList.size(), 30, 80);
        graphics.setColor(color);

        tank.print(graphics, false);

        for (int i = 0; i < enemyTankList.size(); i++) {
            enemyTankList.get(i).print(graphics, true);
        }

        for (int i = 0; i < bulletList.size(); i++) {
            Bullet bullet = bulletList.get(i);
            bullet.print(graphics);
        }

        for (int i = 0; i < bulletList.size(); i++) {
            Bullet bullet = bulletList.get(i);
            for (int j = 0; j < enemyTankList.size(); j++) {
                Tank tank = enemyTankList.get(j);
                bullet.collideWith(tank);
                //if (result){
                //    explosion.print(graphics);
                //}
            }
        }

        for (int i = 0; i < explosionList.size(); i++) {
            explosionList.get(i).print(graphics);
        }
    }

    class MyKeyListener extends KeyAdapter {
        private boolean up = false;
        private boolean down = false;
        private boolean left = false;
        private boolean right = false;

        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_UP:
                    up = true;
                    break;
                case KeyEvent.VK_DOWN:
                    down = true;
                    break;
                case KeyEvent.VK_LEFT:
                    left = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    right = true;
                    break;
                default:
                    break;
            }
            tankMove();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_UP:
                    up = false;
                    break;
                case KeyEvent.VK_DOWN:
                    down = false;
                    break;
                case KeyEvent.VK_LEFT:
                    left = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    right = false;
                    break;
                case KeyEvent.VK_SPACE:
                    tank.fire();
                    break;
                default:
                    break;
            }
            tankMove();
        }

        private void tankMove() {
            if (!up && !down && !left && !right) {
                tank.setMoving(false);
            } else {
                tank.setMoving(true);
                if (up) tank.setDir(Dir.UP);
                if (down) tank.setDir(Dir.DOWN);
                if (left) tank.setDir(Dir.LEFT);
                if (right) tank.setDir(Dir.RIGHT);
            }
        }
    }

}
