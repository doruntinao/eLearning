/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.models;

import BLL.Profesori;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
/**
 *
 * @author Doruntina
 */
public class ProfesoriComboBoxModel extends AbstractListModel<Profesori> implements ComboBoxModel<Profesori>{
    
    List<Profesori> list;
    Profesori selectedItem;

    public ProfesoriComboBoxModel() {
    }
    
    public void add(List<Profesori> list) {
        this.list = list;
    }

    public ProfesoriComboBoxModel(List<Profesori> list) {
        this.list = list;
    }
    
    @Override
    public int getSize() {
        return list.size();
    }

    @Override
    public Profesori getElementAt(int index) {
        return list.get(index);
    }

    @Override
    public void setSelectedItem(Object anItem) {
        selectedItem = (Profesori) anItem;
    }

    @Override
    public Object getSelectedItem() {
        return selectedItem;
    }
}
