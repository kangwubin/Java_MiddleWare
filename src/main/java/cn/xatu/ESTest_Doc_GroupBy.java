package cn.xatu;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;

// 分组统计
public class ESTest_Doc_GroupBy {
    //创建ES客户端对象
    public static void main(String[] args) throws Exception {
        RestHighLevelClient esClient = new RestHighLevelClient(RestClient.builder
                (new HttpHost("localhost", 9200, "http")));
        //查询索引中全部数据
        SearchRequest request = new SearchRequest();
        // 查询的索引
        request.indices("user");
        // 构建查询的请求体
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        // 分组统计
        sourceBuilder.aggregation(AggregationBuilders.terms("age_groupby").field("age"));
        // 设置请求体
        request.source(sourceBuilder);
        // 客户端发送请求，获取响应对象
        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);
        System.out.println(response);
        // 关闭ES Client
        esClient.close();
    }
}
