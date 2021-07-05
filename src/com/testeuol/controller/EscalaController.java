package com.testeuol.controller;

import java.util.List;

import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.processo.util.JsonUtil;
import com.processo.vo.ProcessoVO;
import com.testeuol.service.IEscalaService;
import com.testeuol.vo.PessoaVO;

@Controller
@RequestMapping(value = "/api/", produces = MediaType.APPLICATION_JSON_VALUE)
public class EscalaController {
	
	@Autowired
	IEscalaService escalaService;
	
	@RequestMapping(value = "/im-day", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity<?> montarEscala() {
		 List<PessoaVO> escalaVO = escalaService.montarEscala();
		 
		 JSONObject jsonObject = new JSONObject();
		 JSONArray jsonArray = montarJsonEscala(escalaVO);
		 jsonObject = criarJson("escala", jsonArray, jsonObject);
		 
		 return new ResponseEntity<>(jsonObject.toString(), HttpStatus.OK);
	}
	
	private JSONArray montarJsonEscala(List<PessoaVO> escalaVO) {
        JSONArray jsonArray = new JSONArray();

        for (PessoaVO p : escalaVO) {
            JSONObject json = new JSONObject();
            try {
                json.put("nome", p.getNome());
                json.put("data", p.getData());
            } catch (JSONException e) {
            }

            jsonArray.put(json);
        }

        return jsonArray;
    }
	
	private JSONObject criarJson(String nomeTagJson,JSONArray listaJson, JSONObject jsonComArrays) {
        try {
            if(listaJson.length() != 0){
                jsonComArrays.put(nomeTagJson, listaJson);
                jsonComArrays.put("status", "200");
            }else{
                jsonComArrays.put(nomeTagJson, listaJson);
                jsonComArrays.put("status", "400");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonComArrays;
    }
}
