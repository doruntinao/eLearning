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
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Doruntina
 */
@Entity
@Table(name = "StudentiKursi")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StudentiKursi.findAll", query = "SELECT s FROM StudentiKursi s")
    , @NamedQuery(name = "StudentiKursi.findByStudentiKursiID", query = "SELECT s FROM StudentiKursi s WHERE s.studentiKursiID = :studentiKursiID")
    , @NamedQuery(name = "StudentiKursi.findByTipi", query = "SELECT s FROM StudentiKursi s WHERE s.tipi = :tipi")})
public class StudentiKursi implements Serializable {

    @JoinColumn(name = "ProfesoriID", referencedColumnName = "ProfesorID")
    @ManyToOne
    private Profesori profesoriID;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(generator = "InvSeq")
    @SequenceGenerator(name = "InvSeq", sequenceName = "INV_SEQ", allocationSize = 1)
    @Column(name = "StudentiKursiID")
    private Integer studentiKursiID;
    @Column(name = "Tipi")
    private String tipi;
    @JoinColumn(name = "KursiID", referencedColumnName = "KursID")
    @ManyToOne
    private Kursi kursiID;
    @JoinColumn(name = "StudentiID", referencedColumnName = "StudentID")
    @ManyToOne
    private Studenti studentiID;

    public StudentiKursi() {
    }

    public StudentiKursi(Integer studentiKursiID) {
        this.studentiKursiID = studentiKursiID;
    }

    public Integer getStudentiKursiID() {
        return studentiKursiID;
    }

    public void setStudentiKursiID(Integer studentiKursiID) {
        this.studentiKursiID = studentiKursiID;
    }

    public String getTipi() {
        return tipi;
    }

    public void setTipi(String tipi) {
        this.tipi = tipi;
    }

    public Kursi getKursiID() {
        return kursiID;
    }

    public void setKursiID(Kursi kursiID) {
        this.kursiID = kursiID;
    }

    public Studenti getStudentiID() {
        return studentiID;
    }

    public void setStudentiID(Studenti studentiID) {
        this.studentiID = studentiID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (studentiKursiID != null ? studentiKursiID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StudentiKursi)) {
            return false;
        }
        StudentiKursi other = (StudentiKursi) object;
        if ((this.studentiKursiID == null && other.studentiKursiID != null) || (this.studentiKursiID != null && !this.studentiKursiID.equals(other.studentiKursiID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BLL.StudentiKursi[ studentiKursiID=" + studentiKursiID + " ]";
    }

    public Profesori getProfesoriID() {
        return profesoriID;
    }

    public void setProfesoriID(Profesori profesoriID) {
        this.profesoriID = profesoriID;
    }
    
}
