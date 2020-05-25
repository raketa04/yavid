package by.yavid.Service.Yavid;

import by.yavid.Entity.Yavid.Model;

import java.util.List;

public interface ModelService {
    public List<Model> getModelsFromCods(List<String> codsModels);
    public Model getModelFromCod(String codModel);
}
