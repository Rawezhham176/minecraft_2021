package de.otto.capturetheflag.basicconstructplugin.exceptions;

public class CannotSaveFileException extends FileException {

  public CannotSaveFileException() {
  }

  public CannotSaveFileException(String message) {
    super(message);
  }

  public CannotSaveFileException(String message, Throwable cause) {
    super(message, cause);
  }

  public CannotSaveFileException(Throwable cause) {
    super(cause);
  }

  public CannotSaveFileException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
