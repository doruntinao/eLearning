/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Doruntina
 */
@Entity
@Table(name = "Profesori")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Profesori.findAll", query = "SELECT p FROM Profesori p")
    , @NamedQuery(name = "Profesori.findByProfesorID", query = "SELECT p FROM Profesori p WHERE p.profesorID = :profesorID")
    , @NamedQuery(name = "Profesori.findByEmri", query = "SELECT p FROM Profesori p WHERE p.emri = :emri")
    , @NamedQuery(name = "Profesori.findByMbiemri", query = "SELECT p FROM Profesori p WHERE p.mbiemri = :mbiemri")
    , @NamedQuery(name = "Profesori.findByDataLindjes", query = "SELECT p FROM Profesori p WHERE p.dataLindjes = :dataLindjes")
    , @NamedQuery(name = "Profesori.findByGjinia", query = "SELECT p FROM Profesori p WHERE p.gjinia = :gjinia")
    , @NamedQuery(name = "Profesori.findByGradaAkademike", query = "SELECT p FROM Profesori p WHERE p.gradaAkademike = :gradaAkademike")})
public class Profesori implements Serializable {

    @OneToMany(mappedBy = "profesoriID")
    private Collection<StudentiKursi> studentiKursiCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ProfesorID")
    private Integer profesorID;
    @Column(name = "Emri")
    private String emri;
    @Column(name = "Mbiemri")
    private String mbiemri;
    @Column(name = "DataLindjes")
    @Temporal(TemporalType.DATE)
    private Date dataLindjes;
    @Column(name = "Gjinia")
    private Character gjinia;
    @Column(name = "GradaAkademike")
    private String gradaAkademike;
    @OneToMany(mappedBy = "profesoriID")
    private Collection<KursiProfesori> kursiProfesoriCollection;
    @OneToMany(mappedBy = "profesorID")
    private Collection<Vleresimi> vleresimiCollection;

    public Profesori() {
    }

    public Profesori(Integer profesorID) {
        this.profesorID = profesorID;
    }

    public Integer getProfesorID() {
        return profesorID;
    }

    public void setProfesorID(Integer profesorID) {
        this.profesorID = profesorID;
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

    public Date getDataLindjes() {
        return dataLindjes;
    }

    public void setDataLindjes(Date dataLindjes) {
        this.dataLindjes = dataLindjes;
    }

    public Character getGjinia() {
        return gjinia;
    }

    public void setGjinia(Character gjinia) {
        this.gjinia = gjinia;
    }

    public String getGradaAkademike() {
        return gradaAkademike;
    }

    public void setGradaAkademike(String gradaAkademike) {
        this.gradaAkademike = gradaAkademike;
    }

    @XmlTransient
    public Collection<KursiProfesori> getKursiProfesoriCollection() {
        return kursiProfesoriCollection;
    }

    public void setKursiProfesoriCollection(Collection<KursiProfesori> kursiProfesoriCollection) {
        this.kursiProfesoriCollection = kursiProfesoriCollection;
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
        hash += (profesorID != null ? profesorID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Profesori)) {
            return false;
        }
        Profesori other = (Profesori) object;
        if ((this.profesorID == null && other.profesorID != null) || (this.profesorID != null && !this.profesorID.equals(other.profesorID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return emri+" "+mbiemri;
    }

    @XmlTransient
    public Collection<StudentiKursi> getStudentiKursiCollection() {
        return studentiKursiCollection;
    }

    public void setStudentiKursiCollection(Collection<StudentiKursi> studentiKursiCollection) {
        this.studentiKursiCollection = studentiKursiCollection;
    }
    
}
