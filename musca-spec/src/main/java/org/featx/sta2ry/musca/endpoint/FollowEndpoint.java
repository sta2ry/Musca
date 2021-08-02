package org.featx.sta2ry.musca.endpoint;

import org.featx.spec.model.BaseResponse;
import org.featx.spec.model.PageResponse;
import org.featx.sta2ry.musca.model.FollowInfo;
import org.featx.sta2ry.musca.model.FollowItem;
import org.featx.sta2ry.musca.model.FollowPageQuery;
import org.featx.sta2ry.musca.model.FollowSave;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "musca-core", fallbackFactory = FollowFallbackFactory.class)
@RequestMapping("/follow")
public interface FollowEndpoint {

    @PostMapping
    @ResponseBody
    BaseResponse<FollowItem> save(@RequestBody FollowSave followSave);

    @PutMapping
    @ResponseBody
    BaseResponse<FollowItem> update(@RequestBody FollowSave followSave);

    @DeleteMapping
    @ResponseBody
    BaseResponse<Boolean> delete(@RequestParam("code") String followCode);


    @GetMapping
    @ResponseBody
    BaseResponse<FollowInfo> get(@RequestParam("code") String followCode);

    @GetMapping("page")
    @ResponseBody
    PageResponse<FollowItem> page(@RequestBody FollowPageQuery pageQuery);
}