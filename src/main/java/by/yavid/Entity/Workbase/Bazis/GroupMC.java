package by.yavid.Entity.Workbase.Bazis;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "GroupMC")
public class GroupMC {

    public interface GetGroupMC{}
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    @JsonView({KDList.getKDList.class,GetGroupMC.class})
    private Integer id;

    @Column(name="NameGr")
    @JsonView({KDList.getKDList.class,GetGroupMC.class})
    private String nameGroup;

    @Column(name="cMainGr")
    @JsonView({KDList.getKDList.class})
    private int cMainGr;

    @OneToMany(mappedBy = "typeProduct", cascade=CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval=true)
    private Set<KDList> kd = new HashSet<>();

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

    public int getcMainGr() {
        return cMainGr;
    }

    public void setcMainGr(int cMainGr) {
        this.cMainGr = cMainGr;
    }

    public Set<KDList> getKd() {
        return kd;
    }

    public void setKd(Set<KDList> kd) {
        this.kd = kd;
    }
}
