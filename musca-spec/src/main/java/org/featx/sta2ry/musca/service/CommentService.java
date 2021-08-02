package org.featx.sta2ry.musca.service;

import org.featx.spec.model.QuerySection;
import org.featx.sta2ry.musca.model.CommentInfo;
import org.featx.sta2ry.musca.model.CommentItem;
import org.featx.sta2ry.musca.model.CommentPageQuery;
import org.featx.sta2ry.musca.model.CommentSave;

import java.util.List;

/**
 *
 */
public interface CommentService {

    CommentItem save(CommentSave commentSave);

    CommentItem update(CommentSave commentSave);

    void delete(String code);

    CommentInfo findOne(String code);

    List<CommentItem> listByCodes(List<String> codes);

    QuerySection<CommentItem> page(CommentPageQuery pageQuery);

}
