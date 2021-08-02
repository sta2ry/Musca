package org.featx.sta2ry.musca.convert;

import org.featx.sta2ry.musca.criteria.AttitudeCriteria;
import org.featx.sta2ry.musca.entity.AttitudeEntity;
import org.featx.sta2ry.musca.model.AttitudeInfo;
import org.featx.sta2ry.musca.model.AttitudeItem;
import org.featx.sta2ry.musca.model.AttitudePageQuery;
import org.featx.sta2ry.musca.model.AttitudeSave;

public class AttitudeConvert {

    private AttitudeConvert() {

    }

    public static AttitudeEntity toEntity(AttitudeSave attitudeSave) {
        AttitudeEntity attitudeEntity = new AttitudeEntity();
        attitudeEntity.setCode(attitudeSave.getCode());
        attitudeEntity.setType(attitudeSave.getType());
        attitudeEntity.setScore(attitudeSave.getScore());
        attitudeEntity.setUserCode(attitudeSave.getUserCode());
        attitudeEntity.setTargetCode(attitudeSave.getTargetCode());
        return attitudeEntity;
    }

    public static AttitudeInfo toInfo(AttitudeEntity entity) {
        AttitudeInfo info = new AttitudeInfo();
        info.setCode(entity.getCode());
        info.setType(entity.getType());
        return info;
    }

    public static AttitudeItem toItem(AttitudeEntity entity) {
        AttitudeItem item = new AttitudeItem();
        item.setCode(entity.getCode());
        item.setType(entity.getType());
        return item;
    }

    public static AttitudeCriteria toCriteria(AttitudePageQuery pageQuery) {
        AttitudeCriteria criteria = new AttitudeCriteria();
//        criteria.setCode(pageQuery.getCode());
        return criteria;
    }
}
