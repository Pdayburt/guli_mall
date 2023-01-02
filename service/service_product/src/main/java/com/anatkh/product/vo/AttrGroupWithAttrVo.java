package com.anatkh.product.vo;

import com.anatkh.product.entity.Attr;
import com.anatkh.product.entity.AttrGroup;
import lombok.Data;

import java.util.List;
@Data
public class AttrGroupWithAttrVo extends AttrGroup {

    private List<Attr> attrs;

}
