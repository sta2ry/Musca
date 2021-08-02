package org.featx.sta2ry.musca.controller;

import org.featx.spec.model.BaseResponse;
import org.featx.spec.model.PageResponse;
import org.featx.spec.model.QuerySection;
import org.featx.sta2ry.musca.endpoint.TopicusedEndpoint;
import org.featx.sta2ry.musca.model.TopicusedInfo;
import org.featx.sta2ry.musca.model.TopicusedItem;
import org.featx.sta2ry.musca.model.TopicusedPageQuery;
import org.featx.sta2ry.musca.model.TopicusedSave;
import org.featx.sta2ry.musca.service.TopicusedService;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TopicusedController implements TopicusedEndpoint {
    @Resource
    private TopicusedService topicusedService;
    @Override
    public BaseResponse<TopicusedItem> save(TopicusedSave topicusedSave) {
        return BaseResponse.succeeded(topicusedService.save(topicusedSave));
    }

    @Override
    public BaseResponse<TopicusedItem> update(TopicusedSave topicusedSave) {
        return BaseResponse.succeeded(topicusedService.update(topicusedSave));
    }

    @Override
    public BaseResponse<Boolean> delete(String topicusedCode) {
        topicusedService.delete(topicusedCode);
        return BaseResponse.succeeded(true);
    }

    @Override
    public BaseResponse<TopicusedInfo> get(String topicusedCode) {
        return BaseResponse.succeeded(topicusedService.findOne(topicusedCode));
    }

    @Override
    public PageResponse<TopicusedItem> page(TopicusedPageQuery pageQuery) {
        QuerySection<TopicusedItem> section = topicusedService.page(pageQuery);
        return PageResponse.succeeded(section.list()).page(pageQuery.getPage()).total(section.getTotal());
    }
}
