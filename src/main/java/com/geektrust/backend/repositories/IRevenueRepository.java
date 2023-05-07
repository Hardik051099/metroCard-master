package com.geektrust.backend.repositories;

import java.util.List;
import java.util.Optional;
import com.geektrust.backend.dtos.TotalCollection;

public interface IRevenueRepository {
    public TotalCollection save(TotalCollection entity);
    public Optional<TotalCollection> findById(String integer);
    public Optional<List<TotalCollection>> findAll();
}
