package org.featx.sta2ry.musca.service;

import org.featx.spec.model.QuerySection;
import org.featx.sta2ry.musca.model.TopicUsedInfo;
import org.featx.sta2ry.musca.model.TopicUsedItem;
import org.featx.sta2ry.musca.model.TopicUsedPageQuery;
import org.featx.sta2ry.musca.model.TopicUsedSave;

import java.util.List;

/**
 * @author Excepts
 * @since 2020/4/12 13:57
 */
public interface TopicUsedService {

    TopicUsedItem save(TopicUsedSave TopicUsedSave);

    TopicUsedItem update(TopicUsedSave TopicUsedSave);

    void delete(String code);

    TopicUsedInfo findOne(String code);

    List<TopicUsedItem> listByCodes(List<String> codes);

    QuerySection<TopicUsedItem> page(TopicUsedPageQuery pageQuery);
}
