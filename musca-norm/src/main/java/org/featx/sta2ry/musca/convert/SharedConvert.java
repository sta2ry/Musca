package org.featx.sta2ry.musca.convert;

import org.featx.sta2ry.musca.criteria.SharedCriteria;
import org.featx.sta2ry.musca.entity.SharedEntity;
import org.featx.sta2ry.musca.model.SharedInfo;
import org.featx.sta2ry.musca.model.SharedItem;
import org.featx.sta2ry.musca.model.SharedPageQuery;
import org.featx.sta2ry.musca.model.SharedSave;

public class SharedConvert {

    private SharedConvert() {

    }

    public static SharedEntity toEntity(SharedSave sharedSave) {
        SharedEntity sharedEntity = new SharedEntity();
        sharedEntity.setCode(sharedSave.getCode());
        sharedEntity.setType(sharedSave.getType());
        return sharedEntity;
    }

    public static SharedInfo toInfo(SharedEntity entity) {
        SharedInfo info = new SharedInfo();
        info.setCode(entity.getCode());
        info.setType(entity.getType());
        return info;
    }

    public static SharedItem toItem(SharedEntity entity) {
        SharedItem item = new SharedItem();
        item.setCode(entity.getCode());
        item.setType(entity.getType());
        return item;
    }

    public static SharedCriteria toCriteria(SharedPageQuery pageQuery) {
        SharedCriteria criteria = new SharedCriteria();
//        criteria.setCode(pageQuery.getCode());
        return criteria;
    }
}
