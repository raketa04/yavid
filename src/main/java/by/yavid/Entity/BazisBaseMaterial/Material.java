package by.yavid.Entity.BazisBaseMaterial;

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

    public Material() {
    }

    public Material(String nameMaterial, String codMaterial) {
        this.nameMaterial = nameMaterial;
        this.codMaterial = codMaterial;
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
}

