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

    @Column(name="NAME_MAT")
    String memo1;


}
