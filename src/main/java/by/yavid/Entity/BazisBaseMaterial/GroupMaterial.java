package by.yavid.Entity.BazisBaseMaterial;

import javax.persistence.*;


import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "GROUP_MATERIAL")
public class GroupMaterial {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID_GRM")
    private Integer id;

    @Column(name="NAME_GROUP")
    private String nameGroup;

    @OneToMany(mappedBy = "groupMaterial", cascade=CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval=true)
    private Set<Material>  materials = new HashSet<>();

    @ManyToOne
    @JoinColumn(name="ID_TM")
    private TypeMaterial typeMaterial;

    @ManyToOne
    @JoinColumn(name="ENTRY")
    private GroupMaterial groupMaterial;

    public GroupMaterial() {
    }

    public GroupMaterial(String nameGroup, TypeMaterial typeMaterial, GroupMaterial groupMaterial) {
        this.nameGroup = nameGroup;
        this.typeMaterial = typeMaterial;
        this.groupMaterial = groupMaterial;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameGroup() {
        return nameGroup;
    }

    public void setNameGroup(String nameGroup) {
        this.nameGroup = nameGroup;
    }

    public Set<Material> getMaterials() {
        return materials;
    }

    public void setMaterials(Set<Material> materials) {
        this.materials = materials;
    }

    public GroupMaterial getGroupMaterial() {
        return groupMaterial;
    }

    public void setGroupMaterial(GroupMaterial groupMaterial) {
        this.groupMaterial = groupMaterial;
    }

    public TypeMaterial getTypeMaterial() {
        return typeMaterial;
    }

    public void setTypeMaterial(TypeMaterial typeMaterial) {
        this.typeMaterial = typeMaterial;
    }
}
