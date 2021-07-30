package org.featx.sta2ry.musca.service;

import org.featx.spec.model.QuerySection;
import org.featx.sta2ry.musca.criteria.TopicusedCriteria;
import org.featx.sta2ry.musca.entity.TopicusedEntity;
import org.featx.sta2ry.musca.model.TopicusedInfo;
import org.featx.sta2ry.musca.model.TopicusedItem;
import org.featx.sta2ry.musca.model.TopicusedPageQuery;
import org.featx.sta2ry.musca.model.TopicusedSave;

import java.util.List;

/**
 * @author Excepts
 * @since 2020/4/12 13:57
 */
public interface TopicusedService {

    TopicusedItem save(TopicusedSave topicusedSave);

    TopicusedItem update(TopicusedSave topicusedSave);

    void delete(String code);

    TopicusedInfo findOne(String code);

    List<TopicusedItem> listByCodes(List<String> codes);

    QuerySection<TopicusedItem> page(TopicusedPageQuery pageQuery);

    default TopicusedEntity toEntity(TopicusedSave topicusedSave) {
        TopicusedEntity topicusedEntity = new TopicusedEntity();
        topicusedEntity.setCode(topicusedSave.getCode());
        topicusedEntity.setName(topicusedSave.getName());
        topicusedEntity.setType(topicusedSave.getType());
        topicusedEntity.setDescription(topicusedSave.getDescription());
        return topicusedEntity;
    }

    default TopicusedInfo toInfo(TopicusedEntity entity) {
        TopicusedInfo info = new TopicusedInfo();
        info.setCode(entity.getCode());
        info.setName(entity.getName());
        info.setType(entity.getType());
        info.setDescription(entity.getDescription());
        return info;
    }

    default TopicusedItem toItem(TopicusedEntity entity) {
        TopicusedItem item = new TopicusedItem();
        item.setCode(entity.getCode());
        item.setName(entity.getName());
        item.setType(entity.getType());
        item.setDescription(entity.getDescription());
        return item;
    }

    default TopicusedCriteria toCriteria(TopicusedPageQuery pageQuery) {
        TopicusedCriteria criteria = new TopicusedCriteria();
//        criteria.setCode(pageQuery.getCode());
        return criteria;
    }
}
