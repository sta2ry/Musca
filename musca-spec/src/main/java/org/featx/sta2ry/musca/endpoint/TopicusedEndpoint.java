package org.featx.sta2ry.musca.endpoint;

import org.featx.spec.model.BaseResponse;
import org.featx.spec.model.PageResponse;
import org.featx.sta2ry.musca.model.TopicUsedInfo;
import org.featx.sta2ry.musca.model.TopicUsedItem;
import org.featx.sta2ry.musca.model.TopicUsedPageQuery;
import org.featx.sta2ry.musca.model.TopicUsedSave;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "Musca-core", fallbackFactory = TopicusedFallbackFactory.class)
@RequestMapping("/topicused")
public interface TopicusedEndpoint {

    @PostMapping
    @ResponseBody
    BaseResponse<TopicUsedItem> save(@RequestBody TopicUsedSave topicusedSave);

    @PutMapping
    @ResponseBody
    BaseResponse<TopicUsedItem> update(@RequestBody TopicUsedSave topicusedSave);

    @DeleteMapping
    @ResponseBody
    BaseResponse<Boolean> delete(@RequestParam("code") String topicusedCode);


    @GetMapping
    @ResponseBody
    BaseResponse<TopicUsedInfo> get(@RequestParam("code") String topicusedCode);

    @GetMapping("page")
    @ResponseBody
    PageResponse<TopicUsedItem> page(@RequestBody TopicUsedPageQuery pageQuery);
}