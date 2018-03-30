package com.ming;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;

/**
 * es client   使用 高级 http 客户端
 *
 * @author ming
 * @date 2018-03-30 10:51
 */
public class ElasticSearchClientUtils {
    private static final String host = "localhost";
    private static  final  int port = 9200;
    private static  final String type = "http";
    public static  final  String index = "ming-index-";

    private static RestHighLevelClient restHighLevelClient;

    volatile BulkRequest bulkRequest= new BulkRequest();
    /**
     * 初始化 es client
     * public static final int DEFAULT_CONNECT_TIMEOUT_MILLIS = 1000;
     *     public static final int DEFAULT_SOCKET_TIMEOUT_MILLIS = 30000;
     *     public static final int DEFAULT_MAX_RETRY_TIMEOUT_MILLIS = DEFAULT_SOCKET_TIMEOUT_MILLIS;
     *     public static final int DEFAULT_CONNECTION_REQUEST_TIMEOUT_MILLIS = 500;
     *     public static final int DEFAULT_MAX_CONN_PER_ROUTE = 10;
     *     public static final int DEFAULT_MAX_CONN_TOTAL = 30;
     * @author ming
     * @date 2018-03-30 10:55
     */
    public static void init() {
        RestClientBuilder builder = RestClient.builder(new HttpHost(host, port, type));
        builder.setMaxRetryTimeoutMillis(5*60*1000);
        builder.setRequestConfigCallback(requestConfigBuilder -> {
            requestConfigBuilder.setConnectTimeout(3000);
            requestConfigBuilder.setSocketTimeout(3000);
            requestConfigBuilder.setConnectionRequestTimeout(3000);
            return requestConfigBuilder;
        });
        builder.setHttpClientConfigCallback(httpClientBuilder -> {
            httpClientBuilder.setMaxConnPerRoute(1000);
            httpClientBuilder.setMaxConnTotal(2000);
            return httpClientBuilder;
        });

        restHighLevelClient = new RestHighLevelClient(builder);
        //createIndex(index,index);
    }


    /**
     * 创建 index
     *
     * @author ming
     * @date 2018-03-30 10:56
     */
    public static boolean createIndex(String type, String index) {
        String mappingSource = "  {\n" +
                "    \""+index+"\": {\n" +
                "      \"properties\": {\n" +
                "        \"message\": {\n" +
                "          \"type\": \"text\"\n" +
                "        }\n" +
                "      }\n" +
                "    }\n" +
                "  }";
        boolean result = false;
        CreateIndexRequest createIndexRequest = new CreateIndexRequest(index);
        createIndexRequest.mapping(type, mappingSource, XContentType.JSON);
        try {
            CreateIndexResponse createIndexResponse = restHighLevelClient.indices().create(createIndexRequest);
            System.out.println(JSON.toJSONString(createIndexResponse));
            //result = createIndexResponse.isShardsAcknowledged();
        } catch (IOException e) {
            //e.printStackTrace();
        }
        return result;
    }


    /**
     * 插入数据到 index中
     *
     * @param index
     * @param type
     * @param source
     * @return int
     * @exception IOException
     * @author ming
     * @date 2018-03-30 11:09
     */
    public static int insertToIndex(String index, String type, Object source){
        IndexRequest indexRequest = new IndexRequest(index, type);
        indexRequest.source(JSON.toJSONString(source), XContentType.JSON);
        try {
            restHighLevelClient.index(indexRequest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 1;
    }


}
