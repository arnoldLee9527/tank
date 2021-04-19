package abstractFactory;

import base.Dir;
import base.Group;
import bean.Tank;
import windows.TankFrame;

import java.awt.*;

/**
 *
 * <strong>Description : TODO</strong><br>
 * <strong>Create on : 2021/4/1 13:34<br>
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
public abstract class BaseTank {
    private Group group = Group.GOOD;

    public Rectangle rectangle = new Rectangle();
    
    public abstract void print(Graphics graphics);

    public Group getGroup(){
        return this.group;
    };

    public abstract void die();

    public abstract Integer getX();

    public abstract Integer getY();

    public abstract void collideWith(BaseTank tank);

    public abstract int getNumberOfLives();

    public abstract Dir getDir();

    public abstract void setDir(Dir up);

    public abstract TankFrame getTankFrame();

    public abstract void fire();

    public abstract void setMoving(boolean b);
}
