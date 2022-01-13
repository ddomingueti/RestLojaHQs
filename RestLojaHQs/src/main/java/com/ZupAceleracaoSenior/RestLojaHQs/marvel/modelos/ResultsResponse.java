package com.ZupAceleracaoSenior.RestLojaHQs.marvel.modelos;
import java.util.List;

public class ResultsResponse {
    private int comicId;
    private String title;
    private String description;
    private String isbn;
    private List<ComicPrice> prices;
    private Creators creators;

    public int getId() { return this.comicId; }
    public void setId(int id) { this.comicId = id; }

    public void setTitle(String title) { this.title = title; }
    public String getTitle() { return this.title; }

    public String getDescription() { return this.description; }
    public void setDescription(String valor) { this.description = valor; }

    public String getIsbn() { return this.isbn; }
    public void setIsbn(String valor) { this.isbn = valor; }

    public List<ComicPrice> getPrices() { return this.prices; }
    public void setPrices (List<ComicPrice> valor) { this.prices = valor; }

    public Creators getCreators() { return this.creators; }
    public void setCreators(Creators valor) { this.creators = valor; }

}
