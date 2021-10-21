package de.otto.capturetheflag.basicconstructplugin.exceptions;

public class FileException extends Exception {

  public FileException() {
  }

  public FileException(String message) {
    super(message);
  }

  public FileException(String message, Throwable cause) {
    super(message, cause);
  }

  public FileException(Throwable cause) {
    super(cause);
  }

  public FileException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
