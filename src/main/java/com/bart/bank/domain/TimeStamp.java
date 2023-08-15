package com.bart.bank.domain;

import jakarta.persistence.Embeddable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Embeddable
public class TimeStamp {

  String time;

  public TimeStamp() {
    this(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
  }
}
