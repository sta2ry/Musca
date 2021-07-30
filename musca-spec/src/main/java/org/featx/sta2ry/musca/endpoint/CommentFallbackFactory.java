package org.featx.sta2ry.musca.endpoint;

import lombok.extern.slf4j.Slf4j;
import org.featx.spec.model.BaseResponse;
import org.featx.spec.model.PageResponse;
import org.featx.sta2ry.musca.model.CommentInfo;
import org.featx.sta2ry.musca.model.CommentItem;
import org.featx.sta2ry.musca.model.CommentPageQuery;
import org.featx.sta2ry.musca.model.CommentSave;
import org.springframework.cloud.openfeign.FallbackFactory;

import java.util.Collections;

import static org.featx.spec.enums.BusinessError.SERVER_REMOTE_PROCEDURE_CALL;

@Slf4j
public class CommentFallbackFactory implements FallbackFactory<CommentEndpoint> {
    @Override
    public CommentEndpoint create(Throwable cause) {
        return new CommentEndpoint() {
            @Override
            public BaseResponse<CommentItem> save(CommentSave commentSave) {
                log.error("Rpc comment save error", cause);
                return BaseResponse.occur(SERVER_REMOTE_PROCEDURE_CALL, cause.getMessage()).of(CommentItem.class);
            }

            @Override
            public BaseResponse<CommentItem> update(CommentSave commentSave) {
                log.error("Rpc comment update error", cause);
                return BaseResponse.occur(SERVER_REMOTE_PROCEDURE_CALL, cause.getMessage()).of(CommentItem.class);
            }

            @Override
            public BaseResponse<Boolean> delete(String commentCode) {
                log.error("Rpc comment delete error", cause);
                return BaseResponse.occur(SERVER_REMOTE_PROCEDURE_CALL, cause.getMessage()).of(Boolean.class);
            }

            @Override
            public BaseResponse<CommentInfo> get(String commentCode) {
                log.error("Rpc comment retrieve error", cause);
                return BaseResponse.occur(SERVER_REMOTE_PROCEDURE_CALL, cause.getMessage()).of(CommentInfo.class);
            }

            @Override
            public PageResponse<CommentItem> page(CommentPageQuery pageQuery) {
                log.error("Rpc page comment retrieve error", cause);
                return PageResponse.occur(SERVER_REMOTE_PROCEDURE_CALL, Collections.singletonList(cause.getMessage()))
                        .ofList(CommentItem.class);
            }
        };
    }
}
