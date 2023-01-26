package nl.hu.inno.thuusbezorgd.driver.adapters.in.controller.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class CreateDeliveryRequest {

    @NotBlank(message = "riderName can't be empty")
    String riderName;

    @NotBlank
    String orderId;

}
