/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BLL.KursiProfesori;
import java.util.List;

/**
 *
 * @author Doruntina
 */
public interface KursiProfesoriInterface {
    void create(KursiProfesori kp) throws eLearningException;
    void edit(KursiProfesori kp) throws eLearningException;
    void delete(KursiProfesori kp) throws eLearningException;
    List<KursiProfesori> findAll() throws eLearningException;
    KursiProfesori findById(Integer Id) throws eLearningException;
}
