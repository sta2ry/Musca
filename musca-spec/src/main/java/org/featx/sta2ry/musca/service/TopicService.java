package org.featx.sta2ry.musca.service;

import org.featx.spec.model.QuerySection;
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
}
