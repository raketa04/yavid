package by.yavid.Entity.Workbase.Bazis;

import javax.persistence.*;
import by.yavid.Entity.Workbase.Administrate.User;
import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "KDList")
public class KDList {


    public interface getKDList{
    }



    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    @JsonView({getKDList.class})
    private Integer id;

    @Column(name = "NomKD")
    @JsonView({getKDList.class})
    private String numberKD;

    @Column(name = "Remark")
    @JsonView({getKDList.class})
    private String remark;


    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name="idGroupMC")
    @JsonView({getKDList.class})
    private GroupMC typeProduct;

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name="idUsers")
    @JsonView({getKDList.class})
    private User user;

    @OneToMany(mappedBy = "kdList", cascade=CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval=true)
    private Set<FilesBazisKD> filesBazisKDS = new HashSet<>();

    public KDList(){
    }


    public KDList(String numberKD, String remark, GroupMC typeProduct, User user, Set<FilesBazisKD> filesBazisKDS) {
        this.numberKD = numberKD;
        this.remark = remark;
        this.typeProduct = typeProduct;
        this.user = user;
        this.filesBazisKDS = filesBazisKDS;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumberKD() {
        return numberKD;
    }

    public void setNumberKD(String numberKD) {
        this.numberKD = numberKD;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public GroupMC getTypeProduct() {
        return typeProduct;
    }

    public void setTypeProduct(GroupMC typeProduct) {
        this.typeProduct = typeProduct;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<FilesBazisKD> getFilesBazisKDS() {
        return filesBazisKDS;
    }

    public void setFilesBazisKDS(Set<FilesBazisKD> filesBazisKDS) {
        this.filesBazisKDS = filesBazisKDS;
    }
}
