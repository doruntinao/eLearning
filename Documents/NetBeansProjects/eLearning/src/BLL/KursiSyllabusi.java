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
@Table(name = "KursiSyllabusi")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "KursiSyllabusi.findAll", query = "SELECT k FROM KursiSyllabusi k")
    , @NamedQuery(name = "KursiSyllabusi.findByKursiSyllabusiID", query = "SELECT k FROM KursiSyllabusi k WHERE k.kursiSyllabusiID = :kursiSyllabusiID")})
public class KursiSyllabusi implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "KursiSyllabusiID")
    private Integer kursiSyllabusiID;
    @JoinColumn(name = "KursiID", referencedColumnName = "KursID")
    @ManyToOne
    private Kursi kursiID;
    @JoinColumn(name = "SyllabusiID", referencedColumnName = "SyllabusID")
    @ManyToOne
    private Syllabusi syllabusiID;

    public KursiSyllabusi() {
    }

    public KursiSyllabusi(Integer kursiSyllabusiID) {
        this.kursiSyllabusiID = kursiSyllabusiID;
    }

    public Integer getKursiSyllabusiID() {
        return kursiSyllabusiID;
    }

    public void setKursiSyllabusiID(Integer kursiSyllabusiID) {
        this.kursiSyllabusiID = kursiSyllabusiID;
    }

    public Kursi getKursiID() {
        return kursiID;
    }

    public void setKursiID(Kursi kursiID) {
        this.kursiID = kursiID;
    }

    public Syllabusi getSyllabusiID() {
        return syllabusiID;
    }

    public void setSyllabusiID(Syllabusi syllabusiID) {
        this.syllabusiID = syllabusiID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (kursiSyllabusiID != null ? kursiSyllabusiID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof KursiSyllabusi)) {
            return false;
        }
        KursiSyllabusi other = (KursiSyllabusi) object;
        if ((this.kursiSyllabusiID == null && other.kursiSyllabusiID != null) || (this.kursiSyllabusiID != null && !this.kursiSyllabusiID.equals(other.kursiSyllabusiID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BLL.KursiSyllabusi[ kursiSyllabusiID=" + kursiSyllabusiID + " ]";
    }
    
}
