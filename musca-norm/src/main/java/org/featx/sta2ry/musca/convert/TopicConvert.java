package org.featx.sta2ry.musca.convert;

import org.featx.sta2ry.musca.criteria.TopicCriteria;
import org.featx.sta2ry.musca.entity.TopicEntity;
import org.featx.sta2ry.musca.model.TopicInfo;
import org.featx.sta2ry.musca.model.TopicItem;
import org.featx.sta2ry.musca.model.TopicPageQuery;
import org.featx.sta2ry.musca.model.TopicSave;

public class TopicConvert {

    private TopicConvert() {

    }

    public static TopicEntity toEntity(TopicSave topicSave) {
        TopicEntity topicEntity = new TopicEntity();
        topicEntity.setCode(topicSave.getCode());
        topicEntity.setName(topicSave.getName());
        topicEntity.setType(topicSave.getType());
        return topicEntity;
    }

    public static TopicInfo toInfo(TopicEntity entity) {
        TopicInfo info = new TopicInfo();
        info.setCode(entity.getCode());
        info.setName(entity.getName());
        info.setType(entity.getType());
        return info;
    }

    public static TopicItem toItem(TopicEntity entity) {
        TopicItem item = new TopicItem();
        item.setCode(entity.getCode());
        item.setName(entity.getName());
        item.setType(entity.getType());
        return item;
    }

    public static TopicCriteria toCriteria(TopicPageQuery pageQuery) {
        TopicCriteria criteria = new TopicCriteria();
//        criteria.setCode(pageQuery.getCode());
        return criteria;
    }
}
