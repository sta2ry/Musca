package org.featx.sta2ry.musca.service;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.featx.spec.feature.IdGenerate;
import org.featx.spec.model.QuerySection;
import org.featx.spec.util.CollectionUtil;
import org.featx.spec.util.StringUtil;
import org.featx.sta2ry.musca.convert.AttitudeConvert;
import org.featx.sta2ry.musca.criteria.AttitudeCriteria;
import org.featx.sta2ry.musca.entity.AttitudeEntity;
import org.featx.sta2ry.musca.mapper.AttitudeMapper;
import org.featx.sta2ry.musca.model.AttitudeInfo;
import org.featx.sta2ry.musca.model.AttitudeItem;
import org.featx.sta2ry.musca.model.AttitudePageQuery;
import org.featx.sta2ry.musca.model.AttitudeSave;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.featx.sta2ry.musca.convert.AttitudeConvert.*;

/**
 * @author Excepts
 * @since 2020/4/12 14:27
 */
@Slf4j
@Service
public class AttitudeServiceImpl implements AttitudeService {

    @Resource
    private AttitudeMapper attitudeMapper;

    @Resource
    private IdGenerate idGenerate;

    @Override
    @Transactional
    public AttitudeItem save(AttitudeSave attitudeSave) {
        AttitudeEntity entity = toEntity(attitudeSave);
        if (StringUtil.isBlank(entity.getCode())) {
            entity.setCode(String.format("%s%s", "DFM", Long.toString(idGenerate.nextId(), 36)));
            attitudeMapper.insert(entity);
        } else {
            attitudeMapper.upsert(entity);
        }
        return toItem(entity);
    }

    @Override
    @Transactional
    public AttitudeItem update(AttitudeSave attitudeSave) {
        AttitudeEntity entity = toEntity(attitudeSave);
        attitudeMapper.update(entity);
        return toItem(entity);
    }

    @Override
    public void delete(String code) {
        attitudeMapper.delete(code, true);
    }

    @Override
    public AttitudeInfo findOne(String code) {
        return toInfo(attitudeMapper.selectByCode(code));
    }

    @Override
    public List<AttitudeItem> listByCodes(List<String> codes) {
        return Optional.ofNullable(codes)
                .map(list -> list.stream().filter(StringUtil::isNotBlank).collect(Collectors.toList()))
                .filter(CollectionUtil::isNotEmpty)
                .map(list -> attitudeMapper.selectByCodes(list))
                .filter(CollectionUtil::isNotEmpty)
                .map(list -> list.stream()
                        .map(AttitudeConvert::toItem)
                        .sorted(Comparator.comparingInt(dme -> codes.indexOf(dme.getCode())))
                        .collect(Collectors.toList()))
                .orElseGet(Lists::newArrayList);
    }

    @Override
    @Transactional(readOnly = true)
    public QuerySection<AttitudeItem> page(AttitudePageQuery pageQuery) {
        AttitudeCriteria criteria = toCriteria(pageQuery);
        long count = attitudeMapper.countByQuery(criteria);
        if(count <= 0) {
            return QuerySection.of(Lists.newArrayList());
        }
        List<AttitudeEntity> moduleEntities =
                attitudeMapper.selectByPage(criteria, pageQuery.correctProperties());
        return QuerySection.of(moduleEntities.stream().map(AttitudeConvert::toItem).collect(Collectors.toList()))
                .total(count);
    }

}
