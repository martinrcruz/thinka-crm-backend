package cl.thinka.clientmicroservice.v1.controller;

import cl.thinka.clientmicroservice.v1.dto.QuoteDTO;
import cl.thinka.clientmicroservice.v1.dto.request.AddQuoteRequestDTO;
import cl.thinka.clientmicroservice.v1.dto.request.EditQuoteRequestDTO;
import cl.thinka.clientmicroservice.v1.enums.ResponseCode;
import cl.thinka.clientmicroservice.v1.service.IQuoteService;
import cl.thinka.clientmicroservice.v1.service.impl.QuoteServiceImpl;
import cl.thinka.clientmicroservice.v1.util.ThinkaHttpBuilder;
import cl.thinka.clientmicroservice.v1.util.ThinkaResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/quote")
@RequiredArgsConstructor
@Log4j2
public class QuoteController {

    private final IQuoteService quoteService;

    @Operation(summary = "List all active quotes")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Server Error")
    })
    @GetMapping(value = "/list")
    public ResponseEntity<ThinkaResponse> listQuotes() {
        return ThinkaHttpBuilder.buildHttpResponse(this.quoteService.listQuotes());
    }

    @Operation(summary = "Get a quote by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Server Error")
    })
    @GetMapping(value = "/get/{id}")
    public ResponseEntity<ThinkaResponse> getQuoteById(@PathVariable Long id) {
       return ThinkaHttpBuilder.buildHttpResponse(this.quoteService.getQuoteById(id));
    }

    @Operation(summary = "Create a new Quote")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Server Error")
    })
    @PostMapping(value = "/add")
    public ResponseEntity<ThinkaResponse> addQuote(@RequestBody AddQuoteRequestDTO quote) {
        return ThinkaHttpBuilder.buildHttpResponse(this.quoteService.saveQuote(quote));

    }

    @Operation(summary = "Edit a Quote")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Server Error")
    })
    @PutMapping(value = "/edit")
    public ResponseEntity<ThinkaResponse> editQuote(@RequestBody EditQuoteRequestDTO quote) {
        return ThinkaHttpBuilder.buildHttpResponse(this.quoteService.editQuote(quote));
    }

    @Operation(summary = "Delete a Quote")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Server Error")
    })
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<ThinkaResponse> deleteQuote(@PathVariable Long id) {
        return ThinkaHttpBuilder.buildHttpResponse(this.quoteService.deleteQuoteById(id));
    }


}
