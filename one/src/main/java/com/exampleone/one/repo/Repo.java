package com.exampleone.one.repo;
import org.springframework.data.jpa.repository.JpaRepository;
import com.exampleone.one.entity.Item;

public interface Repo extends JpaRepository<Item,Integer> {

}
