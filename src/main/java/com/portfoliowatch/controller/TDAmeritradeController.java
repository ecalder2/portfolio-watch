package com.portfoliowatch.controller;

import com.portfoliowatch.model.dto.ResponseDto;
import com.portfoliowatch.model.tdameritrade.TDAmeriPosition;
import com.portfoliowatch.model.tdameritrade.TDAmeriQuote;
import com.portfoliowatch.model.tdameritrade.TDAmeriTransaction;
import com.portfoliowatch.service.TDAmeritradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

@RequestMapping("/td")
@RestController
public class TDAmeritradeController {

    @Autowired
    TDAmeritradeService tdAmeritradeService;

    @GetMapping("positions")
    public ResponseEntity<List<TDAmeriPosition>> positions() {
        try {
            return new ResponseEntity<>(tdAmeritradeService.getTDAccountPositions(), HttpStatus.OK);
        } catch (IOException | URISyntaxException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("transactions")
    public ResponseEntity<List<TDAmeriTransaction>> transactions() {
        try {
            return new ResponseEntity<>(tdAmeritradeService.refreshTDTransactionRecord(), HttpStatus.OK);
        } catch (IOException | URISyntaxException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("quotes")
    public ResponseEntity<Map<String, TDAmeriQuote>> quotes(@RequestParam List<String> symbols) {
        return new ResponseEntity<>(tdAmeritradeService.getTDAccountQuotes(symbols), HttpStatus.OK);
    }

    @GetMapping("login")
    public ResponseEntity<String> login() {
        return new ResponseEntity<>(tdAmeritradeService.getLoginURL(), HttpStatus.OK);
    }

    @GetMapping("oauth")
    public ResponseEntity<ResponseDto<Boolean>> callback(@RequestParam String code) {
        boolean data;
        HttpStatus httpStatus;
        data = tdAmeritradeService.authorize(code);
        httpStatus = HttpStatus.OK;
        return new ResponseEntity<>(new ResponseDto<>(data, null, httpStatus.value()), httpStatus);
    }

}
