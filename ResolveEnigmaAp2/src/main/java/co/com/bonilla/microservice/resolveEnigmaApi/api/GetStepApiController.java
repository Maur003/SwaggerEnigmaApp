package co.com.bonilla.microservice.resolveEnigmaApi.api;

import co.com.bonilla.microservice.resolveEnigmaApi.model.GetEnigmaRequest;
import co.com.bonilla.microservice.resolveEnigmaApi.model.GetEnigmaStepResponse;
import co.com.bonilla.microservice.resolveEnigmaApi.model.Header;
import co.com.bonilla.microservice.resolveEnigmaApi.model.JsonApiBodyRequest;
import co.com.bonilla.microservice.resolveEnigmaApi.model.JsonApiBodyResponseSuccess;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

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

    public GetStepApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }
    
    @GetMapping("/getStepTwo")
    public ResponseEntity<String> getTwo(){
    	return new ResponseEntity<>("Step2: Put the giraffe in", HttpStatus.OK);
    }

    public ResponseEntity<List<JsonApiBodyResponseSuccess>> getStep(@ApiParam(value = "request body get enigma step", required = true) @Valid @RequestBody JsonApiBodyRequest body) {
        List<GetEnigmaRequest> enigmas = body.getData();
        List<JsonApiBodyResponseSuccess> responseList = new ArrayList<>();

        for (GetEnigmaRequest enigma : enigmas) {
            // Obtener los datos del enigma
            Header header = enigma.getHeader();
            String id = header.getId();
            String type = header.getType();
            String enigmaQuestion = enigma.getEnigma();

            // Resolver el enigma
            String solution = solveEnigma(enigmaQuestion);

            // Construir la respuesta
            GetEnigmaStepResponse enigmaStepResponse = new GetEnigmaStepResponse();
            enigmaStepResponse.setId(id);
            enigmaStepResponse.setType(type);
            enigmaStepResponse.setSolution(solution);

            // Construir la respuesta JSON
            JsonApiBodyResponseSuccess responseBody = new JsonApiBodyResponseSuccess();
            responseBody.addDataItem(enigmaStepResponse);
            responseList.add(responseBody);
        }

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    // Método para resolver el enigma
    private String solveEnigma(String enigmaQuestion) {
    	if (enigmaQuestion.equals("2")) {
    		
    		return "Step2: Put the giraffe in";
    	}
    	
    	return "Open the next project for the next step!!";
    }
}
