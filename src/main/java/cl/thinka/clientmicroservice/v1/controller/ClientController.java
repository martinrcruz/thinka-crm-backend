package cl.thinka.clientmicroservice.v1.controller;

import cl.thinka.clientmicroservice.v1.dto.request.AddClientRequestDTO;
import cl.thinka.clientmicroservice.v1.dto.request.EditClientRequestDTO;
import cl.thinka.clientmicroservice.v1.service.IClientService;

import cl.thinka.clientmicroservice.v1.util.ThinkaHttpBuilder;
import cl.thinka.clientmicroservice.v1.util.ThinkaResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/client")
@RequiredArgsConstructor
@Log4j2
public class ClientController {

    private final IClientService clientService;

    @Operation(summary = "List all active clients")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Server Error")
    })
    @GetMapping(value = "/list")
    public ResponseEntity<ThinkaResponse> listClients() {
        return ThinkaHttpBuilder.buildHttpResponse(this.clientService.listClients());
    }

    @Operation(summary = "Get a client by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Server Error")
    })
    @GetMapping(value = "/get/{id}")
    public ResponseEntity<ThinkaResponse> getClientById(@PathVariable Long id) {
        return ThinkaHttpBuilder.buildHttpResponse(this.clientService.getClientById(id));
    }

    @Operation(summary = "Create a new Client")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Server Error")
    })
    @PostMapping(value = "/add")
    public ResponseEntity<ThinkaResponse> addClient(@RequestBody AddClientRequestDTO client) {
        return ThinkaHttpBuilder.buildHttpResponse(this.clientService.saveClient(client));
    }

    @Operation(summary = "Edit a Client")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Server Error")
    })
    @PutMapping(value = "/edit")
    public ResponseEntity<ThinkaResponse> editClient(@RequestBody EditClientRequestDTO client) {
        return ThinkaHttpBuilder.buildHttpResponse(this.clientService.editClient(client));
    }

    @Operation(summary = "Delete a Client")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Server Error")
    })
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<ThinkaResponse> deleteClient(@PathVariable Long id) {
        return ThinkaHttpBuilder.buildHttpResponse(this.clientService.deleteClientById(id));
    }
}
