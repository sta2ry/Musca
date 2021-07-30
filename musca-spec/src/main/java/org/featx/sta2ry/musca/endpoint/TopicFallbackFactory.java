package org.featx.sta2ry.musca.endpoint;

import lombok.extern.slf4j.Slf4j;
import org.featx.spec.model.BaseResponse;
import org.featx.spec.model.PageResponse;
import org.featx.sta2ry.musca.model.TopicInfo;
import org.featx.sta2ry.musca.model.TopicItem;
import org.featx.sta2ry.musca.model.TopicPageQuery;
import org.featx.sta2ry.musca.model.TopicSave;
import org.springframework.cloud.openfeign.FallbackFactory;

import java.util.Collections;

import static org.featx.spec.enums.BusinessError.SERVER_REMOTE_PROCEDURE_CALL;

@Slf4j
public class TopicFallbackFactory implements FallbackFactory<TopicEndpoint> {
    @Override
    public TopicEndpoint create(Throwable cause) {
        return new TopicEndpoint() {
            @Override
            public BaseResponse<TopicItem> save(TopicSave topicSave) {
                log.error("Rpc topic save error", cause);
                return BaseResponse.occur(SERVER_REMOTE_PROCEDURE_CALL, cause.getMessage()).of(TopicItem.class);
            }

            @Override
            public BaseResponse<TopicItem> update(TopicSave topicSave) {
                log.error("Rpc topic update error", cause);
                return BaseResponse.occur(SERVER_REMOTE_PROCEDURE_CALL, cause.getMessage()).of(TopicItem.class);
            }

            @Override
            public BaseResponse<Boolean> delete(String topicCode) {
                log.error("Rpc topic delete error", cause);
                return BaseResponse.occur(SERVER_REMOTE_PROCEDURE_CALL, cause.getMessage()).of(Boolean.class);
            }

            @Override
            public BaseResponse<TopicInfo> get(String topicCode) {
                log.error("Rpc topic retrieve error", cause);
                return BaseResponse.occur(SERVER_REMOTE_PROCEDURE_CALL, cause.getMessage()).of(TopicInfo.class);
            }

            @Override
            public PageResponse<TopicItem> page(TopicPageQuery pageQuery) {
                log.error("Rpc page topic retrieve error", cause);
                return PageResponse.occur(SERVER_REMOTE_PROCEDURE_CALL, Collections.singletonList(cause.getMessage()))
                        .ofList(TopicItem.class);
            }
        };
    }
}
