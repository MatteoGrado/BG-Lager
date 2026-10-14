package de.bg.lagersystem.repository;

import de.bg.lagersystem.model.Storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StorageRepository extends JpaRepository<Storage, Long>
{
    List<Storage> findByProductNameContaining(String term);
}
