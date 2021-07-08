package com.soondae.camp.favorite.repository;

import com.soondae.camp.favorite.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {



}
