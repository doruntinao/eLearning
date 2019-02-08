/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Doruntina
 */
@Entity
@Table(name = "Vleresimi")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vleresimi.findAll", query = "SELECT v FROM Vleresimi v")
    , @NamedQuery(name = "Vleresimi.findByVleresimiID", query = "SELECT v FROM Vleresimi v WHERE v.vleresimiID = :vleresimiID")})
public class Vleresimi implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "VleresimiID")
    private Integer vleresimiID;
    @JoinColumn(name = "KursiID", referencedColumnName = "KursID")
    @ManyToOne
    private Kursi kursiID;
    @JoinColumn(name = "ProfesorID", referencedColumnName = "ProfesorID")
    @ManyToOne
    private Profesori profesorID;
    @JoinColumn(name = "StudentID", referencedColumnName = "StudentID")
    @ManyToOne
    private Studenti studentID;

    public Vleresimi() {
    }

    public Vleresimi(Integer vleresimiID) {
        this.vleresimiID = vleresimiID;
    }

    public Integer getVleresimiID() {
        return vleresimiID;
    }

    public void setVleresimiID(Integer vleresimiID) {
        this.vleresimiID = vleresimiID;
    }

    public Kursi getKursiID() {
        return kursiID;
    }

    public void setKursiID(Kursi kursiID) {
        this.kursiID = kursiID;
    }

    public Profesori getProfesorID() {
        return profesorID;
    }

    public void setProfesorID(Profesori profesorID) {
        this.profesorID = profesorID;
    }

    public Studenti getStudentID() {
        return studentID;
    }

    public void setStudentID(Studenti studentID) {
        this.studentID = studentID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vleresimiID != null ? vleresimiID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vleresimi)) {
            return false;
        }
        Vleresimi other = (Vleresimi) object;
        if ((this.vleresimiID == null && other.vleresimiID != null) || (this.vleresimiID != null && !this.vleresimiID.equals(other.vleresimiID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BLL.Vleresimi[ vleresimiID=" + vleresimiID + " ]";
    }
    
}
