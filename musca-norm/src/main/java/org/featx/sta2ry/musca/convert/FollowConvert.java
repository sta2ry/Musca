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
        followEntity.setName(followSave.getName());
        followEntity.setType(followSave.getType());
        followEntity.setDescription(followSave.getDescription());
        return followEntity;
    }

    public static FollowInfo toInfo(FollowEntity entity) {
        FollowInfo info = new FollowInfo();
        info.setCode(entity.getCode());
        info.setName(entity.getName());
        info.setType(entity.getType());
        info.setDescription(entity.getDescription());
        return info;
    }

    public static FollowItem toItem(FollowEntity entity) {
        FollowItem item = new FollowItem();
        item.setCode(entity.getCode());
        item.setName(entity.getName());
        item.setType(entity.getType());
        item.setDescription(entity.getDescription());
        return item;
    }

    public static FollowCriteria toCriteria(FollowPageQuery pageQuery) {
        FollowCriteria criteria = new FollowCriteria();
//        criteria.setCode(pageQuery.getCode());
        return criteria;
    }
}
