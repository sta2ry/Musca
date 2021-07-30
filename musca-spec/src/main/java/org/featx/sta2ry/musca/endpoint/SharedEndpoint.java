package org.featx.sta2ry.musca.endpoint;

import org.featx.spec.model.BaseResponse;
import org.featx.spec.model.PageResponse;
import org.featx.sta2ry.musca.model.SharedInfo;
import org.featx.sta2ry.musca.model.SharedItem;
import org.featx.sta2ry.musca.model.SharedPageQuery;
import org.featx.sta2ry.musca.model.SharedSave;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "Musca-core", fallbackFactory = SharedFallbackFactory.class)
@RequestMapping("/shared")
public interface SharedEndpoint {

    @PostMapping
    @ResponseBody
    BaseResponse<SharedItem> save(@RequestBody SharedSave sharedSave);

    @PutMapping
    @ResponseBody
    BaseResponse<SharedItem> update(@RequestBody SharedSave sharedSave);

    @DeleteMapping
    @ResponseBody
    BaseResponse<Boolean> delete(@RequestParam("code") String sharedCode);


    @GetMapping
    @ResponseBody
    BaseResponse<SharedInfo> get(@RequestParam("code") String sharedCode);

    @GetMapping("page")
    @ResponseBody
    PageResponse<SharedItem> page(@RequestBody SharedPageQuery pageQuery);
}