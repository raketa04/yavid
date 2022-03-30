package by.yavid.Service.Report.Statistic;

import by.yavid.DTO.Unf1C.ShippedOrder;
import by.yavid.Entity.Ecadmaster.ItemOrder;
import by.yavid.Entity.Yavid.ProductInModel;
import by.yavid.Service.Ecadmaster.OrderService;
import by.yavid.Service.Yavid.ProductService;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Service;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ReportShippedProductsForStatistics {

    private ProductService productService;
    private OrderService orderService;
    private RateService rateService;

    private String PathReport;

    public ReportShippedProductsForStatistics(ProductService productService, OrderService orderService,RateService rateService) {
        this.productService = productService;
        this.orderService = orderService;
        this.rateService = rateService;
    }

    private void printTextFromSheetExcel(HSSFSheet sheet,int positionRow,String textFromCell2,String textFromCell3){
        Cell cell;
        Row row;
        row = sheet.getRow(positionRow);
        cell = row.getCell(2);
        cell.setCellValue(textFromCell2);
        cell = row.getCell(3);
        cell.setCellValue(textFromCell3);
    }

    private double sumPriceDetailInItemOrder(ItemOrder itemOrder ,List<ItemOrder> componentsItemOrder, ShippedOrder order,float rate){
        double sum = getPriceItemDependingOnTypePrice(itemOrder,order.getTypePrice(),order.getCurrency(),rate);
        for(ItemOrder componentItemOrder:componentsItemOrder){
            sum += getPriceItemDependingOnTypePrice(componentItemOrder,order.getTypePrice(),order.getCurrency(),rate);
        }
        return sum;
    }

    private double getPriceItemDependingOnTypePrice(ItemOrder itemOrder,Integer typePrice,int typeCurrency,float rate){
        itemOrder.setExtract(true);
        if (typePrice==1){
            if (typeCurrency == 643 || typeCurrency ==978) return Math.round(itemOrder.getPriceRetail())*rate;
            else return itemOrder.getPriceRetail();
        }
        else if (typePrice==2) {
            if (typeCurrency == 643 || typeCurrency ==978) return Math.round(itemOrder.getPriceTrade())*rate;
            else return itemOrder.getPriceRetail();
        }
        return 0;
    }

    public Boolean report(List<ShippedOrder> orders){

        int countComplectProductsFromKitchen = 0;
        int countOneProductsInOrderFromKitchen = 0;
        float priceComplectProductsFromKitchen = 0;

        int countComplectProductsFromBedroom = 0;
        int countOneProductsInOrderFromBedroom = 0;
        float priceComplectProductsFromBedroom = 0;

        int countComplectProductsFromDiningAndLivingRoom = 0;
        int countOneProductsInOrderFromDiningAndLivingRoom = 0;
        float priceComplectProductsFromDiningAndLivingRoom  = 0;

        int countOfProductsFromHallway = 0;
        float priceComplectProductsFromHallway = 0;

        int countUpholsteredChairs = 0;
        int countUnupholsteredChairs = 0;
        long priceChairs=0;

        for(ShippedOrder order:orders){
            int countOfProductsFromKitchenInOrder = 0;
            int countOfProductsFromBedroomInOrder = 0;
            int countOfProductsFromDiningAndLivingRoomInOrder = 0;
            boolean manualInputFromKitchenInOrder = false;
            boolean manualInputFromBedroomInOrder = false;
            boolean manualInputFromDiningAndLivingRoomInOrder = false;
            boolean manualInputFromHallway = false;
            float rate = 1;
            if(order.getCurrency()!=933) rate = rateService.getCusrRateForBYN(order.getCurrency(),order.getDateShipment());
            List<ItemOrder> itemsOrder = orderService.getItemsOrderFromOrder("YAVID",order.getNumberOrder());
            for(ItemOrder itemOrder:itemsOrder) {
                ProductInModel product = productService.getProductByCodProduct(itemOrder.getCodProduct());
                Integer typeProduction = product.getProduct().getTypeProduct();
                List<ItemOrder> componentsItemOrder = orderService.getComponentItemOrderFromOrder("YAVID",order.getNumberOrder(),itemOrder.getItemOrderId().getPositionInOrder());
                if (typeProduction >= 1000 && typeProduction < 3000) {
                    countOfProductsFromKitchenInOrder += itemOrder.getNumberProduct();
                    priceComplectProductsFromKitchen += sumPriceDetailInItemOrder(itemOrder,componentsItemOrder,order,rate);
                    continue;
                }
                if (typeProduction >= 6500 && typeProduction < 7000) {
                    countOfProductsFromBedroomInOrder += itemOrder.getNumberProduct();
                    priceComplectProductsFromBedroom += sumPriceDetailInItemOrder(itemOrder,componentsItemOrder,order,rate);
                    continue;
                }
                if ((typeProduction >= 6000 && typeProduction < 6200) || (typeProduction >= 7000 && typeProduction < 7400)
                        || (typeProduction >= 4000 && typeProduction < 4030)){
                    countOfProductsFromDiningAndLivingRoomInOrder += itemOrder.getNumberProduct();
                    priceComplectProductsFromDiningAndLivingRoom += sumPriceDetailInItemOrder(itemOrder,componentsItemOrder,order,rate);
                    continue;
                }
                if (typeProduction >= 5000 && typeProduction < 6000) {
                    countOfProductsFromHallway += itemOrder.getNumberProduct();
                    priceComplectProductsFromHallway+= sumPriceDetailInItemOrder(itemOrder,componentsItemOrder,order,rate);
                    continue;
                }
                if (typeProduction >= 4030 && typeProduction < 4040) {
                    countUpholsteredChairs += itemOrder.getNumberProduct();
                    priceChairs+= sumPriceDetailInItemOrder(itemOrder,componentsItemOrder,order,rate);
                    continue;
                }
                if (typeProduction >= 4040 && typeProduction < 4060) {
                    countUnupholsteredChairs += itemOrder.getNumberProduct();
                    priceChairs+= sumPriceDetailInItemOrder(itemOrder,componentsItemOrder,order,rate);
                    continue;
                }
                if((Integer.parseInt(product.getModel().getCod()) >100 && Integer.parseInt(product.getModel().getCod()) <=110) ||
                        Integer.parseInt(product.getModel().getCod()) ==190){
                    if (typeProduction == 8900) countOfProductsFromKitchenInOrder += itemOrder.getNumberProduct();
                    else manualInputFromKitchenInOrder = true;
                    priceComplectProductsFromKitchen += sumPriceDetailInItemOrder(itemOrder,componentsItemOrder,order,rate);
                    continue;
                }
                if((Integer.parseInt(product.getModel().getCod()) >=140 && Integer.parseInt(product.getModel().getCod()) <150) ||
                        (Integer.parseInt(product.getModel().getCod()) >=164 && Integer.parseInt(product.getModel().getCod()) <=168)||
                        (Integer.parseInt(product.getModel().getCod()) == 181)){
                    if (typeProduction == 8900) countOfProductsFromDiningAndLivingRoomInOrder += itemOrder.getNumberProduct();
                    else manualInputFromDiningAndLivingRoomInOrder = true;
                    priceComplectProductsFromDiningAndLivingRoom+= sumPriceDetailInItemOrder(itemOrder,componentsItemOrder,order,rate);
                    continue;
                }
                if(Integer.parseInt(product.getModel().getCod()) >=150 && Integer.parseInt(product.getModel().getCod()) <160) {
                    if (typeProduction == 8900) countOfProductsFromHallway += itemOrder.getNumberProduct();
                    else manualInputFromHallway = true;
                    priceComplectProductsFromHallway += sumPriceDetailInItemOrder(itemOrder,componentsItemOrder,order,rate);
                    continue;
                }
                if(Integer.parseInt(product.getModel().getCod()) >=173 && Integer.parseInt(product.getModel().getCod()) <=179) {
                    if (typeProduction == 8900) countOfProductsFromBedroomInOrder += itemOrder.getNumberProduct();
                    else manualInputFromBedroomInOrder = true;
                    priceComplectProductsFromBedroom += sumPriceDetailInItemOrder(itemOrder,componentsItemOrder,order,rate);
                    continue;
                }

                for(ItemOrder componentItemOrder:componentsItemOrder){
                    if (!componentItemOrder.isExtract()) System.out.println(order.getNumberOrder()+ " " + componentItemOrder.getItemOrderId().getPositionInOrder() + " " + componentItemOrder.getCodProduct());
                }
            }
            for(ItemOrder itemOrder:itemsOrder) {
                if (!itemOrder.isExtract()) System.out.println(order.getNumberOrder() + " " + itemOrder.getCodProduct());
            }
            if(manualInputFromKitchenInOrder) countOfProductsFromKitchenInOrder++;
            if(manualInputFromBedroomInOrder) countOfProductsFromBedroomInOrder++;
            if(manualInputFromDiningAndLivingRoomInOrder) countOfProductsFromDiningAndLivingRoomInOrder++;
            if(manualInputFromHallway) countOfProductsFromHallway++;
            if (countOfProductsFromKitchenInOrder ==  1) countOneProductsInOrderFromKitchen++;
            else if (countOfProductsFromKitchenInOrder >  0) countComplectProductsFromKitchen++;
            if (countOfProductsFromBedroomInOrder ==  1) countOneProductsInOrderFromBedroom++;
            else if (countOfProductsFromBedroomInOrder >  0)countComplectProductsFromBedroom++;
            if (countOfProductsFromDiningAndLivingRoomInOrder ==  1) countOneProductsInOrderFromDiningAndLivingRoom++;
            else if (countOfProductsFromDiningAndLivingRoomInOrder >  0) countComplectProductsFromDiningAndLivingRoom++;
        }

        FileInputStream inputStream;
        try {
            File fileTemplate = new File("C:\\template.xls");
            inputStream = new FileInputStream(fileTemplate);
            HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
            HSSFSheet sheet = workbook.getSheet("fdsgdf");


            printTextFromSheetExcel(sheet,1,String.valueOf(countUnupholsteredChairs+countUpholsteredChairs),String.valueOf(priceChairs));
            printTextFromSheetExcel(sheet,2,String.valueOf(countUpholsteredChairs),"");
            printTextFromSheetExcel(sheet,3,String.valueOf(countUnupholsteredChairs),"");

            printTextFromSheetExcel(sheet,5,String.valueOf(countComplectProductsFromKitchen),"");

            printTextFromSheetExcel(sheet,6,String.valueOf(priceComplectProductsFromKitchen),"");

            printTextFromSheetExcel(sheet,8,String.valueOf(countOneProductsInOrderFromBedroom),"");
            printTextFromSheetExcel(sheet,9,String.valueOf(countComplectProductsFromBedroom),"");
            printTextFromSheetExcel(sheet,10,String.valueOf(priceComplectProductsFromBedroom),"");


            printTextFromSheetExcel(sheet,11,String.valueOf(countOneProductsInOrderFromDiningAndLivingRoom),"");
            printTextFromSheetExcel(sheet,12,String.valueOf(countComplectProductsFromDiningAndLivingRoom),"");

            printTextFromSheetExcel(sheet,14,String.valueOf(priceComplectProductsFromBedroom + priceComplectProductsFromDiningAndLivingRoom),"");

            printTextFromSheetExcel(sheet,16,String.valueOf(countOneProductsInOrderFromDiningAndLivingRoom),String.valueOf(priceComplectProductsFromHallway));

            printTextFromSheetExcel(sheet,18,String.valueOf(priceComplectProductsFromBedroom + priceComplectProductsFromDiningAndLivingRoom + priceChairs +
                        priceComplectProductsFromHallway + priceComplectProductsFromKitchen),"");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
            File file = new File("\\\\Sezambuh\\интеграцияунфбухгалтерия\\" + dateFormat.format(new Date()) +".xls");
            file.getParentFile().mkdirs();

            FileOutputStream outFile = new FileOutputStream(file);
            workbook.write(outFile);
            outFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }




}
