package org.featx.sta2ry.musca.endpoint;

import lombok.extern.slf4j.Slf4j;
import org.featx.spec.model.BaseResponse;
import org.featx.spec.model.PageResponse;
import org.featx.sta2ry.musca.model.TopicusedInfo;
import org.featx.sta2ry.musca.model.TopicusedItem;
import org.featx.sta2ry.musca.model.TopicusedPageQuery;
import org.featx.sta2ry.musca.model.TopicusedSave;
import org.springframework.cloud.openfeign.FallbackFactory;

import java.util.Collections;

import static org.featx.spec.enums.BusinessError.SERVER_REMOTE_PROCEDURE_CALL;

@Slf4j
public class TopicusedFallbackFactory implements FallbackFactory<TopicusedEndpoint> {
    @Override
    public TopicusedEndpoint create(Throwable cause) {
        return new TopicusedEndpoint() {
            @Override
            public BaseResponse<TopicusedItem> save(TopicusedSave topicusedSave) {
                log.error("Rpc topicused save error", cause);
                return BaseResponse.occur(SERVER_REMOTE_PROCEDURE_CALL, cause.getMessage()).of(TopicusedItem.class);
            }

            @Override
            public BaseResponse<TopicusedItem> update(TopicusedSave topicusedSave) {
                log.error("Rpc topicused update error", cause);
                return BaseResponse.occur(SERVER_REMOTE_PROCEDURE_CALL, cause.getMessage()).of(TopicusedItem.class);
            }

            @Override
            public BaseResponse<Boolean> delete(String topicusedCode) {
                log.error("Rpc topicused delete error", cause);
                return BaseResponse.occur(SERVER_REMOTE_PROCEDURE_CALL, cause.getMessage()).of(Boolean.class);
            }

            @Override
            public BaseResponse<TopicusedInfo> get(String topicusedCode) {
                log.error("Rpc topicused retrieve error", cause);
                return BaseResponse.occur(SERVER_REMOTE_PROCEDURE_CALL, cause.getMessage()).of(TopicusedInfo.class);
            }

            @Override
            public PageResponse<TopicusedItem> page(TopicusedPageQuery pageQuery) {
                log.error("Rpc page topicused retrieve error", cause);
                return PageResponse.occur(SERVER_REMOTE_PROCEDURE_CALL, Collections.singletonList(cause.getMessage()))
                        .ofList(TopicusedItem.class);
            }
        };
    }
}
