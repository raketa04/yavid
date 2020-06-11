package by.yavid.Entity.BazisBaseMaterial;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "MATERIAL")
public class Material {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID_M")
    private Integer id;

    @Column(name="NAME_MAT")
    private String nameMaterial;


    @Column(name="ARTICLE")
    private String codMaterial;

    @ManyToOne
    @JoinColumn(name="ID_GRM")
    private GroupMaterial groupMaterial;

    @OneToOne(mappedBy = "material",cascade=CascadeType.ALL,fetch = FetchType.LAZY)
    private MaterialAdvance materialAdvance;


    public Material() {
    }

    public Material(String nameMaterial, String codMaterial, GroupMaterial groupMaterial) {
        this.nameMaterial = nameMaterial;
        this.codMaterial = codMaterial;
        this.groupMaterial = groupMaterial;
    }

    public Material(String nameMaterial, String codMaterial, GroupMaterial groupMaterial,MaterialAdvance materialAdvance) {
        this.nameMaterial = nameMaterial;
        this.codMaterial = codMaterial;
        this.groupMaterial = groupMaterial;
        this.materialAdvance = materialAdvance;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameMaterial() {
        return nameMaterial;
    }

    public void setNameMaterial(String nameMaterial) {
        this.nameMaterial = nameMaterial;
    }


    public String getCodMaterial() {
        return codMaterial;
    }

    public void setCodMaterial(String codMaterial) {
        this.codMaterial = codMaterial;
    }

    public GroupMaterial getGroupMaterial() {
        return groupMaterial;
    }

    public void setGroupMaterial(GroupMaterial groupMaterial) {
        this.groupMaterial = groupMaterial;
    }

    public MaterialAdvance getMaterialAdvance() {
        return materialAdvance;
    }

    public void setMaterialAdvance(MaterialAdvance materialAdvance) {
        this.materialAdvance = materialAdvance;
    }
}

