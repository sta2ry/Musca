package org.featx.sta2ry.musca.controller;

import org.featx.spec.model.BaseResponse;
import org.featx.spec.model.PageResponse;
import org.featx.spec.model.QuerySection;
import org.featx.sta2ry.musca.endpoint.SharedEndpoint;
import org.featx.sta2ry.musca.model.SharedInfo;
import org.featx.sta2ry.musca.model.SharedItem;
import org.featx.sta2ry.musca.model.SharedPageQuery;
import org.featx.sta2ry.musca.model.SharedSave;
import org.featx.sta2ry.musca.service.SharedService;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class SharedController implements SharedEndpoint {
    @Resource
    private SharedService sharedService;
    @Override
    public BaseResponse<SharedItem> save(SharedSave sharedSave) {
        return BaseResponse.succeeded(sharedService.save(sharedSave));
    }

    @Override
    public BaseResponse<SharedItem> update(SharedSave sharedSave) {
        return BaseResponse.succeeded(sharedService.update(sharedSave));
    }

    @Override
    public BaseResponse<Boolean> delete(String sharedCode) {
        sharedService.delete(sharedCode);
        return BaseResponse.succeeded(true);
    }

    @Override
    public BaseResponse<SharedInfo> get(String sharedCode) {
        return BaseResponse.succeeded(sharedService.findOne(sharedCode));
    }

    @Override
    public PageResponse<SharedItem> page(SharedPageQuery pageQuery) {
        QuerySection<SharedItem> section = sharedService.page(pageQuery);
        return PageResponse.succeeded(section.list()).page(pageQuery.getPage()).total(section.getTotal());
    }
}
