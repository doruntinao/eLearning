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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Doruntina
 */
@Entity
@Table(name = "Kursi")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Kursi.findAll", query = "SELECT k FROM Kursi k")
    , @NamedQuery(name = "Kursi.findByKursID", query = "SELECT k FROM Kursi k WHERE k.kursID = :kursID")
    , @NamedQuery(name = "Kursi.findByEmriKursit", query = "SELECT k FROM Kursi k WHERE k.emriKursit = :emriKursit")
    , @NamedQuery(name = "Kursi.findByKategoria", query = "SELECT k FROM Kursi k WHERE k.kategoria = :kategoria")})
public class Kursi implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "KursID")
    private Integer kursID;
    @Column(name = "EmriKursit")
    private String emriKursit;
    @Column(name = "Kategoria")
    private String kategoria;
    @OneToMany(mappedBy = "kursiID")
    private Collection<StudentiKursi> studentiKursiCollection;
    
    private static Kursi obj; 
    
    public Kursi() {
    }
    //Lazy loading design pattern
    public static synchronized Kursi getInstance() 
    { 
        if (obj==null) 
            obj = new Kursi(); 
        return obj; 
    } 

    public Kursi(Integer kursID) {
        this.kursID = kursID;
    }

    public Integer getKursID() {
        return kursID;
    }

    public void setKursID(Integer kursID) {
        this.kursID = kursID;
    }

    public String getEmriKursit() {
        return emriKursit;
    }

    public void setEmriKursit(String emriKursit) {
        this.emriKursit = emriKursit;
    }

    public String getKategoria() {
        return kategoria;
    }

    public void setKategoria(String kategoria) {
        this.kategoria = kategoria;
    }

    @XmlTransient
    public Collection<StudentiKursi> getStudentiKursiCollection() {
        return studentiKursiCollection;
    }

    public void setStudentiKursiCollection(Collection<StudentiKursi> studentiKursiCollection) {
        this.studentiKursiCollection = studentiKursiCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (kursID != null ? kursID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Kursi)) {
            return false;
        }
        Kursi other = (Kursi) object;
        if ((this.kursID == null && other.kursID != null) || (this.kursID != null && !this.kursID.equals(other.kursID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BLL.Kursi[ kursID=" + kursID + " ]";
    }
    
}
