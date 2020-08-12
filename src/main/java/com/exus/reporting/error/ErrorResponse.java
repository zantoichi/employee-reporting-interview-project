package com.exus.reporting.error;

import lombok.Value;

@Value
class ErrorResponse {
  String errorMessage;
  int errorCode;
}
