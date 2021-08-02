package org.featx.sta2ry.musca.service;

import org.featx.spec.model.QuerySection;
import org.featx.sta2ry.musca.model.ProsecuteInfo;
import org.featx.sta2ry.musca.model.ProsecuteItem;
import org.featx.sta2ry.musca.model.ProsecutePageQuery;
import org.featx.sta2ry.musca.model.ProsecuteSave;

import java.util.List;

/**
 *
 */
public interface ProsecuteService {

    ProsecuteItem save(ProsecuteSave prosecuteSave);

    ProsecuteItem update(ProsecuteSave prosecuteSave);

    void delete(String code);

    ProsecuteInfo findOne(String code);

    List<ProsecuteItem> listByCodes(List<String> codes);

    QuerySection<ProsecuteItem> page(ProsecutePageQuery pageQuery);

}
