package org.featx.sta2ry.musca.endpoint;

import org.featx.spec.model.BaseResponse;
import org.featx.spec.model.PageResponse;
import org.featx.sta2ry.musca.model.AttitudeInfo;
import org.featx.sta2ry.musca.model.AttitudeItem;
import org.featx.sta2ry.musca.model.AttitudePageQuery;
import org.featx.sta2ry.musca.model.AttitudeSave;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "Musca-core", fallbackFactory = AttitudeFallbackFactory.class)
@RequestMapping("/attitude")
public interface AttitudeEndpoint {

    @PostMapping
    @ResponseBody
    BaseResponse<AttitudeItem> save(@RequestBody AttitudeSave attitudeSave);

    @PutMapping
    @ResponseBody
    BaseResponse<AttitudeItem> update(@RequestBody AttitudeSave attitudeSave);

    @DeleteMapping
    @ResponseBody
    BaseResponse<Boolean> delete(@RequestParam("code") String attitudeCode);


    @GetMapping
    @ResponseBody
    BaseResponse<AttitudeInfo> get(@RequestParam("code") String attitudeCode);

    @GetMapping("page")
    @ResponseBody
    PageResponse<AttitudeItem> page(@RequestBody AttitudePageQuery pageQuery);
}