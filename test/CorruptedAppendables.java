import java.io.IOException;

/**
 * Class represents the Appendable that always throws an IOException.
 */
public class CorruptedAppendables implements Appendable {
  
  @Override
  public Appendable append(CharSequence csq) throws IOException {
    throw new IOException();
  }
  
  @Override
  public Appendable append(CharSequence csq, int start, int end) throws IOException {
    throw new IOException();
  }
  
  @Override
  public Appendable append(char c) throws IOException {
    throw new IOException();
  }
}