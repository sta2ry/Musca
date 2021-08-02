package org.featx.sta2ry.musca.convert;

import org.featx.sta2ry.musca.criteria.MentionCriteria;
import org.featx.sta2ry.musca.entity.MentionEntity;
import org.featx.sta2ry.musca.model.MentionInfo;
import org.featx.sta2ry.musca.model.MentionItem;
import org.featx.sta2ry.musca.model.MentionPageQuery;
import org.featx.sta2ry.musca.model.MentionSave;

public class MentionConvert {
    private MentionConvert() {

    }

    public static MentionEntity toEntity(MentionSave mentionSave) {
        MentionEntity mentionEntity = new MentionEntity();
        mentionEntity.setCode(mentionSave.getCode());
        mentionEntity.setType(mentionSave.getType());
        return mentionEntity;
    }

    public static MentionInfo toInfo(MentionEntity entity) {
        MentionInfo info = new MentionInfo();
        info.setCode(entity.getCode());
        info.setType(entity.getType());
        return info;
    }

    public static MentionItem toItem(MentionEntity entity) {
        MentionItem item = new MentionItem();
        item.setCode(entity.getCode());
        item.setType(entity.getType());
        return item;
    }

    public static MentionCriteria toCriteria(MentionPageQuery pageQuery) {
        MentionCriteria criteria = new MentionCriteria();
//        criteria.setCode(pageQuery.getCode());
        return criteria;
    }
}
