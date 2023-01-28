package com.anatkh;

import com.alibaba.fastjson.JSON;
import com.anathkh.es.ESApplication;
import com.anathkh.es.config.GuliElasticSearchConfig;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.metrics.Avg;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ESApplication.class)
public class ESTest {

    @Autowired
    private RestHighLevelClient restHighLevelClient;


    @Test
    public void SearchData() throws IOException {

        /**
         * address 中包含mill的 并且按照年龄聚合，并且请求这些年龄段的这些人的平均薪资
         */
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("bank");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchQuery("address","mill"))
                .aggregation(AggregationBuilders.terms("ageAGG").field("age"))
                        .aggregation(AggregationBuilders.avg("balanceAVG").field("balance"));
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, GuliElasticSearchConfig.COMMON_OPTIONS);
        SearchHits hits = searchResponse.getHits();
        SearchHit[] searchHits = hits.getHits();
        for (SearchHit searchHit : searchHits) {
            String sourceAsString = searchHit.getSourceAsString();
            System.out.println(sourceAsString);
            Account account = JSON.parseObject(sourceAsString, Account.class);
            System.out.println(account);
        }
        Aggregations aggregations = searchResponse.getAggregations();
        Terms ageAGG = aggregations.get("ageAGG");
        for (Terms.Bucket bucket : ageAGG.getBuckets()) {
            Object age = bucket.getKey();
            System.out.println("年龄为："+age);
        }
        Avg balanceAVG = aggregations.get("balanceAVG");
        double value = balanceAVG.getValue();
        System.out.println("平均薪资为："+value);

    }

    @Test
    public void testSearch() throws IOException {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("users");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.source(searchSourceBuilder);
        SearchResponse search = restHighLevelClient.search(searchRequest, GuliElasticSearchConfig.COMMON_OPTIONS);
        SearchHits hits = search.getHits();
        for (SearchHit hit : hits.getHits()) {
            System.out.println(hit.toString());
        }
    }

    /**
     * Auto-generated: 2023-01-12 16:17:37
     * @author bejson.com (i@bejson.com)
     * @website http://www.bejson.com/java2pojo/
     */
    @Data
    @ToString
     static class Account {

        private int account_number;
        private int balance;
        private String firstname;
        private String lastname;
        private int age;
        private String gender;
        private String address;
        private String employer;
        private String email;
        private String city;
        private String state;

    }
    
    
    
    
    
    
    
    
    
    
    @Test
    public void indexData() throws IOException {
        /**
         * 保存 更新二合一
         */
        IndexRequest indexRequest = new IndexRequest("users").id("2");
        User user = new User();
        user.setUserName("jack");
        user.setAge(18);
        user.setGender("男");
        indexRequest.source(JSON.toJSONString(user), XContentType.JSON);

        IndexResponse response = restHighLevelClient.index(indexRequest, GuliElasticSearchConfig.COMMON_OPTIONS);
        System.out.println(response);
    }
    @Data
    @ToString
    @EqualsAndHashCode
    class User{
        private String userName;
        private String gender;
        private Integer age;
    }



}
