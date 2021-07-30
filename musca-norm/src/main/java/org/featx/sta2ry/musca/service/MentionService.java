package org.featx.sta2ry.musca.service;

import org.featx.spec.model.QuerySection;
import org.featx.sta2ry.musca.criteria.MentionCriteria;
import org.featx.sta2ry.musca.entity.MentionEntity;
import org.featx.sta2ry.musca.model.MentionInfo;
import org.featx.sta2ry.musca.model.MentionItem;
import org.featx.sta2ry.musca.model.MentionPageQuery;
import org.featx.sta2ry.musca.model.MentionSave;

import java.util.List;

/**
 * @author Excepts
 * @since 2020/4/12 13:57
 */
public interface MentionService {

    MentionItem save(MentionSave mentionSave);

    MentionItem update(MentionSave mentionSave);

    void delete(String code);

    MentionInfo findOne(String code);

    List<MentionItem> listByCodes(List<String> codes);

    QuerySection<MentionItem> page(MentionPageQuery pageQuery);

    default MentionEntity toEntity(MentionSave mentionSave) {
        MentionEntity mentionEntity = new MentionEntity();
        mentionEntity.setCode(mentionSave.getCode());
        mentionEntity.setName(mentionSave.getName());
        mentionEntity.setType(mentionSave.getType());
        mentionEntity.setDescription(mentionSave.getDescription());
        return mentionEntity;
    }

    default MentionInfo toInfo(MentionEntity entity) {
        MentionInfo info = new MentionInfo();
        info.setCode(entity.getCode());
        info.setName(entity.getName());
        info.setType(entity.getType());
        info.setDescription(entity.getDescription());
        return info;
    }

    default MentionItem toItem(MentionEntity entity) {
        MentionItem item = new MentionItem();
        item.setCode(entity.getCode());
        item.setName(entity.getName());
        item.setType(entity.getType());
        item.setDescription(entity.getDescription());
        return item;
    }

    default MentionCriteria toCriteria(MentionPageQuery pageQuery) {
        MentionCriteria criteria = new MentionCriteria();
//        criteria.setCode(pageQuery.getCode());
        return criteria;
    }
}
