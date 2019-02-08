/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.models;

import BLL.StudentiKursi;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Doruntina
 */
public class StudentiKursiTableModel extends AbstractTableModel {

    List<StudentiKursi> list;
    String[] columns = {"Courses"};

    public StudentiKursiTableModel() {
    }

    public StudentiKursiTableModel(List<StudentiKursi> list) {
        this.list = list;
    }

    public void add(List<StudentiKursi> list) {
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

    public StudentiKursi getStudentiKursi(int i) {
        return list.get(i);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        StudentiKursi k = getStudentiKursi(rowIndex);

        switch (columnIndex) {
            case 0:
                return k.getKursiID().getEmriKursit();
            default:
                return null;
        }
    }

}
