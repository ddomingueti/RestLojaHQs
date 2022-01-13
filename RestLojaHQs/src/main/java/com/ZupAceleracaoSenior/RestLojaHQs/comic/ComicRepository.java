package com.ZupAceleracaoSenior.RestLojaHQs.comic;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ComicRepository extends JpaRepository<Comic, Integer>{

    @Query(value = "SELECT * FROM (SELECT DISTINCT * FROM COMIC INNER JOIN USUARIO_COMIC ON (COMIC.ID = USUARIO_COMIC.COMIC_ID)) WHERE USUARIO_ID = ?", nativeQuery = true)
    List<Comic> findByUsuario(int id);
}
