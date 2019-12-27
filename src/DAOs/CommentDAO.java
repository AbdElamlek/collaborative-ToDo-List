/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import Entities.CommentEntity;
import java.util.ArrayList;

/**
 *
 * @author Abd-Elmalek
 */
public interface CommentDAO {
    
    ArrayList<CommentEntity> findAllComment();
    ArrayList<CommentEntity> findCommentById();
    boolean insertComment(CommentEntity comment);
    boolean updateComment(CommentEntity comment);
    boolean deleteComment(CommentEntity comment);
    
}
