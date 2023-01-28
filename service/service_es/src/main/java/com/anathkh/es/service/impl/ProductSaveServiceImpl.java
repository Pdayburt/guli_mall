package com.anathkh.es.service.impl;

import com.alibaba.fastjson.JSON;
import com.anathkh.es.config.GuliElasticSearchConfig;
import com.anathkh.es.constant.ESConstant;
import com.anathkh.es.service.ProductSaveService;
import com.anatkh.commonUtil.to.es.SkuESModel;
import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ProductSaveServiceImpl implements ProductSaveService {

    @Autowired
    private RestHighLevelClient restHighLevelClient;
    @Override
    public Boolean productStatusUp(List<SkuESModel> skuESModelList) throws IOException {
//        BulkRequest bulkRequest, RequestOptions options
        BulkRequest bulkRequest = new BulkRequest();
        skuESModelList.forEach(skuESModel -> {
            IndexRequest indexRequest = new IndexRequest(ESConstant.PRODUCT_INDEX);
            indexRequest.id(skuESModel.getSkuId().toString());
            indexRequest.source(JSON.toJSONString(skuESModel), XContentType.JSON);
            bulkRequest.add(indexRequest);
        });
        BulkResponse bulk = restHighLevelClient.bulk(bulkRequest, GuliElasticSearchConfig.COMMON_OPTIONS);
        boolean b = bulk.hasFailures();
        List<String> collect = Arrays.stream(bulk.getItems()).sequential().map(item -> {
            return item.getId();
        }).collect(Collectors.toList());
        log.error("商品上架完成：{},返回数据",collect,bulk.toString());
        return b;
    }
}
