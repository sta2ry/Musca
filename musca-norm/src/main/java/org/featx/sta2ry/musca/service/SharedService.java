package org.featx.sta2ry.musca.service;

import org.featx.spec.model.QuerySection;
import org.featx.sta2ry.musca.criteria.SharedCriteria;
import org.featx.sta2ry.musca.entity.SharedEntity;
import org.featx.sta2ry.musca.model.SharedInfo;
import org.featx.sta2ry.musca.model.SharedItem;
import org.featx.sta2ry.musca.model.SharedPageQuery;
import org.featx.sta2ry.musca.model.SharedSave;

import java.util.List;

/**
 * @author Excepts
 * @since 2020/4/12 13:57
 */
public interface SharedService {

    SharedItem save(SharedSave sharedSave);

    SharedItem update(SharedSave sharedSave);

    void delete(String code);

    SharedInfo findOne(String code);

    List<SharedItem> listByCodes(List<String> codes);

    QuerySection<SharedItem> page(SharedPageQuery pageQuery);

    default SharedEntity toEntity(SharedSave sharedSave) {
        SharedEntity sharedEntity = new SharedEntity();
        sharedEntity.setCode(sharedSave.getCode());
        sharedEntity.setName(sharedSave.getName());
        sharedEntity.setType(sharedSave.getType());
        sharedEntity.setDescription(sharedSave.getDescription());
        return sharedEntity;
    }

    default SharedInfo toInfo(SharedEntity entity) {
        SharedInfo info = new SharedInfo();
        info.setCode(entity.getCode());
        info.setName(entity.getName());
        info.setType(entity.getType());
        info.setDescription(entity.getDescription());
        return info;
    }

    default SharedItem toItem(SharedEntity entity) {
        SharedItem item = new SharedItem();
        item.setCode(entity.getCode());
        item.setName(entity.getName());
        item.setType(entity.getType());
        item.setDescription(entity.getDescription());
        return item;
    }

    default SharedCriteria toCriteria(SharedPageQuery pageQuery) {
        SharedCriteria criteria = new SharedCriteria();
//        criteria.setCode(pageQuery.getCode());
        return criteria;
    }
}
