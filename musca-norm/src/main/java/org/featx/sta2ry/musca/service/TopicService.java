package org.featx.sta2ry.musca.service;

import org.featx.spec.model.QuerySection;
import org.featx.sta2ry.musca.criteria.TopicCriteria;
import org.featx.sta2ry.musca.entity.TopicEntity;
import org.featx.sta2ry.musca.model.TopicInfo;
import org.featx.sta2ry.musca.model.TopicItem;
import org.featx.sta2ry.musca.model.TopicPageQuery;
import org.featx.sta2ry.musca.model.TopicSave;

import java.util.List;

/**
 * @author Excepts
 * @since 2020/4/12 13:57
 */
public interface TopicService {

    TopicItem save(TopicSave topicSave);

    TopicItem update(TopicSave topicSave);

    void delete(String code);

    TopicInfo findOne(String code);

    List<TopicItem> listByCodes(List<String> codes);

    QuerySection<TopicItem> page(TopicPageQuery pageQuery);

    default TopicEntity toEntity(TopicSave topicSave) {
        TopicEntity topicEntity = new TopicEntity();
        topicEntity.setCode(topicSave.getCode());
        topicEntity.setName(topicSave.getName());
        topicEntity.setType(topicSave.getType());
        topicEntity.setDescription(topicSave.getDescription());
        return topicEntity;
    }

    default TopicInfo toInfo(TopicEntity entity) {
        TopicInfo info = new TopicInfo();
        info.setCode(entity.getCode());
        info.setName(entity.getName());
        info.setType(entity.getType());
        info.setDescription(entity.getDescription());
        return info;
    }

    default TopicItem toItem(TopicEntity entity) {
        TopicItem item = new TopicItem();
        item.setCode(entity.getCode());
        item.setName(entity.getName());
        item.setType(entity.getType());
        item.setDescription(entity.getDescription());
        return item;
    }

    default TopicCriteria toCriteria(TopicPageQuery pageQuery) {
        TopicCriteria criteria = new TopicCriteria();
//        criteria.setCode(pageQuery.getCode());
        return criteria;
    }
}
