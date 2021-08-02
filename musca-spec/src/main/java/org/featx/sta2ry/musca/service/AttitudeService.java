package org.featx.sta2ry.musca.service;

import org.featx.spec.model.QuerySection;
import org.featx.sta2ry.musca.model.AttitudeInfo;
import org.featx.sta2ry.musca.model.AttitudeItem;
import org.featx.sta2ry.musca.model.AttitudePageQuery;
import org.featx.sta2ry.musca.model.AttitudeSave;

import java.util.List;

/**
 * @author Excepts
 * @since 2020/4/12 13:57
 */
public interface AttitudeService {

    AttitudeItem save(AttitudeSave attitudeSave);

    AttitudeItem update(AttitudeSave attitudeSave);

    void delete(String code);

    AttitudeInfo findOne(String code);

    List<AttitudeItem> listByCodes(List<String> codes);

    QuerySection<AttitudeItem> page(AttitudePageQuery pageQuery);
}
