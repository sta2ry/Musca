package org.featx.sta2ry.musca.endpoint;

import lombok.extern.slf4j.Slf4j;
import org.featx.spec.model.BaseResponse;
import org.featx.spec.model.PageResponse;
import org.featx.sta2ry.musca.model.FollowInfo;
import org.featx.sta2ry.musca.model.FollowItem;
import org.featx.sta2ry.musca.model.FollowPageQuery;
import org.featx.sta2ry.musca.model.FollowSave;
import org.springframework.cloud.openfeign.FallbackFactory;

import java.util.Collections;

import static org.featx.spec.enums.BusinessError.SERVER_REMOTE_PROCEDURE_CALL;

@Slf4j
public class FollowFallbackFactory implements FallbackFactory<FollowEndpoint> {
    @Override
    public FollowEndpoint create(Throwable cause) {
        return new FollowEndpoint() {
            @Override
            public BaseResponse<FollowItem> save(FollowSave followSave) {
                log.error("Rpc follow save error", cause);
                return BaseResponse.occur(SERVER_REMOTE_PROCEDURE_CALL, cause.getMessage()).of(FollowItem.class);
            }

            @Override
            public BaseResponse<FollowItem> update(FollowSave followSave) {
                log.error("Rpc follow update error", cause);
                return BaseResponse.occur(SERVER_REMOTE_PROCEDURE_CALL, cause.getMessage()).of(FollowItem.class);
            }

            @Override
            public BaseResponse<Boolean> delete(String followCode) {
                log.error("Rpc follow delete error", cause);
                return BaseResponse.occur(SERVER_REMOTE_PROCEDURE_CALL, cause.getMessage()).of(Boolean.class);
            }

            @Override
            public BaseResponse<FollowInfo> get(String followCode) {
                log.error("Rpc follow retrieve error", cause);
                return BaseResponse.occur(SERVER_REMOTE_PROCEDURE_CALL, cause.getMessage()).of(FollowInfo.class);
            }

            @Override
            public PageResponse<FollowItem> page(FollowPageQuery pageQuery) {
                log.error("Rpc page follow retrieve error", cause);
                return PageResponse.occur(SERVER_REMOTE_PROCEDURE_CALL, Collections.singletonList(cause.getMessage()))
                        .ofList(FollowItem.class);
            }
        };
    }
}
