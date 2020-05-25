package by.yavid.Service.Bazis;

import by.yavid.Entity.BazisBaseMaterial.GroupMaterial;
import by.yavid.Entity.BazisBaseMaterial.Material;
import by.yavid.Entity.Yavid.Model;
import by.yavid.Entity.Yavid.Variant;
import by.yavid.Repository.BazisBaseMaterial.MaterialRepository;
import by.yavid.Service.Yavid.ModelService;
import by.yavid.Service.Yavid.VariantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SyncWith3CadServiceImpl implements SyncWith3CadService {

    @Autowired
    VariantService variantService;

    @Autowired
    MaterialRepository materialRepository;

    @Autowired
    ModelService modelService;

    @Autowired
    GroupMaterialService groupMaterialService;

    @Autowired
    MaterialService materialService;


    private void SyncGroupFasad(List<Variant> variants){

        ArrayList<String> codsModels =  new ArrayList<>();
        for (Variant variant:variants) {
            if(!(variant.getParametrFromVariant(2).equals("100")
                    | variant.getParametrFromVariant(2).equals("500")
                    | codsModels.contains(variant.getParametrFromVariant(2)))){
                codsModels.add(variant.getParametrFromVariant(2));
            }
        }

        List<Model> models = modelService.getModelsFromCods(codsModels);

        ArrayList<String> namesModels =  new ArrayList<>();
        for(Model model:models){
            namesModels.add(model.getNameModel());
        }

        List<GroupMaterial> groupsMaterials = groupMaterialService.GetGroupsMaterialsInNames(namesModels);
        GroupMaterial groupMaterialFasad = groupMaterialService.GetGroupMaterialByName("08.Фасады");
        for(GroupMaterial groupMaterial:groupsMaterials){
            if(namesModels.contains(groupMaterial.getNameGroup())){
                namesModels.remove(groupMaterial.getNameGroup());
            }
        }
        for(String nameModel:namesModels){
            GroupMaterial groupMaterial = new GroupMaterial(nameModel,groupMaterialFasad);
            groupMaterialService.AddGroupMaterial(groupMaterial);
        }


    }


    @Override
    public void SyncMaterial() {
        List<Variant> variants = variantService.GetVariantsByCodVar("material");
        for(Variant variant:variants){
            if(true){
            }
        }
    }

    @Override
    public void SyncFasad() {

        List<Variant> variants = variantService.GetVariantsByCodVar("tipfas");

        SyncGroupFasad(variants);

        for(Variant variant:variants){
            if(!(materialService.isPresentCodMaterial(variant.getParametrFromVariant(9)))
                    & !(variant.getParametrFromVariant(2).equals("100") | variant.getParametrFromVariant(2).equals("500")|variant.getParametrFromVariant(2).equals(""))) {
                Model model = modelService.getModelFromCod(variant.getParametrFromVariant(2));
                GroupMaterial groupMaterial = groupMaterialService.GetGroupMaterialByName(model.getNameModel());
                Material material = new Material(variant.getName(),variant.getParametrFromVariant(9),groupMaterial);
                materialService.AddMaterial(material);
            }
        }

    }

    @Override
    public void SyncFurniture() {

    }
}
