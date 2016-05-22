/*apackage com.products.core.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;

import com.products.core.data.Catalogue;
import com.products.core.data.Product;

public class ProductsLinkProcessor implements ResourceProcessor<Resource<Catalogue>> {

	
	@Autowired
	private RepositoryEntityLinks entityLinks;
	@Override
	public Resource<Catalogue> process(Resource<Catalogue> resource) {
		Catalogue cat = resource.getContent();
		resource.add(entityLinks.linkToCollectionResource(Product.class).withRel("products"));
        return resource;
	}

}
*/