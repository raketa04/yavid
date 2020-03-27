package by.yavid.Entity.Yavid;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "VARIANTI")
public class Variant {

    @Column(name="NAME_MAT")
    String name;

    @Column(name="NAME_MAT")
    String memo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
