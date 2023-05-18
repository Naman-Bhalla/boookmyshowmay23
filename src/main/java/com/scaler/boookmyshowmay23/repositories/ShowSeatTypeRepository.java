package com.scaler.boookmyshowmay23.repositories;

import com.scaler.boookmyshowmay23.models.Show;
import com.scaler.boookmyshowmay23.models.ShowSeatType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShowSeatTypeRepository extends JpaRepository<ShowSeatType, Long> {


    List<ShowSeatType> findAllByShow(Show show);
}
