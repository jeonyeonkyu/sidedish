package com.codesquad.team14.repository;

import com.codesquad.team14.domain.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Long> {
}
