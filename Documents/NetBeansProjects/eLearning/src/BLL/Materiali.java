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
import javax.persistence.Lob;
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
@Table(name = "Materiali")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Materiali.findAll", query = "SELECT m FROM Materiali m")
    , @NamedQuery(name = "Materiali.findByMaterialID", query = "SELECT m FROM Materiali m WHERE m.materialID = :materialID")
    , @NamedQuery(name = "Materiali.findByMaterialet", query = "SELECT m FROM Materiali m WHERE m.materialet = :materialet")})
public class Materiali implements Serializable {

    @Column(name = "Emri")
    private String emri;
    @Lob
    @Column(name = "Materialet")
    private byte[] materialet;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "MaterialID")
    private Integer materialID;
    @OneToMany(mappedBy = "materialiID")
    private Collection<MaterialiKursi> materialiKursiCollection;

    public Materiali() {
    }

    public Materiali(Integer materialID) {
        this.materialID = materialID;
    }

    public Integer getMaterialID() {
        return materialID;
    }

    public void setMaterialID(Integer materialID) {
        this.materialID = materialID;
    }


    @XmlTransient
    public Collection<MaterialiKursi> getMaterialiKursiCollection() {
        return materialiKursiCollection;
    }

    public void setMaterialiKursiCollection(Collection<MaterialiKursi> materialiKursiCollection) {
        this.materialiKursiCollection = materialiKursiCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (materialID != null ? materialID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Materiali)) {
            return false;
        }
        Materiali other = (Materiali) object;
        if ((this.materialID == null && other.materialID != null) || (this.materialID != null && !this.materialID.equals(other.materialID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BLL.Materiali[ materialID=" + materialID + " ]";
    }

    public String getEmri() {
        return emri;
    }

    public void setEmri(String emri) {
        this.emri = emri;
    }

    public byte[] getMaterialet() {
        return materialet;
    }

    public void setMaterialet(byte[] materialet) {
        this.materialet = materialet;
    }
    
}
