package org.featx.sta2ry.musca.convert;

import org.featx.sta2ry.musca.criteria.FollowCriteria;
import org.featx.sta2ry.musca.entity.FollowEntity;
import org.featx.sta2ry.musca.model.FollowSave;
import org.featx.sta2ry.musca.model.FollowInfo;
import org.featx.sta2ry.musca.model.FollowItem;
import org.featx.sta2ry.musca.model.FollowPageQuery;

public class FollowConvert {

    private FollowConvert() {
        
    }

    public static FollowEntity toEntity(FollowSave followSave) {
        FollowEntity followEntity = new FollowEntity();
        followEntity.setCode(followSave.getCode());
        followEntity.setType(followSave.getType());
        return followEntity;
    }

    public static FollowInfo toInfo(FollowEntity entity) {
        FollowInfo info = new FollowInfo();
        info.setCode(entity.getCode());
        info.setType(entity.getType());
        return info;
    }

    public static FollowItem toItem(FollowEntity entity) {
        FollowItem item = new FollowItem();
        item.setCode(entity.getCode());
        item.setType(entity.getType());
        return item;
    }

    public static FollowCriteria toCriteria(FollowPageQuery pageQuery) {
        FollowCriteria criteria = new FollowCriteria();
//        criteria.setCode(pageQuery.getCode());
        return criteria;
    }
}
