package org.featx.sta2ry.musca.convert;

import org.featx.sta2ry.musca.criteria.CommentCriteria;
import org.featx.sta2ry.musca.entity.CommentEntity;
import org.featx.sta2ry.musca.model.CommentSave;
import org.featx.sta2ry.musca.model.CommentInfo;
import org.featx.sta2ry.musca.model.CommentItem;
import org.featx.sta2ry.musca.model.CommentPageQuery;

public class CommentConvert {

    private CommentConvert() {
        
    }

    public static CommentEntity toEntity(CommentSave commentSave) {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setCode(commentSave.getCode());
        commentEntity.setName(commentSave.getName());
        commentEntity.setType(commentSave.getType());
        commentEntity.setDescription(commentSave.getDescription());
        return commentEntity;
    }

    public static CommentInfo toInfo(CommentEntity entity) {
        CommentInfo info = new CommentInfo();
        info.setCode(entity.getCode());
        info.setName(entity.getName());
        info.setType(entity.getType());
        info.setDescription(entity.getDescription());
        return info;
    }

    public static CommentItem toItem(CommentEntity entity) {
        CommentItem item = new CommentItem();
        item.setCode(entity.getCode());
        item.setName(entity.getName());
        item.setType(entity.getType());
        item.setDescription(entity.getDescription());
        return item;
    }

    public static CommentCriteria toCriteria(CommentPageQuery pageQuery) {
        CommentCriteria criteria = new CommentCriteria();
//        criteria.setCode(pageQuery.getCode());
        return criteria;
    }
}
