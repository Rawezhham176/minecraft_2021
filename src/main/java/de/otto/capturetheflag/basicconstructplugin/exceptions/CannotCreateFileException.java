package de.otto.capturetheflag.basicconstructplugin.exceptions;

public class CannotCreateFileException extends
    CannotLoadFileException {

  public CannotCreateFileException() {
    super();
  }

  public CannotCreateFileException(String message) {
    super(message);
  }

  public CannotCreateFileException(String message, Throwable cause) {
    super(message, cause);
  }

  public CannotCreateFileException(Throwable cause) {
    super(cause);
  }

  public CannotCreateFileException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
