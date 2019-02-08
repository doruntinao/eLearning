/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Doruntina
 */
@Entity
@Table(name = "Studenti")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Studenti.findAll", query = "SELECT s FROM Studenti s")
    , @NamedQuery(name = "Studenti.findByStudentID", query = "SELECT s FROM Studenti s WHERE s.studentID = :studentID")
    , @NamedQuery(name = "Studenti.findByEmri", query = "SELECT s FROM Studenti s WHERE s.emri = :emri")
    , @NamedQuery(name = "Studenti.findByMbiemri", query = "SELECT s FROM Studenti s WHERE s.mbiemri = :mbiemri")
    , @NamedQuery(name = "Studenti.findByInteresat", query = "SELECT s FROM Studenti s WHERE s.interesat = :interesat")})
public class Studenti implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(generator = "InvSeq")
    @SequenceGenerator(name = "InvSeq", sequenceName = "INV_SEQ", allocationSize = 1)
    @Column(name = "StudentID")
    private Integer studentID;
    @Column(name = "Emri")
    private String emri;
    @Column(name = "Mbiemri")
    private String mbiemri;
    @Column(name = "Interesat")
    private String interesat;
    @OneToMany(mappedBy = "studentiID")
    private Collection<StudentiKursi> studentiKursiCollection;
    @OneToMany(mappedBy = "studentID")
    private Collection<Login> loginCollection;
    @OneToMany(mappedBy = "studentID")
    private Collection<Vleresimi> vleresimiCollection;

    public Studenti() {
    }

    public Studenti(Integer studentID) {
        this.studentID = studentID;
    }

    public Integer getStudentID() {
        return studentID;
    }

    public void setStudentID(Integer studentID) {
        this.studentID = studentID;
    }

    public String getEmri() {
        return emri;
    }

    public void setEmri(String emri) {
        this.emri = emri;
    }

    public String getMbiemri() {
        return mbiemri;
    }

    public void setMbiemri(String mbiemri) {
        this.mbiemri = mbiemri;
    }

    public String getInteresat() {
        return interesat;
    }

    public void setInteresat(String interesat) {
        this.interesat = interesat;
    }

    @XmlTransient
    public Collection<StudentiKursi> getStudentiKursiCollection() {
        return studentiKursiCollection;
    }

    public void setStudentiKursiCollection(Collection<StudentiKursi> studentiKursiCollection) {
        this.studentiKursiCollection = studentiKursiCollection;
    }

    @XmlTransient
    public Collection<Login> getLoginCollection() {
        return loginCollection;
    }

    public void setLoginCollection(Collection<Login> loginCollection) {
        this.loginCollection = loginCollection;
    }

    @XmlTransient
    public Collection<Vleresimi> getVleresimiCollection() {
        return vleresimiCollection;
    }

    public void setVleresimiCollection(Collection<Vleresimi> vleresimiCollection) {
        this.vleresimiCollection = vleresimiCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (studentID != null ? studentID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Studenti)) {
            return false;
        }
        Studenti other = (Studenti) object;
        if ((this.studentID == null && other.studentID != null) || (this.studentID != null && !this.studentID.equals(other.studentID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BLL.Studenti[ studentID=" + studentID + " ]";
    }
    
}
