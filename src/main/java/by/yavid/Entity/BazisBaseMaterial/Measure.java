package by.yavid.Entity.BazisBaseMaterial;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "MEASURE")
public class Measure {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID_MS")
    private Integer id;

    @Column(name = "NAME_MEAS")
    private String nameMeasure;

    @OneToMany(mappedBy = "groupMaterial", cascade=CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval=true)
    private Set<Material> materials = new HashSet<>();


    public Measure() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameMeasure() {
        return nameMeasure;
    }

    public void setNameMeasure(String nameMeasure) {
        this.nameMeasure = nameMeasure;
    }

    public Set<Material> getMaterials() {
        return materials;
    }

    public void setMaterials(Set<Material> materials) {
        this.materials = materials;
    }
}
