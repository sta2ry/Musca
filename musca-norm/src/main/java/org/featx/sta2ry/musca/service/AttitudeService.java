package org.featx.sta2ry.musca.service;

import org.featx.spec.model.QuerySection;
import org.featx.sta2ry.musca.criteria.AttitudeCriteria;
import org.featx.sta2ry.musca.entity.AttitudeEntity;
import org.featx.sta2ry.musca.model.AttitudeInfo;
import org.featx.sta2ry.musca.model.AttitudeItem;
import org.featx.sta2ry.musca.model.AttitudePageQuery;
import org.featx.sta2ry.musca.model.AttitudeSave;

import java.util.List;

/**
 * @author Excepts
 * @since 2020/4/12 13:57
 */
public interface AttitudeService {

    AttitudeItem save(AttitudeSave attitudeSave);

    AttitudeItem update(AttitudeSave attitudeSave);

    void delete(String code);

    AttitudeInfo findOne(String code);

    List<AttitudeItem> listByCodes(List<String> codes);

    QuerySection<AttitudeItem> page(AttitudePageQuery pageQuery);

    default AttitudeEntity toEntity(AttitudeSave attitudeSave) {
        AttitudeEntity attitudeEntity = new AttitudeEntity();
        attitudeEntity.setCode(attitudeSave.getCode());
        attitudeEntity.setName(attitudeSave.getName());
        attitudeEntity.setType(attitudeSave.getType());
        attitudeEntity.setDescription(attitudeSave.getDescription());
        return attitudeEntity;
    }

    default AttitudeInfo toInfo(AttitudeEntity entity) {
        AttitudeInfo info = new AttitudeInfo();
        info.setCode(entity.getCode());
        info.setName(entity.getName());
        info.setType(entity.getType());
        info.setDescription(entity.getDescription());
        return info;
    }

    default AttitudeItem toItem(AttitudeEntity entity) {
        AttitudeItem item = new AttitudeItem();
        item.setCode(entity.getCode());
        item.setName(entity.getName());
        item.setType(entity.getType());
        item.setDescription(entity.getDescription());
        return item;
    }

    default AttitudeCriteria toCriteria(AttitudePageQuery pageQuery) {
        AttitudeCriteria criteria = new AttitudeCriteria();
//        criteria.setCode(pageQuery.getCode());
        return criteria;
    }
}
