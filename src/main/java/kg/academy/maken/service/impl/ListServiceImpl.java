package kg.academy.maken.service.impl;

import kg.academy.maken.converter.ListConverter;
import kg.academy.maken.converter.ListNameUpdateConverter;
import kg.academy.maken.converter.ListUpdateStatusConverter;
import kg.academy.maken.entity.Dashboard;
import kg.academy.maken.entity.List;
import kg.academy.maken.exception.ApiException;
import kg.academy.maken.model.ListGetModel;
import kg.academy.maken.model.ListModel;
import kg.academy.maken.model.ListNameUpdateModel;
import kg.academy.maken.model.ListStatusUpdateModel;
import kg.academy.maken.repository.ListRepository;
import kg.academy.maken.service.CardService;
import kg.academy.maken.service.ListService;
import kg.academy.maken.service.StatusService;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ListServiceImpl implements ListService {
    @Autowired
    private StatusService statusService;
    @Autowired
    private ListRepository listRepository;
    @Autowired
    private ListConverter listConverter;
    @Autowired
    private ListUpdateStatusConverter listUpdateStatusConverter;
    @Autowired
    private ListNameUpdateConverter listNameUpdateConverter;
    @Autowired
    private CardService cardService;
    
    @Override
    public void defaultLists(Dashboard dashboard) {
        listRepository.save(List.builder()
                .name("TO DO")
                .dashboard(dashboard)
                .status(statusService.findById(1L))
                .build());
        listRepository.save(List.builder()
                .name("IN PROCESS")
                .dashboard(dashboard)
                .status(statusService.findById(2L))
                .build()
        );
        listRepository.save(List.builder()
                .name("DONE")
                .dashboard(dashboard)
                .status(statusService.findById(3L))
                .build());
    }

    @Override
    public java.util.List<ListGetModel> getListByDashboard(Long id) {
        java.util.List<List> list = listRepository.findByDashboard(id)
                .orElseThrow(()-> new ApiException("Список пуст", HttpStatus.NO_CONTENT));
        java.util.List<ListGetModel> listGetModels = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            listGetModels.add(new ListGetModel(list.get(i).getId(), cardService.getCardsByList(list.get(i).getId())));
        }
        return listGetModels;
    }

    @Override
    public ListStatusUpdateModel update(ListStatusUpdateModel listStatusUpdateModel) {
        List list = findById(listStatusUpdateModel.getId());
        if(listStatusUpdateModel.getStatusId()!= null)
            list.setStatus(statusService.findById(listStatusUpdateModel.getStatusId()));
        listRepository.save(list);
        return listUpdateStatusConverter.convertToModel(list);
    }

    @Override
    public ListNameUpdateModel update(ListNameUpdateModel listNameUpdateModel) {
        List list = findById(listNameUpdateModel.getID());
        if(listNameUpdateModel.getName()!= null)
            list.setName((listNameUpdateModel.getName()));
        listRepository.save(list);
        return listNameUpdateConverter.convertToModel(list);
    }

    @Override
    public ListModel saveModel(ListModel model) {
        List list = listConverter.convertToEntity(model);
        listRepository.save(list);
        return listConverter.convertToModel(list);
    }

    @Override
    public ListModel deleteModelById(Long id) {
        return listConverter.convertToModel(deleteById(id));
    }

    @Override
    public ListModel getModelById(Long id) {
        return listConverter.convertToModel(findById(id));
    }

    @Override
    public java.util.List<ListModel> getAllModel() {
        return getAll()
                .stream()
                .map(listConverter::convertToModel)
                .collect(Collectors.toList());
    }

    @Override
    public Page<ListModel> getPage(Pageable pageable) {
        return listRepository.findAll(pageable)
                .map(listConverter::convertToModel);
    }

    @Override
    public ListModel update(ListModel model) {
        List list = findById(model.getID());
        if(model.getName()!= null) 
            list.setName(model.getName());
        if(model.getStatusId()!= null) 
            list.setStatus(statusService.findById(model.getStatusId()));
        listRepository.save(list);
        return listConverter.convertToModel(list);
    }

    @Override
    public List save(List list) {
        return listRepository.save(list);
    }

    @Override
    public java.util.List<List> getAll() {
        return listRepository.findAll();
    }

    @Override
    public List findById(Long id) {
        return listRepository.findById(id).orElse(null);
    }

    @Override
    public List deleteById(Long id) {
        List list = findById(id);
        if (list != null)
            listRepository.deleteById(id);
        return list;
    }
}
