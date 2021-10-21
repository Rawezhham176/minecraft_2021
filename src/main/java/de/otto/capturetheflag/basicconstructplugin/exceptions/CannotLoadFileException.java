package de.otto.capturetheflag.basicconstructplugin.exceptions;

public class CannotLoadFileException extends FileException {

  public CannotLoadFileException() {
  }

  public CannotLoadFileException(String message) {
    super(message);
  }

  public CannotLoadFileException(String message, Throwable cause) {
    super(message, cause);
  }

  public CannotLoadFileException(Throwable cause) {
    super(cause);
  }

  public CannotLoadFileException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
