package kg.academy.maken.service;

import kg.academy.maken.entity.Dashboard;
import kg.academy.maken.entity.List;
import kg.academy.maken.model.list_model.ListGetModel;
import kg.academy.maken.model.list_model.ListModel;
import kg.academy.maken.model.list_model.ListNameUpdateModel;
import kg.academy.maken.model.list_model.ListStatusUpdateModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ListService extends BaseService<List>, BaseModelService<ListModel> {
    void defaultLists(Dashboard dashboard);

    java.util.List<ListGetModel> getListByDashboard(Long id);

    ListStatusUpdateModel update(ListStatusUpdateModel listStatusUpdateModel);

    ListNameUpdateModel update(ListNameUpdateModel listNameUpdateModel);

    List findByStatusOnDashboard(Long idStatus, Long idDashboard);

    Boolean isListExist(String name);

    Page<ListModel> getPage(Pageable pageable);
}
