package by.yavid.Controllers.Administrate;


import by.yavid.Entity.Workbase.Bazis.FilesBazisKD;
import by.yavid.Entity.Workbase.Bazis.KDList;
import by.yavid.Service.Bazis.FilesBazisKDService;
import by.yavid.Service.Bazis.KDListService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

@RestController
public class KDListController {

    private KDListService kdListService;
    private FilesBazisKDService filesBazisKDService;

    public KDListController(KDListService kdListService, FilesBazisKDService filesBazisKDService) {
        this.kdListService = kdListService;
        this.filesBazisKDService = filesBazisKDService;
    }

    @RequestMapping(value = "kd/create", method = RequestMethod.GET)
    public ResponseEntity<Integer> getNewKDList() {
        Integer kd = kdListService.getNewKD();
        return new ResponseEntity<>(kd, HttpStatus.OK);
    }

    @RequestMapping(value = "kd/save", method = RequestMethod.POST)
    public ResponseEntity<KDList> saveNewKDList(@RequestBody KDList kd) {
        return new ResponseEntity<>(kdListService.saveKDList(kd), HttpStatus.OK);
    }

    @RequestMapping(value = "kd/savefile", method = RequestMethod.POST)
    @JsonView(KDList.getKDList.class)
    public ResponseEntity<FilesBazisKD> saveFileKDList(@RequestParam("uploadedFile") MultipartFile uploadedFileRef,@RequestParam("kd") String numberKD) {
        return new ResponseEntity<>(filesBazisKDService.saveFileKDBazis(uploadedFileRef,kdListService.getKdList(numberKD)), HttpStatus.OK);
    }

    @RequestMapping(value = "kd/openfile", method = RequestMethod.GET)
    public ResponseEntity<?> openFileKDList(@RequestParam("idfilekdlist") Integer id) throws FileNotFoundException {
        File file = filesBazisKDService.openFileKDBazis(id);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                // Content-Disposition
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
                // Contet-Length
                .contentLength(file.length()) //
                .body(resource);
    }



    /*@RequestMapping(value = "kd", method = RequestMethod.GET)
    @JsonView(KDList.getKDList.class)
    public ResponseEntity<List<KDList>> getKDList() {
        List<KDList> list = kdListService.getAllKdList();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    */

    @RequestMapping(value = "kd/{numberKD}", method = RequestMethod.GET)
    @ResponseBody
    @JsonView(KDList.getKDList.class)
    public ResponseEntity<KDList> getKDListByIdKDList(@PathVariable String numberKD) {
        KDList kdList = kdListService.getKdList(numberKD);

        return new ResponseEntity<>(kdList, HttpStatus.OK);
    }

    @RequestMapping(value = "kd/{numberKD}/create_specification", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> createSpecificationFor3Cad(@PathVariable String numberKD,@RequestBody String specification) {
        return new ResponseEntity<>(filesBazisKDService.saveFileSpecificationFor3Cad(numberKD,specification), HttpStatus.OK);
    }
}
