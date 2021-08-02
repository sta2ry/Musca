package org.featx.sta2ry.musca.service;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.featx.spec.feature.IdGenerate;
import org.featx.spec.model.QuerySection;
import org.featx.spec.util.CollectionUtil;
import org.featx.spec.util.StringUtil;
import org.featx.sta2ry.musca.convert.ProsecuteConvert;
import org.featx.sta2ry.musca.criteria.ProsecuteCriteria;
import org.featx.sta2ry.musca.entity.ProsecuteEntity;
import org.featx.sta2ry.musca.mapper.ProsecuteMapper;
import org.featx.sta2ry.musca.model.ProsecuteInfo;
import org.featx.sta2ry.musca.model.ProsecuteItem;
import org.featx.sta2ry.musca.model.ProsecutePageQuery;
import org.featx.sta2ry.musca.model.ProsecuteSave;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.featx.sta2ry.musca.convert.ProsecuteConvert.*;
/**
 *
 */
@Slf4j
@Service
@DubboService
public class ProsecuteServiceImpl implements ProsecuteService {

    @Resource
    private ProsecuteMapper prosecuteMapper;

    @Resource
    private IdGenerate idGenerate;

    @Override
    @Transactional
    public ProsecuteItem save(ProsecuteSave prosecuteSave) {
        ProsecuteEntity entity = toEntity(prosecuteSave);
        if (StringUtil.isBlank(entity.getCode())) {
            entity.setCode(String.format("%s%s", "DFM", Long.toString(idGenerate.nextId(), 36)));
            prosecuteMapper.insert(entity);
        } else {
            prosecuteMapper.upsert(entity);
        }
        return toItem(entity);
    }

    @Override
    @Transactional
    public ProsecuteItem update(ProsecuteSave prosecuteSave) {
        ProsecuteEntity entity = toEntity(prosecuteSave);
        prosecuteMapper.update(entity);
        return toItem(entity);
    }

    @Override
    public void delete(String code) {
        prosecuteMapper.delete(code, true);
    }

    @Override
    public ProsecuteInfo findOne(String code) {
        return toInfo(prosecuteMapper.selectByCode(code));
    }

    @Override
    public List<ProsecuteItem> listByCodes(List<String> codes) {
        return Optional.ofNullable(codes)
                .map(list -> list.stream().filter(StringUtil::isNotBlank).collect(Collectors.toList()))
                .filter(CollectionUtil::isNotEmpty)
                .map(list -> prosecuteMapper.selectByCodes(list))
                .filter(CollectionUtil::isNotEmpty)
                .map(list -> list.stream()
                        .map(ProsecuteConvert::toItem)
                        .sorted(Comparator.comparingInt(dme -> codes.indexOf(dme.getCode())))
                        .collect(Collectors.toList()))
                .orElseGet(Lists::newArrayList);
    }

    @Override
    @Transactional(readOnly = true)
    public QuerySection<ProsecuteItem> page(ProsecutePageQuery pageQuery) {
        ProsecuteCriteria criteria = toCriteria(pageQuery);
        long count = prosecuteMapper.countByQuery(criteria);
        if(count <= 0) {
            return QuerySection.of(Lists.newArrayList());
        }
        List<ProsecuteEntity> moduleEntities =
                prosecuteMapper.selectByPage(criteria, pageQuery.correctProperties());
        return QuerySection.of(moduleEntities.stream().map(ProsecuteConvert::toItem).collect(Collectors.toList()))
                .total(count);
    }

}
