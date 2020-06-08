package by.yavid.Entity.BazisBaseMaterial;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "MATERIAL_ADVANCE")
public class MaterialAdvance {

    @Id
    @Column(name = "ID_M")
    private Integer id;

    @OneToOne
    @PrimaryKeyJoinColumn(name="ID_M", referencedColumnName="ID_M")
    private Material material;

    @Column(name="THICKNESS")
    private double thickness;

    public MaterialAdvance() {
    }

    public MaterialAdvance(Integer id,Material material, double thickness) {
        this.id = id;
        this.material = material;
        this.thickness = thickness;
    }

    public MaterialAdvance( double thickness) {
        this.thickness = thickness;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public double getThickness() {
        return thickness;
    }

    public void setThickness(double thickness) {
        this.thickness = thickness;
    }
}
