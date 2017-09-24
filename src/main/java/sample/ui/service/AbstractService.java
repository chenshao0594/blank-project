package sample.ui.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AbstractService<E, K> {
	Page<E> findAll(Pageable pageable);

}
