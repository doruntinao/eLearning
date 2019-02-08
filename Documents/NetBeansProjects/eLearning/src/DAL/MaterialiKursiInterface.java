/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BLL.MaterialiKursi;
import java.util.List;

/**
 *
 * @author Doruntina
 */
public interface MaterialiKursiInterface {
    void create(MaterialiKursi mk) throws eLearningException;
    void edit(MaterialiKursi mk) throws eLearningException;
    void delete(MaterialiKursi mk) throws eLearningException;
    List<MaterialiKursi> findAll() throws eLearningException;
    MaterialiKursi findById(Integer Id) throws eLearningException;
}
