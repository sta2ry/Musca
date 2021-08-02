package org.featx.sta2ry.musca.endpoint;

import org.featx.spec.model.BaseResponse;
import org.featx.spec.model.PageResponse;
import org.featx.sta2ry.musca.model.ProsecuteInfo;
import org.featx.sta2ry.musca.model.ProsecuteItem;
import org.featx.sta2ry.musca.model.ProsecutePageQuery;
import org.featx.sta2ry.musca.model.ProsecuteSave;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "musca-core", fallbackFactory = ProsecuteFallbackFactory.class)
@RequestMapping("/prosecute")
public interface ProsecuteEndpoint {

    @PostMapping
    @ResponseBody
    BaseResponse<ProsecuteItem> save(@RequestBody ProsecuteSave prosecuteSave);

    @PutMapping
    @ResponseBody
    BaseResponse<ProsecuteItem> update(@RequestBody ProsecuteSave prosecuteSave);

    @DeleteMapping
    @ResponseBody
    BaseResponse<Boolean> delete(@RequestParam("code") String prosecuteCode);


    @GetMapping
    @ResponseBody
    BaseResponse<ProsecuteInfo> get(@RequestParam("code") String prosecuteCode);

    @GetMapping("page")
    @ResponseBody
    PageResponse<ProsecuteItem> page(@RequestBody ProsecutePageQuery pageQuery);
}