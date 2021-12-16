package kg.academy.maken.service;

import kg.academy.maken.entity.Dashboard;
import kg.academy.maken.entity.List;
import kg.academy.maken.model.ListGetModel;
import kg.academy.maken.model.ListModel;
import kg.academy.maken.model.ListNameUpdateModel;
import kg.academy.maken.model.ListStatusUpdateModel;

public interface ListService extends BaseService<List>, BaseModelService<ListModel>{
    void defaultLists(Dashboard dashboard);
    java.util.List<ListGetModel> getListByDashboard(Long id);
    ListStatusUpdateModel update(ListStatusUpdateModel listStatusUpdateModel);
    ListNameUpdateModel update(ListNameUpdateModel listNameUpdateModel);
}
