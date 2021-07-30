package org.featx.sta2ry.musca.controller;

import org.featx.spec.model.BaseResponse;
import org.featx.spec.model.PageResponse;
import org.featx.spec.model.QuerySection;
import org.featx.sta2ry.musca.endpoint.TopicEndpoint;
import org.featx.sta2ry.musca.model.TopicInfo;
import org.featx.sta2ry.musca.model.TopicItem;
import org.featx.sta2ry.musca.model.TopicPageQuery;
import org.featx.sta2ry.musca.model.TopicSave;
import org.featx.sta2ry.musca.service.TopicService;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TopicController implements TopicEndpoint {
    @Resource
    private TopicService topicService;
    @Override
    public BaseResponse<TopicItem> save(TopicSave topicSave) {
        return BaseResponse.succeeded(topicService.save(topicSave));
    }

    @Override
    public BaseResponse<TopicItem> update(TopicSave topicSave) {
        return BaseResponse.succeeded(topicService.update(topicSave));
    }

    @Override
    public BaseResponse<Boolean> delete(String topicCode) {
        topicService.delete(topicCode);
        return BaseResponse.succeeded(true);
    }

    @Override
    public BaseResponse<TopicInfo> get(String topicCode) {
        return BaseResponse.succeeded(topicService.findOne(topicCode));
    }

    @Override
    public PageResponse<TopicItem> page(TopicPageQuery pageQuery) {
        QuerySection<TopicItem> section = topicService.page(pageQuery);
        return PageResponse.succeeded(section.list()).page(pageQuery.getPage()).total(section.getTotal());
    }
}
