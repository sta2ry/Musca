package org.featx.sta2ry.musca.controller;

import org.featx.spec.model.BaseResponse;
import org.featx.spec.model.PageResponse;
import org.featx.spec.model.QuerySection;
import org.featx.sta2ry.musca.endpoint.TopicusedEndpoint;
import org.featx.sta2ry.musca.model.TopicUsedInfo;
import org.featx.sta2ry.musca.model.TopicUsedItem;
import org.featx.sta2ry.musca.model.TopicUsedPageQuery;
import org.featx.sta2ry.musca.model.TopicUsedSave;
import org.featx.sta2ry.musca.service.TopicUsedService;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TopicusedController implements TopicusedEndpoint {
    @Resource
    private TopicUsedService topicusedService;
    @Override
    public BaseResponse<TopicUsedItem> save(TopicUsedSave topicusedSave) {
        return BaseResponse.succeeded(topicusedService.save(topicusedSave));
    }

    @Override
    public BaseResponse<TopicUsedItem> update(TopicUsedSave topicusedSave) {
        return BaseResponse.succeeded(topicusedService.update(topicusedSave));
    }

    @Override
    public BaseResponse<Boolean> delete(String topicusedCode) {
        topicusedService.delete(topicusedCode);
        return BaseResponse.succeeded(true);
    }

    @Override
    public BaseResponse<TopicUsedInfo> get(String topicusedCode) {
        return BaseResponse.succeeded(topicusedService.findOne(topicusedCode));
    }

    @Override
    public PageResponse<TopicUsedItem> page(TopicUsedPageQuery pageQuery) {
        QuerySection<TopicUsedItem> section = topicusedService.page(pageQuery);
        return PageResponse.succeeded(section.list()).page(pageQuery.getPage()).total(section.getTotal());
    }
}
