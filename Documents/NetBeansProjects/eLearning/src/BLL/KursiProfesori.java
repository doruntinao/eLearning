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
@Table(name = "KursiProfesori")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "KursiProfesori.findAll", query = "SELECT k FROM KursiProfesori k")
    , @NamedQuery(name = "KursiProfesori.findByKursiProfesoriID", query = "SELECT k FROM KursiProfesori k WHERE k.kursiProfesoriID = :kursiProfesoriID")})
public class KursiProfesori implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "KursiProfesoriID")
    private Integer kursiProfesoriID;
    @JoinColumn(name = "KursiID", referencedColumnName = "KursID")
    @ManyToOne
    private Kursi kursiID;
    @JoinColumn(name = "ProfesoriID", referencedColumnName = "ProfesorID")
    @ManyToOne
    private Profesori profesoriID;

    public KursiProfesori() {
    }

    public KursiProfesori(Integer kursiProfesoriID) {
        this.kursiProfesoriID = kursiProfesoriID;
    }

    public Integer getKursiProfesoriID() {
        return kursiProfesoriID;
    }

    public void setKursiProfesoriID(Integer kursiProfesoriID) {
        this.kursiProfesoriID = kursiProfesoriID;
    }

    public Kursi getKursiID() {
        return kursiID;
    }

    public void setKursiID(Kursi kursiID) {
        this.kursiID = kursiID;
    }

    public Profesori getProfesoriID() {
        return profesoriID;
    }

    public void setProfesoriID(Profesori profesoriID) {
        this.profesoriID = profesoriID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (kursiProfesoriID != null ? kursiProfesoriID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof KursiProfesori)) {
            return false;
        }
        KursiProfesori other = (KursiProfesori) object;
        if ((this.kursiProfesoriID == null && other.kursiProfesoriID != null) || (this.kursiProfesoriID != null && !this.kursiProfesoriID.equals(other.kursiProfesoriID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BLL.KursiProfesori[ kursiProfesoriID=" + kursiProfesoriID + " ]";
    }
    
}
