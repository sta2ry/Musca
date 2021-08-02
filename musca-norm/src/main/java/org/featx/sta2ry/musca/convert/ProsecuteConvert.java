package org.featx.sta2ry.musca.convert;

import org.featx.sta2ry.musca.criteria.ProsecuteCriteria;
import org.featx.sta2ry.musca.entity.ProsecuteEntity;
import org.featx.sta2ry.musca.model.ProsecuteSave;
import org.featx.sta2ry.musca.model.ProsecuteInfo;
import org.featx.sta2ry.musca.model.ProsecuteItem;
import org.featx.sta2ry.musca.model.ProsecutePageQuery;

public class ProsecuteConvert {

    private ProsecuteConvert() {
        
    }

    public static ProsecuteEntity toEntity(ProsecuteSave prosecuteSave) {
        ProsecuteEntity prosecuteEntity = new ProsecuteEntity();
        prosecuteEntity.setCode(prosecuteSave.getCode());
        prosecuteEntity.setType(prosecuteSave.getType());
        prosecuteEntity.setDescription(prosecuteSave.getDescription());
        return prosecuteEntity;
    }

    public static ProsecuteInfo toInfo(ProsecuteEntity entity) {
        ProsecuteInfo info = new ProsecuteInfo();
        info.setCode(entity.getCode());
        info.setType(entity.getType());
        info.setDescription(entity.getDescription());
        return info;
    }

    public static ProsecuteItem toItem(ProsecuteEntity entity) {
        ProsecuteItem item = new ProsecuteItem();
        item.setCode(entity.getCode());
        item.setType(entity.getType());
        item.setDescription(entity.getDescription());
        return item;
    }

    public static ProsecuteCriteria toCriteria(ProsecutePageQuery pageQuery) {
        ProsecuteCriteria criteria = new ProsecuteCriteria();
//        criteria.setCode(pageQuery.getCode());
        return criteria;
    }
}
