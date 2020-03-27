package by.yavid.Controllers.Administrate;

import by.yavid.Entity.Workbase.Bazis.GroupMC;
import by.yavid.Service.Bazis.GroupMCService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("product")
public class GroupMCController {

    @Autowired
    GroupMCService groupMCService;

    @GetMapping("all")
    @JsonView(GroupMC.GetGroupMC.class)
    public ResponseEntity<List<GroupMC>> getTypeProduct() {
        List<GroupMC> list = groupMCService.getTypeProduction();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
