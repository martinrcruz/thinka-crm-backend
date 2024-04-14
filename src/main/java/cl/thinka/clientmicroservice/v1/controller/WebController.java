package cl.thinka.clientmicroservice.v1.controller;

import cl.thinka.clientmicroservice.v1.dto.request.AddClientRequestDTO;
import cl.thinka.clientmicroservice.v1.dto.request.WebFormQuoteRequestDTO;
import cl.thinka.clientmicroservice.v1.enums.ResponseCode;
import cl.thinka.clientmicroservice.v1.service.IClientService;
import cl.thinka.clientmicroservice.v1.service.impl.ClientServiceImpl;
import cl.thinka.clientmicroservice.v1.util.CustomResponse;
import cl.thinka.clientmicroservice.v1.util.ThinkaHttpBuilder;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/web")
@RequiredArgsConstructor
@Log4j2
public class WebController {

    private final IClientService clientService;

//    @Operation(summary = "Add a new client when the quote form is completed and sent.")
//    @ApiResponses({
//            @ApiResponse(responseCode = "200", description = "OK"),
//            @ApiResponse(responseCode = "401", description = "Unauthorized"),
//            @ApiResponse(responseCode = "403", description = "Forbidden"),
//            @ApiResponse(responseCode = "404", description = "Not Found"),
//            @ApiResponse(responseCode = "500", description = "Server Error")
//    })
//    @PostMapping(value = "/quote-form")
//    public CustomResponse addWebClient(@RequestBody WebFormQuoteRequestDTO client) throws Exception {
//        return ThinkaHttpBuilder.buildHttpResponse(this.taskService.editTask(task));
//
//    }
}
