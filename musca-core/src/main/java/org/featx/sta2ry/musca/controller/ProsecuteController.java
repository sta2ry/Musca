package org.featx.sta2ry.musca.controller;

import org.featx.spec.model.BaseResponse;
import org.featx.spec.model.PageResponse;
import org.featx.spec.model.QuerySection;
import org.featx.sta2ry.musca.endpoint.ProsecuteEndpoint;
import org.featx.sta2ry.musca.model.ProsecuteInfo;
import org.featx.sta2ry.musca.model.ProsecuteItem;
import org.featx.sta2ry.musca.model.ProsecutePageQuery;
import org.featx.sta2ry.musca.model.ProsecuteSave;
import org.featx.sta2ry.musca.service.ProsecuteService;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class ProsecuteController implements ProsecuteEndpoint {
    @Resource
    private ProsecuteService prosecuteService;
    @Override
    public BaseResponse<ProsecuteItem> save(ProsecuteSave prosecuteSave) {
        return BaseResponse.succeeded(prosecuteService.save(prosecuteSave));
    }

    @Override
    public BaseResponse<ProsecuteItem> update(ProsecuteSave prosecuteSave) {
        return BaseResponse.succeeded(prosecuteService.update(prosecuteSave));
    }

    @Override
    public BaseResponse<Boolean> delete(String prosecuteCode) {
        prosecuteService.delete(prosecuteCode);
        return BaseResponse.succeeded(true);
    }

    @Override
    public BaseResponse<ProsecuteInfo> get(String prosecuteCode) {
        return BaseResponse.succeeded(prosecuteService.findOne(prosecuteCode));
    }

    @Override
    public PageResponse<ProsecuteItem> page(ProsecutePageQuery pageQuery) {
        QuerySection<ProsecuteItem> section = prosecuteService.page(pageQuery);
        return PageResponse.succeeded(section.list()).page(pageQuery.getPage()).total(section.getTotal());
    }
}
