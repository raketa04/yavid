package by.yavid.Entity.Yavid;


import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "VARIANTI")
public class Variant {

    @EmbeddedId
    private CodVariant codVariant;

    @Column(name="DES")
    private String name;

    @Column(name="MEMO")
    private String memo;

    public Variant() {
    }

    public CodVariant getCodVariant() {
        return codVariant;
    }

    public void setCodVariant(CodVariant codVariant) {
        this.codVariant = codVariant;
    }

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

    public String getParametrFromVariant(int number){
        String[] parametrs = memo.split("\t");
        if(parametrs.length > number) {
            return parametrs[number];
        }
        else {
            return "";
        }
    }

}
