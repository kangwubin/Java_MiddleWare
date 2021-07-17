package cn.xatu;

import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

public class ESTest_Index_Delete {
    public static void main(String[] args) throws Exception {
        //创建ES客户端对象
        RestHighLevelClient esClient = new RestHighLevelClient(RestClient.builder
                (new HttpHost("localhost", 9200, "http")));
        // 查询索引 - 请求对象
        DeleteIndexRequest request = new DeleteIndexRequest("user");
        // 发送删除请求，获取响应
        AcknowledgedResponse response = esClient.indices().delete(request, RequestOptions.DEFAULT);
        //响应状态
        System.out.println(response.isAcknowledged());
        //关闭ES Client
        esClient.close();
    }
}
