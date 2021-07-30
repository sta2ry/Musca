package org.featx.sta2ry.musca.endpoint;

import lombok.extern.slf4j.Slf4j;
import org.featx.spec.model.BaseResponse;
import org.featx.spec.model.PageResponse;
import org.featx.sta2ry.musca.model.MentionInfo;
import org.featx.sta2ry.musca.model.MentionItem;
import org.featx.sta2ry.musca.model.MentionPageQuery;
import org.featx.sta2ry.musca.model.MentionSave;
import org.springframework.cloud.openfeign.FallbackFactory;

import java.util.Collections;

import static org.featx.spec.enums.BusinessError.SERVER_REMOTE_PROCEDURE_CALL;

@Slf4j
public class MentionFallbackFactory implements FallbackFactory<MentionEndpoint> {
    @Override
    public MentionEndpoint create(Throwable cause) {
        return new MentionEndpoint() {
            @Override
            public BaseResponse<MentionItem> save(MentionSave mentionSave) {
                log.error("Rpc mention save error", cause);
                return BaseResponse.occur(SERVER_REMOTE_PROCEDURE_CALL, cause.getMessage()).of(MentionItem.class);
            }

            @Override
            public BaseResponse<MentionItem> update(MentionSave mentionSave) {
                log.error("Rpc mention update error", cause);
                return BaseResponse.occur(SERVER_REMOTE_PROCEDURE_CALL, cause.getMessage()).of(MentionItem.class);
            }

            @Override
            public BaseResponse<Boolean> delete(String mentionCode) {
                log.error("Rpc mention delete error", cause);
                return BaseResponse.occur(SERVER_REMOTE_PROCEDURE_CALL, cause.getMessage()).of(Boolean.class);
            }

            @Override
            public BaseResponse<MentionInfo> get(String mentionCode) {
                log.error("Rpc mention retrieve error", cause);
                return BaseResponse.occur(SERVER_REMOTE_PROCEDURE_CALL, cause.getMessage()).of(MentionInfo.class);
            }

            @Override
            public PageResponse<MentionItem> page(MentionPageQuery pageQuery) {
                log.error("Rpc page mention retrieve error", cause);
                return PageResponse.occur(SERVER_REMOTE_PROCEDURE_CALL, Collections.singletonList(cause.getMessage()))
                        .ofList(MentionItem.class);
            }
        };
    }
}
