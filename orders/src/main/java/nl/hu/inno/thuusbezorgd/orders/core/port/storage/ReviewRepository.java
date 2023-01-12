package nl.hu.inno.thuusbezorgd.orders.core.port.storage;

import nl.hu.inno.thuusbezorgd.orders.core.domain.Dish;
import nl.hu.inno.thuusbezorgd.orders.core.domain.DishReview;
import nl.hu.inno.thuusbezorgd.orders.core.domain.Review;
import nl.hu.inno.thuusbezorgd.orders.core.domain.external.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("select r from DishReview r where r.dish = :dish")
    List<DishReview> findDishReviews(@Param("dish") Dish dish);


    @Query("select r from DeliveryReview r where r.delivery = :delivery")
    List<DeliveryReview> findDeliveryReviews(@Param("delivery") Delivery delivery);
}
