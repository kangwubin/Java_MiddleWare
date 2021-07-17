package cn.xatu;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;

// 分页查询
public class ESTest_Doc_Filter {
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
        // 查询所有数据
        sourceBuilder.query(QueryBuilders.matchAllQuery());
        // 查询过滤字段
        String[] excludes = {};
        String[] includes = {"name", "age"};
        sourceBuilder.fetchSource(excludes, includes);
        request.source(sourceBuilder);
        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);
        //查询匹配
        SearchHits hits = response.getHits();
        System.out.println("took:" + response.getTook());
        System.out.println("timeout:" + response.isTimedOut());
        System.out.println("total:" + hits.getTotalHits());
        System.out.println("MaxScore:" + hits.getMaxScore());
        ///输出每条查询的结果信息
        for (SearchHit hit : hits) {
            System.out.println(hit.getSourceAsString());
        }
        // 关闭ES Client
        esClient.close();
    }
}
