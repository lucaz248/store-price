package com.store.controller;

import com.store.dto.PriceDto;
import com.store.exception.InvalidInputDataException;
import com.store.exception.PriceNotFoundException;
import com.store.repository.PriceRepository;
import com.store.service.PriceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("price")
public class PriceController {
    private static final Logger log = LoggerFactory.getLogger(PriceController.class);
    @Autowired
    private PriceService priceService;
    private final HttpServletRequest request;
    @Autowired
    private PriceRepository priceRepository;

    @Autowired
    public PriceController(HttpServletRequest request) {
        this.request = request;
    }

    @Operation(summary = "Find price", description = "Find a pvp single price", tags = {"price"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PriceDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data supplied"),
            @ApiResponse(responseCode = "404", description = "Price not found")})
    @RequestMapping(value = "/find-price-pvp/pricedate/{pricedate}/productid/{productid}/brandid/{brandid}",
            produces = {"application/json", "application/xml"},
            method = RequestMethod.GET)
    public ResponseEntity<PriceDto> findPricePvp(@Parameter(in = ParameterIn.PATH, description = "Price date to find", required = true, example = "2020-06-14-10.00", schema = @Schema()) @PathVariable("pricedate") String pricedate,
                                                 @Parameter(in = ParameterIn.PATH, description = "Product id to find", required = true, example = "35455", schema = @Schema()) @PathVariable("productid") Integer productid,
                                                 @Parameter(in = ParameterIn.PATH, description = "Brand id to find", required = true, example = "1", schema = @Schema()) @PathVariable("brandid") Integer brandid) {

        try {
            PriceDto pvpPrice = priceService.findPricePvp(pricedate, productid, brandid);
            return new ResponseEntity<PriceDto>(pvpPrice, HttpStatus.OK);
        } catch (PriceNotFoundException e) {
            return new ResponseEntity<PriceDto>(HttpStatus.NOT_FOUND);
        } catch (InvalidInputDataException e) {
            return new ResponseEntity<PriceDto>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("Unexpected error:", e);
            return new ResponseEntity<PriceDto>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
