package co.com.bonilla.microservice.resolveEnigmaApi.api;

import co.com.bonilla.microservice.resolveEnigmaApi.model.GetEnigmaRequest;
import co.com.bonilla.microservice.resolveEnigmaApi.model.GetEnigmaStepResponse;
import co.com.bonilla.microservice.resolveEnigmaApi.model.Header;
import co.com.bonilla.microservice.resolveEnigmaApi.model.JsonApiBodyRequest;
import co.com.bonilla.microservice.resolveEnigmaApi.model.JsonApiBodyResponseSuccess;
import co.com.bonilla.microservice.resolveEnigmaApi.service.RestTemplateService;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class GetStepApiController implements GetStepApi {

    private final ObjectMapper objectMapper;
    private final HttpServletRequest request;
    private JsonApiBodyResponseSuccess response;
    
    private GetEnigmaStepResponse enigma;
    private final RestTemplateService restTemplateService;

    public GetStepApiController(ObjectMapper objectMapper, HttpServletRequest request, RestTemplateService restTemplateService) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.restTemplateService = restTemplateService;
    }
    
    public ResponseEntity<List<JsonApiBodyResponseSuccess>> getStep(@ApiParam(value = "request body get enigma step", required = true) @Valid @RequestBody JsonApiBodyRequest body) {
     
        return new ResponseEntity<>(bodyResponseSuccess (body), HttpStatus.OK);
    }

    // Método para unir los servicios
    private List<JsonApiBodyResponseSuccess> bodyResponseSuccess(JsonApiBodyRequest body) {
    	
        GetEnigmaStepResponse responseEnigma = new GetEnigmaStepResponse();    
        responseEnigma.setHeader(body.getData().get(0).getHeader());
        ResponseEntity<String> response1 = restTemplateService.getAll("http://localhost:8080/v1/getOneEnigma/getStepOne");
        ResponseEntity<String> response2 = restTemplateService.getAll("http://localhost:8081/v1/getOneEnigma/getStepTwo");
        ResponseEntity<String> response3 = restTemplateService.getAll("http://localhost:8082/v1/getOneEnigma/getStepThree");
        responseEnigma.setAnswer(response1.getBody() + " - " + response2.getBody() + " - " + response3.getBody());
        
        JsonApiBodyResponseSuccess responseSuccess = new JsonApiBodyResponseSuccess();
        responseSuccess.addDataItem(responseEnigma);

        List<JsonApiBodyResponseSuccess> responseSuccessList = new ArrayList<JsonApiBodyResponseSuccess>();  
        responseSuccessList.add(responseSuccess);
        
        return responseSuccessList;
    }
}
