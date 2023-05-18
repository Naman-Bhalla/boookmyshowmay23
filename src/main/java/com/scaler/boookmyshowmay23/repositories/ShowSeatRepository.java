package com.scaler.boookmyshowmay23.repositories;

import com.scaler.boookmyshowmay23.models.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeat, Long> {

    @Override
    List<ShowSeat> findAllById(Iterable<Long> longs);

    @Override
    ShowSeat save(ShowSeat entity); // update if ShowSeat has an ID, else insert
}
