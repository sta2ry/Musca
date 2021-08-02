package org.featx.sta2ry.musca.endpoint;

import org.featx.spec.model.BaseResponse;
import org.featx.spec.model.PageResponse;
import org.featx.sta2ry.musca.model.TopicusedInfo;
import org.featx.sta2ry.musca.model.TopicusedItem;
import org.featx.sta2ry.musca.model.TopicusedPageQuery;
import org.featx.sta2ry.musca.model.TopicusedSave;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "Musca-core", fallbackFactory = TopicusedFallbackFactory.class)
@RequestMapping("/topicused")
public interface TopicusedEndpoint {

    @PostMapping
    @ResponseBody
    BaseResponse<TopicusedItem> save(@RequestBody TopicusedSave topicusedSave);

    @PutMapping
    @ResponseBody
    BaseResponse<TopicusedItem> update(@RequestBody TopicusedSave topicusedSave);

    @DeleteMapping
    @ResponseBody
    BaseResponse<Boolean> delete(@RequestParam("code") String topicusedCode);


    @GetMapping
    @ResponseBody
    BaseResponse<TopicusedInfo> get(@RequestParam("code") String topicusedCode);

    @GetMapping("page")
    @ResponseBody
    PageResponse<TopicusedItem> page(@RequestBody TopicusedPageQuery pageQuery);
}