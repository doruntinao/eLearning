/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BLL.Kursi;
import java.util.List;

/**
 *
 * @author Doruntina
 */
public interface KursiInterface {
    void create(Kursi k) throws eLearningException;
    void edit(Kursi k) throws eLearningException;
    void delete(Kursi k) throws eLearningException;
    List<Kursi> findAll() throws eLearningException;
    Kursi findById(Integer Id) throws eLearningException;
}
