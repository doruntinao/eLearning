/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BLL.Materiali;
import java.util.List;

/**
 *
 * @author Doruntina
 */
public interface MaterialiInterface {
    void create(Materiali m) throws eLearningException;
    void edit(Materiali m) throws eLearningException;
    void delete(Materiali m) throws eLearningException;
    List<Materiali> findAll() throws eLearningException;
    Materiali findById(Integer Id) throws eLearningException;
}
