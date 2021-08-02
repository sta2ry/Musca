package org.featx.sta2ry.musca.service;

import org.featx.spec.model.QuerySection;
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
}
