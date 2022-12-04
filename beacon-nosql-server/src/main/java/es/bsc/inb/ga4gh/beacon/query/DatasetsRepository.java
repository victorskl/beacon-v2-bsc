package es.bsc.inb.ga4gh.beacon.query;

import es.bsc.inb.ga4gh.beacon.nosql.DatasetEntity;
import javax.enterprise.context.ApplicationScoped;
import jakarta.nosql.mapping.Pagination;
import jakarta.nosql.mapping.Param;
import jakarta.nosql.mapping.Query;
import jakarta.nosql.mapping.Repository;
import java.util.List;
import java.util.Optional;

/**
 * @author Dmitry Repchevsky
 */

@ApplicationScoped
public interface DatasetsRepository extends Repository<DatasetEntity, String> {

    @Override
    @Query("select * from Datasets where id = @id")
    public Optional<DatasetEntity> findById(@Param("id") String id);
    
    List<DatasetEntity> findAll();
    
    List<DatasetEntity> findAll(Pagination pagination);
}
