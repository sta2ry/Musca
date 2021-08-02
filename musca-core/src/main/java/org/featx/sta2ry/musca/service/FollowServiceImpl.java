package org.featx.sta2ry.musca.service;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.featx.spec.feature.IdGenerate;
import org.featx.spec.model.QuerySection;
import org.featx.spec.util.CollectionUtil;
import org.featx.spec.util.StringUtil;
import org.featx.sta2ry.musca.criteria.FollowCriteria;
import org.featx.sta2ry.musca.entity.FollowEntity;
import org.featx.sta2ry.musca.mapper.FollowMapper;
import org.featx.sta2ry.musca.model.FollowInfo;
import org.featx.sta2ry.musca.model.FollowItem;
import org.featx.sta2ry.musca.model.FollowPageQuery;
import org.featx.sta2ry.musca.model.FollowSave;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.featx.sta2ry.musca.convert.FollowConvert.*;
/**
 *
 */
@Slf4j
@Service
@DubboService
public class FollowServiceImpl implements FollowService {

    @Resource
    private FollowMapper followMapper;

    @Resource
    private IdGenerate idGenerate;

    @Override
    @Transactional
    public FollowItem save(FollowSave followSave) {
        FollowEntity entity = toEntity(followSave);
        if (StringUtil.isBlank(entity.getCode())) {
            entity.setCode(String.format("%s%s", "DFM", Long.toString(idGenerate.nextId(), 36)));
            followMapper.insert(entity);
        } else {
            followMapper.upsert(entity);
        }
        return toItem(entity);
    }

    @Override
    @Transactional
    public FollowItem update(FollowSave followSave) {
        FollowEntity entity = toEntity(followSave);
        followMapper.update(entity);
        return toItem(entity);
    }

    @Override
    public void delete(String code) {
        followMapper.delete(code, true);
    }

    @Override
    public FollowInfo findOne(String code) {
        return toInfo(followMapper.selectByCode(code));
    }

    @Override
    public List<FollowItem> listByCodes(List<String> codes) {
        return Optional.ofNullable(codes)
                .map(list -> list.stream().filter(StringUtil::isNotBlank).collect(Collectors.toList()))
                .filter(CollectionUtil::isNotEmpty)
                .map(list -> followMapper.selectByCodes(list))
                .filter(CollectionUtil::isNotEmpty)
                .map(list -> list.stream()
                        .map(this::toItem)
                        .sorted(Comparator.comparingInt(dme -> codes.indexOf(dme.getCode())))
                        .collect(Collectors.toList()))
                .orElseGet(Lists::newArrayList);
    }

    @Override
    @Transactional(readOnly = true)
    public QuerySection<FollowItem> page(FollowPageQuery pageQuery) {
        FollowCriteria criteria = toCriteria(pageQuery);
        long count = followMapper.countByQuery(criteria);
        if(count <= 0) {
            return QuerySection.of(Lists.newArrayList());
        }
        List<FollowEntity> moduleEntities =
                followMapper.selectByPage(criteria, pageQuery.correctProperties());
        return QuerySection.of(moduleEntities.stream().map(this::toItem).collect(Collectors.toList()))
                .total(count);
    }

}
