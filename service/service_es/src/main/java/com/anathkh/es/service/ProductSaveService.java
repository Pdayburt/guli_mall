package com.anathkh.es.service;

import com.anatkh.commonUtil.to.es.SkuESModel;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.IOException;
import java.util.List;

public interface ProductSaveService {

    Boolean productStatusUp(List<SkuESModel> skuESModelList) throws IOException;
}
