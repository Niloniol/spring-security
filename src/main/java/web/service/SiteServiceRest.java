package web.service;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import web.model.User;

import java.util.List;

@Service
public class SiteServiceRest implements SiteService{

    private final RestTemplate restTemplate;
    private final String serverUrl;

    public SiteServiceRest(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.serverUrl = "http://localhost:8080";
    }

    @Override
    public List<User> getUsersTable() {
        return restTemplate.exchange(
                serverUrl + "/rest-getuserlist",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<User>>() {
                }
        ).getBody();
    }
}
