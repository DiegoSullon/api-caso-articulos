package com.caso.articulos.repositories;
import com.caso.articulos.models.Articulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Articulo, Integer> {
    public boolean existsByCodigo(String codigo);
}
