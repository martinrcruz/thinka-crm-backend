package cl.thinka.clientmicroservice.v1.controller;

import cl.thinka.clientmicroservice.v1.dto.SaleDTO;
import cl.thinka.clientmicroservice.v1.dto.request.AddSaleRequestDTO;
import cl.thinka.clientmicroservice.v1.dto.request.EditSaleRequestDTO;
import cl.thinka.clientmicroservice.v1.service.ISaleService;
import cl.thinka.clientmicroservice.v1.service.impl.SaleServiceImpl;
import cl.thinka.clientmicroservice.v1.enums.ResponseCode;
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
import cl.thinka.clientmicroservice.v1.util.CustomResponse;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/sale")
@RequiredArgsConstructor
@Log4j2
public class SaleController {

    private final ISaleService saleService;

    @Operation(summary = "List all active sales")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Server Error")
    })
    @GetMapping(value = "/list")
    public ResponseEntity<ThinkaResponse> listSales() {
        return ThinkaHttpBuilder.buildHttpResponse(this.saleService.listSales());

    }

    @Operation(summary = "Get a sale by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Server Error")
    })
    @GetMapping(value = "/get/{id}")
    public ResponseEntity<ThinkaResponse> getSaleById(@PathVariable Long id) {
        return ThinkaHttpBuilder.buildHttpResponse(this.saleService.getSaleById(id));
    }

    @Operation(summary = "Create a new Sale")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Server Error")
    })
    @PostMapping(value = "/add")
    public ResponseEntity<ThinkaResponse> addSale(@RequestBody AddSaleRequestDTO sale) {
        return ThinkaHttpBuilder.buildHttpResponse(this.saleService.saveSale(sale));
    }

    @Operation(summary = "Edit a Sale")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Server Error")
    })
    @PutMapping(value = "/edit")
    public ResponseEntity<ThinkaResponse> editSale(@RequestBody EditSaleRequestDTO sale) {
        return ThinkaHttpBuilder.buildHttpResponse(this.saleService.editSale(sale));

    }

    @Operation(summary = "Delete a Sale")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Server Error")
    })
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<ThinkaResponse> deleteSale(@PathVariable Long id) {
        return ThinkaHttpBuilder.buildHttpResponse(this.saleService.deleteSaleById(id));

    }


}
