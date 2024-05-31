package by.yavid.Controllers.CncMachine;


import by.yavid.DTO.CncMachine.NodeDrawing;
import by.yavid.Service.CncMachine.FileDefaultDrawingService;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Set;


@Controller
@RequestMapping("drawing")
public class ViewDefaultDrawing {

    private FileDefaultDrawingService fileDefaultDrawingService;

    public ViewDefaultDrawing(FileDefaultDrawingService fileDefaultDrawingService) {
        this.fileDefaultDrawingService = fileDefaultDrawingService;
    }

    @GetMapping("default")
    public String getAllDefaultDrawing (Model model) throws JsonProcessingException {
        ArrayList<NodeDrawing> nodesDrawing = new ArrayList<>();
        Map<String,Set<String>> map = fileDefaultDrawingService.getAllFileDefaultDrawingService();
        for (String key : map.keySet()) {
            NodeDrawing node = new NodeDrawing();
            node.setText(key);
            for (String text:map.get(key)){
                node.AddNodes(new NodeDrawing(text));
            }
            Collections.sort(node.getNodes());
            nodesDrawing.add(node);
        }
        Collections.sort(nodesDrawing);
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        String data = mapper.writeValueAsString(nodesDrawing);

        model.addAttribute("default", "http://192.168.11.4:8080/drawing/getDefaultFile/DB061-57-Лист_2");
        model.addAttribute("data", data);
        return "default";
    }

    @RequestMapping(value = "getDefaultFile/{nameFile}", method = RequestMethod.GET , produces = "drawing/x-dwf")
    public ResponseEntity<?> getDefaultFile(@PathVariable String nameFile) throws FileNotFoundException {
        File file = fileDefaultDrawingService.getFileDefaultDrawingService(nameFile);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                .contentLength(file.length())
                .body(resource);
    }

}
