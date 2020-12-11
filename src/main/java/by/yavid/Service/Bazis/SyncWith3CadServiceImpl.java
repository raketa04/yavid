package by.yavid.Service.Bazis;

import by.yavid.Entity.BazisBaseMaterial.*;
import by.yavid.Entity.Yavid.Model;
import by.yavid.Entity.Yavid.Variant;
import by.yavid.Service.Yavid.ModelService;
import by.yavid.Service.Yavid.VariantService;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class SyncWith3CadServiceImpl implements SyncWith3CadService {

    private VariantService variantService;
    private ModelService modelService;
    private GroupMaterialService groupMaterialService;
    private MaterialService materialService;
    private MaterialAdvanceService materialAdvanceService;

    private MeasureService measureService;

    public SyncWith3CadServiceImpl(VariantService variantService, ModelService modelService, GroupMaterialService groupMaterialService,
                                   MaterialService materialService, MaterialAdvanceService materialAdvanceService,
                                   MeasureService measureService) {
        this.variantService = variantService;
        this.modelService = modelService;
        this.groupMaterialService = groupMaterialService;
        this.materialService = materialService;
        this.materialAdvanceService = materialAdvanceService;
        this.measureService = measureService;
    }




    private void SyncGroupFasad(List<Variant> variants){
        List<String> codsModels =  variantService.getCodsModelsOfVariants(variants);
        List<Model> models = modelService.getModelsFromCods(codsModels);
        ArrayList<String> namesModels =  new ArrayList<>();
        for(Model model:models){
            namesModels.add(model.getNameModel());
        }
        groupMaterialService.addGroupsMaterialForFasad(namesModels);
    }


    @Override
    public void SyncMaterial() {
        List<Variant> variants = variantService.GetVariantsByCodVar("material");
        GroupMaterial groupMaterial = groupMaterialService.getGroupMaterialByName("01. Листовые материалы");
        Measure measure = measureService.getMeasureFromNameMeasure("кв.м");

        int numberParametrCodMaterialFromVariant = 41;
        int numberParametrDepthMaterialFromVariant = 4;

        for(Variant variant:variants){
            if(!variant.getParametrFromVariant(numberParametrCodMaterialFromVariant).equals("")){
                if(materialService.isPresentCodMaterial(variant.getParametrFromVariant(numberParametrCodMaterialFromVariant))){

                }
                else {
                    Material material = new Material(variant.getName(), variant.getParametrFromVariant(numberParametrCodMaterialFromVariant), groupMaterial, measure);
                    material = materialService.saveMaterial(material);
                    if (!variant.getParametrFromVariant(numberParametrDepthMaterialFromVariant).equals("")) {
                        MaterialAdvance materialAdvance = new MaterialAdvance(material.getId(), Double.valueOf(variant.getParametrFromVariant(numberParametrDepthMaterialFromVariant)));
                        materialAdvanceService.AddMaterialAdvance(materialAdvance);
                    }
                }
            }
        }
    }

    @Override
    public void SyncFasad() {
        List<Variant> variants = variantService.GetVariantsByCodVar("tipfas");
        SyncGroupFasad(variants);
        Measure measure = measureService.getMeasureFromNameMeasure("кв.м");
        int numberParametrCodModelFromVariant = 2;
        int numberParametrDepthFasadFromVariant = 1;
        for(Variant variant:variants){
            if(!(materialService.isPresentCodMaterial(variant.getCodVariant().getCodOpz()))
                    & !(variant.getParametrFromVariant(numberParametrCodModelFromVariant).equals("100") | variant.getParametrFromVariant(numberParametrCodModelFromVariant).equals("500")
                    | variant.getParametrFromVariant(numberParametrCodModelFromVariant).equals(""))) {
                Model model = modelService.getModelFromCod(variant.getParametrFromVariant(numberParametrCodModelFromVariant));
                GroupMaterial groupMaterial = groupMaterialService.getGroupMaterialByName(model.getNameModel());
                Material material = new Material(variant.getName(),variant.getCodVariant().getCodOpz(),groupMaterial,measure);
                material = materialService.saveMaterial(material);
                MaterialAdvance materialAdvance = new MaterialAdvance(material.getId(),Double.valueOf(variant.getParametrFromVariant(numberParametrDepthFasadFromVariant)));
                materialAdvanceService.AddMaterialAdvance(materialAdvance);
            }
        }
    }

    @Override
    public void SyncHandles() {
        List<Variant> variants = variantService.GetVariantsByCodVar("handles");
        Measure measure = measureService.getMeasureFromNameMeasure("шт");
        GroupMaterial groupMaterial = groupMaterialService.getGroupMaterialByName("Ручки");
        int numberParametrCodHandlesFromVariant = 6;
        for(Variant variant:variants){
            Material material = new Material(variant.getName(), variant.getParametrFromVariant(numberParametrCodHandlesFromVariant), groupMaterial, measure);
            if(materialService.isPresentCodMaterial(variant.getParametrFromVariant(numberParametrCodHandlesFromVariant))) {
                Material materialInBase = materialService.getMaterialByCod(variant.getParametrFromVariant(numberParametrCodHandlesFromVariant));
                if(!material.equals(materialInBase)) {
                    material.setId(materialInBase.getId());
                    materialService.saveMaterial(material);
                }
            }
            else{
                if (!variant.getParametrFromVariant(numberParametrCodHandlesFromVariant).equals("")) {
                    materialService.saveMaterial(material);
                }
            }
        }
    }

    @Override
    public void SyncFurniture() {
        Material material = new Material();

        List<Variant> variants = variantService.GetVariantsByCodVar("furmelkaya");
        Measure measureQuantity = measureService.getMeasureFromNameMeasure("шт");
        Measure measureKit = measureService.getMeasureFromNameMeasure("к-т");

        GroupMaterial groupHinges = groupMaterialService.getGroupMaterialByName("Петли");
        GroupMaterial groupLiftingGears = groupMaterialService.getGroupMaterialByName("Подъемные механизмы");
        GroupMaterial groupDrawerMehanism = groupMaterialService.getGroupMaterialByName("Направляющие");
        GroupMaterial groupFilling = groupMaterialService.getGroupMaterialByName("Наполнение");
        GroupMaterial groupFasteners = groupMaterialService.getGroupMaterialByName("Крепеж");
        GroupMaterial groupMounts = groupMaterialService.getGroupMaterialByName("Навески");

        int numberParametrIsHingesFromVariant = 2;
        int numberParametrIsDrawerMehanismFromVariant = 3;
        int numberParametrIsLiftingGearsFromVariant = 4;
        int numberParametrIsFastenersFromVariant = 33;
        int numberParametrIsGroupMountsFromVariant = 34;
        int numberParametrCodFurnitureFromVariant = 0;

        for(Variant variant:variants){
            if(!materialService.isPresentCodMaterial(variant.getParametrFromVariant(numberParametrCodFurnitureFromVariant)) & !variant.getParametrFromVariant(numberParametrCodFurnitureFromVariant).equals("")){
                int i = 0;
                if(!variant.getParametrFromVariant(numberParametrIsHingesFromVariant).equals("")){
                    material = new Material(variant.getName(),variant.getParametrFromVariant(numberParametrCodFurnitureFromVariant),groupHinges,measureKit);
                    i++;
                }
                if(!variant.getParametrFromVariant(numberParametrIsDrawerMehanismFromVariant).equals("")){
                    material = new Material(variant.getName(),variant.getParametrFromVariant(numberParametrCodFurnitureFromVariant),groupDrawerMehanism,measureKit);
                    i++;
                }
                if(!variant.getParametrFromVariant(numberParametrIsLiftingGearsFromVariant).equals("")){
                    material = new Material(variant.getName(),variant.getParametrFromVariant(numberParametrCodFurnitureFromVariant),groupLiftingGears,measureKit);
                    i++;
                }
                if(!variant.getParametrFromVariant(numberParametrIsFastenersFromVariant).equals("")){
                    material = new Material(variant.getName(),variant.getParametrFromVariant(numberParametrCodFurnitureFromVariant),groupFasteners,measureQuantity);
                    i++;
                }
                if( !variant.getParametrFromVariant(numberParametrIsGroupMountsFromVariant).equals("")){
                    material = new Material(variant.getName(),variant.getParametrFromVariant(numberParametrCodFurnitureFromVariant),groupMounts,measureQuantity);
                    i++;
                }
                if( i == 0) material = new Material(variant.getName(),variant.getParametrFromVariant(numberParametrCodFurnitureFromVariant),groupFilling,measureQuantity);
                if (!(i > 1)) materialService.saveMaterial(material);

            }
        }
    }
}
