package org.featx.sta2ry.musca.controller;

import org.featx.spec.model.BaseResponse;
import org.featx.spec.model.PageResponse;
import org.featx.spec.model.QuerySection;
import org.featx.sta2ry.musca.endpoint.CommentEndpoint;
import org.featx.sta2ry.musca.model.CommentInfo;
import org.featx.sta2ry.musca.model.CommentItem;
import org.featx.sta2ry.musca.model.CommentPageQuery;
import org.featx.sta2ry.musca.model.CommentSave;
import org.featx.sta2ry.musca.service.CommentService;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class CommentController implements CommentEndpoint {
    @Resource
    private CommentService commentService;
    @Override
    public BaseResponse<CommentItem> save(CommentSave commentSave) {
        return BaseResponse.succeeded(commentService.save(commentSave));
    }

    @Override
    public BaseResponse<CommentItem> update(CommentSave commentSave) {
        return BaseResponse.succeeded(commentService.update(commentSave));
    }

    @Override
    public BaseResponse<Boolean> delete(String commentCode) {
        commentService.delete(commentCode);
        return BaseResponse.succeeded(true);
    }

    @Override
    public BaseResponse<CommentInfo> get(String commentCode) {
        return BaseResponse.succeeded(commentService.findOne(commentCode));
    }

    @Override
    public PageResponse<CommentItem> page(CommentPageQuery pageQuery) {
        QuerySection<CommentItem> section = commentService.page(pageQuery);
        return PageResponse.succeeded(section.list()).page(pageQuery.getPage()).total(section.getTotal());
    }
}
