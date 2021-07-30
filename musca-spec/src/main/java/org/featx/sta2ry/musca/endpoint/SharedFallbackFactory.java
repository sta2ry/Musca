package org.featx.sta2ry.musca.endpoint;

import lombok.extern.slf4j.Slf4j;
import org.featx.spec.model.BaseResponse;
import org.featx.spec.model.PageResponse;
import org.featx.sta2ry.musca.model.SharedInfo;
import org.featx.sta2ry.musca.model.SharedItem;
import org.featx.sta2ry.musca.model.SharedPageQuery;
import org.featx.sta2ry.musca.model.SharedSave;
import org.springframework.cloud.openfeign.FallbackFactory;

import java.util.Collections;

import static org.featx.spec.enums.BusinessError.SERVER_REMOTE_PROCEDURE_CALL;

@Slf4j
public class SharedFallbackFactory implements FallbackFactory<SharedEndpoint> {
    @Override
    public SharedEndpoint create(Throwable cause) {
        return new SharedEndpoint() {
            @Override
            public BaseResponse<SharedItem> save(SharedSave sharedSave) {
                log.error("Rpc shared save error", cause);
                return BaseResponse.occur(SERVER_REMOTE_PROCEDURE_CALL, cause.getMessage()).of(SharedItem.class);
            }

            @Override
            public BaseResponse<SharedItem> update(SharedSave sharedSave) {
                log.error("Rpc shared update error", cause);
                return BaseResponse.occur(SERVER_REMOTE_PROCEDURE_CALL, cause.getMessage()).of(SharedItem.class);
            }

            @Override
            public BaseResponse<Boolean> delete(String sharedCode) {
                log.error("Rpc shared delete error", cause);
                return BaseResponse.occur(SERVER_REMOTE_PROCEDURE_CALL, cause.getMessage()).of(Boolean.class);
            }

            @Override
            public BaseResponse<SharedInfo> get(String sharedCode) {
                log.error("Rpc shared retrieve error", cause);
                return BaseResponse.occur(SERVER_REMOTE_PROCEDURE_CALL, cause.getMessage()).of(SharedInfo.class);
            }

            @Override
            public PageResponse<SharedItem> page(SharedPageQuery pageQuery) {
                log.error("Rpc page shared retrieve error", cause);
                return PageResponse.occur(SERVER_REMOTE_PROCEDURE_CALL, Collections.singletonList(cause.getMessage()))
                        .ofList(SharedItem.class);
            }
        };
    }
}
