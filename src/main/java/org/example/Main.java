package org.example;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.GetResponse;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.json.JsonpMapper;
import co.elastic.clients.json.JsonpMapperFeatures;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.json.stream.JsonGenerator;
import nl.altindag.ssl.SSLFactory;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;




@JsonIgnoreProperties(ignoreUnknown = true)//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    //Logger factory
    private static final Logger logger
            = LoggerFactory.getLogger(Main.class);


    public static void main(String[] args) throws IOException {




//        // URL and API key
//        String serverUrl = "https://172.21.0.2:9200";
//        String apiKey = "N3NlWm5vNEItbEZWNEJEcjZ1Qk46ZFhpaE0tZ1ZSamUyMFpkdmhTVmh3UQ==";
//
//
//        // SSL bypass
//        SSLFactory sslFactory = SSLFactory.builder()
//                .withUnsafeTrustMaterial()
//                .withUnsafeHostnameVerifier()
//                .build();



        // Create the low-level client
//        RestClient restClient = RestClient
//                .builder(HttpHost.create(serverUrl))
//                .setDefaultHeaders(new Header[] {
//                        new BasicHeader("Authorization", "ApiKey " + apiKey)
//                })
//                .setHttpClientConfigCallback(httpAsyncClientBuilder -> httpAsyncClientBuilder.setSSLContext(sslFactory.getSslContext())
//                        .setSSLHostnameVerifier(sslFactory.getHostnameVerifier()))
//                .build();
//
//        // Create the transport with a Jackson mapper
//        ElasticsearchTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());
//
//        // And create the API client
//        ElasticsearchClient esClient = new ElasticsearchClient(transport);

//        JsonpMapper mapper = esClient._jsonpMapper()
//                .withAttribute(JsonpMapperFeatures.SERIALIZE_TYPED_KEYS, false);
//





//        Person person = new Person("25", "Tial", "Jashore");
//        IndexResponse response = esClient.index(i -> i
//                .index("person")
//                .id(person.getId())
//                .document(person)
//        );

////        logger.info("Indexed with version " + response.version());
////
//        GetResponse<Person> personById = esClient.get(g -> g
//                        .index("person")
//                        .id("25"),
//                Person.class
//        );
//
//        if (personById.found()) {
////            Person product = personById.source();
////            System.out.println("Person name: "+product.getName().toString());
//        } else {
//            System.out.println("No person found!");
//        }


//        String searchText = "Tial";
//
//        SearchResponse<Person> searchResponse = esClient.search(s -> s
//                        .index("person")
//                        .query(q -> q
//                                .match(t -> t
//                                        .field("name")
//                                        .query(searchText)
//                                )
//                        ),
//                Person.class
//        );

//        String searchTerm = "Tial";

//        SearchRequest searchRequest = SearchRequest.of(b -> b
//                .index("person")
//                .query(q -> q
//                        .match(m -> m
//                                .field("name")
//                                .query(searchTerm)
//                        )
//                )
//        );
//
//        System.out.println(searchRequest);
////         Execute the search and handle response
//        SearchResponse searchResponse = esClient.search(searchRequest, Hit.class);
//
//        List<Hit<Person>> hits = searchResponse.hits().hits();
//
//        System.out.println("Hits "+hits);
//
//        SearchResponse<Person> searchResponse = esClient.search(s -> s
//                .index("person")
//                .query(q -> q
//                        .match(t -> t
//                                .field("name")
//                                .query("Pial")
//                        )
//                ),
//                Person.class
//        );

//        StringWriter writer = new StringWriter();
//        try (JsonGenerator generator = mapper.jsonProvider().createGenerator(writer)) {
//            mapper.serialize(searchResponse, generator);
//        }
//        String result = writer.toString();
//
//        System.out.println("Resutl " + result);




//        List<Hit<Person>> hits = searchResponse.hits().hits();
//        for (Hit<Person> hit: hits) {
//            Person resPerson = hit.source();
//            System.out.println("Name: "+resPerson.getName());
//            System.out.println("City: "+resPerson.getCity());
//        }


        // Initialize elastic search first
//        new InitElastic();
        ElasticSearch elasticSearch = new ElasticSearch();

        // Create new index

        // Create bulk index
//        elasticSearch.BulkIndexPerson();

        // Delete index
//        elasticSearch.DeleteIndex("person");


        // Add person to index
//        Person person = new Person("1", "Emon", "Noakhali", "30");
//        elasticSearch.IndexPerson(person);


        // Search Person
        elasticSearch.SearchPerson("pial");

        // Get all person
//        elasticSearch.GetAllPerson();






    }
}

