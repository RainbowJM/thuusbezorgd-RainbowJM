package nl.hu.inno.thuusbezorgd.driver.core.domain;

import nl.hu.inno.thuusbezorgd.driver.core.domain.external.Review;
import nl.hu.inno.thuusbezorgd.driver.core.domain.external.ReviewRating;
import nl.hu.inno.thuusbezorgd.driver.core.domain.external.User;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class DeliveryReview extends Review {

    @ManyToOne
    private Delivery delivery;


    protected DeliveryReview() {
    }

    public DeliveryReview(Delivery delivery, ReviewRating rating, User user) {
        this.delivery = delivery;
        super.rating = rating;
        super.user = user;
    }

    public Delivery getDelivery() {
        return delivery;
    }
}
