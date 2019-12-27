/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import Entities.RequestEntity;
import java.util.ArrayList;

/**
 *
 * @author Abd-Elmalek
 */
public interface RequestDAO {
    ArrayList<RequestEntity> findAllIRequsets();
    ArrayList<RequestEntity> findRequsetById();
    boolean insertRequset(RequestEntity request);
    boolean updateRequset(RequestEntity request);
    boolean deleteRequset(RequestEntity request);
}
