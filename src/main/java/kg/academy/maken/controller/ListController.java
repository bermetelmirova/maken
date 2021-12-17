package kg.academy.maken.controller;

import kg.academy.maken.model.ResponseMessage;
import kg.academy.maken.model.list_model.ListGetModel;
import kg.academy.maken.model.list_model.ListModel;
import kg.academy.maken.model.list_model.ListNameUpdateModel;
import kg.academy.maken.model.list_model.ListStatusUpdateModel;
import kg.academy.maken.service.ListService;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/list")
@RequiredArgsConstructor
public class ListController {
    private final ListService listService;

    @PostMapping
    public ResponseMessage<ListModel> save(@RequestBody ListModel listModel) {
        return new ResponseMessage<ListModel>().prepareSuccessMessage(listService.saveModel(listModel));
    }

    @GetMapping("/get-all/{id}")
    public List<ListGetModel> getModelWithCard(@PathVariable Long id) {
        return listService.getListByDashboard(id);
    }

    @GetMapping("/get-all")
    public List<ListModel> getAll() {
        return listService.getAllModel();
    }

    @GetMapping("/get-all/pageable")
    public Page<ListModel> getPage(Pageable pageable) {
        return listService.getPage(pageable);
    }

    @GetMapping("/{id}")
    public ResponseMessage<ListModel> getById(@PathVariable Long id) {
        return new ResponseMessage<ListModel>().prepareSuccessMessage(listService.getModelById(id));
    }

    @PutMapping
    public ListModel update(@RequestBody ListModel listModel) {
        return listService.update(listModel);
    }

    @PutMapping("/update-status")
    public ResponseMessage<ListStatusUpdateModel> update(@RequestBody ListStatusUpdateModel listStatusUpdateModel) {
        return new ResponseMessage<ListStatusUpdateModel>().prepareSuccessMessage(listService.update(listStatusUpdateModel));
    }

    @PutMapping("/update-name")
    public ResponseMessage<ListNameUpdateModel> update(@RequestBody ListNameUpdateModel listNameUpdateModel) {
        return new ResponseMessage<ListNameUpdateModel>().prepareSuccessMessage(listService.update(listNameUpdateModel));
    }

    @DeleteMapping("/{id}")
    public ResponseMessage<ListModel> deleteById(@PathVariable Long id) {
        return new ResponseMessage<ListModel>().prepareSuccessMessage(listService.deleteModelById(id));
    }
}
