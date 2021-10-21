package de.otto.capturetheflag.basicconstructplugin.exceptions;

public class CannotLoadConfigFileException extends CannotLoadFileException {

  public CannotLoadConfigFileException() {
  }

  public CannotLoadConfigFileException(String message) {
    super(message);
  }

  public CannotLoadConfigFileException(String message, Throwable cause) {
    super(message, cause);
  }

  public CannotLoadConfigFileException(Throwable cause) {
    super(cause);
  }

  public CannotLoadConfigFileException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
