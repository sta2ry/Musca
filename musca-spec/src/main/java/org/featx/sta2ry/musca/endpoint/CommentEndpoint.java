package org.featx.sta2ry.musca.endpoint;

import org.featx.spec.model.BaseResponse;
import org.featx.spec.model.PageResponse;
import org.featx.sta2ry.musca.model.CommentInfo;
import org.featx.sta2ry.musca.model.CommentItem;
import org.featx.sta2ry.musca.model.CommentPageQuery;
import org.featx.sta2ry.musca.model.CommentSave;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "musca-core", fallbackFactory = CommentFallbackFactory.class)
@RequestMapping("/comment")
public interface CommentEndpoint {

    @PostMapping
    @ResponseBody
    BaseResponse<CommentItem> save(@RequestBody CommentSave commentSave);

    @PutMapping
    @ResponseBody
    BaseResponse<CommentItem> update(@RequestBody CommentSave commentSave);

    @DeleteMapping
    @ResponseBody
    BaseResponse<Boolean> delete(@RequestParam("code") String commentCode);


    @GetMapping
    @ResponseBody
    BaseResponse<CommentInfo> get(@RequestParam("code") String commentCode);

    @GetMapping("page")
    @ResponseBody
    PageResponse<CommentItem> page(@RequestBody CommentPageQuery pageQuery);
}