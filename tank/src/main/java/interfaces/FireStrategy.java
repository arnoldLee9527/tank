package interfaces;

import bean.Tank;

/**
 *
 * <strong>Description : 开火策略</strong><br>
 * <strong>Create on : 2021/3/17 10:34<br>
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
public interface FireStrategy {
    void fire(Tank tank);
}
