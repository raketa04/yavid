package by.yavid.Entity.BazisBaseMaterial;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "MATERIAL")
public class Material {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID_M")
    Integer id;

    @Column(name="NAME_MAT")
    String nameMaterial;


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

}

