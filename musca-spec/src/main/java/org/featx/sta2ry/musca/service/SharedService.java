package org.featx.sta2ry.musca.service;

import org.featx.spec.model.QuerySection;
import org.featx.sta2ry.musca.model.SharedInfo;
import org.featx.sta2ry.musca.model.SharedItem;
import org.featx.sta2ry.musca.model.SharedPageQuery;
import org.featx.sta2ry.musca.model.SharedSave;

import java.util.List;

/**
 * @author Excepts
 * @since 2020/4/12 13:57
 */
public interface SharedService {

    SharedItem save(SharedSave sharedSave);

    SharedItem update(SharedSave sharedSave);

    void delete(String code);

    SharedInfo findOne(String code);

    List<SharedItem> listByCodes(List<String> codes);

    QuerySection<SharedItem> page(SharedPageQuery pageQuery);
}
