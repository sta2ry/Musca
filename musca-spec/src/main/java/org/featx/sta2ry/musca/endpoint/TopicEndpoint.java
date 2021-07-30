package org.featx.sta2ry.musca.endpoint;

import org.featx.spec.model.BaseResponse;
import org.featx.spec.model.PageResponse;
import org.featx.sta2ry.musca.model.TopicInfo;
import org.featx.sta2ry.musca.model.TopicItem;
import org.featx.sta2ry.musca.model.TopicPageQuery;
import org.featx.sta2ry.musca.model.TopicSave;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "Musca-core", fallbackFactory = TopicFallbackFactory.class)
@RequestMapping("/topic")
public interface TopicEndpoint {

    @PostMapping
    @ResponseBody
    BaseResponse<TopicItem> save(@RequestBody TopicSave topicSave);

    @PutMapping
    @ResponseBody
    BaseResponse<TopicItem> update(@RequestBody TopicSave topicSave);

    @DeleteMapping
    @ResponseBody
    BaseResponse<Boolean> delete(@RequestParam("code") String topicCode);


    @GetMapping
    @ResponseBody
    BaseResponse<TopicInfo> get(@RequestParam("code") String topicCode);

    @GetMapping("page")
    @ResponseBody
    PageResponse<TopicItem> page(@RequestBody TopicPageQuery pageQuery);
}