package org.featx.sta2ry.musca.endpoint;

import lombok.extern.slf4j.Slf4j;
import org.featx.spec.model.BaseResponse;
import org.featx.spec.model.PageResponse;
import org.featx.sta2ry.musca.model.ProsecuteInfo;
import org.featx.sta2ry.musca.model.ProsecuteItem;
import org.featx.sta2ry.musca.model.ProsecutePageQuery;
import org.featx.sta2ry.musca.model.ProsecuteSave;
import org.springframework.cloud.openfeign.FallbackFactory;

import java.util.Collections;

import static org.featx.spec.enums.BusinessError.SERVER_REMOTE_PROCEDURE_CALL;

@Slf4j
public class ProsecuteFallbackFactory implements FallbackFactory<ProsecuteEndpoint> {
    @Override
    public ProsecuteEndpoint create(Throwable cause) {
        return new ProsecuteEndpoint() {
            @Override
            public BaseResponse<ProsecuteItem> save(ProsecuteSave prosecuteSave) {
                log.error("Rpc prosecute save error", cause);
                return BaseResponse.occur(SERVER_REMOTE_PROCEDURE_CALL, cause.getMessage()).of(ProsecuteItem.class);
            }

            @Override
            public BaseResponse<ProsecuteItem> update(ProsecuteSave prosecuteSave) {
                log.error("Rpc prosecute update error", cause);
                return BaseResponse.occur(SERVER_REMOTE_PROCEDURE_CALL, cause.getMessage()).of(ProsecuteItem.class);
            }

            @Override
            public BaseResponse<Boolean> delete(String prosecuteCode) {
                log.error("Rpc prosecute delete error", cause);
                return BaseResponse.occur(SERVER_REMOTE_PROCEDURE_CALL, cause.getMessage()).of(Boolean.class);
            }

            @Override
            public BaseResponse<ProsecuteInfo> get(String prosecuteCode) {
                log.error("Rpc prosecute retrieve error", cause);
                return BaseResponse.occur(SERVER_REMOTE_PROCEDURE_CALL, cause.getMessage()).of(ProsecuteInfo.class);
            }

            @Override
            public PageResponse<ProsecuteItem> page(ProsecutePageQuery pageQuery) {
                log.error("Rpc page prosecute retrieve error", cause);
                return PageResponse.occur(SERVER_REMOTE_PROCEDURE_CALL, Collections.singletonList(cause.getMessage()))
                        .ofList(ProsecuteItem.class);
            }
        };
    }
}
