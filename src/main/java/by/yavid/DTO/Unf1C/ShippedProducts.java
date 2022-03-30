package by.yavid.DTO.Unf1C;

import java.math.BigDecimal;

public class ShippedProducts {
    private TypeProduction typeProduction;
    private Units units;
    private BigDecimal price;

    public ShippedProducts(TypeProduction typeProduction, Units units, BigDecimal price) {
        this.typeProduction = typeProduction;
        this.units = units;
        this.price = price;
    }

    public TypeProduction getTypeProduction() {
        return typeProduction;
    }

    public void setTypeProduction(TypeProduction typeProduction) {
        this.typeProduction = typeProduction;
    }

    public Units getUnits() {
        return units;
    }

    public void setUnits(Units units) {
        this.units = units;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
