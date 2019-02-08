/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.models;

import BLL.Kursi;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Doruntina
 */
public class KursiTableModel extends AbstractTableModel {

    List<Kursi> list;
    String[] columns = {"Courses"};

    public KursiTableModel() {
    }

    public KursiTableModel(List<Kursi> list) {
        this.list = list;
    }

    public void add(List<Kursi> list) {
        this.list = list;
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    public String getColumnName(int col) {
        return columns[col];
    }

    public Kursi getKursi(int i) {
        return list.get(i);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Kursi k = getKursi(rowIndex);

        switch (columnIndex) {
            case 0:
                return k.getEmriKursit();
            default:
                return null;
        }
    }

}
