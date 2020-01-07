/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControllerBase;

import Entities.EntityWrapper;

/**
 *
 * @author pc
 */
public interface AdapterInterface {
    String entity2Json(EntityWrapper entity);
    EntityWrapper json2Entity(String json);
}
