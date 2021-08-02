package org.featx.sta2ry.musca.convert;

import org.featx.sta2ry.musca.criteria.TopicUsedCriteria;
import org.featx.sta2ry.musca.entity.TopicUsedEntity;
import org.featx.sta2ry.musca.model.TopicUsedInfo;
import org.featx.sta2ry.musca.model.TopicUsedItem;
import org.featx.sta2ry.musca.model.TopicUsedPageQuery;
import org.featx.sta2ry.musca.model.TopicUsedSave;

public class TopicUsedConvert {
    private TopicUsedConvert() {

    }

    public static TopicUsedEntity toEntity(TopicUsedSave TopicUsedSave) {
        TopicUsedEntity TopicUsedEntity = new TopicUsedEntity();
        TopicUsedEntity.setCode(TopicUsedSave.getCode());
        return TopicUsedEntity;
    }

    public static TopicUsedInfo toInfo(TopicUsedEntity entity) {
        TopicUsedInfo info = new TopicUsedInfo();
        info.setCode(entity.getCode());
        return info;
    }

    public static TopicUsedItem toItem(TopicUsedEntity entity) {
        TopicUsedItem item = new TopicUsedItem();
        item.setCode(entity.getCode());
        return item;
    }

    public static TopicUsedCriteria toCriteria(TopicUsedPageQuery pageQuery) {
        TopicUsedCriteria criteria = new TopicUsedCriteria();
//        criteria.setCode(pageQuery.getCode());
        return criteria;
    }
}
