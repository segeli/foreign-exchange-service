package com.foreignexchange.controller;

import com.foreignexchange.entity.Conversion;
import com.foreignexchange.repository.ConversionRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(ConversionController.ENDPOINT)
@RequiredArgsConstructor
@Tag(name = "Conversion Search API")
public class ConversionController {
    public static final String ENDPOINT = "conversions";

    private final ConversionRepository conversionRepository;

    @Operation(
            summary = "Get conversions",
            description = "Retrieves a list of conversions filtered by transaction ID or transaction date"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Success",
                    content = {@Content(schema = @Schema(implementation = Conversion.class), mediaType = "application/json")}
            )
    })
    @GetMapping
    public List<Conversion> getConversions(
            @RequestParam(value = "transactionId", required = false) String transactionId,
            @RequestParam(value = "transactionDate", required = false) LocalDate transactionDate,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {

        Specification<Conversion> spec = Specification.where(null);
        if (transactionId != null) {
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("id"), UUID.fromString(transactionId)));
        }
        if (transactionDate != null) {
            LocalDateTime startDateTime = transactionDate.atStartOfDay();
            LocalDateTime endDateTime = startDateTime.plusDays(1).minusSeconds(1);
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.between(root.get("createdDate"), startDateTime, endDateTime));
        }

        Pageable pageable = PageRequest.of(page, size);
        Page<Conversion> conversionPage = conversionRepository.findAll(spec, pageable);

        return conversionPage.getContent();
    }
}
