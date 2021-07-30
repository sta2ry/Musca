package org.featx.sta2ry.musca.controller;

import org.featx.spec.model.BaseResponse;
import org.featx.spec.model.PageResponse;
import org.featx.spec.model.QuerySection;
import org.featx.sta2ry.musca.endpoint.AttitudeEndpoint;
import org.featx.sta2ry.musca.model.AttitudeInfo;
import org.featx.sta2ry.musca.model.AttitudeItem;
import org.featx.sta2ry.musca.model.AttitudePageQuery;
import org.featx.sta2ry.musca.model.AttitudeSave;
import org.featx.sta2ry.musca.service.AttitudeService;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class AttitudeController implements AttitudeEndpoint {
    @Resource
    private AttitudeService attitudeService;
    @Override
    public BaseResponse<AttitudeItem> save(AttitudeSave attitudeSave) {
        return BaseResponse.succeeded(attitudeService.save(attitudeSave));
    }

    @Override
    public BaseResponse<AttitudeItem> update(AttitudeSave attitudeSave) {
        return BaseResponse.succeeded(attitudeService.update(attitudeSave));
    }

    @Override
    public BaseResponse<Boolean> delete(String attitudeCode) {
        attitudeService.delete(attitudeCode);
        return BaseResponse.succeeded(true);
    }

    @Override
    public BaseResponse<AttitudeInfo> get(String attitudeCode) {
        return BaseResponse.succeeded(attitudeService.findOne(attitudeCode));
    }

    @Override
    public PageResponse<AttitudeItem> page(AttitudePageQuery pageQuery) {
        QuerySection<AttitudeItem> section = attitudeService.page(pageQuery);
        return PageResponse.succeeded(section.list()).page(pageQuery.getPage()).total(section.getTotal());
    }
}
