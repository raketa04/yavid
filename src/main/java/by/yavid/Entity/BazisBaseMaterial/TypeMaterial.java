package by.yavid.Entity.BazisBaseMaterial;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "TYPE_MATERIAL")
public class TypeMaterial {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID_TM")
    private Integer id;

    @Column(name="NAME_TYPE")
    private String nameType;

    @OneToMany(mappedBy = "typeMaterial", cascade=CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval=true)
    private Set<GroupMaterial> groupMaterials = new HashSet<>();

    public TypeMaterial() {
    }

    public TypeMaterial(String nameType, Set<GroupMaterial> groupMaterials) {
        this.nameType = nameType;
        this.groupMaterials = groupMaterials;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameType() {
        return nameType;
    }

    public void setNameType(String nameType) {
        this.nameType = nameType;
    }

    public Set<GroupMaterial> getGroupMaterials() {
        return groupMaterials;
    }

    public void setGroupMaterials(Set<GroupMaterial> groupMaterials) {
        this.groupMaterials = groupMaterials;
    }
}
