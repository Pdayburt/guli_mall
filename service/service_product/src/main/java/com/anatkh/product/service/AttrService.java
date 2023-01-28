package com.anatkh.product.service;

import com.anatkh.commonUtil.utils.PageUtils;
import com.anatkh.product.entity.Attr;
import com.anatkh.product.vo.AttrGroupRelationVo;
import com.anatkh.product.vo.AttrResponseVo;
import com.anatkh.product.vo.AttrVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
* @author anatkh
* @description 针对表【pms_attr】的数据库操作Service
* @createDate 2022-12-13 10:57:17
*/
public interface AttrService extends IService<Attr> {

    void saveAttrVo(AttrVo attrVo);

    PageUtils queryPage(Map<String, Object> params, Long catelogId, String attrType);

    AttrResponseVo getAttrInfo(Long attrId);

    void updateAttr(AttrVo attrVo);

    List<Attr> getRelationAttr(Long attrGroupId);

    void deleteRelation(List<AttrGroupRelationVo> vos);

    PageUtils getNoRelationAttr(Long attrGroupId, Map<String, Object> params);

    List<Long> selectSearchAttrs(List<Long> attrIdList);
}
