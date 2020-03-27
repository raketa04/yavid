package by.yavid.Entity.Workbase.Bazis;


import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "FilesBazisKD")
public class FilesBazisKD {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "idFileBazisKD")
    @JsonView({KDList.getKDList.class})
    private Integer idFileBazisKD;

    @Column(name = "revision")
    @JsonView({KDList.getKDList.class})
    private Integer revision;

    @Column(name = "filePath")
    private String filePath;

    @ManyToOne
    @JoinColumn(name="KDList")
    private KDList kdList;

    public FilesBazisKD(){

    }

    public FilesBazisKD (Integer revision,String filePath,KDList kdList){
        this.revision = revision;
        this.filePath = filePath;
        this.kdList = kdList;
    }
    public Integer getIdFileBazisKD() {
        return idFileBazisKD;
    }

    public void setIdFileBazisKD(Integer idFileBazisKD) {
        this.idFileBazisKD = idFileBazisKD;
    }

    public Integer getRevision() {
        return revision;
    }

    public void setRevision(Integer revision) {
        this.revision = revision;
    }

    public KDList getKdList() {
        return kdList;
    }

    public void setKdList(KDList kdList) {
        this.kdList = kdList;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
