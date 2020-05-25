package by.yavid.Entity.Yavid;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CodVariant implements Serializable {

    @Column(name="CODVAR")
    private String codVar;

    @Column(name="CODOPZ")
    private String codOpz;

    public CodVariant() {
    }

    public String getCodVar() {
        return codVar;
    }

    public void setCodVar(String codVar) {
        this.codVar = codVar;
    }

    public String getCodOpz() {
        return codOpz;
    }

    public void setCodOpz(String codOpz) {
        this.codOpz = codOpz;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CodVariant that = (CodVariant) o;
        return Objects.equals(codVar, that.codVar) &&
                Objects.equals(codOpz, that.codOpz);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codVar, codOpz);
    }
}
