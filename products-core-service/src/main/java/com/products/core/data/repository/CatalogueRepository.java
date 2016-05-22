/*apackage com.products.core.data.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.products.core.data.Catalogue;

@RepositoryRestResource(collectionResourceRel = "catalogue", path = "catalogue")
public interface CatalogueRepository extends PagingAndSortingRepository<Catalogue, Integer> {
	List<Catalogue> findByNameContainsIgnoreCase(@Param("name") String name);
}
*/