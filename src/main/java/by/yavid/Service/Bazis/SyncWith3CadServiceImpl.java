package by.yavid.Service.Bazis;

import by.yavid.Entity.BazisBaseMaterial.*;
import by.yavid.Entity.Yavid.Model;
import by.yavid.Entity.Yavid.Variant;
import by.yavid.Repository.BazisBaseMaterial.MaterialRepository;
import by.yavid.Service.Yavid.ModelService;
import by.yavid.Service.Yavid.VariantService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SyncWith3CadServiceImpl implements SyncWith3CadService {

    private VariantService variantService;
    private MaterialRepository materialRepository;
    private ModelService modelService;
    private GroupMaterialService groupMaterialService;
    private MaterialService materialService;
    private MaterialAdvanceService materialAdvanceService;
    private TypeMaterialService typeMaterialService;
    private MeasureService measureService;

    public SyncWith3CadServiceImpl(VariantService variantService, MaterialRepository materialRepository, ModelService modelService,
                                   GroupMaterialService groupMaterialService, MaterialService materialService, MaterialAdvanceService materialAdvanceService,
                                   TypeMaterialService typeMaterialService, MeasureService measureService) {
        this.variantService = variantService;
        this.materialRepository = materialRepository;
        this.modelService = modelService;
        this.groupMaterialService = groupMaterialService;
        this.materialService = materialService;
        this.materialAdvanceService = materialAdvanceService;
        this.typeMaterialService = typeMaterialService;
        this.measureService = measureService;
    }

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
        TypeMaterial typeMaterial = typeMaterialService.getTypeMaterialFromNameType("Листовой и погонный материалы");
        for(String nameModel:namesModels){
            GroupMaterial groupMaterial = new GroupMaterial(nameModel,typeMaterial,groupMaterialFasad);
            groupMaterialService.AddGroupMaterial(groupMaterial);
        }


    }


    @Override
    public void SyncMaterial() {
        List<Variant> variants = variantService.GetVariantsByCodVar("material");
        GroupMaterial groupMaterial = groupMaterialService.GetGroupMaterialByName("01. Листовые материалы");
        Measure measure = measureService.getMeasureFromNameMeasure("кв.м");
        for(Variant variant:variants){
            if(!materialService.isPresentCodMaterial(variant.getParametrFromVariant(41)) & !variant.getParametrFromVariant(41).equals("")){
                Material material = new Material(variant.getName(),variant.getParametrFromVariant(41),groupMaterial,measure);
                material = materialService.AddMaterial(material);
                if (!variant.getParametrFromVariant(4).equals("")){
                    MaterialAdvance materialAdvance = new MaterialAdvance(material.getId(),Double.valueOf(variant.getParametrFromVariant(4)));
                    materialAdvanceService.AddMaterialAdvance(materialAdvance);
                }
            }
        }
    }

    @Override
    public void SyncFasad() {
        List<Variant> variants = variantService.GetVariantsByCodVar("tipfas");
        SyncGroupFasad(variants);
        Measure measure = measureService.getMeasureFromNameMeasure("кв.м");
        for(Variant variant:variants){
            if(!(materialService.isPresentCodMaterial(variant.getCodVariant().getCodOpz()))
                    & !(variant.getParametrFromVariant(2).equals("100") | variant.getParametrFromVariant(2).equals("500")|variant.getParametrFromVariant(2).equals(""))) {
                Model model = modelService.getModelFromCod(variant.getParametrFromVariant(2));
                GroupMaterial groupMaterial = groupMaterialService.GetGroupMaterialByName(model.getNameModel());
                Material material = new Material(variant.getName(),variant.getCodVariant().getCodOpz(),groupMaterial,measure);
                material = materialService.AddMaterial(material);
                MaterialAdvance materialAdvance = new MaterialAdvance(material.getId(),Double.valueOf(variant.getParametrFromVariant(1)));
                materialAdvanceService.AddMaterialAdvance(materialAdvance);
            }
        }
    }

    @Override
    public void SyncHandles() {
        List<Variant> variants = variantService.GetVariantsByCodVar("handles");
        Measure measure = measureService.getMeasureFromNameMeasure("шт");
        GroupMaterial groupMaterial = groupMaterialService.GetGroupMaterialByName("Ручки");
        for(Variant variant:variants){
            if(!materialService.isPresentCodMaterial(variant.getParametrFromVariant(0)) & !variant.getParametrFromVariant(6).equals("")) {
                Material material = new Material(variant.getName(),variant.getParametrFromVariant(6),groupMaterial,measure);
                materialService.AddMaterial(material);
            }
        }
    }

    @Override
    public void SyncFurniture() {
        Material material;

        List<Variant> variants = variantService.GetVariantsByCodVar("furmelkaya");
        Measure measureQuantity = measureService.getMeasureFromNameMeasure("шт");
        Measure measureKit = measureService.getMeasureFromNameMeasure("к-т");

        GroupMaterial groupHinges = groupMaterialService.GetGroupMaterialByName("Петли");
        GroupMaterial groupLiftingGears = groupMaterialService.GetGroupMaterialByName("Подъемные механизмы");
        GroupMaterial groupDrawerMehanism = groupMaterialService.GetGroupMaterialByName("Направляющие");
        GroupMaterial groupFilling = groupMaterialService.GetGroupMaterialByName("Наполнение");
        GroupMaterial groupFasteners = groupMaterialService.GetGroupMaterialByName("Крепеж");
        GroupMaterial groupMounts = groupMaterialService.GetGroupMaterialByName("Навески");
        for(Variant variant:variants){
            System.out.println(variant.getName() + " "+variant.getParametrFromVariant(35));
            if(!materialService.isPresentCodMaterial(variant.getParametrFromVariant(0)) & !variant.getParametrFromVariant(0).equals("")){
                if(!variant.getParametrFromVariant(2).equals("") & variant.getParametrFromVariant(3).equals("")
                        & variant.getParametrFromVariant(4).equals("") & variant.getParametrFromVariant(33).equals("")
                        & variant.getParametrFromVariant(34).equals("")){
                    material = new Material(variant.getName(),variant.getParametrFromVariant(0),groupHinges,measureKit);
                    materialService.AddMaterial(material);
                }
                if(variant.getParametrFromVariant(2).equals("") & !variant.getParametrFromVariant(3).equals("")
                        & variant.getParametrFromVariant(4).equals("")& variant.getParametrFromVariant(33).equals("")
                        & variant.getParametrFromVariant(34).equals("")){
                    material = new Material(variant.getName(),variant.getParametrFromVariant(0),groupDrawerMehanism,measureKit);
                    materialService.AddMaterial(material);
                }
                if(variant.getParametrFromVariant(2).equals("") & variant.getParametrFromVariant(3).equals("")
                        & !variant.getParametrFromVariant(4).equals("")& variant.getParametrFromVariant(33).equals("")
                        & variant.getParametrFromVariant(34).equals("")){
                    material = new Material(variant.getName(),variant.getParametrFromVariant(0),groupLiftingGears,measureKit);
                    materialService.AddMaterial(material);
                }
                if(variant.getParametrFromVariant(2).equals("") & variant.getParametrFromVariant(3).equals("")
                        & variant.getParametrFromVariant(4).equals("") & !variant.getParametrFromVariant(33).equals("")
                        & variant.getParametrFromVariant(34).equals("")){
                    material = new Material(variant.getName(),variant.getParametrFromVariant(0),groupFasteners,measureQuantity);
                    materialService.AddMaterial(material);
                }
                if(variant.getParametrFromVariant(2).equals("") & variant.getParametrFromVariant(3).equals("")
                        & variant.getParametrFromVariant(4).equals("")& variant.getParametrFromVariant(33).equals("")
                        & !variant.getParametrFromVariant(34).equals("")){
                    material = new Material(variant.getName(),variant.getParametrFromVariant(0),groupMounts,measureQuantity);
                    materialService.AddMaterial(material);
                }
                if(variant.getParametrFromVariant(2).equals("") & variant.getParametrFromVariant(3).equals("")
                        & variant.getParametrFromVariant(4).equals("")& variant.getParametrFromVariant(33).equals("")
                        & variant.getParametrFromVariant(34).equals("")){
                    material = new Material(variant.getName(),variant.getParametrFromVariant(0),groupFilling,measureQuantity);
                    materialService.AddMaterial(material);
                }
            }
        }
    }
}
