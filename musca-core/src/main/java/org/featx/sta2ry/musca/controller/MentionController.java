package org.featx.sta2ry.musca.controller;

import org.featx.spec.model.BaseResponse;
import org.featx.spec.model.PageResponse;
import org.featx.spec.model.QuerySection;
import org.featx.sta2ry.musca.endpoint.MentionEndpoint;
import org.featx.sta2ry.musca.model.MentionInfo;
import org.featx.sta2ry.musca.model.MentionItem;
import org.featx.sta2ry.musca.model.MentionPageQuery;
import org.featx.sta2ry.musca.model.MentionSave;
import org.featx.sta2ry.musca.service.MentionService;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class MentionController implements MentionEndpoint {
    @Resource
    private MentionService mentionService;
    @Override
    public BaseResponse<MentionItem> save(MentionSave mentionSave) {
        return BaseResponse.succeeded(mentionService.save(mentionSave));
    }

    @Override
    public BaseResponse<MentionItem> update(MentionSave mentionSave) {
        return BaseResponse.succeeded(mentionService.update(mentionSave));
    }

    @Override
    public BaseResponse<Boolean> delete(String mentionCode) {
        mentionService.delete(mentionCode);
        return BaseResponse.succeeded(true);
    }

    @Override
    public BaseResponse<MentionInfo> get(String mentionCode) {
        return BaseResponse.succeeded(mentionService.findOne(mentionCode));
    }

    @Override
    public PageResponse<MentionItem> page(MentionPageQuery pageQuery) {
        QuerySection<MentionItem> section = mentionService.page(pageQuery);
        return PageResponse.succeeded(section.list()).page(pageQuery.getPage()).total(section.getTotal());
    }
}
