package edu.bootcamp_sb.service_market.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDto {

    private Integer id;

    private String status;

    private Time timestamp;

    private Date date;

}
