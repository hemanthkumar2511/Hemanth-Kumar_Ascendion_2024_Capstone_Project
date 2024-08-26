package com.onlineshoppingrestapi.cartlineitem.repository;

import com.onlineshoppingrestapi.cartlineitem.dto.LineItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILineItemRepository extends JpaRepository<LineItem,Integer>{
}
