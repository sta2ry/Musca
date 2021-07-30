package org.featx.sta2ry.musca.service;

import org.featx.spec.model.QuerySection;
import org.featx.sta2ry.musca.criteria.CommentCriteria;
import org.featx.sta2ry.musca.entity.CommentEntity;
import org.featx.sta2ry.musca.model.CommentInfo;
import org.featx.sta2ry.musca.model.CommentItem;
import org.featx.sta2ry.musca.model.CommentPageQuery;
import org.featx.sta2ry.musca.model.CommentSave;

import java.util.List;

/**
 * @author Excepts
 * @since 2020/4/12 13:57
 */
public interface CommentService {

    CommentItem save(CommentSave commentSave);

    CommentItem update(CommentSave commentSave);

    void delete(String code);

    CommentInfo findOne(String code);

    List<CommentItem> listByCodes(List<String> codes);

    QuerySection<CommentItem> page(CommentPageQuery pageQuery);

    default CommentEntity toEntity(CommentSave commentSave) {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setCode(commentSave.getCode());
        commentEntity.setName(commentSave.getName());
        commentEntity.setType(commentSave.getType());
        commentEntity.setDescription(commentSave.getDescription());
        return commentEntity;
    }

    default CommentInfo toInfo(CommentEntity entity) {
        CommentInfo info = new CommentInfo();
        info.setCode(entity.getCode());
        info.setName(entity.getName());
        info.setType(entity.getType());
        info.setDescription(entity.getDescription());
        return info;
    }

    default CommentItem toItem(CommentEntity entity) {
        CommentItem item = new CommentItem();
        item.setCode(entity.getCode());
        item.setName(entity.getName());
        item.setType(entity.getType());
        item.setDescription(entity.getDescription());
        return item;
    }

    default CommentCriteria toCriteria(CommentPageQuery pageQuery) {
        CommentCriteria criteria = new CommentCriteria();
//        criteria.setCode(pageQuery.getCode());
        return criteria;
    }
}
