/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import Entities.NotificationEntity;
import java.util.ArrayList;

/**
 *
 * @author Abd-Elmalek
 */
public interface NotificationDAO {
    ArrayList<NotificationEntity> findAllINotifications();
    ArrayList<NotificationEntity> findNotificationById();
    boolean insertNotification(NotificationEntity notification);
    boolean updateNotification(NotificationEntity notification);
    boolean deleteNotification(NotificationEntity notification);
    
}
