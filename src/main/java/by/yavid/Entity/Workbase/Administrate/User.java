package by.yavid.Entity.Workbase.Administrate;

import by.yavid.Entity.Workbase.Bazis.KDList;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "Users")
public class User {

    public interface getUser{
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "idUsers")
    @JsonView({KDList.getKDList.class,getUser.class})
    private Integer id;

    @Column(name="FIO")
    @JsonView({KDList.getKDList.class,getUser.class})
    private String fio;

    @Column(name="UserName")
    @JsonView({KDList.getKDList.class,getUser.class})
    private String userName;

    @OneToMany(mappedBy = "user", cascade=CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval=true)
    private Set<KDList> kd = new HashSet<>();

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public Set<KDList> getKd() {
        return kd;
    }

    public void setKd(Set<KDList> kd) {
        this.kd = kd;
    }
    public void addKd(KDList kd){
        kd.setUser(this);
        getKd().add(kd);
    }
    public void removeKd(KDList kd){
        getKd().remove(kd);
    }
}
