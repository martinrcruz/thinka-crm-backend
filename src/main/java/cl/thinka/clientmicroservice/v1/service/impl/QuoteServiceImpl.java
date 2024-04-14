package cl.thinka.clientmicroservice.v1.service.impl;

import cl.thinka.clientmicroservice.v1.dto.QuoteDTO;
import cl.thinka.clientmicroservice.v1.dto.request.AddQuoteRequestDTO;
import cl.thinka.clientmicroservice.v1.dto.request.EditQuoteRequestDTO;
import cl.thinka.clientmicroservice.v1.enums.ResponseCode;
import cl.thinka.clientmicroservice.v1.exception.ThinkaException;
import cl.thinka.clientmicroservice.v1.jpa.entity.Quote;
import cl.thinka.clientmicroservice.v1.jpa.repository.QuoteRepository;
import cl.thinka.clientmicroservice.v1.mapper.QuoteMapper;
import cl.thinka.clientmicroservice.v1.service.IQuoteService;
import cl.thinka.clientmicroservice.v1.util.ThinkaResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class QuoteServiceImpl implements IQuoteService {

    private final QuoteRepository quoteRepository;

    @Override
    public ThinkaResponse listQuotes() throws ThinkaException {
        try {
            List<QuoteDTO> quotesDTO = new ArrayList<>();
            List<Quote> quotes = quoteRepository.getAll();
            for (Quote quote : quotes) {
                QuoteDTO quoteDTO = new QuoteDTO();
                quoteDTO = QuoteMapper.INSTANCE.toDto(quote);
                quotesDTO.add(quoteDTO);
            }
            return new ThinkaResponse(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), quotesDTO);
        } catch (ThinkaException e) {
            log.error("Error: " + e.getMessage());
            return new ThinkaResponse(ResponseCode.BAD_REQUEST.getCode(), ResponseCode.BAD_REQUEST.getMessage(), e.getMessage());
        }
    }

    @Override
    public ThinkaResponse getQuoteById(Long id) throws ThinkaException {
        QuoteDTO response;
        try {
            Quote quote = quoteRepository.getById(id);
            if(quote == null){
                log.error("Error on get quote. Quote not found ");
                return new ThinkaResponse(ResponseCode.NOT_FOUND.getCode(), ResponseCode.NOT_FOUND.getMessage(), "Quote not found.");
            }

            response = QuoteMapper.INSTANCE.toDto(quote);
            return new ThinkaResponse(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), response);
        } catch (ThinkaException e) {
            log.error("Error: " + e.getMessage());
            return new ThinkaResponse(ResponseCode.BAD_REQUEST.getCode(), ResponseCode.BAD_REQUEST.getMessage(), e.getMessage());
        }
    }

    @Override
    @Transactional
    public ThinkaResponse deleteQuoteById(Long id) throws ThinkaException {
        try {
            Quote quote = quoteRepository.getById(id);
            if(quote == null){
                log.error("Error on delete quote. Quote not found ");
                return new ThinkaResponse(ResponseCode.NOT_FOUND.getCode(), ResponseCode.NOT_FOUND.getMessage(), "Quote not found.");
            }
            this.quoteRepository.deleteById(id, 1L);
            return new ThinkaResponse(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), "Deleted successfully.");
        } catch (ThinkaException e) {
            log.error("Error: " + e.getMessage());
            return new ThinkaResponse(ResponseCode.BAD_REQUEST.getCode(), ResponseCode.BAD_REQUEST.getMessage(), e.getMessage());
        }
    }

    @Override
    public ThinkaResponse editQuote(EditQuoteRequestDTO quote) throws ThinkaException {
        try {
            Quote existingQuote = this.quoteRepository.getById(quote.getId());
            if(existingQuote == null){
                log.error("Error on edit quote. Quote not found ");
                return new ThinkaResponse(ResponseCode.BAD_REQUEST.getCode(), ResponseCode.BAD_REQUEST.getMessage(), "Error on edit quote. Quote not found ");
            }
            Quote newQuote = QuoteMapper.INSTANCE.toEntity(quote);
            newQuote.setLastModifiedBy(1L);

            if (quote.getStartDate().matches("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}")) {
                newQuote.setStartDate(LocalDateTime.parse(quote.getStartDate()));
            } else {
                throw new ThinkaException("Invalid Start Date format. Please use YYYY-MM-DD'T'HH:mm:ss format.");
            }

            if (quote.getEndDate().matches("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}")) {
                newQuote.setStartDate(LocalDateTime.parse(quote.getEndDate()));
            } else {
                throw new ThinkaException("Invalid End Date format. Please use YYYY-MM-DD'T'HH:mm:ss format.");
            }

            return new ThinkaResponse(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), this.quoteRepository.save(newQuote));
        } catch (ThinkaException e) {
            log.error("Error: " + e.getMessage());
            return new ThinkaResponse(ResponseCode.BAD_REQUEST.getCode(), ResponseCode.BAD_REQUEST.getMessage(), e.getMessage());
        }
    }

    @Override
    public ThinkaResponse saveQuote(AddQuoteRequestDTO quote) throws ThinkaException {
        try {
            Quote newQuote = QuoteMapper.INSTANCE.toEntity(quote);
            newQuote.setStatus(1);
            newQuote.setCreatedBy(1L);

            if (quote.getStartDate().matches("\\d{4}-\\d{2}-\\d{2}")) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                newQuote.setStartDate(LocalDateTime.parse(quote.getStartDate()+" 00:00:00", formatter));
            } else {
                throw new ThinkaException("Invalid Start Date format. Please use YYYY-MM-DD format.");
            }

            if (quote.getEndDate().matches("\\d{4}-\\d{2}-\\d{2}")) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                newQuote.setEndDate(LocalDateTime.parse(quote.getEndDate() + " 23:59:59", formatter));
            } else {
                throw new ThinkaException("Invalid End Date format. Please use YYYY-MM-DD format.");
            }

            return new ThinkaResponse(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), this.quoteRepository.save(newQuote));
        } catch (ThinkaException e) {
            log.error("Error: " + e.getMessage());
            return new ThinkaResponse(ResponseCode.BAD_REQUEST.getCode(), ResponseCode.BAD_REQUEST.getMessage(), e.getMessage());
        }
    }
}
