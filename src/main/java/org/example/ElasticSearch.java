package org.example;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.BulkRequest;
import co.elastic.clients.elasticsearch.core.DeleteRequest;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.indices.DeleteIndexRequest;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.altindag.ssl.SSLFactory;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.RestClient;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ElasticSearch {


    // URL and API key
    String serverUrl = "https://172.21.0.2:9200";

    //SSL key from kibana
    String apiKey = "N3NlWm5vNEItbEZWNEJEcjZ1Qk46ZFhpaE0tZ1ZSamUyMFpkdmhTVmh3UQ==";


    // SSL bypass
    SSLFactory sslFactory = SSLFactory.builder()
            .withUnsafeTrustMaterial()
            .withUnsafeHostnameVerifier()
            .build();



    // Create client
    RestClient restClient = RestClient
            .builder(HttpHost.create(serverUrl))
            .setDefaultHeaders(new Header[] {
                    new BasicHeader("Authorization", "ApiKey " + apiKey)
            })
            .setHttpClientConfigCallback(httpAsyncClientBuilder -> httpAsyncClientBuilder.setSSLContext(sslFactory.getSslContext())
                    .setSSLHostnameVerifier(sslFactory.getHostnameVerifier()))
            .build();

    // Create the transport with a Jackson mapper
    ElasticsearchTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());

    // Create the esClient
    ElasticsearchClient esClient = new ElasticsearchClient(transport);



    // Index a person
    public void IndexPerson(Person person) throws IOException {
        esClient.index(i -> i
                .index("person")
                .id(person.getId())
                .document(person)
        );
        System.out.println("Index added for: "+person.getName());
    }


    List<Person> FetchPersonFromJson(String filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(filePath);
        return objectMapper.readValue(file, new TypeReference<>() {});
    }

    // Bulk index person
    public void BulkIndexPerson() throws IOException {

        List<Person> persons = FetchPersonFromJson("/home/omar/IdeaProjects/elastic-test-backend/src/main/resources/persons.json");

        BulkRequest.Builder br = new BulkRequest.Builder();

        for (Person person : persons) {
            br.operations(op -> op
                    .index(idx -> idx
                            .index("person")
                            .id(person.getId())
                            .document(person)
                    )
            );
        }
        esClient.bulk(br.build());
        System.out.println("Bulk document created...");
    }

    // Get All Person
    public void GetAllPerson() throws IOException {
        SearchResponse<Person> searchResponse = esClient.search(s -> s
                        .index("person"),
                Person.class
        );

        System.out.println("\nAll Person");
        System.out.println("-----------------");

        List<Hit<Person>> hits = searchResponse.hits().hits();
        for (Hit<Person> hit: hits) {
            Person resPerson = hit.source();
            System.out.println("Name: "+resPerson.getName());
            System.out.println("City: "+resPerson.getCity());
            System.out.println("-----------------");
        }
    }

    // Delete index
    public void DeleteIndex(String indexName) throws IOException {
//        DeleteRequest deleteRequest = new DeleteRequest.Builder()
//                .index(indexName)
//                        .build();
//        esClient.delete(deleteRequest);

        DeleteIndexRequest request = new DeleteIndexRequest.Builder()
                .index(indexName)
                .build();
        esClient.indices().delete(request);

        System.out.println("Index "+indexName+" deleted successfully.");
    }

    // Search person
    public void SearchPerson(String searchTerm) throws IOException{
        SearchResponse<Person> searchResponse = esClient.search(s -> s
                        .index("person")
                        .query(q -> q
                                .match(t -> t
                                        .field("name")
                                        .query(searchTerm)
                                )
                        ),
                Person.class
        );

        System.out.println("\nSearching "+searchTerm+" in person...");
        System.out.println("-----------------");

        List<Hit<Person>> hits = searchResponse.hits().hits();
        if (hits.isEmpty()) {
            System.out.println("No person found!");
        } else {
            for (Hit<Person> hit: hits) {
                Person resPerson = hit.source();
                System.out.println("Name: "+resPerson.getName());
                System.out.println("City: "+resPerson.getCity());
                System.out.println("-----------------");

            }
        }

    }


}

