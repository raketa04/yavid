package by.yavid.Entity.Yavid;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MODELLI")
public class Model {

    @Id
    @Column(name="COD")
    private String cod;

    @Column(name="DES")
    private String nameModel;

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getNameModel() {
        return nameModel;
    }

    public void setNameModel(String nameModel) {
        this.nameModel = nameModel;
    }
}
