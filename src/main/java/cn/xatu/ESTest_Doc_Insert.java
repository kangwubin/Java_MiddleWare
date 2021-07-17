package cn.xatu;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

public class ESTest_Doc_Insert {
    //创建ES客户端对象
    public static void main(String[] args) throws Exception {
        RestHighLevelClient esClient = new RestHighLevelClient(RestClient.builder
                (new HttpHost("localhost", 9200, "http")));
        //插入数据
        IndexRequest request = new IndexRequest();
        //插入索引为user，id为1001的索引数据
        request.index("user").id("1001");

        User user = new User();
        user.setName("AAAA");
        user.setSex("男");
        user.setAge(28);

        //向ES插入数据，必须将数据格式转为json
        ObjectMapper mapper = new ObjectMapper();
        String userJson = mapper.writeValueAsString(user);
        request.source(userJson, XContentType.JSON);

        IndexResponse response = esClient.index(request, RequestOptions.DEFAULT);
        System.out.println(response.getResult());
        // 关闭ES Client
        esClient.close();
    }
}
