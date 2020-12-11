package by.yavid.Service.Yavid;

import by.yavid.Entity.Yavid.Variant;

import java.util.List;

public interface VariantService {
    public List<Variant> GetVariantsByCodVar(String codVar);
    public List<String> getCodsModelsOfVariants(List<Variant> variants);
}
