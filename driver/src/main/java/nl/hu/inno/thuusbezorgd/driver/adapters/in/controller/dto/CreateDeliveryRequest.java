package nl.hu.inno.thuusbezorgd.driver.adapters.in.controller.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class CreateDeliveryRequest {

    @NotBlank
    String orderId;

}
