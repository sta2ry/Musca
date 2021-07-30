package org.featx.sta2ry.musca.endpoint;

import org.featx.spec.model.BaseResponse;
import org.featx.spec.model.PageResponse;
import org.featx.sta2ry.musca.model.MentionInfo;
import org.featx.sta2ry.musca.model.MentionItem;
import org.featx.sta2ry.musca.model.MentionPageQuery;
import org.featx.sta2ry.musca.model.MentionSave;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "Musca-core", fallbackFactory = MentionFallbackFactory.class)
@RequestMapping("/mention")
public interface MentionEndpoint {

    @PostMapping
    @ResponseBody
    BaseResponse<MentionItem> save(@RequestBody MentionSave mentionSave);

    @PutMapping
    @ResponseBody
    BaseResponse<MentionItem> update(@RequestBody MentionSave mentionSave);

    @DeleteMapping
    @ResponseBody
    BaseResponse<Boolean> delete(@RequestParam("code") String mentionCode);


    @GetMapping
    @ResponseBody
    BaseResponse<MentionInfo> get(@RequestParam("code") String mentionCode);

    @GetMapping("page")
    @ResponseBody
    PageResponse<MentionItem> page(@RequestBody MentionPageQuery pageQuery);
}