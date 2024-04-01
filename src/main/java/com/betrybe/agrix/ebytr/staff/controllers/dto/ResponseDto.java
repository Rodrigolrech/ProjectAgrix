package com.betrybe.agrix.ebytr.staff.controllers.dto;

/**
 * ResponseDTO.
 *
 * @param message Response Message.
 * @param data Response Data.
 * @param <T> Data type.
 */
public record ResponseDto<T>(String message, T data) {

}
