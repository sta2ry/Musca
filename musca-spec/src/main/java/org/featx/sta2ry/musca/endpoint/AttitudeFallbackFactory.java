package org.featx.sta2ry.musca.endpoint;

import lombok.extern.slf4j.Slf4j;
import org.featx.spec.model.BaseResponse;
import org.featx.spec.model.PageResponse;
import org.featx.sta2ry.musca.model.AttitudeInfo;
import org.featx.sta2ry.musca.model.AttitudeItem;
import org.featx.sta2ry.musca.model.AttitudePageQuery;
import org.featx.sta2ry.musca.model.AttitudeSave;
import org.springframework.cloud.openfeign.FallbackFactory;

import java.util.Collections;

import static org.featx.spec.enums.BusinessError.SERVER_REMOTE_PROCEDURE_CALL;

@Slf4j
public class AttitudeFallbackFactory implements FallbackFactory<AttitudeEndpoint> {
    @Override
    public AttitudeEndpoint create(Throwable cause) {
        return new AttitudeEndpoint() {
            @Override
            public BaseResponse<AttitudeItem> save(AttitudeSave attitudeSave) {
                log.error("Rpc attitude save error", cause);
                return BaseResponse.occur(SERVER_REMOTE_PROCEDURE_CALL, cause.getMessage()).of(AttitudeItem.class);
            }

            @Override
            public BaseResponse<AttitudeItem> update(AttitudeSave attitudeSave) {
                log.error("Rpc attitude update error", cause);
                return BaseResponse.occur(SERVER_REMOTE_PROCEDURE_CALL, cause.getMessage()).of(AttitudeItem.class);
            }

            @Override
            public BaseResponse<Boolean> delete(String attitudeCode) {
                log.error("Rpc attitude delete error", cause);
                return BaseResponse.occur(SERVER_REMOTE_PROCEDURE_CALL, cause.getMessage()).of(Boolean.class);
            }

            @Override
            public BaseResponse<AttitudeInfo> get(String attitudeCode) {
                log.error("Rpc attitude retrieve error", cause);
                return BaseResponse.occur(SERVER_REMOTE_PROCEDURE_CALL, cause.getMessage()).of(AttitudeInfo.class);
            }

            @Override
            public PageResponse<AttitudeItem> page(AttitudePageQuery pageQuery) {
                log.error("Rpc page attitude retrieve error", cause);
                return PageResponse.occur(SERVER_REMOTE_PROCEDURE_CALL, Collections.singletonList(cause.getMessage()))
                        .ofList(AttitudeItem.class);
            }
        };
    }
}
