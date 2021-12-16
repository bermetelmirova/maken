package kg.academy.maken.controller;

import kg.academy.maken.model.ListGetModel;
import kg.academy.maken.model.ListModel;
import kg.academy.maken.model.ListNameUpdateModel;
import kg.academy.maken.model.ListStatusUpdateModel;
import kg.academy.maken.service.ListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/list")
@RequiredArgsConstructor
public class ListController {
    private final ListService listService;

    @PostMapping
    public ListModel save(@RequestBody ListModel listModel) {
        return listService.saveModel(listModel);
    }

    @GetMapping("/get-all/{id}")
    public List<ListGetModel> getModelWithCard(@PathVariable Long id) {
        return listService.getListByDashboard(id);
    }

    @GetMapping
    public List<ListModel> getAll(){
        return listService.getAllModel();
    }

    @GetMapping("/{id}")
    public ListModel getById(@PathVariable Long id){
        return  listService.getModelById(id);
    }

    @PutMapping
    public ListModel update(@RequestBody ListModel listModel) {
        return listService.update(listModel);
    }

    @PutMapping("/update-status")
    public ListStatusUpdateModel update(@RequestBody ListStatusUpdateModel listStatusUpdateModel) {
        return listService.update(listStatusUpdateModel);
    }

    @PutMapping("/update-name")
    public ListNameUpdateModel update(@RequestBody ListNameUpdateModel listNameUpdateModel) {
        return listService.update(listNameUpdateModel);
    }

    @DeleteMapping("/{id}")
    public ListModel deleteById(@PathVariable Long id){
        return listService.deleteModelById(id);
    }
}
