package com.ZupAceleracaoSenior.RestLojaHQs.marvel.modelos;
import java.util.List;

public class DataResponse {
    
    private List<ResultsResponse> results;

    public void setResults(List<ResultsResponse> valor) { this.results = valor; }
    public List<ResultsResponse> getResults() { return this.results; }
}
