package org.featx.sta2ry.musca.controller;

import org.featx.spec.model.BaseResponse;
import org.featx.spec.model.PageResponse;
import org.featx.spec.model.QuerySection;
import org.featx.sta2ry.musca.endpoint.FollowEndpoint;
import org.featx.sta2ry.musca.model.FollowInfo;
import org.featx.sta2ry.musca.model.FollowItem;
import org.featx.sta2ry.musca.model.FollowPageQuery;
import org.featx.sta2ry.musca.model.FollowSave;
import org.featx.sta2ry.musca.service.FollowService;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class FollowController implements FollowEndpoint {
    @Resource
    private FollowService followService;
    @Override
    public BaseResponse<FollowItem> save(FollowSave followSave) {
        return BaseResponse.succeeded(followService.save(followSave));
    }

    @Override
    public BaseResponse<FollowItem> update(FollowSave followSave) {
        return BaseResponse.succeeded(followService.update(followSave));
    }

    @Override
    public BaseResponse<Boolean> delete(String followCode) {
        followService.delete(followCode);
        return BaseResponse.succeeded(true);
    }

    @Override
    public BaseResponse<FollowInfo> get(String followCode) {
        return BaseResponse.succeeded(followService.findOne(followCode));
    }

    @Override
    public PageResponse<FollowItem> page(FollowPageQuery pageQuery) {
        QuerySection<FollowItem> section = followService.page(pageQuery);
        return PageResponse.succeeded(section.list()).page(pageQuery.getPage()).total(section.getTotal());
    }
}
