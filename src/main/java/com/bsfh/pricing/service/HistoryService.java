package com.bsfh.pricing.service;

import com.bsfh.pricing.model.PriceHistory;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.stream.Stream;

@Repository
public class HistoryService extends DBService<PriceHistory> {

    @Transactional
    public Stream<PriceHistory> findByProduct(UUID productId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<PriceHistory> cq = cb.createQuery(PriceHistory.class);
        Root<PriceHistory> root = cq.from(PriceHistory.class);

        cq.where(cb.equal(root.get("product").get("id"), productId));

        return em.createQuery(cq).getResultStream();
    }
}
