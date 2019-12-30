/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import Entities.BaseEntity;
import Entities.CommentEntity;
import java.util.ArrayList;

/**
 *
 * @author Abd-Elmalek
 */
public interface CommentDAO extends BaseDAO{
    
    ArrayList<BaseEntity> findAll();
    BaseEntity findById(int id);
    boolean insert(CommentEntity comment);
    boolean update(CommentEntity comment);
    boolean delete(CommentEntity comment);
    
}
