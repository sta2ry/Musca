package org.featx.sta2ry.musca.service;

import org.featx.spec.model.QuerySection;
import org.featx.sta2ry.musca.model.FollowInfo;
import org.featx.sta2ry.musca.model.FollowItem;
import org.featx.sta2ry.musca.model.FollowPageQuery;
import org.featx.sta2ry.musca.model.FollowSave;

import java.util.List;

/**
 *
 */
public interface FollowService {

    FollowItem save(FollowSave followSave);

    FollowItem update(FollowSave followSave);

    void delete(String code);

    FollowInfo findOne(String code);

    List<FollowItem> listByCodes(List<String> codes);

    QuerySection<FollowItem> page(FollowPageQuery pageQuery);

}
