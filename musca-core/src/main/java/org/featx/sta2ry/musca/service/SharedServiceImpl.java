package org.featx.sta2ry.musca.service;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.featx.spec.feature.IdGenerate;
import org.featx.spec.model.QuerySection;
import org.featx.spec.util.CollectionUtil;
import org.featx.spec.util.StringUtil;
import org.featx.sta2ry.musca.convert.SharedConvert;
import org.featx.sta2ry.musca.criteria.SharedCriteria;
import org.featx.sta2ry.musca.entity.SharedEntity;
import org.featx.sta2ry.musca.mapper.SharedMapper;
import org.featx.sta2ry.musca.model.SharedInfo;
import org.featx.sta2ry.musca.model.SharedItem;
import org.featx.sta2ry.musca.model.SharedPageQuery;
import org.featx.sta2ry.musca.model.SharedSave;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.featx.sta2ry.musca.convert.SharedConvert.*;

/**
 * @author Excepts
 * @since 2020/4/12 14:27
 */
@Slf4j
@Service
public class SharedServiceImpl implements SharedService {

    @Resource
    private SharedMapper sharedMapper;

    @Resource
    private IdGenerate idGenerate;

    @Override
    @Transactional
    public SharedItem save(SharedSave sharedSave) {
        SharedEntity entity = toEntity(sharedSave);
        if (StringUtil.isBlank(entity.getCode())) {
            entity.setCode(String.format("%s%s", "DFM", Long.toString(idGenerate.nextId(), 36)));
            sharedMapper.insert(entity);
        } else {
            sharedMapper.upsert(entity);
        }
        return toItem(entity);
    }

    @Override
    @Transactional
    public SharedItem update(SharedSave sharedSave) {
        SharedEntity entity = toEntity(sharedSave);
        sharedMapper.update(entity);
        return toItem(entity);
    }

    @Override
    public void delete(String code) {
        sharedMapper.delete(code, true);
    }

    @Override
    public SharedInfo findOne(String code) {
        return toInfo(sharedMapper.selectByCode(code));
    }

    @Override
    public List<SharedItem> listByCodes(List<String> codes) {
        return Optional.ofNullable(codes)
                .map(list -> list.stream().filter(StringUtil::isNotBlank).collect(Collectors.toList()))
                .filter(CollectionUtil::isNotEmpty)
                .map(list -> sharedMapper.selectByCodes(list))
                .filter(CollectionUtil::isNotEmpty)
                .map(list -> list.stream()
                        .map(SharedConvert::toItem)
                        .sorted(Comparator.comparingInt(dme -> codes.indexOf(dme.getCode())))
                        .collect(Collectors.toList()))
                .orElseGet(Lists::newArrayList);
    }

    @Override
    @Transactional(readOnly = true)
    public QuerySection<SharedItem> page(SharedPageQuery pageQuery) {
        SharedCriteria criteria = toCriteria(pageQuery);
        long count = sharedMapper.countByQuery(criteria);
        if(count <= 0) {
            return QuerySection.of(Lists.newArrayList());
        }
        List<SharedEntity> moduleEntities =
                sharedMapper.selectByPage(criteria, pageQuery.correctProperties());
        return QuerySection.of(moduleEntities.stream().map(SharedConvert::toItem).collect(Collectors.toList()))
                .total(count);
    }

}
